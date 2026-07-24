package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.UserDAO;
import model.User;

@WebServlet("/SignupServlet")
public class SignupServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO;

    @Override
    public void init() {
        userDAO = new UserDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email"); // Get email

        // 1. Check if username already exists
        User existingUser = userDAO.getUserByUsername(username);
        if (existingUser != null) {
            request.setAttribute("errorMessage", "Username is already taken. Please choose another.");
            request.getRequestDispatcher("signup.jsp").forward(request, response);
            return;
        }

        // 2. Create new user object and assign details
       User newUser = new User();
newUser.setUsername(username);
newUser.setPassword(password);
newUser.setEmail(email); // Set email
newUser.setRole("Customer");

        // 3. Register user in database
        boolean isRegistered = userDAO.registerUser(newUser);

        if (isRegistered) {
            // Redirect to login page with success parameter for the popup alert
            response.sendRedirect("login.jsp?success=true");
        } else {
            request.setAttribute("errorMessage", "Registration failed. Please try again.");
            request.getRequestDispatcher("signup.jsp").forward(request, response);
        }
    }
}