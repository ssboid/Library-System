<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Add Book | Library Management System</title>
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
        <div class="container">
            <div class="user-info-container">
                <div class="user-info-container-sub">
                    <div class="User-profile-heading">Issue a Book</div>
                    <br>
                    <div class="User-profile-display displaytable">
                        <form action="admin?page=addbook" method="post" enctype="multipart/form-data">
                            <div class="inputsection">
                                <label for="title">Title:</label><br>
                                <input type="text" class="input-box" id="title" name="title" required><br>
                            </div>
                            <div class="inputsection">
                                <label for="author">Author:</label><br>
                                <input type="text" class="input-box" id="author" name="author" required><br>
                            </div>
                            <div class="inputsection">
                                <label for="isbn">ISBN:</label><br>
                                <input type="number" pattern="[0-9]" class="input-box" id="isbn" name="isbn"
                                       required><br>
                            </div>
                            <div class="inputsection">
                                <label for="publisher">Publisher:</label><br>
                                <input type="text" class="input-box" id="publisher" name="publisher" required><br>
                            </div>
                            <div class="inputsection inputsection-sub" style="margin-bottom: 0px;">
                                <div class="compact">
                                    <label for="publication_year">Publication Year:</label>
                                </div>
                                <div class="compact">
                                    <label for="genre">Genre:</label>
                                </div>
                                <div class="compact">
                                    <label for="number_of_pages">Number of Pages:</label>
                                </div>
                                <div class="compact">
                                    <label for="language">Language:</label>
                                </div>
                            </div>
                            <div class="inputsection inputsection-sub">
                                <input type="number" pattern="[0-9]" class="input-box" id="publication_year"
                                       name="publication_year"
                                       required>
                                <select class="input-box compactf" id="genre" name="genre" style="width: 21.5%;"
                                        required>
                                    <option value="" disabled selected>-- Select a genre --</option>
                                    <option value="Adventure">Adventure</option>
                                    <option value="Art">Art</option>
                                    <option value="Children">Children</option>
                                    <option value="Cookbook">Cookbook</option>
                                    <option value="Contemporary">Contemporary</option>
                                    <option value="Development">Development</option>
                                    <option value="Dystopian">Dystopian</option>
                                    <option value="Families & Relationship">Families & Relationship</option>
                                    <option value="Fantasy">Fantasy</option>
                                    <option value="Guide">Guide / How-to</option>
                                    <option value="Health">Health</option>
                                    <option value="Historical Fiction">Historical Fiction</option>
                                    <option value="History">History</option>
                                    <option value="Horror">Horror</option>
                                    <option value="Humor">Humor</option>
                                    <option value="Memoir">Memoir</option>
                                    <option value="Motivational">Motivational</option>
                                    <option value="Mystery">Mystery</option>
                                    <option value="Paranormal">Paranormal</option>
                                    <option value="Romance">Romance</option>
                                    <option value="Science Fiction">Science Fiction</option>
                                    <option value="Self-Help">Self-help</option>
                                    <option value="Thriller">Thriller</option>
                                    <option value="Travel">Travel</option>
                                </select>
                                <input type="number" pattern="[0-9]" class="input-box compactf" id="number_of_pages"
                                       name="number_of_pages"
                                       style="width: 22%;" required>
                                <input type="text" class="input-box compactf" id="language" name="language"
                                       style="width: 25%;"
                                       required>
                            </div>
                            <div class="inputsection">
                                <label for="location">Location:</label><br>
                                <input type="text" class="input-box" id="location" name="location" required><br>
                            </div>
                            <div class="inputsection">
                                <label for="synopsis">Synopsis:</label><br>
                                <textarea id="synopsis" name="synopsis" rows="5"></textarea><br>
                            </div>
                            <div class="inputsection">
                                <label for="cover_image"></label><br>
                                <input type="file" id="cover_image" name="cover_image" accept="image/*">
                            </div>
                            <img id="preview" src="#" alt="Preview Image"><br>
                            <input type="submit" value="FINISH" id="add" title="Add a book into the registry"
                                   onclick="on()">
                        </form>
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
<%--        <button class="enter" onclick="off()">OK</button>--%>
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
    const fileInput = document.getElementById('cover_image');
    const preview = document.getElementById('preview');

    fileInput.addEventListener('change', function () {
        const file = this.files[0];
        if (file) {
            const reader = new FileReader();
            reader.addEventListener('load', function () {
                preview.src = reader.result;
                preview.style.display = 'block';
            });
            reader.readAsDataURL(file);
        }
    });
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