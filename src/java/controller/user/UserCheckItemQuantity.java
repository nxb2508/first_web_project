/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.user;

import dao.CategoryDAO;
import dao.InventoryDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.CartModel;
import model.CategoryModel;
import model.InventoryModel;
import model.ItemModel;

/**
 *
 * @author Bach
 */
public class UserCheckItemQuantity extends HttpServlet {

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
        //reset cookie
        Cookie[] cookies = request.getCookies();
        String cookieTxt = "";
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("cart")) {
                    cookieTxt += cookie.getValue();
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
        }
        CartModel cart = new CartModel(cookieTxt);
        try {
            int inventoryId = Integer.parseInt(request.getParameter("inventory_id"));
            InventoryModel inventory = new InventoryDAO().getInventoryById(inventoryId);
            int inventoryQuantity = inventory.getQuantity();
            int number = Integer.parseInt(request.getParameter("number"));
            int itemQuantity = cart.getItemQuantityByInventoryId(inventoryId);
            if (number == 1) {
                if (itemQuantity >= inventoryQuantity) {
                    List<ItemModel> items = cart.getItems();
                    request.setAttribute("cart", cart);
                    request.setAttribute("items", items);
                    cookieTxt = cart.getCookieTxt();
                    Cookie cookie = new Cookie("cart", cookieTxt);
                    cookie.setMaxAge(7 * 24 * 60 * 60);
                    response.addCookie(cookie);
                    List<CategoryModel> categories_raw = new CategoryDAO().getAllCategories();
                    List<CategoryModel> categories = new CategoryDAO().getCategoriesByPage(categories_raw, 0, Math.min(10, categories_raw.size()));
                    request.setAttribute("categories", categories);
                    request.setAttribute("outOfProduct", "khong du san pham");
                    request.getRequestDispatcher("views/user/cart.jsp").forward(request, response);
                } else {
                    ItemModel item = new ItemModel(inventory, number, inventory.getProduct().getPrice());
                    cart.addItem(item);
                }
            } else if (number == -1) {
                if (itemQuantity <= 1) {
                    cart.removeItem(inventoryId);
                } else {
                    ItemModel item = new ItemModel(inventory, number, inventory.getProduct().getPrice());
                    cart.addItem(item);
                }
            } else if (number == 0) {
                cart.removeItem(inventoryId);
            }
            cookieTxt = cart.getCookieTxt();
            Cookie cookie = new Cookie("cart", cookieTxt);
            cookie.setMaxAge(7 * 24 * 60 * 60);
            response.addCookie(cookie);
        } catch (NumberFormatException e) {
        }
        response.sendRedirect("user_cart");
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
        processRequest(request, response);
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
