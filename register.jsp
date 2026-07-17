<%-- 
    Document   : register
    Created on : 17 Jul 2026, 11:37:00 pm
    Author     : ACER
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>

    <title>Parcel Tracking System | Register</title>

    <!-- Bootstrap 5 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">


    <style>

        body{
            background: linear-gradient(135deg,#ffd6e7,#fff5fa);
            min-height:100vh;
            display:flex;
            justify-content:center;
            align-items:center;
            padding:30px 0;
            font-family:'Segoe UI', Arial, Helvetica, sans-serif;
        }

        .register-card{
            width:420px;
            border:none;
            border-radius:25px;
            background:white;
            box-shadow:0px 15px 35px rgba(255,105,150,0.25);
            overflow:hidden;
        }

        .register-header{
            text-align:center;
            padding:20px 20px 5px;
        }

        .register-header h1{
            font-size:45px;
            margin-bottom:5px;
        }

        .register-header h2{
            color:#d63384;
            font-weight:700;
        }

        .register-header p{
            color:#999;
        }

        .card-body{
            padding:25px;
        }

        label{
            font-weight:600;
            color:#555;
        }

        .form-control{
            border-radius:12px;
            border:1px solid #f3b6d2;
            padding:10px;
        }

        .form-control:focus{
            border-color:#d63384;
            box-shadow:0 0 0 0.2rem rgba(214,51,132,.15);
        }

        .btn-register{
            width:100%;
            border-radius:12px;
            background:#d63384;
            border:none;
            padding:12px;
            font-weight:bold;
            transition:0.3s;
        }

        .btn-register:hover{
            background:#b02a6b;
            transform:translateY(-2px);
        }

        a{
            color:#d63384;
            font-weight:600;
            text-decoration:none;
        }

        a:hover{
            text-decoration:underline;
        }

    </style>


</head>


<body>


<div class="card register-card">


    <div class="register-header">

        <h1>📦</h1>

        <h2>Create Account</h2>

        <p>
            Register to start tracking your parcel
        </p>

    </div>



    <div class="card-body">


        <form action="RegisterServlet" method="post">


            <div class="mb-3">

                <label>Full Name</label>

                <input 
                    type="text"
                    name="fullName"
                    class="form-control"
                    placeholder="Enter Full Name"
                    required>

            </div>



            <div class="mb-3">

                <label>Email</label>

                <input 
                    type="email"
                    name="email"
                    class="form-control"
                    placeholder="Enter Email"
                    required>

            </div>



            <div class="mb-3">

                <label>Phone Number</label>

                <input 
                    type="text"
                    name="phoneNumber"
                    class="form-control"
                    placeholder="Enter Phone Number"
                    required>

            </div>



            <div class="mb-3">

                <label>Password</label>

                <input 
                    type="password"
                    name="password"
                    class="form-control"
                    placeholder="Create Password"
                    required>

            </div>



            <div class="mb-3">

                <label>Confirm Password</label>

                <input 
                    type="password"
                    name="confirmPassword"
                    class="form-control"
                    placeholder="Confirm Password"
                    required>

            </div>



            <button 
                type="submit"
                class="btn btn-primary btn-register">

                Register

            </button>



        </form>


        <hr>


        <div class="text-center">

            Already have an account?

            <a href="login.jsp">
                Login
            </a>

        </div>


    </div>


</div>


</body>

</html>
