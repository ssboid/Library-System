<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 4/3/2023
  Time: 10:25 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <title>Issue Book | Library Management System</title>
  <link rel="shortcut icon" type="image/jpg" href="CSS/images/LM.ico" />
  <link href='https://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>
  <link rel="stylesheet" href="CSS/styles.css">
  <script src="https://kit.fontawesome.com/6f3a65e23d.js" crossorigin="anonymous"></script>
</head>

<body>
<!--welcome face-->
<header>
  <div class="pagehead">
    <div class="headtitles" id="headtitlesfix">
      <div class="one">
        <ul>
          <li><a href="homepage.html"><img src="CSS/images/LMB.png" id="logo"></a></li>
        </ul>
      </div>
      <div class="two">
        <ul>
          <li class="headlink" id="userprofile"><a href="admin?page=logout" id="logout"
                                                   style="text-decoration: none">Log Out</a></li>
        </ul>
      </div>
    </div>
  </div>
</header>

<div class="parent">
  <section class="admin-nav">
    <span id="adminid">Admin Panel</span>
    <ul id="navlist">
      <li>
        <div class="listitem collapsible">Manage Library</div>
        <ul class="collapseitem" style="width: 224px;">
          <a href="admin?page=gotoaddbook">
            <li class="listitem sublistitem">Add Book</li>
          </a>
          <a href="admin?page=gotomanagebook">
            <li class="listitem sublistitem">Manage Book</li>
          </a>
          <a href="admin?page=gotoissuebook">
            <li class="listitem sublistitem">Issue Book</li>
          </a>
          <a href="admin?page=gotoissuedbooks">
            <li class="listitem sublistitem">View Issued Books</li>
          </a>
        </ul>
      </li>
      <a href="admin?page=gotomanageuser">
        <li class="listitem">Manage Users</li>
      </a>
      <a href="admin?page=gotomanagesubs">
        <li class="listitem">Manage Subscribers</li>
      </a>
    </ul>
  </section>

  <section class="page">
    <div class="container">
      <div class="user-info-container">
        <div class="user-info-container-sub">
          <div class="User-profile-heading">Issue a Book</div><br>
          <div class="User-profile-display displaytable">
            <div class="User-profile-heading-lv2">Select a Book</div><br>
            <form action="borrowbook?page=issue" method="post">
              <div class="inputsection">
                <label for="title">Title:</label><br>
                <input type="text" class="input-box" id="title" name="title"  value="1984" required><br>
              </div>
              <div class="inputsection">
                <label for="author">Author:</label><br>
                <input type="text" class="input-box" id="author" name="author" value="George Orwell" required><br>
              </div>
              <br>
              <div class="User-profile-heading-lv2">Select a User</div>
              <br>
              <div class="inputsection">
                <label for="username">Borrower's Name:</label><br>
                <input type="text" class="input-box" id="username" name="username" value="Sunit" required><br>
              </div>
              <input type="submit" value="Issue" id="add" title="Issue A book" onclick="on()">
            </form>
          </div>
        </div>
      </div>
    </div>
  </section>
</div>

<div class="container" id="notification" >
  <div class="notifcard" id="registercard">
    <br>
    <a class="signup">Issued</a>
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
<script>
  var coll = document.getElementsByClassName("collapsible");
  var i;

  for (i = 0; i < coll.length; i++) {
    coll[i].addEventListener("click", function () {
      this.classList.toggle("active");
      var collapseitem = this.nextElementSibling;
      if (collapseitem.style.display === "block") {
        collapseitem.style.display = "none";
      } else {
        collapseitem.style.display = "block";
      }
    });
  }
</script>
</body>

</html>