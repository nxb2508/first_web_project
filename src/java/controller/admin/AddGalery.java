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
public class AddGalery extends HttpServlet {

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
            out.println("<title>Servlet AddGalery</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddGalery at " + request.getContextPath() + "</h1>");
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
        List<ProductModel> products = new ProductDAO().getAllProducts();
        request.setAttribute("products", products);
        request.getRequestDispatcher("views/admin/add_galery.jsp").forward(request, response);
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
        String product_id_raw = request.getParameter("product_id");
        String thumbnail = request.getParameter("thumbnail");
        try {
            int product_id = Integer.parseInt(product_id_raw);
            GaleryModel galery = new GaleryModel();
            ProductModel product = new ProductDAO().getProductById(product_id);
            galery.setProduct(product);
            galery.setThumbnail(thumbnail);
            int result = new GaleryDAO().addGalery(galery);
            if (result != 0) {
                request.setAttribute("added", "Đã thêm hình ảnh");
                request.getRequestDispatcher("list_galery").forward(request, response);
            } else {
                request.setAttribute("error", "Đã xảy ra lỗi!");
                request.getRequestDispatcher("list_galery").forward(request, response);
            }
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Đã xảy ra lỗi!");
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
