/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package parcelTracking.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import parcelTracking.util.DBConnection;

/**
 *
 * @author ACER
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
                          throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {

            Connection con = DBConnection.getConnection();

            String sql = "SELECT * FROM users WHERE email=? AND password=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                HttpSession session = request.getSession();

                session.setAttribute("userID", rs.getInt("userID"));
                session.setAttribute("fullName", rs.getString("fullName"));
                session.setAttribute("role", rs.getString("role"));

                if (rs.getString("role").equals("Admin")) {
                    response.sendRedirect("adminDashboard.jsp");
                } else {
                    String redirect = request.getParameter("redirect");

                    if("customerService".equals(redirect)){

                        response.sendRedirect("customerService.jsp");

                    }else{

                        response.sendRedirect("customerDashboard.jsp");

                    }
                }

            } else {

                response.sendRedirect("login.jsp?error=1");

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

    }
}
