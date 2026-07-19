/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package parcelTracking.servlet;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import parcelTracking.dao.ProfileDAO;

/**
 *
 * @author ACER
 */
@WebServlet(name = "ChangePasswordServlet", urlPatterns = {"/ChangePasswordServlet"})
public class ChangePasswordServlet extends HttpServlet {

@Override
protected void doPost(HttpServletRequest request,
HttpServletResponse response)
throws ServletException,IOException{

HttpSession session=request.getSession();

int userID=(Integer)session.getAttribute("userID");

String current=request.getParameter("currentPassword");

String newPass=request.getParameter("newPassword");

String confirm=request.getParameter("confirmPassword");

ProfileDAO dao=new ProfileDAO();

if(!newPass.equals(confirm)){

response.sendRedirect("changePassword.jsp?match=false");

return;

}

if(dao.changePassword(userID,current,newPass)){

response.sendRedirect("ProfileServlet");

}else{

response.sendRedirect("changePassword.jsp?error=1");

}

}

}