<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Library Management System | Home</title>
    <link rel="shortcut icon" type="image/jpg" href="CSS/images/LM.ico"/>
    <link href='https://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>

    <script src="https://kit.fontawesome.com/6f3a65e23d.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="CSS/styles.css">
    <style>
        #loginlogin {
            color: #e35200;
            cursor: pointer;
        }

        #signupsignup {
            background-image: linear-gradient(to right top, #e37100, #e36a00, #e36201, #e35a02, #e35205);
            color: #fbf6f3;
            cursor: pointer;
        }

        #profileprofile{
            text-decoration: none;
            color: #e35200;
            cursor: pointer;
        }
    </style>
</head>

<body>
<!--welcome face-->
<header>
    <div class="pagehead" id="pagehead">

        <!--nav bar-->
        <div class="headtitles">
            <div class="one">
                <ul>
                    <li><a href="user?page=home"><img src="CSS/images/LMB.png" id="logo"></a></li>
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
                    <%
                        if (session.getAttribute("email") == null) {
                    %>
                    <div class="toshow">
                        <li class="headlink" id="loginlogin" onclick="onl()">LOGIN</li>
                        <li id="signupsignup" class="headlink" onclick="ons()">SIGN UP</li>
                    </div>
                    <%
                    } else {
                    %>
                    <div class="toshow">
                        <li class="headlink"><a href="user?page=profile" id="profileprofile">My Profile</a></li>
                    </div>
                    <%}%>
                </ul>
            </div>
        </div>

        <!--slogan and stuff-->
        <div class="slg">
            <p id="king">Books are the windows to the world,<br>and a library is the gateway to endless
                possibilities.</p>
            <p id="ful">Expand your horizons and explore new worlds with just a few clicks.<br>The possibilities are
                endless, and the journey starts with a visit to our library website portal.</p>
        </div>
        <!--welcome box-->
        <div class="welc">
            <div class="welcbox">
                <div class="welchead">Welcome</div>
                <div class="welctxt">Discover new stories and explore
                    different perspectives starting with just a few clicks on your device. Once you have found your
                    desired
                    titles, simply make a reservation and collect them from our library location at your
                    convenience. Start your literary journey today with our online portal.
                </div>
            </div>
        </div>
    </div>
</header>

<!--body-->
<div>
    <!--"popular section" heading-->
    <div class="labels">
        <div class="labelheading">
            Popular Books
        </div>
        <div class="labelsubheading">
            Browse our selection of popular books
        </div>
    </div>
    <!--"popular section" images-->
    <div class="displaybox">
        <div class="hpbooks">
            <ul>
                <li>
                    <div class="hpimage"> <!--link this to book info page-->
                        <div class="bimage"><!--insert book imge here--></div>
                        <div class="bname">Placeholder name but wat if it overflowed<!--insert book name here-->
                        </div>
                    </div>
                </li>
                <li>
                    <div class="hpimage">
                        <div class="bimage"></div>
                        <div class="bname">Placeholder name and it did overflow<!--insert book name here--></div>
                    </div>
                </li>
                <li>
                    <div class="hpimage">
                        <div class="bimage"></div>
                        <div class="bname">Placeholder name and it didnt screw me over<!--insert book name here-->
                        </div>
                    </div>
                </li>
                <li>
                    <div class="hpimage">
                        <div class="bimage"></div>
                        <div class="bname">Placeholder name and now what<!--insert book name here--></div>
                    </div>
                </li>
            </ul>
        </div>
        <p class="seemore">See more...</p><!--hyperlink to "popular books"-->
    </div>
    <!--"new section" heading-->
    <div class="labels">
        <div class="labelheading">
            New Books
        </div>
        <div class="labelsubheading">
            Browse our newest additions
        </div>
    </div>
    <!--"popular section" images-->
    <div class="displaybox">
        <div class="hpbooks">
            <ul>
                <li>
                    <div class="hpimage"> <!--link this to book info page-->
                        <div class="bimage"><!--insert book imge here--></div>
                        <div class="bname">Placeholder name but wat if it overflowed<!--insert book name here-->
                        </div>
                    </div>
                </li>
                <li>
                    <div class="hpimage">
                        <div class="bimage"></div>
                        <div class="bname">Placeholder name and it did overflow<!--insert book name here--></div>
                    </div>
                </li>
                <li>
                    <div class="hpimage">
                        <div class="bimage"></div>
                        <div class="bname">Placeholder name and it didnt screw me over<!--insert book name here-->
                        </div>
                    </div>
                </li>
                <li>
                    <div class="hpimage">
                        <div class="bimage"></div>
                        <div class="bname">Placeholder name and now what<!--insert book name here--></div>
                    </div>
                </li>
            </ul>
        </div>
        <p class="seemore">See more...</p><!--hyperlink to "popular books"-->
    </div>
