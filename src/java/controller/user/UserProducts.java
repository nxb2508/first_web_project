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
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.CategoryModel;
import model.ProductModel;

/**
 *
 * @author Bach
 */
@WebServlet(name="UserProducts", urlPatterns={"/user_products"})
public class UserProducts extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UserProducts</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserProducts at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
        List<ProductModel> products = new ProductDAO().getAllProducts();
        List<ProductModel> productsByPrice1 = new ProductDAO().getProductsByPrice(0, 199000);
        List<ProductModel> productsByPrice2 = new ProductDAO().getProductsByPrice(200000, 399000);
        List<ProductModel> productsByPrice3 = new ProductDAO().getProductsByPrice(400000, 599000);
        List<ProductModel> productsByPrice4 = new ProductDAO().getProductsByPrice(600000, 799000);
        List<ProductModel> productsByPrice5 = new ProductDAO().getProductsByPrice(800000, 999000);
        request.setAttribute("productsByPrice1", productsByPrice1);
        request.setAttribute("productsByPrice2", productsByPrice2);
        request.setAttribute("productsByPrice3", productsByPrice3);
        request.setAttribute("productsByPrice4", productsByPrice4);
        request.setAttribute("productsByPrice5", productsByPrice5);
        
        request.setAttribute("products", products);
        request.setAttribute("categories", categories);
        request.getRequestDispatcher("views/user/products.jsp").forward(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
