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
import parcelTracking.bean.User;
import parcelTracking.dao.ProfileDAO;

/**
 *
 * @author ACER
 */
@WebServlet(name = "EditProfileServlet", urlPatterns = {"/EditProfileServlet"})
public class EditProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if(session == null || session.getAttribute("userID") == null){
            response.sendRedirect("login.jsp");
            return;
        }

        int userID = (Integer) session.getAttribute("userID");

        ProfileDAO dao = new ProfileDAO();

        User user = dao.getUser(userID);

        request.setAttribute("user", user);

        request.getRequestDispatcher("editProfile.jsp")
               .forward(request,response);
    }

@Override
protected void doPost(HttpServletRequest request,
HttpServletResponse response)
throws ServletException,IOException{

HttpSession session=request.getSession();

int userID=(Integer)session.getAttribute("userID");

User user=new User();

user.setUserID(userID);
user.setFullName(request.getParameter("fullName"));
user.setEmail(request.getParameter("email"));
user.setPhoneNumber(request.getParameter("phoneNumber"));

ProfileDAO dao=new ProfileDAO();

if(dao.updateProfile(user)){

session.setAttribute("fullName",user.getFullName());

response.sendRedirect("ProfileServlet");

}else{

response.sendRedirect("editProfile.jsp?error=1");

}

}

}