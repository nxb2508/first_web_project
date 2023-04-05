/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.user;

import dao.CategoryDAO;
import dao.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import model.CartModel;
import model.CategoryModel;
import model.ItemModel;
import model.ProductModel;

/**
 *
 * @author Bach
 */
public class UserProducts extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UserProducts</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserProducts at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    private boolean isCheckCategory(int category_id, List<Integer> categoryId) {
        if (categoryId == null) {
            return false;
        } else {
            for (int id : categoryId) {
                if (category_id == id) {
                    return true;
                }
            }
            return false;
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<CategoryModel> categories = new CategoryDAO().getAllCategories(); //lay toan bo ds san pham
        request.setAttribute("categories", categories); //gui categories sang front end
        List<ProductModel> products = new ArrayList<>(); //tao danh sach san pham
        List<ProductModel> allProducts = new ProductDAO().getAllProducts();
        //bat dau lay du lieu phia client
        String categoryId = request.getParameter("category_id");

        Cookie[] cookies = request.getCookies();
        String cookieTxt = "";
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("cart")){
                    cookieTxt += cookie.getValue();
                }
            }
        }
        CartModel cart = new CartModel(cookieTxt);
        List<ItemModel> items = cart.getItems();
        
        request.setAttribute("cart", cart);
        request.setAttribute("items", items);
        
        //ket thuc lay du lieu phia client
        boolean[] checkCategoryId = new boolean[categories.size() + 1]; //tao mang kiem tra checked cho categories
        boolean[] checkPrice = new boolean[6]; //tao mang kiem tra checked cho prices
        //gui luon du lieu khi chon tren thanh nav bar
        if (categoryId != null) {
            for (int i = 0; i < categories.size(); i++) {
                if (categories.get(i).getId() == Integer.parseInt(categoryId)) {
                    checkCategoryId[i + 1] = true;
                }
            }
            checkPrice[0] = true;
            CategoryModel category = new CategoryModel();
            category.setId(Integer.parseInt(categoryId));
            products = new ProductDAO().getProductsByCategoryId(category);
            int itemsPerPage = 10;
            String page_raw = request.getParameter("page");
            int page;
            if (page_raw != null) {
                try {
                    page = Integer.parseInt(page_raw);
                } catch (NumberFormatException e) {
                    page = 1;
                    System.out.println(e);
                }
            } else {
                page = 1;
            }
            int totalPages = (int) Math.ceil(products.size() * 1.0 / itemsPerPage);
            int start = (page - 1) * itemsPerPage;
            int end = Math.min(page * itemsPerPage, products.size());
            request.setAttribute("page", page);
            request.setAttribute("totalPages", totalPages);
            request.setAttribute("products", new ProductDAO().getProductsByPage(products, start, end));
            request.setAttribute("category_id", categoryId);
            request.setAttribute("checkCategoryId", checkCategoryId);
            request.setAttribute("checkPrice", checkPrice);
            request.setAttribute("allProducts", allProducts);
            request.getRequestDispatcher("views/user/products.jsp").forward(request, response);
        }

        String productName = request.getParameter("product_name");
        String[] listCategoryIdRaw = request.getParameterValues("list_category_id");
        String[] pricesRaw = request.getParameterValues("price");
        String sortBy = request.getParameter("sort_by");

        //chuan hoa du lieu nhan ve
        List<Integer> listCategoryId = new ArrayList<>();
        List<Integer> prices = new ArrayList<>();
        List<CategoryModel> listCategorySearch = null;
        if (listCategoryIdRaw != null) {
            for (String temp : listCategoryIdRaw) {
                int tempId = Integer.parseInt(temp);
                listCategoryId.add(tempId);
                //List<CategoryModel>
                if (listCategorySearch == null) {
                    listCategorySearch = new ArrayList<>();
                }
                if (tempId != 0) {
                    listCategorySearch.add(new CategoryDAO().getCategoryById(tempId));
                }
            }
            if (listCategoryId.contains(0)) {
                checkCategoryId[0] = true;
                listCategorySearch = null;
            } else {
                checkCategoryId[0] = false;
                for (int i = 1; i < checkCategoryId.length; i++) {
                    checkCategoryId[i] = isCheckCategory(categories.get(i - 1).getId(), listCategoryId);
                }
            }
        } else {
            checkCategoryId[0] = true;
            listCategorySearch = null;
        }
        if (pricesRaw != null) {
            for (String temp : pricesRaw) {
                prices.add(Integer.parseInt(temp));
            }
            if (prices.contains(0)) {
                checkPrice[0] = true;
                products = new ProductDAO().searchByCategoriesAndPrice(productName, listCategorySearch, null, null);
            } else {
                checkPrice[0] = false;
                for (int i : prices) {
                    if (i == 1) {
                        products.addAll(new ProductDAO().searchByCategoriesAndPrice(productName, listCategorySearch, 0, 199000));
                        checkPrice[i] = true;
                    }
                    if (i == 2) {
                        products.addAll(new ProductDAO().searchByCategoriesAndPrice(productName, listCategorySearch, 200000, 399000));
                        checkPrice[i] = true;
                    }
                    if (i == 3) {
                        products.addAll(new ProductDAO().searchByCategoriesAndPrice(productName, listCategorySearch, 400000, 599000));
                        checkPrice[i] = true;
                    }
                    if (i == 4) {
                        products.addAll(new ProductDAO().searchByCategoriesAndPrice(productName, listCategorySearch, 600000, 799000));
                        checkPrice[i] = true;
                    }
                    if (i == 5) {
                        products.addAll(new ProductDAO().searchByCategoriesAndPrice(productName, listCategorySearch, 800000, 990000));
                        checkPrice[i] = true;
                    }
                }

            }
        } else {
            checkPrice[0] = true;
            products = new ProductDAO().searchByCategoriesAndPrice(productName, listCategorySearch, null, null);
        }

        if (sortBy != null) {
            if (sortBy.equals("asc")) {
                Collections.sort(products);
            } else if (sortBy.equals("desc")) {
                Collections.sort(products, Collections.reverseOrder());
            }
        }

        int itemsPerPage = 10;
        String page_raw = request.getParameter("page");
        int page;
        if (page_raw != null) {
            try {
                page = Integer.parseInt(page_raw);
            } catch (NumberFormatException e) {
                page = 1;
                System.out.println(e);
            }
        } else {
            page = 1;
        }
        int totalPages = (int) Math.ceil(products.size() * 1.0 / itemsPerPage);
        int start = (page - 1) * itemsPerPage;
        int end = Math.min(page * itemsPerPage, products.size());
        request.setAttribute("page", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("products", new ProductDAO().getProductsByPage(products, start, end));
        request.setAttribute("checkCategoryId", checkCategoryId);
        request.setAttribute("checkPrice", checkPrice);
        request.setAttribute("sort_by", sortBy);
        request.setAttribute("product_name", productName);
        request.setAttribute("allProducts", allProducts);
        request.getRequestDispatcher("views/user/products.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
