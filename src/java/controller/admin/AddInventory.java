/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dao.InventoryDAO;
import dao.ProductDAO;
import dao.SizeDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.InventoryModel;
import model.ProductModel;
import model.SizeModel;

/**
 *
 * @author Bach
 */
public class AddInventory extends HttpServlet {

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
            out.println("<title>Servlet AddInventory</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddInventory at " + request.getContextPath() + "</h1>");
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
        String productIdRaw = request.getParameter("product_id");
        try {
            int productId = Integer.parseInt(productIdRaw);
            ProductModel product = new ProductDAO().getProductById(productId);
            List<SizeModel> sizes = new SizeDAO().getAllSizes();
            request.setAttribute("sizes", sizes);
            request.setAttribute("product", product);
            request.getRequestDispatcher("views/admin/add_inventory.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            List<SizeModel> sizes = new SizeDAO().getAllSizes();
            request.setAttribute("sizes", sizes);
            request.getRequestDispatcher("views/admin/add_inventory.jsp").forward(request, response);
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
        String productIdRaw = request.getParameter("product_id");
        String sizeIdRaw = request.getParameter("size_id");
        String quantityRaw = request.getParameter("quantity");
        try {
            int productId = Integer.parseInt(productIdRaw);
            int sizeId = Integer.parseInt(sizeIdRaw);
            int quantity = Integer.parseInt(quantityRaw);
            InventoryModel inventory = new InventoryModel();
            inventory.setProduct(new ProductDAO().getProductById(productId));
            inventory.setSize(new SizeDAO().getSizeById(sizeId));
            inventory.setQuantity(quantity);
            int result = new InventoryDAO().addInventory(inventory);
            if (result == 0) {
                List<SizeModel> sizes = new SizeDAO().getAllSizes();
                request.setAttribute("sizes", sizes);
                request.setAttribute("error", "Da Xay Ra Loi");
                request.getRequestDispatcher("admin-add-inventory").forward(request, response);
            } else {
                request.setAttribute("added", "Đã thêm sản phẩm vào kho");
                request.getRequestDispatcher("admin-list-inventory").forward(request, response);
            }
        } catch (NumberFormatException e) {
            List<SizeModel> sizes = new SizeDAO().getAllSizes();
            request.setAttribute("sizes", sizes);
            request.setAttribute("error", "Da Xay Ra Loi");
            request.getRequestDispatcher("admin-add-inventory").forward(request, response);
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
