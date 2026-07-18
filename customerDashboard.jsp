<%-- 
    Document   : customerDashboard
    Created on : 18 Jul 2026, 2:28:56 pm
    Author     : ACER
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%

    if(session.getAttribute("userID") == null){
        response.sendRedirect("login.jsp");
        return;
    }

%>


<!DOCTYPE html>
<html>

<head>

<title>Customer Dashboard | Lovelle</title>


<style>

    :root{

    --bg-color:#fcf7f7;
    --text-dark:#3e2723;
    --accent-pink:#d88c9a;
    --white:#ffffff;
    --border-light:#f0e6e6;

    }


    body{

    font-family:'Helvetica Neue',Arial,sans-serif;
    background:var(--bg-color);
    color:var(--text-dark);
    margin:0;

    }


    /* NAVBAR */

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

    .nav-links a:hover{

    color:var(--accent-pink);

    }

    .nav-links a {
        margin: 0 15px;
        text-decoration: none;
        color: #666;
        font-weight: 500;
    }
          
    .nav-links a.active {
        color: var(--text-dark);
        border-bottom: 2px solid var(--accent-pink);
        padding-bottom: 5px;
    }


    /* DASHBOARD */

    .container{

    max-width:900px;
    margin:50px auto;

    }


    .welcome-card{

    background:white;
    padding:35px;
    border-radius:20px;
    box-shadow:0 5px 20px rgba(0,0,0,.05);
    margin-bottom:30px;

    }


    .welcome-card h2{

    font-family:Georgia,serif;
    margin-bottom:10px;

    }


    .cards{

    display:grid;
    grid-template-columns:repeat(3,1fr);
    gap:20px;

    }


    .card{

    background:white;
    padding:30px;
    border-radius:20px;
    text-align:center;
    box-shadow:0 5px 20px rgba(0,0,0,.05);

    }


    .card-icon{

    font-size:45px;

    }


    .card h3{

    margin-top:15px;

    }


    .card p{

    color:#777;

    }


    .btn{

    display:inline-block;
    margin-top:15px;
    padding:10px 25px;
    border-radius:30px;
    background:var(--text-dark);
    color:white;
    text-decoration:none;

    }


    .btn:hover{

    background:#5d4037;

    }



</style>


</head>


<body>


<header>


<div class="logo">

Lovelle

</div>



<div class="nav-links">


<a href="customerDashboard.jsp" class="active">

Home

</a>


<a href="trackOrder.jsp">

Track Parcel

</a>


<a href="customerService.jsp">

Customer Service

</a>


<a href="LogoutServlet">

Logout

</a>



</div>


</header>



<div class="container">



<div class="welcome-card">


<h2>

Welcome, <%= session.getAttribute("fullName") %> 👋

</h2>


<p>

Welcome to Lovelle Customer Portal.
You can track parcels and request customer assistance here.

</p>


</div>




<div class="cards">


<div class="card">


<div class="card-icon">

📦

</div>


<h3>

Track Parcel

</h3>


<p>

Check your parcel status using tracking number.

</p>


<a href="trackOrder.jsp" class="btn">

Track Now

</a>


</div>





<div class="card">


<div class="card-icon">

💬

</div>


<h3>

Customer Service

</h3>


<p>

Need help? Contact our support team.

</p>


<a href="customerService.jsp" class="btn">

Contact Us

</a>


</div>





<div class="card">


<div class="card-icon">

👤

</div>


<h3>

My Profile

</h3>


<p>

View your account information.

</p>


<a href="#" class="btn">

View Profile

</a>


</div>



</div>


</div>



</body>

</html>
