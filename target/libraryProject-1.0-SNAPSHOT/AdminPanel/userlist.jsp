<%@ page import="Model.Student" %>
<%@ page import="java.util.List" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Service.AdminService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="Service.AdminService.SessionChecker" %>
<%
    SessionChecker sessionChecker = new SessionChecker();
    sessionChecker.checkSession(request, response);
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>User List | Library Management System</title>
    <link rel="shortcut icon" type="image/jpg" href="..CSS/images/LM.ico"/>
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
                    <li><img src="CSS/images/LMB.png" id="logo"></li>
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
        <section>
            <div class="container">
                <div class="user-info-container">
                    <div class="user-info-container-sub">
                        <div class="User-profile-heading">Users List</div>
                        <br>
                        <div class="searcher">
                            <form action="admin?page=usearch" method="post">
                                <input type="text" name="query" id="admsearchbox" placeholder="Search...">
                                <button type="submit" class="search_button"><i class="fas fa-search"></i></button>
                            </form>
                        </div>
                        <br>
                        <div class="User-profile-display displaytable">
                            <table>
                                <colgroup>
                                    <col span="1" style="width: 6%;">
                                    <col span="1" style="width: 50%;">
                                    <col span="1" style="width: 33%;">
                                    <col span="1" style="width: 11%;">
                                </colgroup>
                                <thead>
                                <tr>
                                    <th>UID</th>
                                    <th>Name</th>
                                    <th>Email</th>
                                    <th>Action</th>
                                </tr>
                                </thead>
                                <tbody id="paginated-list" data-current-page="1" aria-live="polite">
                                <%
                                    PrintWriter printt = response.getWriter();
                                    List<Student> userList = new AdminService().getAllUsers();
                                    int sn = 1;
                                    for (Student student : userList) {
                                %>
                                <tr>
                                    <td style="float:right; height: 24px;"><%=sn%>.
                                    </td>
                                    <td><%=student.getUserName()%>
                                    </td>
                                    <td><%=student.getEmail()%>
                                    </td>
                                    <td style="text-align: center;">
                                        <div><a href="admin?page=deleteuser&id=<%=student.getId()%>"><img
                                                src="${pageContext.request.contextPath}/CSS/images/icons/delet.svg"
                                                class="manage lock" title="Delete user"></a>
                                        </div>
                                    </td>

                                </tr>

                                <%

                                        sn = sn + 1;
                                    }
                                %>
                                </tbody>

                            </table>
                            <nav class="pagination-container">
                                <button class="pagination-button" id="prev-button" aria-label="Previous page"
                                        title="Previous page">
                                    &lt;
                                </button>

                                <div id="pagination-numbers">

                                </div>

                                <button class="pagination-button" id="next-button" aria-label="Next page"
                                        title="Next page">
                                    &gt;
                                </button>
                            </nav>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </section>
</div>
<script src="CSS/paginationscript.js"></script>
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
