<%@ page import="Model.Student" %>
<%@ page import="java.util.List" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Service.AdminService" %>
<%@ page import="Service.AdminService.SessionChecker" %>
<%@ page import="Service.UserService" %>
<%
    SessionChecker sessionChecker = new SessionChecker();
    sessionChecker.checkSession(request, response);
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Issued Books | Library Management System</title>
    <link rel="shortcut icon" type="image/jpg" href="CSS/images/LM.ico"/>
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
                    <img src="CSS/images/LMB.png" id="logo"></li>
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
                    <div class="User-profile-heading">Issued Books</div>
                    <br>
                    <div class="User-profile-display displaytable">
                        <table>
                            <colgroup>
                                <col span="1" style="width: 5%;">
                                <col span="1" style="width: 60%;">
                                <col span="1" style="width: 25%;">
                                <col span="1" style="width: 10%;">
                            </colgroup>
                            <thead>
                            <tr>
                                <th></th>
                                <th>Title</th>
                                <th>Book Holder</th>
                                <th>Action</th>
                            </tr>
                            </thead>
                            <tbody id="paginated-list" data-current-page="1" aria-live="polite">
                            <%
                                PrintWriter printt = response.getWriter();
                                List<Student> issuedbookList = new AdminService().getIssuedBookList();
                                System.out.println("Issue Count: " + issuedbookList.size());
                                int sn = 1;
                                for (Student student : issuedbookList) {
                                    Student book = new AdminService().getBook(student.getBookID());
                                    Student user = new UserService().getUser(student.getId());

                            %>
                            <tr>
                                <td style="float:right; height: 24px;"><%=sn%>.
                                </td>
                                <td><%=book.getTitle()%>
                                </td>
                                <td><%=user.getUserName()%>
                                </td>
                                <td style="text-align: center;">
                                    <div><img src="${pageContext.request.contextPath}/CSS/images/icons/checkmark.svg"
                                              class="manage unlock" title="Book Returned"
                                              style="float:right; height: 24px;">
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
</div>
<div class="container" id="notification">
    <div class="notifcard" id="registercard">
        <br>
        <a class="signup">Book Added</a>
        <button class="enter" onclick="off()">OK</button>
    </div>
    <div id="shadowlayern" onclick="off()"></div>
</div>
<script src="CSS/paginationscript.js"></script>
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