<%@ page import="Service.UserService" %>
<%@ page import="Model.Student" %>
<%@ page import="java.util.List" %>
<%@ page import="java.io.PrintWriter" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="Service.UserService.SessionChecker" %>
<%
    SessionChecker sessionChecker = new SessionChecker();
    sessionChecker.checkSession(request, response);
%>
<html>
<head>
    <meta charset="UTF-8">
    <title>${details.btitle} | Library Management System</title>
    <link rel="shortcut icon" type="image/jpg" href="..CSS/images/LM.ico"/>
    <link href='https://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="CSS/styles.css">
    <script src="https://kit.fontawesome.com/6f3a65e23d.js" crossorigin="anonymous"></script>
</head>
<body>
<header>
    <div class="pagehead">
        <div class="headtitles" id="headtitlesfix">
            <div class="one">
                <a href="user?page=home" style="text-decoration: none">
                    <ul>
                        <li><img src="${pageContext.request.contextPath}/CSS/images/LMB.png"
                                 id="logo"></li>
                        <li id="name">LIBRI<br><span id="mahiti">MAHITI</span></li>
                    </ul>
                </a>
            </div>
            <div class="two">
                <ul>
                    <a href="user?page=browse" style="text-decoration: none">
                        <li class="browselink" id="browse"><span>BROWSE</span></li>
                    </a>
                    <li>
                        <form action="user?page=userbsearch" method="post">
                            <input type="search" name="query" id="search" placeholder="Search...">
                            <button type="submit" class="search_button"><i class="fas fa-search"></i></button>
                        </form>
                    </li>

                    <li class="headlink"><a href="user?page=profile" id="profileprofile">My Profile</a></li>
                </ul>
            </div>
        </div>
    </div>
</header>
<div class="book-info-box">

    <div class="book-info-form">
        <div class="book-image-reservation">
            <div class="book-image-container">
                    <img class="book-image" src="data:image/png;base64,${details.bimage}">

            </div>
       <a href="user?page=wishlist&title=${details.btitle}" class="reservation-button" onclick="on()">Add to wish list</a>

        </div>
        <div class="book-details">
            <div>
                <h2>Title</h2>
                <p>${details.btitle}</p>
            </div>
            <div>
                <h2>Author</h2>
                <p>${details.bauthor}</p>
            </div>
            <div>
                <h2>ISBN</h2>
                <p>${details.bisbn}</p>
            </div>
            <div>
                <h2>Publisher</h2>
                <p>${details.bpublisher}r</p>
            </div>
            <div>
                <h2>Publication Year</h2>
                <p>${details.bpubyear}</p>
            </div>
            <div>
                <h2>Genre</h2>
                <p>${details.bgenre}</p>
            </div>
            <div>
                <h2>Language</h2>
                <p>${details.blanguage}</p>
            </div>
            <div>
                <h2>Location</h2>
                <p>${details.blocation}</p>
            </div>
            <div>
                <h2>Synopsis</h2>
                <p>${details.bsynopsis}</p>
            </div>
        </div>

    </div>

</div>
<div class="container" id="notification">
    <div class="notifcard" id="registercard">
        <br>
        <a class="signup">Book Added to the wishlist!</a>
        <button class="enter" onclick="off()">OK</button>
    </div>
    <div id="shadowlayern" onclick="off()"></div>
</div>
<script>
    function on() {
        document.getElementById("notification").style.display = "block";
    }

    function off() {
        document.getElementById("notification").style.display = "none";
    }
</script>
</body>
</html>
