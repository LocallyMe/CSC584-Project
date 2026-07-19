/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parcelTracking.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

import parcelTracking.bean.User;
import parcelTracking.dao.ProfileDAO;

@WebServlet("/ProfileServlet")
public class ProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
                         throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        // User not logged in
        if(session == null || session.getAttribute("userID") == null){

            response.sendRedirect("login.jsp");
            return;

        }

        int userID = (Integer) session.getAttribute("userID");

        ProfileDAO dao = new ProfileDAO();

        User user = dao.getUser(userID);

        request.setAttribute("user", user);

        request.getRequestDispatcher("myProfile.jsp")
               .forward(request, response);

    }

}
