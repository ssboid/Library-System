<%@ page import="Service.UserService.SessionChecker" %>
<%@ page import="Service.UserService" %>
<%@ page import="Model.Student" %>
<%@ page import="java.util.List" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="Service.AdminService" %>
<%
    SessionChecker sessionChecker = new SessionChecker();
    sessionChecker.checkSession(request, response);
%>
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

                    <li class="headlink" id="userprofile">My Profile</li>
                </ul>
            </div>
        </div>
    </div>
</header>
<section>
    <div class="container">
        <div class="user-info-container">
            <div class="user-info-container-sub">
                <div class="User-profile-heading">Profile</div>
                <br>
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
                <div class="User-profile-heading">Books with me</div>
                <br>
                <div class="User-profile-display displaytable">
                    <table>
                        <colgroup>
                            <col span="1" style="width: 5%;">
                            <col span="1" style="width: 70%;">
                            <col span="1" style="width: 25%;">
                        </colgroup>
                        <thead>
                        <tr>
                            <th></th>
                            <th>Title</th>
                            <th>Author</th>
                        </tr>
                        <%
                            PrintWriter printt = response.getWriter();
                            List<Student> issuedbookList = new AdminService().getIssuedBookList();
                            System.out.println("Issue Count: " + issuedbookList.size());
                            int sn = 1;
                            for (Student student : issuedbookList) {
//                                if(session.getAttribute("uid"==))                            }
                                Student book = new AdminService().getBook(student.getBookID());
                                book.getGenre();

                        %>
                        </thead>
                        <tbody>
                        <tr>
                            <td><%=sn%></td>
                            <td>
                                <div class="item"><%=book.getTitle()%></div>
                            </td>
                            <td>
                                <div class="item"><%=book.getGenre()%></div>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>

    <div class="container">
        <div class="user-info-container">
            <div class="user-info-container-sub">
                <div class="User-profile-heading">My Wishlist</div>
                <br>
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
                            <th></th>
                            <th>Title</th>
                            <th>Author</th>
                            <th>Status</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        <%
                            HttpSession session2 = request.getSession();
                            List<Student> bookList = new UserService().getwishlist(session2);
                            int sn = 1;
                            for (Student student : bookList) {
                        %>
                        <tr>
                            <td><%=sn%>
                            </td>
                            <td><a href="user?page=getbook&id=<%=student.getId()%>"
                                   class="infolink"><%= student.getTitle() %>
                            </a></td>
                            <td><a href="user?page=getbookauthor&query=<%=student.getAuthor()%>"
                                   class="infolink"><%= student.getAuthor() %>
                            </a></td>
                            <td><%= student.getStatus() %>
                            </td>
                            <td style="text-align: center;">
                                <div><a href="user?page=deletewl&id=<%=student.getWid()%>"><img
                                        src="${pageContext.request.contextPath}/CSS/images/icons/delet.svg"
                                        class="manage lock" title="Delete Subscriber" style="float:left;">
                                </a></div>
                            </td>
                        </tr>
                        <%
                                sn = sn + 1;
                            }
                        %>
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
<div class="container" id="notification">
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
