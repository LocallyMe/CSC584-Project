<%-- 
    Document   : login
    Created on : 17 Jul 2026, 11:23:28 pm
    Author     : ACER
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Parcel Tracking System | Login</title>

    <!-- Bootstrap 5 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>

    body{
        background: linear-gradient(135deg,#ffd6e7,#fff5fa);
        height:100vh;
        display:flex;
        justify-content:center;
        align-items:center;
        font-family:'Segoe UI', Arial, Helvetica, sans-serif;
    }


    .login-card{

        width:420px;
        border:none;
        border-radius:25px;
        background:white;
        box-shadow:0px 15px 35px rgba(255, 105, 150, 0.25);
        overflow:hidden;

    }


    .login-header{

        text-align:center;
        padding:30px 20px 15px;

    }


    .login-header h1{

        font-size:55px;
        margin-bottom:10px;

    }


    .login-header h2{

        font-weight:700;
        color:#d63384;
        font-size:28px;

    }


    .login-header p{

        color:#999 !important;

    }


    .card-body{

        padding:30px;

    }


    label{

        font-weight:600;
        color:#555;

    }


    .form-control{

        border-radius:12px;
        border:1px solid #f3b6d2;
        padding:12px;

    }


    .form-control:focus{

        border-color:#d63384;
        box-shadow:0 0 0 0.2rem rgba(214,51,132,.15);

    }


    .btn-login{

        width:100%;
        border-radius:12px;
        background:#d63384;
        border:none;
        padding:12px;
        font-weight:bold;
        transition:0.3s;

    }


    .btn-login:hover{

        background:#b02a6b;
        transform:translateY(-2px);

    }


    hr{

        margin:25px 0;
        color:#f5b7d2;

    }


    a{

        color:#d63384;
        font-weight:600;
        text-decoration:none;

    }


    a:hover{

        color:#b02a6b;
        text-decoration:underline;

    }

</style>

</head>

<body>

<div class="card login-card">

    <div class="login-header">

        <h1>📦</h1>

        <h2>Parcel Tracking System</h2>

        <p class="text-muted">
            Login to continue
        </p>

    </div>

    <div class="card-body">

        <form action="LoginServlet" method="post">

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

                <label>Password</label>

                <input
                    type="password"
                    name="password"
                    class="form-control"
                    placeholder="Enter Password"
                    required>

            </div>

            <button
                type="submit"
                class="btn btn-primary btn-login">

                Login

            </button>

        </form>

        <hr>

        <div class="text-center">

            Don't have an account?

            <a href="register.jsp">

                Register

            </a>

        </div>

    </div>

</div>

</body>

</html>
