/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dao.SizeDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.SizeModel;

/**
 *
 * @author Bach
 */
public class UpdateSize extends HttpServlet {

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
            out.println("<title>Servlet UpdateSize</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateSize at " + request.getContextPath() + "</h1>");
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
        int sizeId = Integer.parseInt(request.getParameter("id"));
        String sizeName = request.getParameter("name");
        request.setAttribute("size_name", sizeName);
        request.setAttribute("size_id", sizeId);
        request.getRequestDispatcher("views/admin/update_size.jsp").forward(request, response);
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
        int size_id = Integer.parseInt(request.getParameter("size_id"));
        String size_name = request.getParameter("size_name");
        SizeModel size = new SizeModel();
        size.setId(size_id);
        size.setName(size_name);
        SizeDAO sizeDB = new SizeDAO();
        int result = sizeDB.updateSize(size);
        if (result == 0) {
            request.setAttribute("updateError", "Đã Xảy Ra Lỗi Khi Sửa Kích Thước Sản Phẩm");
        } else {
            request.setAttribute("updated", "Kích Thước Sản Phẩm Đã Được Chỉnh Sửa");
        }
        request.getRequestDispatcher("admin-list-size").forward(request, response);
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
