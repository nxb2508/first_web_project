/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.user;

import dao.CategoryDAO;
import dao.OrderDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import model.CartModel;
import model.CategoryModel;
import model.ItemModel;
import model.OrderDetailModel;
import model.OrderModel;
import model.UserModel;

/**
 *
 * @author Bach
 */
public class UserCheckOut extends HttpServlet {

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
            out.println("<title>Servlet UserCheckOut</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserCheckOut at " + request.getContextPath() + "</h1>");
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

        List<CategoryModel> categories_raw = new CategoryDAO().getAllCategories();
        List<CategoryModel> categories = new CategoryDAO().getCategoriesByPage(categories_raw, 0, Math.min(10, categories_raw.size()));
        request.setAttribute("categories", categories);

        request.getRequestDispatcher("views/user/check_out.jsp").forward(request, response);
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
//        Cookie[] cookies = request.getCookies();
//        String cookieTxt = "";
//        if (cookies != null) {
//            for (Cookie cookie : cookies) {
//                if (cookie.getName().equals("cart")) {
//                    cookieTxt += cookie.getValue();
//                }
//            }
//        }
//        CartModel cart = new CartModel(cookieTxt);
//
//        HttpSession session = request.getSession();
//        UserModel user = (UserModel) session.getAttribute("user");
//
//        String fullname = request.getParameter("fullname");
//        String phoneNumber = request.getParameter("phone_number");
//        String address = request.getParameter("address");
//        String note = request.getParameter("note");
//
//        OrderModel order = new OrderModel();
//        order.setUser(user);
//        order.setFullname(fullname);
//        order.setPhoneNumber(phoneNumber);
//        order.setEmail(user.getEmail());
//        order.setAddress(address);
//        order.setNote(note);
//        order.setTotalMoney(cart.getTotalMoney());
//
//        List<OrderDetailModel> listOrderDetail = new ArrayList<>();
//        for (ItemModel item : cart.getItems()) {
//            OrderDetailModel orderDetail = new OrderDetailModel();
//            orderDetail.setProduct(item.getProduct());
//            orderDetail.setPrice(item.getPrice());
//            orderDetail.setQuantity(item.getQuantity());
//            listOrderDetail.add(orderDetail);
//        }
//
//        order.setOrderDetails(listOrderDetail);
//
//        OrderDAO orderDB = new OrderDAO();
//        int result = orderDB.addOrder(order);
//        if (result == 0) {
//            request.setAttribute("addOrderError", "Da Xay Ra Loi Trong Luc Dat Hang");
//            request.getRequestDispatcher("user_check_out").forward(request, response);
//        } else {
//            Cookie cookie = new Cookie("cart", "");
//            cookie.setMaxAge(0);
//            response.addCookie(cookie);
//            session.setAttribute("addOrderSuccess", "Dat Hang Thanh Cong");
//            response.sendRedirect("user_check_out");
//        }

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
