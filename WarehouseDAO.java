package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.UserDAO;
import model.User;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO;

    @Override
    public void init() {
        userDAO = new UserDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Uses authenticate (which safely points to loginUser)
        User user = userDAO.authenticate(username, password);

        if (user != null) {
            System.out.println("Logged in user: " + user.getUsername() + " | Role: [" + user.getRole() + "]");

            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            String role = user.getRole() != null ? user.getRole().trim() : "";

            if ("Customer".equalsIgnoreCase(role)) {
                // Inside your LoginServlet upon successful customer login:
response.sendRedirect("CustomerDashboard");
            } else if ("Admin".equalsIgnoreCase(role)) {
                response.sendRedirect("admin-dashboard.jsp");
            } else {
                request.setAttribute("errorMessage", "Invalid user role assigned.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("errorMessage", "Invalid username or password.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}