/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.user;

import dao.CategoryDAO;
import dao.ProductDAO;
import dao.SizeDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.CartModel;
import model.CategoryModel;
import model.ItemModel;
import model.ProductModel;
import model.SizeModel;

/**
 *
 * @author Bach
 */
public class UserProductDetails extends HttpServlet {

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
            out.println("<title>Servlet UserProductDetails</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserProductDetails at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        List<CategoryModel> categories_raw = new CategoryDAO().getAllCategories();
        List<CategoryModel> categories = new CategoryDAO().getCategoriesByPage(categories_raw, 0, Math.min(10, categories_raw.size()));
        String product_id_raw = request.getParameter("id");
        
        //cookie
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
        
        
        try {
            int product_id = Integer.parseInt(product_id_raw);
            ProductModel product = new ProductDAO().getProductById(product_id);
            List<ProductModel> related_products_raw = new ProductDAO().getProductsRelatedByCategoryId(product);
            List<ProductModel> related_products = new ProductDAO().getProductsByPage(related_products_raw, 0, Math.min(5, related_products_raw.size()));
            List<SizeModel> sizes = new SizeDAO().getAllSizes();
            request.setAttribute("sizes", sizes);
            request.setAttribute("related_products", related_products);
            request.setAttribute("categories", categories);
            request.setAttribute("product", product);
            request.getRequestDispatcher("views/user/product_details.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            System.out.println(e);
            List<ProductModel> products_raw = new ProductDAO().getAllProducts();
            List<ProductModel> products = new ProductDAO().getProductsByPage(products_raw, 0, Math.min(20, products_raw.size()));
            request.setAttribute("products", products);
            request.setAttribute("categories", categories);
            request.getRequestDispatcher("views/user/home.jsp").forward(request, response);
        }
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
