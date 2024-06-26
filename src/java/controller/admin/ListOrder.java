/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dao.CategoryDAO;
import dao.OrderDAO;
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
import model.OrderModel;

/**
 *
 * @author Bach
 */
public class ListOrder extends HttpServlet {

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
            out.println("<title>Servlet ListOrder</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ListOrder at " + request.getContextPath() + "</h1>");
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
        List<CategoryModel> categories = new CategoryDAO().getAllCategories(); //lay toan bo ds san pham
        request.setAttribute("categories", categories); //gui categories sang front end
        Cookie[] cookies = request.getCookies();
        String cookieTxt = "";
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("cart")) {
                    cookieTxt += cookie.getValue();
                }
            }
        }
        CartModel cart = new CartModel(cookieTxt);
        List<ItemModel> items = cart.getItems();

        request.setAttribute("cart", cart);
        request.setAttribute("items", items);

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

//        HttpSession session = request.getSession();
//        UserModel user = (UserModel) session.getAttribute("user");
        String key = request.getParameter("key");
        if (key == null) {
            key = "";
        }
        List<OrderModel> listOrderRaw = new OrderDAO().searchOrderByKey(key);
        int totalPages = (int) Math.ceil(listOrderRaw.size() * 1.0 / itemsPerPage);
        int start = (page - 1) * itemsPerPage;
        int end = Math.min(page * itemsPerPage, listOrderRaw.size());
        request.setAttribute("page", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("key", key);
        request.setAttribute("orders", new OrderDAO().getOrdersByPage(listOrderRaw, start, end));
        request.getRequestDispatcher("views/admin/list_order.jsp").forward(request, response);
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
        List<CategoryModel> categories = new CategoryDAO().getAllCategories(); //lay toan bo ds san pham
        request.setAttribute("categories", categories); //gui categories sang front end
        Cookie[] cookies = request.getCookies();
        String cookieTxt = "";
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("cart")) {
                    cookieTxt += cookie.getValue();
                }
            }
        }
        CartModel cart = new CartModel(cookieTxt);
        List<ItemModel> items = cart.getItems();

        request.setAttribute("cart", cart);
        request.setAttribute("items", items);

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

//        HttpSession session = request.getSession();
//        UserModel user = (UserModel) session.getAttribute("user");
        String key = request.getParameter("key");
        if (key == null) {
            key = "";
        }
        List<OrderModel> listOrderRaw = new OrderDAO().searchOrderByKey(key);
        int totalPages = (int) Math.ceil(listOrderRaw.size() * 1.0 / itemsPerPage);
        int start = (page - 1) * itemsPerPage;
        int end = Math.min(page * itemsPerPage, listOrderRaw.size());
        request.setAttribute("page", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("key", key);
        request.setAttribute("orders", new OrderDAO().getOrdersByPage(listOrderRaw, start, end));
        request.getRequestDispatcher("views/admin/list_order.jsp").forward(request, response);
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
