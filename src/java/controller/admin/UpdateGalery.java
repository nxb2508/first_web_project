/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dao.GaleryDAO;
import dao.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.GaleryModel;
import model.ProductModel;

/**
 *
 * @author Bach
 */
public class UpdateGalery extends HttpServlet {

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
            out.println("<title>Servlet UpdateGalery</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateGalery at " + request.getContextPath() + "</h1>");
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
        String id_raw = request.getParameter("id");
        String product_id_raw = request.getParameter("product_id");
        String thumbnail = request.getParameter("thumbnail");
        try {
            int id = Integer.parseInt(id_raw);
            int product_id = Integer.parseInt(product_id_raw);
            request.setAttribute("id", id);
            request.setAttribute("product_id", product_id);
            request.setAttribute("thumbnail", thumbnail);
            List<ProductModel> products = new ProductDAO().getAllProducts();
            request.setAttribute("products", products);
            request.getRequestDispatcher("views/admin/update_galery.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            request.setAttribute("update_error", "Đã xảy ra lỗi khi chỉnh sửa hình ảnh!");
            request.getRequestDispatcher("list_galery").forward(request, response);
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
        String id_raw = request.getParameter("id");
        String product_id_raw = request.getParameter("product_id");
        String thumbnail = request.getParameter("thumbnail");
        try {
            int id = Integer.parseInt(id_raw);
            int product_id = Integer.parseInt(product_id_raw);
            ProductModel product = new ProductDAO().getProductById(product_id);
            GaleryModel galery = new GaleryModel(id, product, thumbnail);
            int result = new GaleryDAO().updateGalery(galery);
            if (result == 1) {
                request.setAttribute("updated", "Đã sửa thành công!");
                request.getRequestDispatcher("list_galery").forward(request, response);
            } else {
                request.setAttribute("update_error", "Đã xảy ra lỗi khi chỉnh sửa hình ảnh!");
                request.getRequestDispatcher("list_galery").forward(request, response);
            }
        } catch (NumberFormatException e) {
            request.setAttribute("update_error", "Đã xảy ra lỗi khi chỉnh sửa hình ảnh!");
            request.getRequestDispatcher("list_galery").forward(request, response);
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
