<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 4/3/2023
  Time: 11:49 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <title>Library Management System | Home</title>
  <link rel="shortcut icon" type="image/jpg" href="${pageContext.request.contextPath}/CSS/images/LM.ico"/>
  <link href='https://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>
  <script src="https://kit.fontawesome.com/6f3a65e23d.js" crossorigin="anonymous"></script>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/styles.css">
  <script src="https://kit.fontawesome.com/6f3a65e23d.js" crossorigin="anonymous"></script>
</head>

<body>
<!--welcome face-->
<header>
  <div class="pagehead">
    <div class="headtitles" id="headtitlesfix">
      <div class="one">
        <ul>
          <li><a href="user?page=home"><img src="${pageContext.request.contextPath}/CSS/images/LMB.png" id="logo"></a></li>
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
<section >
  <div class="container" >
    <div class="user-info-container" >
      <div class="user-info-container-sub">
        <div class="User-profile-heading">Profile</div><br>
        <div class="User-profile-display">
          <div class="user-info">
            <div>Username</div>
            <div class="unchangable">${username}</div>
            <div>Email</div>
            <div class="unchangable">${email}</div>
            <div>Password</div>
            <div class="unchangable">●●●●●●●●</div>
          </div>
          <div id="changepw" onclick="on()">Change Password</div>
        </div>
      </div>
    </div>
  </div>

  <div class="container">
    <div class="user-info-container">
      <div class="user-info-container-sub">
        <div class="User-profile-heading">Books with me</div><br>
        <div class="User-profile-display displaytable">
          <table>
            <colgroup>
              <col span="1" style="width: 5%;">
              <col span="1" style="width: 70%;">
              <col span="1" style="width: 25%;">
            </colgroup>
            <thead>
            <tr>
              <th> </th>
              <th>Title</th>
              <th>Author</th>
            </tr>
            </thead>
            <tbody>
            <tr>
              <td>1.</td>
              <td><div class="item">JJBA</div></td>
              <td><div class="item">Araki</div></td>
            </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>

  <div class="container" >
    <div class="user-info-container">
      <div class="user-info-container-sub">
        <div class="User-profile-heading">My Reservations</div><br>
        <div class="User-profile-display displaytable">
          <table>
            <colgroup>
              <col span="1" style="width: 5%;">
              <col span="1" style="width: 49%;">
              <col span="1" style="width: 23%;">
              <col span="1" style="width: 17%;">
              <col span="1" style="width: 6%;">
            </colgroup>
            <thead>
            <tr>
              <th> </th>
              <th>Title</th>
              <th>Author</th>
              <th>Status</th>
              <th></th>
            </tr>
            </thead>
            <tbody>
            <tr>
              <td>1.</td>
              <td><div class="item">JJBA2</div></td>
              <td><div class="item">Araki</div></td>
              <td>Unavailable</td>
              <td style="text-align: center;">
                <div><img src="${pageContext.request.contextPath}/CSS/images/icons/delet.svg" class="manage lock" title="Delete Subscriber" style="float:left;">
                </div>
              </td>
            </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>

  <div id="containerl">
    <a href="user?page=logout" id="logout" style="text-decoration: none">Log Out</a>
  </div>
</section>
<div class="container" id="notification" >
  <div class="pwcard" id="registercard">
    <br>
    <a class="signup">Change Password</a>
    <form action="user?page=changepassword" method="post">
      <div class="inputBox">
        <input type="password" name="oldpassword" required="required">
        <span>Old Password</span>
      </div>
      <div class="inputBox">
        <input type="password" name="newpassword" required="required">
        <span>New Password</span>
      </div>
      <button class="enter" onclick="off()">Save</button>
    </form>
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
