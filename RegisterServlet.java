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
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLIntegrityConstraintViolationException;
import parcelTracking.util.DBConnection;

/**
 *
 * @author ACER
 */
@WebServlet(name = "RegisterServlet", urlPatterns = {"/RegisterServlet"})
public class RegisterServlet extends HttpServlet {

protected void doPost(HttpServletRequest request,
                      HttpServletResponse response)
                      throws ServletException, IOException {


    String fullName = request.getParameter("fullName");
    String email = request.getParameter("email");
    String phoneNumber = request.getParameter("phoneNumber");
    String password = request.getParameter("password");
    String role = "Customer";

    try{

        Connection con = DBConnection.getConnection();

        String sql =
        "INSERT INTO users(fullName,email,phoneNumber,password,role)"
        + " VALUES(?,?,?,?,?)";


        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, fullName);
        ps.setString(2, email);
        ps.setString(3, phoneNumber);
        ps.setString(4, password);
        ps.setString(5, role);

        int result = ps.executeUpdate();

        if(result > 0){
            response.sendRedirect("login.jsp");
        }
        else{
            response.sendRedirect("register.jsp");
        }
    }
    catch(SQLIntegrityConstraintViolationException e){
        response.getWriter()
        .println("Email already registered");
    }
    catch(Exception e){
        e.printStackTrace();
    }
}
}
