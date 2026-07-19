<%-- 
    Document   : editProfile
    Created on : 19 Jul 2026, 9:59:18 pm
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

<title>Edit Profile</title>

<style>

:root{

--bg:#fcf7f7;
--pink:#d88c9a;
--dark:#3e2723;
--border:#f0e6e6;

}

body{

font-family:Arial;
background:var(--bg);
margin:0;

}

.container{

width:650px;
margin:50px auto;

}

.card{

background:white;
padding:40px;
border-radius:20px;
box-shadow:0 5px 15px rgba(0,0,0,.05);

}

input{

width:100%;
padding:12px;
margin-bottom:20px;
border-radius:10px;
border:1px solid var(--border);

}

button{

background:var(--dark);
color:white;
padding:12px 30px;
border:none;
border-radius:25px;
cursor:pointer;

}

button:hover{

background:#5d4037;

}

</style>

</head>

<body>

<div class="container">

<div class="card">

<h2>Edit Profile</h2>

<form action="EditProfileServlet" method="post">

<label>Full Name</label>

<input
type="text"
name="fullName"
value="${user.fullName}"
required>

<label>Email</label>

<input
type="email"
name ="email"
value="${user.email}">

<label>Phone Number</label>

<input
type="text"
name="phoneNumber"
value="${user.phoneNumber}">

<button type="submit">

Save Changes

</button>

</form>

</div>

</div>

</body>

</html>
