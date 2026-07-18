<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Track Order | Lovelle</title>
    <style>
        /* Base Reset & Variables */
        :root {
            --bg-color: #fcf7f7;
            --text-dark: #3e2723;
            --accent-pink: #d88c9a;
            --success-green: #00c853;
            --white: #ffffff;
            --border-light: #f0e6e6;
        }
        body {
            font-family: 'Helvetica Neue', Arial, sans-serif;
            background-color: var(--bg-color);
            color: var(--text-dark);
            margin: 0;
            padding: 0;
        }

        /* Navbar (Placeholder) */
        header {
            display: flex;
            justify-content: space-between;
            padding: 20px 40px;
            background-color: var(--white);
            border-bottom: 1px solid var(--border-light);
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

        /* Main Tracker Container */
        .tracker-container {
            background: var(--white);
            border-radius: 20px;
            padding: 40px;
            max-width: 800px;
            margin: 40px auto;
            box-shadow: 0 4px 15px rgba(0,0,0,0.03);
        }
        h2 { font-family: 'Georgia', serif; font-size: 24px; margin-bottom: 10px; }
        .subtitle { color: #888; font-size: 14px; margin-bottom: 30px; }

        /* Search Form */
        .search-bar {
            display: flex;
            gap: 15px;
            margin-bottom: 40px;
        }
        .search-input {
            flex: 1;
            padding: 15px 20px;
            border: 1px solid var(--border-light);
            border-radius: 30px;
            font-size: 14px;
        }
        .btn-locate {
            background-color: var(--text-dark);
            color: var(--white);
            border: none;
            padding: 15px 30px;
            border-radius: 30px;
            font-weight: bold;
            cursor: pointer;
            transition: 0.3s;
        }
        .btn-locate:hover { background-color: #5d4037; }

        /* Active Status Card */
        .status-card {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 20px;
            background-color: #fafafa;
            border: 1px solid var(--border-light);
            border-radius: 10px;
            margin-bottom: 40px;
        }
        .status-item span { display: block; font-size: 12px; color: #888; text-transform: uppercase; margin-bottom: 5px;}
        .status-item strong { font-size: 16px; }
        .pill-delivered {
            background-color: var(--success-green);
            color: white;
            padding: 5px 15px;
            border-radius: 20px;
            font-size: 14px;
            font-weight: bold;
            text-transform: uppercase;
        }
        .pill-pending { background-color: #ffb300; /* Styles for other statuses */ }

        /* Progress Bar */
        .progress-container {
            display: flex;
            justify-content: space-between;
            position: relative;
            margin-bottom: 50px;
            padding: 0 20px;
        }
        .progress-container::before {
            content: '';
            position: absolute;
            top: 50%;
            left: 40px;
            right: 40px;
            height: 3px;
            background-color: var(--accent-pink);
            z-index: 1;
        }
        .step {
            position: relative;
            z-index: 2;
            text-align: center;
            background: var(--white);
            padding: 0 10px;
        }
        .step-icon {
            width: 35px;
            height: 35px;
            border-radius: 50%;
            background-color: var(--accent-pink);
            color: white;
            display: flex;
            align-items: center;
            justify-content: center;
            margin: 0 auto 10px auto;
        }
        .step.active .step-icon { background-color: var(--text-dark); }
        .step-label { font-size: 11px; font-weight: bold; color: #888; text-transform: uppercase; }

        /* Timeline History */
        .timeline {
            border-top: 1px solid var(--border-light);
            padding-top: 30px;
        }
        .timeline-header {
            font-size: 12px;
            font-weight: bold;
            text-transform: uppercase;
            margin-bottom: 20px;
            color: var(--text-dark);
        }
        .timeline-item {
            display: flex;
            margin-bottom: 25px;
            position: relative;
        }
        .timeline-dot {
            width: 12px;
            height: 12px;
            background-color: var(--accent-pink);
            border-radius: 50%;
            margin-top: 5px;
            margin-right: 20px;
        }
        .timeline-content { flex: 1; }
        .timeline-title { font-weight: bold; font-size: 14px; margin-bottom: 5px; text-transform: uppercase; }
        .timeline-desc { font-size: 14px; color: #666; margin: 2px 0; }
        .timeline-time {
            font-size: 12px;
            color: #888;
            text-align: right;
            min-width: 150px;
        }
        .error-msg { color: red; margin-bottom: 20px; }
    </style>
</head>
<body>

    <header>

<div class="logo">
    Lovelle
</div>

<div class="nav-links">

<%

if(session.getAttribute("userID")==null){

%>

    <a href="trackOrder.jsp" class="active">
        Track Parcel
    </a>

    <a href="login.jsp">
        Login
    </a>

    <a href="login.jsp?redirect=customerService">
        Customer Service
    </a>

<%

}else{

%>

    <a href="customerDashboard.jsp">
        Home
    </a>

    <a href="trackOrder.jsp" class="active">
        Track Parcel
    </a>

    <a href="customerService.jsp">
        Customer Service
    </a>

    <a href="myProfile.jsp">
        My Profile
    </a>

    <a href="LogoutServlet">
        Logout
    </a>

<%

}

%>

</div>

</header>

    <!-- Tracker Interface -->
    <div class="tracker-container">
        <h2>Instant Parcel Tracker</h2>
        <p class="subtitle">Enter your unique Lovelle tracking code to locate your shipment.</p>

        <!-- Search Form -->
        <form action="${pageContext.request.contextPath}/TrackingServlet" method="POST" class="search-bar">
            <input type="text" name="trackingNumber" class="search-input" placeholder="e.g. TRK-BLUSH-59281" required>
            <button type="submit" class="btn-locate">LOCATE SHIPMENT</button>
        </form>

        <c:if test="${not empty error}">
            <div class="error-msg">${error}</div>
        </c:if>

        <!-- Result Display Area -->
        <c:if test="${not empty trackingData}">
            
            <!-- Summary Card -->
            <div class="status-card">
                <div class="status-item">
                    <span>Active Parcel Code</span>
                    <strong>${trackingData.trackingNumber}</strong>
                </div>
                <div class="status-item">
                    <!-- Dynamic Pill based on ENUM Status -->
                    <c:choose>
                        <c:when test="${trackingData.currentStatus == 'Delivered'}">
                            <div class="pill-delivered"> ${trackingData.currentStatus}</div>
                        </c:when>
                        <c:when test="${trackingData.currentStatus == 'Lost'}">
                            <div style="background:#dc3545;color:white;padding:5px 15px;border-radius:20px;"> ${trackingData.currentStatus} </div>
                        </c:when>
                        <c:otherwise>
                            <div class="pill-pending"> ${trackingData.currentStatus}</div>
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="status-item">
                    <span>Estimated Delivery</span>
                    <strong><fmt:formatDate value="${trackingData.estimatedDate}" pattern="MMM dd, yyyy" /></strong>
                </div>
            </div>

            <!-- Progress Bar (Visual representation) -->
            <div class="progress-container">
                <div class="step"><div class="step-icon">📋</div><div class="step-label">Registered</div></div>
                <div class="step"><div class="step-icon">📦</div><div class="step-label">Dispatched</div></div>
                <div class="step"><div class="step-icon">🚚</div><div class="step-label">In Transit</div></div>
                <div class="step"><div class="step-icon">🏢</div><div class="step-label">Out for Delivery</div></div>
                <div class="step ${trackingData.currentStatus == 'Delivered' ? 'active' : ''}">
                    <div class="step-icon">✔️</div><div class="step-label">Delivered</div>
                </div>
            </div>

            <!-- Detailed Transit History -->
            <div class="timeline">
                <div class="timeline-header">Shipment Checkpoints & Transit History</div>
                
                <c:forEach var="checkpoint" items="${trackingData.checkpoints}">
                    <div class="timeline-item">
                        <div class="timeline-dot"></div>
                        <div class="timeline-content">
                            <!-- Assuming the first word/sentence of remark acts as the Title in your mockup -->
                            <div class="timeline-desc"><strong>Location:</strong> ${checkpoint.location}</div>
                            <div class="timeline-desc"><em>${checkpoint.remark}</em></div>
                        </div>
                        <div class="timeline-time">
                            <fmt:formatDate value="${checkpoint.updateTime}" pattern="M/d/yyyy, h:mm:ss a" />
                        </div>
                    </div>
                </c:forEach>
            </div>

        </c:if>
    </div>

</body>
</html>