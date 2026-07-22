<%-- 
    Document   : manageParcel
    Created on : 19 Jul 2026, 10:13:15 pm
    Author     : Nur Amyleeya
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.sql.*, parcelTracking.util.DBConnection" %>
<%
    if (session.getAttribute("userID") == null || !"Admin".equalsIgnoreCase((String) session.getAttribute("role"))) {
        response.sendRedirect("login.jsp?error=unauthorized"); return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Manage Parcels | Lovelle</title>
    <style>
        :root{ --bg-color:#fcf7f7; --text-dark:#3e2723; --accent-pink:#d88c9a; --white:#ffffff; --border-light:#f0e6e6; }
        body{ font-family:'Helvetica Neue',Arial,sans-serif; background:var(--bg-color); color:var(--text-dark); margin:0; }
        header{ display:flex; justify-content:space-between; align-items:center; padding:20px 40px; background:white; border-bottom:1px solid var(--border-light); }
        .logo{ font-size:24px; font-weight:bold; }
        .nav-links a { margin: 0 15px; text-decoration: none; color: #666; font-weight: 500; }
        .nav-links a.active { color: var(--text-dark); border-bottom: 2px solid var(--accent-pink); padding-bottom: 5px; }
        .container{ max-width:900px; margin:50px auto; }
        .table-card { background:white; padding:35px; border-radius:20px; box-shadow:0 5px 20px rgba(0,0,0,.05); }
        .table-card h2 { font-family:Georgia,serif; margin-top:0; margin-bottom:25px; }
        .parcel-table { width:100%; border-collapse:collapse; text-align:left; font-size:14px; }
        .parcel-table th { padding:12px 15px; border-bottom:2px solid var(--border-light); color:#555; font-weight:600; }
        .parcel-table td { padding:15px; border-bottom:1px solid var(--border-light); vertical-align:middle; }
        .form-select { padding:8px 12px; border-radius:20px; border:1px solid var(--border-light); background:var(--bg-color); color:var(--text-dark); font-size:13px; }
        .btn { display:inline-block; padding:8px 18px; border-radius:30px; background:var(--text-dark); color:white; text-decoration:none; border:none; font-size:12px; font-weight:500; cursor:pointer; margin-left:5px; }
        .status-badge { display:inline-block; padding:4px 10px; border-radius:12px; font-size:12px; background:var(--bg-color); border:1px solid var(--border-light); font-weight:500; }
    </style>
</head>
<body>
<header>
    <div class="logo">Lovelle Admin</div>
    <div class="nav-links">
        <a href="adminDashboard.jsp">Dashboard Hub</a>
        <a href="manage-parcels.jsp" class="active">Manage Parcels</a>
        <a href="manageRequest.jsp">Help Desk Queue</a>
        <a href="LogoutServlet">Logout</a>
    </div>
</header>

<div class="container">
    <div class="table-card">
        <h2>Active Logistics Pipelines Matrix</h2>
        <table class="parcel-table">
            <thead>
                <tr>
                    <th>Tracking Number</th>
                    <th>Sender Name</th>
                    <th>Receiver Name</th>
                    <th>Current Status</th>
                    <th>Execution Actions</th>
                </tr>
            </thead>
            <tbody>
            <%
                try (Connection con = DBConnection.getConnection();
                     Statement stmt = con.createStatement();
                     ResultSet rs = stmt.executeQuery("SELECT * FROM parcel ORDER BY parcelID DESC")) {
                    while(rs.next()) {
                        String trackingNo = rs.getString("trackingNumber");
                        String currentStatus = rs.getString("currentStatus");
            %>
                <tr>
                    <td style="font-weight:bold; color:var(--text-dark);"><%= trackingNo %></td>
                    <td><%= rs.getString("senderName") %></td>
                    <td><%= rs.getString("receiverName") %></td>
                    <td><span class="status-badge"><%= currentStatus %></span></td>
                    <td>
                        <form action="${pageContext.request.contextPath}/UpdateParcelStatusServlet" method="post" style="margin:0; display:flex; align-items:center;">
                            <input type="hidden" name="trackingNumber" value="<%= trackingNo %>">
                            <select name="newStatus" class="form-select">
                                <option value="Pending" <%= "Pending".equals(currentStatus)?"selected":"" %>>Pending</option>
                                <option value="Delivered" <%= "Delivered".equals(currentStatus)?"selected":"" %>>Delivered</option>
                                <option value="Lost" <%= "Lost".equals(currentStatus)?"selected":"" %>>Lost</option>
                            </select>
                            <button type="submit" class="btn">Update</button>
                        </form>
                    </td>
                </tr>
            <% 
                    }
                } catch (Exception e) { 
            %>
                <tr><td colspan="5" style="color:#999; text-align:center;">No tracking matrix rows detected.</td></tr>
            <% } %>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>