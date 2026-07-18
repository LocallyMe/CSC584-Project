<%-- 
    Document   : customerService
    Created on : 18 Jul 2026, 5:05:28 pm
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

<title>Customer Service | Lovelle</title>


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


/* SERVICE CARD */


.container{

max-width:700px;
margin:50px auto;

}


.service-card{

background:white;
padding:40px;
border-radius:20px;
box-shadow:0 5px 20px rgba(0,0,0,.05);

}


h2{

font-family:Georgia,serif;

}


.subtitle{

color:#777;
margin-bottom:30px;

}


label{

font-weight:600;

}


input, textarea, select{

width:100%;
padding:12px;
margin-top:8px;
margin-bottom:20px;
border:1px solid var(--border-light);
border-radius:12px;
font-size:14px;
background:white;

}


textarea{

height:130px;
resize:none;

}


input:focus,
textarea:focus,
select:focus{

outline:none;
border-color:var(--accent-pink);

}


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


/* MESSAGE */


.info-box{

background:#fff5f5;
border-left:4px solid var(--accent-pink);
padding:15px;
margin-bottom:25px;
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


<a href="customerService.jsp" class = "active">

Customer Service

</a>


<a href="LogoutServlet">

Logout

</a>


</div>


</header>




<div class="container">


<div class="service-card">


<h2>

Customer Service 💬

</h2>


<p class="subtitle">

Hello <%= session.getAttribute("fullName") %>.
Need assistance? Send us your inquiry below.

</p>

<div class="info-box">

Our support team will review your request and assist you as soon as possible.

</div>

<%
if(request.getParameter("invalidTracking") != null){
%>

<div class="info-box" style="border-left:4px solid red;">
Tracking number not found. Please enter a valid tracking number.
</div>

<%
}
%>

<form action="CustomerServiceServlet" method="post">

<label>
Tracking Number
</label>

<input 
type="text"
name="trackingNumber"
placeholder="Example: TRK100001"
required>

<label>
Issue Type
</label>

<select 
name="issueType"
required>

<option value="">
Select Issue
</option>

<option value="Delivery Delay">
Delivery Delay
</option>

<option value="Wrong Parcel">
Wrong Parcel
</option>

<option value="Damaged Parcel">
Damaged Parcel
</option>

<option value="Lost Parcel">
Lost Parcel
</option>

<option value="Tracking Not Updated">
Tracking Not Updated
</option>

<option value="Courier Problem">
Courier Problem
</option>

<option value="Other">
Other
</option>

</select>

<label>
Priority Level
</label>


<select 
name="priority"
required>

<option value="Low">
Low
</option>

<option value="Medium" selected>
Medium
</option>

<option value="High">
High
</option>

</select>

<label>
Subject
</label>


<input 
type="text"
name="subject"
placeholder="Example: Parcel delivery issue"
required>

<label>
Message
</label>

<textarea
name="message"
placeholder="Describe your problem here..."
required></textarea>

<button 
type="submit"
class="btn-submit">

Submit Request

</button>
</form>
</div>
</div>
</body>
</html>
