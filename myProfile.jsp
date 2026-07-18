<%-- 
    Document   : myProfile
    Created on : 18 Jul 2026, 11:43:01 pm
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

<title>My Profile | Lovelle</title>

<style>

:root{

--bg:#fcf7f7;
--pink:#d88c9a;
--dark:#3e2723;
--white:white;
--border:#f0e6e6;

}

body{

margin:0;
font-family:Arial;
background:var(--bg);

}

header{

display:flex;
justify-content:space-between;
padding:20px 40px;
background:white;
border-bottom:1px solid var(--border);

}

.logo{

font-size:24px;
font-weight:bold;

}

.nav-links a{

margin:0 15px;
text-decoration:none;
color:#666;

}

.nav-links a.active{

color:var(--dark);
border-bottom:2px solid var(--pink);

}

.container{

width:700px;
margin:50px auto;

}

.card{

background:white;
padding:40px;
border-radius:20px;
box-shadow:0 5px 20px rgba(0,0,0,.05);

}

table{

width:100%;

}

td{

padding:15px 0;

}

.label{

font-weight:bold;
width:180px;

}

.btn{

padding:12px 25px;
border:none;
border-radius:30px;
cursor:pointer;
font-weight:bold;
margin-top:20px;

}

.edit{

background:#3e2723;
color:white;

}

.password{

background:#d88c9a;
color:white;
margin-left:10px;

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

<a href="myProfile.jsp" class="active">

My Profile

</a>

<a href="LogoutServlet">

Logout

</a>

</div>

</header>

<div class="container">

<div class="card">

<h2>👤 My Profile</h2>

<hr><br>

<table>

<tr>

<td class="label">

Full Name

</td>

<td>

<%=session.getAttribute("fullName")%>

</td>

</tr>

<tr>

<td class="label">

Email

</td>

<td>

${user.email}

</td>

</tr>

<tr>

<td class="label">

Phone Number

</td>

<td>

${user.phoneNumber}

</td>

</tr>

<tr>

<td class="label">

Address

</td>

<td>

${user.address}

</td>

</tr>

<tr>

<td class="label">

Role

</td>

<td>

Customer

</td>

</tr>

</table>

<br>

<a href="editProfile.jsp">

<button class="btn edit">

Edit Profile

</button>

</a>

<a href="changePassword.jsp">

<button class="btn password">

Change Password

</button>

</a>

</div>

</div>

</body>

</html>