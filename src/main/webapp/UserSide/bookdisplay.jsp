<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 4/6/2023
  Time: 1:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>User List | Library Management System</title>
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
                <ul>
                    <li><a href="user?page=home"><img src="${pageContext.request.contextPath}/CSS/images/LMB.png"
                                                      id="logo"></a></li>
                </ul>
            </div>
            <div class="two">
                <ul>
                    <li>
                        <form>
                            <input type="search" name="search" id="search" placeholder="Search...">
                            <button type="submit" class="search_button"><i class="fas fa-search"></i></button>
                        </form>
                    </li>

                    <li class="headlink" id="userprofile">My Profile</li>
                </ul>
            </div>
        </div>
    </div>
</header>
<div class="book-info-box">
    <div class="book-info-form">
        <div class="book-image-reservation">
            <div class="book-image-container">
                <img class="book-image" src="" alt="Book Cover"><br>
            </div>
            <button class="reservation-button" onclick="on()">Reservation</button>
        </div>
        <div class="book-details">
            <div>
                <h2>Title</h2>
                <p>Lorem ipsum dolor sit amet</p>
            </div>
            <div>
                <h2>Author</h2>
                <p>John Doe</p>
            </div>
            <div>
                <h2>ISBN</h2>
                <p>978-3-16-148410-0</p>
            </div>
            <div>
                <h2>Publisher</h2>
                <p>Example Publisher</p>
            </div>
            <div>
                <h2>Publication Year</h2>
                <p>2022</p>
            </div>
            <div>
                <h2>Genre</h2>
                <p>Fiction</p>
            </div>
            <div>
                <h2>Language</h2>
                <p>English</p>
            </div>
            <div>
                <h2>Location</h2>
                <p>New York, NY</p>
            </div>
            <div>
                <h2>Synopsis</h2>
                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed blandit tortor quam, a lacinia elit
                    faucibus sed. Nullam sodales pulvinar lorem, vitae iaculis sapien fermentum sit amet. Sed eget
                    libero nisl. Duis vel felis sed ante congue volutpat sit amet sit amet ex. Vivamus bibendum orci
                    nulla, vitae posuere lorem elementum eget. Praesent congue est vitae est interdum, nec laoreet nibh
                    eleifend. Nam et aliquam mi. Nullam eu massa vel enim posuere ultrices eget at lectus. Curabitur
                    aliquam bibendum eros ac pharetra. Sed vel ipsum sed libero sodales suscipit eget sit amet arcu.
                    Fusce in dapibus urna, vitae rhoncus magna. Nam dignissim, odio vel hendrerit efficitur, risus ante
                    ultrices mi, in dignissim odio tellus quis eros. Sed id nulla risus. </p>
            </div>
        </div>
    </div>
</div>
<div class="container" id="notification">
    <div class="notifcard" id="registercard">
        <br>
        <a class="signup">Book Registered!</a>
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
