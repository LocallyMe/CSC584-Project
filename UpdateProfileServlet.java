/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parcelTracking.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import parcelTracking.util.DBConnection;

@WebServlet(name = "UpdateProfileServlet", urlPatterns = {"/UpdateProfileServlet"})
public class UpdateProfileServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        
        // 1. Safety check for session existence
        if (session == null || session.getAttribute("userID") == null) {
            response.sendRedirect("login.jsp?error=unauthorized");
            return;
        }

        // 2. Safe ID extraction (Handles both String and Integer session types)
        int userID = 0;
        try {
            Object idObj = session.getAttribute("userID");
            if (idObj instanceof Integer) {
                userID = (Integer) idObj;
            } else if (idObj instanceof String) {
                userID = Integer.parseInt((String) idObj);
            }
        } catch (Exception e) {
            System.out.println("Error parsing userID from session: " + e.getMessage());
            response.sendRedirect("login.jsp?error=unauthorized");
            return;
        }

        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");

        String sql = "UPDATE users SET fullName = ?, email = ?, phoneNumber = ? WHERE userID = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, fullName);
            ps.setString(2, email);
            ps.setString(3, phoneNumber);
            ps.setInt(4, userID);

            int rowsUpdated = ps.executeUpdate();
            
            if (rowsUpdated > 0) {
                // Update live session data so headers and greetings update immediately
                session.setAttribute("fullName", fullName);
                session.setAttribute("email", email);
                response.sendRedirect("myProfile.jsp?success=updated");
            } else {
                response.sendRedirect("editProfile.jsp?error=failed");
            }

        } catch (SQLIntegrityConstraintViolationException e) {
            // Happens if the new email is already used by another user
            e.printStackTrace();
            response.sendRedirect("editProfile.jsp?error=duplicate");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("editProfile.jsp?error=failed");
        }
    }
}