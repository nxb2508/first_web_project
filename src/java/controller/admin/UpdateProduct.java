/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dao.CategoryDAO;
import dao.GaleryDAO;
import dao.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.CategoryModel;
import model.GaleryModel;
import model.ProductModel;

/**
 *
 * @author Bach
 */
public class UpdateProduct extends HttpServlet {

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
            out.println("<title>Servlet UpdateProduct</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateProduct at " + request.getContextPath() + "</h1>");
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
        String category_id_raw = request.getParameter("category_id");
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String price_raw = request.getParameter("price");
        try {
            int id = Integer.parseInt(id_raw);
            int category_id = Integer.parseInt(category_id_raw);
            int price = Integer.parseInt(price_raw);
            List<CategoryModel> categories = new CategoryDAO().getAllCategories();
            request.setAttribute("categories", categories);
            request.setAttribute("id", id);
            request.setAttribute("category_id", category_id);
            request.setAttribute("name", name);
            request.setAttribute("description", description);
            request.setAttribute("price", price);
            request.getRequestDispatcher("views/admin/update_product.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            System.out.println(e);
            response.sendRedirect("list_product");
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
        String category_id_raw = request.getParameter("category_id");
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String price_raw = request.getParameter("price");
        int id = Integer.parseInt(id_raw);
        int category_id = Integer.parseInt(category_id_raw);
        try {
            int price = Integer.parseInt(price_raw);
            CategoryModel category = new CategoryDAO().getCategoryById(category_id);
            ProductModel product = new ProductModel();
            product.setId(id);
            product.setCategory(category);
            product.setName(name);
            product.setDescription(description);
            product.setPrice(price);
            List<GaleryModel> galeries = new GaleryDAO().getGaleriesByProductId(product);
            product.setGaleries(galeries);
            int result = new ProductDAO().updateProduct(product);
            if (result == 1) {
                request.setAttribute("updated", "Đã Sửa Sản Phẩm");
                request.getRequestDispatcher("list_product").forward(request, response);
            } else {
                request.setAttribute("error", "Không Sửa Được Sản Phẩm");
                List<CategoryModel> categories = new CategoryDAO().getAllCategories();
                request.setAttribute("categories", categories);
                request.setAttribute("id", id);
                request.setAttribute("category_id", category_id);
                request.setAttribute("name", name);
                request.setAttribute("description", description);
                request.getRequestDispatcher("views/admin/update_product.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            System.out.println(e);
            List<CategoryModel> categories = new CategoryDAO().getAllCategories();
            request.setAttribute("categories", categories);
            request.setAttribute("id", id);
            request.setAttribute("category_id", category_id);
            request.setAttribute("name", name);
            request.setAttribute("description", description);
            request.setAttribute("error", "Vui Lòng Nhập Số Tiền Nguyên");
            request.getRequestDispatcher("views/admin/update_product.jsp").forward(request, response);
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
