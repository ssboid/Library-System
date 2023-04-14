<%@ page import="Service.UserService.SessionChecker" %>
<%
    SessionChecker sessionChecker = new SessionChecker();
    sessionChecker.checkSession(request, response);
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Books in ${genre}| Library Management System</title>
    <link rel="shortcut icon" type="image/jpg" href="${pageContext.request.contextPath}/CSS/images/LM.ico"/>
    <link href='https://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/styles.css">
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
<%--<div class="container" style="display: flex; flex-direction: column;">--%>
<%--    <div class="search-result-container">--%>
<%--        <div class="User-profile-heading">Displaying search results for {}</div>--%>
<%--    </div>--%>
<%--    <br>--%>
<%--    <div class="search-result-display displaytable">--%>
<%--    </div>--%>
<%--</div>--%>

<div class="container">
    <div class="user-info-container">
        <div class="user-info-container-sub">
            <c:if test="${not empty ubgsearchResults}">
                <c:choose>
                    <c:when test="${not empty genre}">
                        <p class="User-profile-heading">Books in ${genre}</p>
                    </c:when>
                    <c:otherwise>
                        <p class="User-profile-heading">Showing all books</p>
                    </c:otherwise>
                </c:choose>
            </c:if>

            <br>
            <div class="search-result-display displaytable">
                <table>
                    <colgroup>
                        <col span="1" style="width: 5%;">
                        <col span="1" style="width: 45%;">
                        <col span="1" style="width: 20%;">
                        <col span="1" style="width: 15%;">
                        <col span="1" style="width: 15%;">
                    </colgroup>
                    <thead>
                    <tr>
                        <th></th>
                        <th>Title</th>
                        <th>Author</th>
                        <th>Genre</th>
                        <th>Status</th>
                    </tr>
                    </thead>
                    <tbody id="paginated-list" data-current-page="1" aria-live="polite">
                    <c:forEach items="${ubgsearchResults}" var="student" varStatus="status">
                        <tr>
                            <td>${status.count}</td>
                            <td><a href="user?page=getbook&id=${student.id}" class="infolink">${student.title}</a></td>
                            <td><a href="user?page=getbookauthor&query=${student.author}" class="infolink">${student.author}</a></td>
                            <td><a href="user?page=getgenre&query=${student.genre}" class="infolink">${student.genre}</a></td>
                            <td>${student.status}</td>
                        </tr>
                    </c:forEach>

                    </tbody>

                </table>
                <c:if test="${empty ubgsearchResults}">
                    <p>Please enter a search query to see results.</p>
                </c:if>
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

</div>
<script src="${pageContext.request.contextPath}/CSS/paginationscript.js"></script>
</body>
</html>
