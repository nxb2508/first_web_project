/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dao.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;
import model.ProductModel;

/**
 *
 * @author Bach
 */
public class SearchProduct extends HttpServlet {

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
            out.println("<title>Servlet SearchProduct</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SearchProduct at " + request.getContextPath() + "</h1>");
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
        String search_description = request.getParameter("search_description");
        String sort_by = request.getParameter("sort_by");
        List<ProductModel> products = new ProductDAO().searchProducts(search_description);
        if (sort_by.equals("asc")) {
            Collections.sort(products);
        } else if (sort_by.equals("desc")){
            Collections.sort(products, Collections.reverseOrder());
        }
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
        int totalPages = (int) Math.ceil(products.size() * 1.0 / itemsPerPage);
        int start = (page - 1) * itemsPerPage;
        int end = Math.min(page * itemsPerPage, products.size());
        request.setAttribute("search_description", search_description);
        request.setAttribute("sort_by", sort_by);
        request.setAttribute("page", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("products", new ProductDAO().getProductsByPage(products, start, end));
        request.getRequestDispatcher("views/admin/search_product.jsp").forward(request, response);
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
