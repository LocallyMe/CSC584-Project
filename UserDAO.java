/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

import dao.InventoryDAO;
import java.util.List;
import model.StockTransaction;

/**
 *
 * @AZRI
 */
@WebServlet("/InventoryServlet")
public class InventoryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private InventoryDAO inventoryDAO;

    @Override
    public void init() {
        inventoryDAO = new InventoryDAO();
    }

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet InventoryServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet InventoryServlet at " + request.getContextPath() + "</h1>");
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
        String action = request.getParameter("action");

        if ("history".equals(action)) {
            // Fetch and display all stock transactions
            List<StockTransaction> transactions = inventoryDAO.getAllTransactions();
            request.setAttribute("transactionList", transactions);
            request.getRequestDispatcher("transaction-history.jsp").forward(request, response);
        } else {
            // Default: you could redirect to a general inventory dashboard here
            response.sendRedirect("admin-dashboard.jsp");
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
        // Security check: Only logged-in users should modify inventory
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String action = request.getParameter("action");
        int productId = Integer.parseInt(request.getParameter("productId"));
        int warehouseId = Integer.parseInt(request.getParameter("warehouseId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        
        // Record the transaction
        StockTransaction transaction = new StockTransaction();
        transaction.setProductId(productId);
        transaction.setQuantity(quantity);

        if ("stockIn".equals(action)) {
            transaction.setType("IN");
            inventoryDAO.recordTransaction(transaction);
            
            // Here you would also call inventoryDAO.updateStock() to add to current quantity
            // (Assuming you fetch current quantity first, add, then update)
            
        } else if ("stockOut".equals(action)) {
            transaction.setType("OUT");
            inventoryDAO.recordTransaction(transaction);
            
            // Here you would also call inventoryDAO.updateStock() to subtract from current quantity
        }

        // Redirect back to the transaction history page after updating
        response.sendRedirect("InventoryServlet?action=history");
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
