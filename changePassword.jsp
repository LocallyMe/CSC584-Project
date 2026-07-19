<%-- 
    Document   : changePassword
    Created on : 19 Jul 2026, 10:04:49 pm
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
if(session.getAttribute("userID")==null){
    response.sendRedirect("login.jsp");
    return;
}
%>

<!DOCTYPE html>
<html>

<head>

<title>Change Password | Lovelle</title>

<style>

:root{

--bg-color:#fcf7f7;
--text-dark:#3e2723;
--accent-pink:#d88c9a;
--white:#ffffff;
--border-light:#f0e6e6;

}

body{

margin:0;
font-family:'Helvetica Neue',Arial,sans-serif;
background:var(--bg-color);
color:var(--text-dark);

}

/* ================= NAVBAR ================= */

header{

display:flex;
justify-content:space-between;
align-items:center;
padding:20px 40px;
background:white;
border-bottom:1px solid var(--border-light);

}

.logo{

font-size:24px;
font-weight:bold;

}

.nav-links a{

margin:0 15px;
text-decoration:none;
color:#666;
font-weight:500;

}

.nav-links a.active{

color:var(--text-dark);
border-bottom:2px solid var(--accent-pink);
padding-bottom:5px;

}

.nav-links a:hover{

color:var(--accent-pink);

}

/* ================= CARD ================= */

.container{

max-width:600px;
margin:50px auto;

}

.card{

background:white;
padding:40px;
border-radius:20px;
box-shadow:0 5px 20px rgba(0,0,0,.05);

}

h2{

font-family:Georgia,serif;
margin-bottom:10px;

}

.subtitle{

color:#777;
margin-bottom:30px;

}

/* ================= FORM ================= */

label{

font-weight:600;

}

input{

width:100%;
padding:12px;
margin-top:8px;
margin-bottom:20px;
border:1px solid var(--border-light);
border-radius:12px;
font-size:14px;

}

input:focus{

outline:none;
border-color:var(--accent-pink);

}

/* ================= BUTTON ================= */

.btn-submit{

background:var(--text-dark);
color:white;
border:none;
padding:12px 35px;
border-radius:30px;
font-weight:bold;
cursor:pointer;

}

.btn-submit:hover{

background:#5d4037;

}

.btn-back{

display:inline-block;
margin-left:10px;
padding:12px 30px;
border-radius:30px;
background:#d88c9a;
color:white;
text-decoration:none;
font-weight:bold;

}

.btn-back:hover{

background:#c67988;

}

/* ================= MESSAGE ================= */

.success{

background:#e8f5e9;
border-left:4px solid green;
padding:15px;
margin-bottom:20px;
border-radius:10px;

}

.error{

background:#ffecec;
border-left:4px solid red;
padding:15px;
margin-bottom:20px;
border-radius:10px;

}

</style>

</head>

<body>

<header>

<div class="logo">

Lovelle

</div>

<div class="nav-links">

<a href="customerDashboard.jsp">

Home

</a>

<a href="trackOrder.jsp">

Track Parcel

</a>

<a href="customerService.jsp">

Customer Service

</a>

<a href="ProfileServlet">

My Profile

</a>

<a href="LogoutServlet">

Logout

</a>

</div>

</header>

<div class="container">

<div class="card">

<h2>

🔒 Change Password

</h2>

<p class="subtitle">

Keep your Lovelle account secure by updating your password.

</p>

<%
if(request.getParameter("match") != null){
%>

<div class="error">

New Password and Confirm Password do not match.

</div>

<%
}
%>

<%
if(request.getParameter("error") != null){
%>

<div class="error">

Current Password is incorrect.

</div>

<%
}
%>

<%
if(request.getParameter("success") != null){
%>

<div class="success">

Password changed successfully.

</div>

<%
}
%>

<form action="ChangePasswordServlet" method="post">

<label>

Current Password

</label>

<input
type="password"
name="currentPassword"
required>

<label>

New Password

</label>

<input
type="password"
name="newPassword"
required>

<label>

Confirm New Password

</label>

<input
type="password"
name="confirmPassword"
required>

<button
type="submit"
class="btn-submit">

Change Password

</button>

<a href="ProfileServlet" class="btn-back">

Back

</a>

</form>

</div>

</div>

</body>

</html>