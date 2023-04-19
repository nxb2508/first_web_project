/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dao.InventoryDAO;
import dao.SizeDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.InventoryModel;
import model.SizeModel;

/**
 *
 * @author Bach
 */
public class UpdateInventory extends HttpServlet {

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
            out.println("<title>Servlet UpdateInventory</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateInventory at " + request.getContextPath() + "</h1>");
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
        String inventoryIdRaw = request.getParameter("id");
        try {
            List<SizeModel> sizes = new SizeDAO().getAllSizes();
            int inventoryId = Integer.parseInt(inventoryIdRaw);
            InventoryModel inventory = new InventoryDAO().getInventoryById(inventoryId);
            request.setAttribute("sizes", sizes);
            request.setAttribute("inventory", inventory);
            request.getRequestDispatcher("views/admin/update_inventory.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            request.setAttribute("updateError", "Da xay ra loi");
            request.getRequestDispatcher("admin-list-inventory").forward(request, response);
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
        String inventoryIdRaw = request.getParameter("id");
        String quantityRaw = request.getParameter("quantity");
        try {
            int inventoryId = Integer.parseInt(inventoryIdRaw);
            int quantity = Integer.parseInt(quantityRaw);
            InventoryModel inventory = new InventoryDAO().getInventoryById(inventoryId);
            inventory.setQuantity(quantity);
            int result = new InventoryDAO().updateInventory(inventory);
            if (result == 0) {
                request.setAttribute("updateError", "Da xay ra loi");
                request.getRequestDispatcher("admin-list-inventory").forward(request, response);
            } else {
                request.setAttribute("updated", "Cap nhat thanh cong");
                request.getRequestDispatcher("admin-list-inventory").forward(request, response);
            }
        } catch (NumberFormatException e) {
            request.setAttribute("updateError", "Da xay ra loi");
            request.getRequestDispatcher("admin-list-inventory").forward(request, response);
        }
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
