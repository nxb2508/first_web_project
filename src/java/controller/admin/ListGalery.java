/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dao.GaleryDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.GaleryModel;

/**
 *
 * @author Bach
 */
public class ListGalery extends HttpServlet {

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
            out.println("<title>Servlet ListGalery</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ListGalery at " + request.getContextPath() + "</h1>");
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
        String product_name = request.getParameter("product_name");
        if (product_name == null) {
            product_name = "";
        }
        List<GaleryModel> galeries = new GaleryDAO().searchGaleriesByProductName(product_name);
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
        int totalPages = (int) Math.ceil(galeries.size() * 1.0 / itemsPerPage);
        int start = (page - 1) * itemsPerPage;
        int end = Math.min(page * itemsPerPage, galeries.size());
        request.setAttribute("product_name", product_name);
        request.setAttribute("page", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("galeries", new GaleryDAO().getGaleriesByPage(galeries, start, end));
        request.getRequestDispatcher("views/admin/list_galery.jsp").forward(request, response);
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
        String product_name = request.getParameter("product_name");
        if (product_name == null) {
            product_name = "";
        }
        List<GaleryModel> galeries = new GaleryDAO().searchGaleriesByProductName(product_name);
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
        int totalPages = (int) Math.ceil(galeries.size() * 1.0 / itemsPerPage);
        int start = (page - 1) * itemsPerPage;
        int end = Math.min(page * itemsPerPage, galeries.size());
        request.setAttribute("product_name", product_name);
        request.setAttribute("page", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("galeries", new GaleryDAO().getGaleriesByPage(galeries, start, end));
        request.getRequestDispatcher("views/admin/list_galery.jsp").forward(request, response);
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
