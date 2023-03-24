/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dao.CategoryDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.CategoryModel;

/**
 *
 * @author Bach
 */
public class ListCategory extends HttpServlet {

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
            out.println("<title>Servlet ListCategory</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ListCategory at " + request.getContextPath() + "</h1>");
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
        CategoryDAO categoryDB = new CategoryDAO();
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
        int start = (page - 1) * itemsPerPage;
        List<CategoryModel> categoriesRaw = categoryDB.getAllCategories();
        int totalPages = (int) Math.ceil(categoriesRaw.size() * 1.0 / itemsPerPage);
        int end = Math.min(page * itemsPerPage, categoriesRaw.size());
        List<CategoryModel> categories = categoryDB.getCategoriesByPage(categoriesRaw, start, end);
        request.setAttribute("page", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("categories", categories);
        request.getRequestDispatcher("views/admin/list_category.jsp").forward(request, response);
//        response.sendRedirect("views/admin/list_category.jsp");
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
        CategoryDAO categoryDB = new CategoryDAO();
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
        int start = (page - 1) * itemsPerPage;
        List<CategoryModel> categoriesRaw = categoryDB.getAllCategories();
        int totalPages = (int) Math.ceil(categoriesRaw.size() * 1.0 / itemsPerPage);
        int end = Math.min(page * itemsPerPage, categoriesRaw.size());
        List<CategoryModel> categories = categoryDB.getCategoriesByPage(categoriesRaw, start, end);
        request.setAttribute("page", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("categories", categories);

        //alert
        String added = (String) request.getParameter("added");
        String updated = (String) request.getParameter("updated");
        String deleted = (String) request.getParameter("deleted");
        if (added != null) {
            request.setAttribute("added", added);
        } else if (updated != null) {
            request.setAttribute("updated", updated);
        } else if (deleted != null) {
            request.setAttribute("deleted", deleted);
        }
        request.getRequestDispatcher("views/admin/list_category.jsp").forward(request, response);
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
