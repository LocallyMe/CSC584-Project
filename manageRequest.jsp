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
    <title>Help Desk Queue | Lovelle</title>
    <style>
        :root{ --bg-color:#fcf7f7; --text-dark:#3e2723; --accent-pink:#d88c9a; --white:#ffffff; --border-light:#f0e6e6; }
        body{ font-family:'Helvetica Neue',Arial,sans-serif; background:var(--bg-color); color:var(--text-dark); margin:0; }
        header{ display:flex; justify-content:space-between; align-items:center; padding:20px 40px; background:white; border-bottom:1px solid var(--border-light); }
        .logo{ font-size:24px; font-weight:bold; }
        .nav-links a { margin: 0 15px; text-decoration: none; color: #666; font-weight: 500; }
        .nav-links a.active { color: var(--text-dark); border-bottom: 2px solid var(--accent-pink); padding-bottom: 5px; }
        .container{ max-width:900px; margin:50px auto; }
        .ticket-card { background:white; padding:30px; border-radius:20px; box-shadow:0 5px 20px rgba(0,0,0,.05); margin-bottom:25px; }
        .ticket-header { display:flex; justify-content:space-between; align-items:flex-start; border-bottom:1px solid var(--border-light); padding-bottom:15px; margin-bottom:15px; }
        .ticket-header h3 { font-family:Georgia,serif; margin:0 0 5px 0; font-size:18px; }
        .meta-text { font-size:13px; color:#777; line-height:1.4; }
        .status-badge { display:inline-block; padding:5px 12px; border-radius:15px; font-size:11px; font-weight:bold; text-transform:uppercase; }
        .status-Pending { background:#fff3e0; color:#e65100; }
        .status-In-Progress { background:#e3f2fd; color:#0d47a1; }
        .status-Resolved { background:#e8f5e9; color:#2e7d32; }
        .msg-body { background:var(--bg-color); padding:15px; border-radius:10px; font-size:14px; margin:15px 0; border-left:3px solid var(--accent-pink); }
        .form-control { width:100%; padding:12px; border-radius:10px; border:1px solid var(--border-light); background:var(--bg-color); color:var(--text-dark); box-sizing:border-box; font-size:14px; margin-bottom:10px; }
        .btn { display:inline-block; padding:8px 20px; border-radius:30px; background:var(--text-dark); color:white; text-decoration:none; border:none; font-size:13px; font-weight:500; cursor:pointer; }
        .reply-box { background:#f9f1f1; padding:15px; border-radius:10px; font-size:14px; margin-top:15px; }
    </style>
</head>
<body>
<header>
    <div class="logo">Lovelle Admin</div>
    <div class="nav-links">
        <a href="adminDashboard.jsp">Dashboard Hub</a>
        <a href="manageParcel.jsp">Manage Parcels</a>
        <a href="manageRequest.jsp" class="active">Help Desk Queue</a>
        <a href="LogoutServlet">Logout</a>
    </div>
</header>

<div class="container">
    <h2 style="font-family:Georgia,serif; margin-bottom:30px;">Customer Service Ticket Pipeline</h2>
    <%
        try (Connection con = DBConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM customer_service ORDER BY createdAt DESC")) {
            while(rs.next()) {
                int reqID = rs.getInt("requestID");
                String currentStatus = rs.getString("status");
                String statusClass = currentStatus.replace(" ", "-");
    %>
        <div class="ticket-card">
            <div class="ticket-header">
                <div>
                    <h3><%= rs.getString("subject") %></h3>
                    <div class="meta-text">
                        <strong>Ticket ID:</strong> #<%= reqID %> | <strong>Type:</strong> <%= rs.getString("issueType") %> | <strong>Priority:</strong> <%= rs.getString("priority") %><br>
                        <strong>Requester:</strong> <%= rs.getString("requesterName") %> | <strong>Tracking Number:</strong> <%= rs.getString("trackingNumber") %>
                    </div>
                </div>
                <span class="status-badge status-<%= statusClass %>"><%= currentStatus %></span>
            </div>
            
            <div class="msg-body"><%= rs.getString("message") %></div>

            <% if (rs.getString("adminReply") != null && !rs.getString("adminReply").trim().isEmpty()) { %>
                <div class="reply-box">
                    <strong>Administrative Resolution Update:</strong>
                    <p style="margin:5px 0 0 0; color:#555;"><%= rs.getString("adminReply") %></p>
                </div>
            <% } else { %>
                <form action="${pageContext.request.contextPath}/AdminReplyServlet" method="post">
                    <input type="hidden" name="requestID" value="<%= reqID %>">
                    <textarea name="replyText" class="form-control" rows="2" placeholder="Write ticket response action logs here..." required></textarea>
                    <button type="submit" class="btn">Send Reply & Resolve Ticket</button>
                </form>
            <% } %>
        </div>
    <% 
            }
        } catch(Exception e) { 
            out.println("<p>Error loading queue records from schema server database connection lines.</p>"); 
        }
    %>
</div>
</body>
</html>