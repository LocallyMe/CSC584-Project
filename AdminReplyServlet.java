package parcelTracking.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import parcelTracking.util.DBConnection;

@WebServlet(name = "AdminReplyServlet", urlPatterns = {"/AdminReplyServlet"})
public class AdminReplyServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("userID") == null || !"Admin".equalsIgnoreCase((String) session.getAttribute("role"))) {
            response.sendRedirect("login.jsp?error=unauthorized");
            return;
        }

        String requestID = request.getParameter("requestID");
        String replyText = request.getParameter("replyText");

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "UPDATE customer_service SET adminReply = ?, status = 'Resolved' WHERE requestID = ?")) {
            ps.setString(1, replyText);
            ps.setInt(2, Integer.parseInt(requestID));
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("manage-requests.jsp");
    }
}