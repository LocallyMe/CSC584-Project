<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    if (session.getAttribute("userID") == null || !"Admin".equalsIgnoreCase((String) session.getAttribute("role"))) {
        response.sendRedirect("login.jsp?error=unauthorized");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard | Lovelle</title>
    <style>
        :root{ --bg-color:#fcf7f7; --text-dark:#3e2723; --accent-pink:#d88c9a; --white:#ffffff; --border-light:#f0e6e6; }
        body{ font-family:'Helvetica Neue',Arial,sans-serif; background:var(--bg-color); color:var(--text-dark); margin:0; }
        header{ display:flex; justify-content:space-between; align-items:center; padding:20px 40px; background:white; border-bottom:1px solid var(--border-light); }
        .logo{ font-size:24px; font-weight:bold; }
        .nav-links a { margin: 0 15px; text-decoration: none; color: #666; font-weight: 500; }
        .nav-links a:hover{ color:var(--accent-pink); }
        .nav-links a.active { color: var(--text-dark); border-bottom: 2px solid var(--accent-pink); padding-bottom: 5px; }
        .container{ max-width:900px; margin:50px auto; }
        .welcome-card{ background:white; padding:35px; border-radius:20px; box-shadow:0 5px 20px rgba(0,0,0,.05); margin-bottom:30px; }
        .welcome-card h2{ font-family:Georgia,serif; margin-bottom:10px; }
        .cards{ display:grid; grid-template-columns:repeat(2,1fr); gap:20px; }
        .card{ background:white; padding:30px; border-radius:20px; text-align:center; box-shadow:0 5px 20px rgba(0,0,0,.05); }
        .card-icon{ font-size:45px; }
        .card h3{ margin-top:15px; }
        .card p{ color:#777; }
        .btn{ display:inline-block; margin-top:15px; padding:10px 25px; border-radius:30px; background:var(--text-dark); color:white; text-decoration:none; }
        .btn:hover{ background:#5d4037; }
    </style>
</head>
<body>
<header>
    <div class="logo">Lovelle Admin</div>
    <div class="nav-links">
        <a href="adminDashboard.jsp" class="active">Dashboard Hub</a>
        <a href="manage-parcels.jsp">Manage Parcels</a>
        <a href="manage-requests.jsp">Help Desk Queue</a>
        <a href="LogoutServlet">Logout</a>
    </div>
</header>

<div class="container">
    <div class="welcome-card">
        <h2>Operations Console Control Center</h2>
        <p>Welcome back, Admin: <strong><%= session.getAttribute("fullName") %></strong>.</p>
    </div>

    <div class="cards">
        <div class="card">
            <div class="card-icon">📈</div>
            <h3>Manage Parcels</h3>
            <p>Update tracking stages, view order lines, and modify logistics flags.</p>
            <a href="manageParcel.jsp" class="btn">Manage Logistics</a>
        </div>
        <div class="card">
            <div class="card-icon">📬</div>
            <h3>Help Desk Queue</h3>
            <p>Review active customer service tickets and post response actions.</p>
            <a href="manageRequest.jsp" class="btn">Open Tickets Queue</a>
        </div>
    </div>
</div>
</body>
</html>