</div>

<!--footer-->
<footer>
    <div class="newsletterbox">
        <!--newsletter orange box-->
        <div class="newssub">
            <div class="labelheading">
                Subscribe to our newsletter
            </div>

            <div class="newsletterform">
                <form action="user?page=subscribe" method="post">
                    <input type="text" class="nlfinput" name="nlname" placeholder="Name..">
                    <input type="text" class="nlfinput" name="nlemail" placeholder="email@email.com">
                    <input type="submit" id="nlfsubmit" name="nlsend" value="Subscribe">
                </form>
            </div>
        </div>

        <!--white information box-->
        <div class="bottomtext">
            <!--first box-->
            <div class="btbox">
                <img src="CSS/images/LMB.png" id="logo2nd"><br>
                Library Manager gives you the options to find the books you need.
            </div>
            <!--second box-->
            <div class="btbox">
                <div class="btboxheading">
                    Company
                </div>
                <div class="btboxitems">
                    About
                </div>
                <div class="btboxitems">
                    Features
                </div>
            </div>
            <!--third box-->
            <div class="btbox">
                <div class="btboxheading">
                    Help
                </div>
                <div class="btboxitems">
                    Customer Support
                </div>
                <div class="btboxitems">
                    Terms and conditions
                </div>
                <div class="btboxitems">
                    Privacy Policy
                </div>
            </div>
        </div>
    </div>
</footer>
<!-- popup box -->
<!-- register -->
<br>
<div class="container" id="signupoverlay">
    <div class="card" id="registercard">
        <br>
        <form action="user?page=register" method="post">
            <a class="signup">SIGN UP</a>
            <div class="inputBox1">
                <input type="text" name="email" required="required">
                <span class="user">Email</span>
            </div>

            <div class="inputBox">
                <input type="text" name="username" required="required">
                <span>Username</span>
            </div>

            <div class="inputBox">
                <input type="password" name="password" required="required">
                <span>Password</span>
            </div>

            <button class="enter">Sign In</button>
        </form>

    </div>
    <div id="shadowlayerr" onclick="offs()"></div>
</div>

<br>
<!-- login -->
<div class="container" id="loginoverlay">

    <div class="card" id="logincard">
        <a class="login">Log in</a>

        <form action="user?page=login" method="post">
            <div class="inputBox">
                <input type="text" name="email" required="required">
                <span>Email</span>
            </div>

            <div class="inputBox">
                <input type="password" name="password" required="required">
                <span>Password</span>
            </div>

            <button class="enter">Login</button>
        </form>
    </div>
    <div id="shadowlayerl" onclick="offl()"></div>
</div>
<script>
    function ons() {
        document.getElementById("signupoverlay").style.display = "block";
    }

    function offs() {
        document.getElementById("signupoverlay").style.display = "none";
    }

    function onl() {
        document.getElementById("loginoverlay").style.display = "block";
    }

    function offl() {
        document.getElementById("loginoverlay").style.display = "none";
    }


</script>
</body>

</html>