package parcelTracking.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import parcelTracking.util.DBConnection;

@WebServlet(name = "SubmitRequestServlet", urlPatterns = {"/SubmitRequestServlet"})
public class SubmitRequestServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userID = Integer.parseInt(request.getParameter("userID"));
        String requesterName = request.getParameter("requesterName");
        String trackingNumber = request.getParameter("trackingNumber");
        String parcelOwnerName = request.getParameter("parcelOwnerName");
        String issueType = request.getParameter("issueType");
        String priority = request.getParameter("priority");
        String subject = request.getParameter("subject");
        String message = request.getParameter("message");

        String query = "INSERT INTO customer_service (userID, requesterName, trackingNumber, parcelOwnerName, issueType, priority, subject, message, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, 'Pending')";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, userID);
            ps.setString(2, requesterName);
            ps.setString(3, trackingNumber);
            ps.setString(4, parcelOwnerName);
            ps.setString(5, issueType);
            ps.setString(6, priority);
            ps.setString(7, subject);
            ps.setString(8, message);
            ps.executeUpdate();
            
            response.sendRedirect("customerDashboard.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("customerService.jsp?error=failed");
        }
    }
}