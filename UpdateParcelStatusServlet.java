package parcelTracking.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import parcelTracking.util.DBConnection;

@WebServlet(name = "UpdateParcelStatusServlet", urlPatterns = {"/UpdateParcelStatusServlet"})
public class UpdateParcelStatusServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("userID") == null || !"Admin".equalsIgnoreCase((String) session.getAttribute("role"))) {
            response.sendRedirect("login.jsp?error=unauthorized");
            return;
        }

        String trackingNumber = request.getParameter("trackingNumber");
        String newStatus = request.getParameter("newStatus");

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "UPDATE parcel SET currentStatus = ? WHERE trackingNumber = ?")) {
            ps.setString(1, newStatus);
            ps.setString(2, trackingNumber);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("manageParcel.jsp");
    }
}