/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parcelTracking.servlet;


import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import parcelTracking.bean.CustomerService;
import parcelTracking.dao.CustomerServiceDAO;



@WebServlet("/CustomerServiceServlet")
public class CustomerServiceServlet extends HttpServlet {



@Override
protected void doPost(HttpServletRequest request,
                      HttpServletResponse response)
        throws ServletException, IOException {

    HttpSession session = request.getSession();

    int userID = (int) session.getAttribute("userID");
    String requesterName = (String) session.getAttribute("fullName");

    CustomerService cs = new CustomerService();

    cs.setUserID(userID);
    cs.setRequesterName(requesterName);

    // Tracking Number
    String trackingNumber = request.getParameter("trackingNumber");
    cs.setTrackingNumber(trackingNumber);

    // Create DAO first
    CustomerServiceDAO dao = new CustomerServiceDAO();

    // Automatically get parcel owner
    String parcelOwner = dao.getParcelOwner(trackingNumber);

    cs.setParcelOwnerName(parcelOwner);

    // Other form data
    cs.setIssueType(request.getParameter("issueType"));
    cs.setPriority(request.getParameter("priority"));
    cs.setSubject(request.getParameter("subject"));
    cs.setMessage(request.getParameter("message"));

    if (dao.submitRequest(cs)) {

        response.sendRedirect("customerService.jsp?success=true");

    } else {

        response.sendRedirect("customerService.jsp?error=true");

    }

}

}