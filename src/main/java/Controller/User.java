package Controller;

import java.io.*;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import Hashing.HashPassword;
import Model.Student;
import Service.UserService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "User", urlPatterns = "/user")
//@WebServlet(name = "helloServlet", urlPatterns = "/user")
public class User extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String action = request.getParameter("page");

        //login
        if (action.equalsIgnoreCase("login")) {
            String email = request.getParameter("email");
            String password = (HashPassword.hashPassword(request.getParameter("password")));
            Student student = new UserService().getUser(email, password);
            if (student != null) {
                HttpSession session = request.getSession();
                session.setAttribute("uid", student.getId());
                session.setAttribute("email", student.getEmail());
                session.setAttribute("username", student.getUserName());
                session.setAttribute("userdata", student);
                request.setAttribute("msg", "Login Successful!");
                request.setAttribute("email", "email");
                request.setAttribute("username", "username");
                if (student.getAdmin()) {
                    RequestDispatcher rd = request.getRequestDispatcher("/AdminPanel/adminlanding.jsp");
                    rd.forward(request, response);
                } else {
                    RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
                    rd.forward(request, response);
                }
                System.out.println(request.getAttribute("msg"));
                RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
                rd.forward(request, response);
            } else {
                request.setAttribute("msg", "Invalid username or password");
                RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
                rd.forward(request, response);
            }
        }

        //To register a new account
        if (action.equalsIgnoreCase("register")) {
            Student student = new Student();
            student.setUserName(request.getParameter("username"));
            student.setEmail(request.getParameter("email"));
            student.setPassword(HashPassword.hashPassword(request.getParameter("password")));
            new UserService().insertUser(student);
            System.out.printf("Data Inserted");
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            try {
                rd.forward(request, response);
            } catch (ServletException e) {
                throw new RuntimeException(e);
            }
        }

        //to subscribers
        if (action.equalsIgnoreCase("subscribe")) {
            Student student = new Student();
            student.setSubsName(request.getParameter("nlname"));
            student.setSubsEmail(request.getParameter("nlemail"));
            new UserService().insertSubscriber(student);
            System.out.printf("Data Inserted");
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            try {
                rd.forward(request, response);
            } catch (ServletException e) {
                throw new RuntimeException(e);
            }
        }

        // to go to the profile
        if (action.equalsIgnoreCase("profile")) {
            RequestDispatcher rd = request.getRequestDispatcher("/UserSide/userprofile.jsp");
            try {
                rd.forward(request, response);
            } catch (ServletException e) {
                throw new RuntimeException(e);
            }
        }

        // to go to home
        if (action.equalsIgnoreCase("home")) {
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            try {
                rd.forward(request, response);
            } catch (ServletException e) {
                throw new RuntimeException(e);
            }
        }

        //to go to browse
        if (action != null && action.equalsIgnoreCase("browse")) {
            List<Student> sortedNames = UserService.getAllBooks();
            request.setAttribute("bookname", sortedNames);
            RequestDispatcher rd = request.getRequestDispatcher("/UserSide/browse.jsp");
            rd.forward(request, response);
        }

        // to go to popular
        if (action.equalsIgnoreCase("popular")) {
            RequestDispatcher rd = request.getRequestDispatcher("/UserSide/browsebypopular.jsp");
            try {
                rd.forward(request, response);
            } catch (ServletException e) {
                throw new RuntimeException(e);
            }
        }

        // to go to new
        if (action.equalsIgnoreCase("new")) {
            RequestDispatcher rd = request.getRequestDispatcher("/UserSide/browsebynew.jsp");
            try {
                rd.forward(request, response);
            } catch (ServletException e) {
                throw new RuntimeException(e);
            }
        }

        //to display book details
        if (action.equalsIgnoreCase("getbook")) {
            Student student = new Student();
            student.setId(Integer.parseInt(request.getParameter("id")));
            HashMap<String, Object> details = null;
            try {
                details = new UserService().showDetails(student);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            request.setAttribute("details", details);
            RequestDispatcher rd = request.getRequestDispatcher("UserSide/bookdisplay.jsp");
            rd.forward(request, response);

        }

        //For Searching books.
        if (action.equalsIgnoreCase("userbsearch")) {
            String query = request.getParameter("query");
            List<Student> ubsearchResults = UserService.searchuBooks(query);
            request.setAttribute("ubsearchResults", ubsearchResults);
            request.setAttribute("query", query);
            RequestDispatcher dispatcher = request.getRequestDispatcher("UserSide/searchresults.jsp");
            dispatcher.forward(request, response);
        }

        //Filter by author
        if (action.equalsIgnoreCase("getbookauthor")) {
            String author = request.getParameter("query");
            List<Student> searchResults = UserService.searchBooksByAuthor(author);
            request.setAttribute("ubasearchResults", searchResults);
            request.setAttribute("author", author);
            RequestDispatcher dispatcher = request.getRequestDispatcher("UserSide/authorfilter.jsp");
            dispatcher.forward(request, response);
        }

        //Filter by genre
        if (action.equalsIgnoreCase("getgenre")) {
            String genre = request.getParameter("query");
            List<Student> searchResults = UserService.searchBooksByGenre(genre);
            request.setAttribute("ubgsearchResults", searchResults);
            request.setAttribute("genre", genre);
            RequestDispatcher dispatcher = request.getRequestDispatcher("UserSide/genrefilter.jsp");
            dispatcher.forward(request, response);
        }

        //change password
        if (action.equalsIgnoreCase("changepassword")) {
            Student student = new Student();
            HttpSession session = request.getSession();
            String username = (String) session.getAttribute("username");
            student.setPassword(HashPassword.hashPassword(request.getParameter("oldpassword")));
            student.setNewpassword(HashPassword.hashPassword(request.getParameter("newpassword")));
            new UserService().changePassword(student, username);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
            requestDispatcher.forward(request, response);

        }

        // for deleting book from wishlist
        if (action.equalsIgnoreCase("deletewl")) {
            int id = Integer.parseInt(request.getParameter("id"));
            UserService wlserv = new UserService();
            wlserv.deleteWishlist(id);
            request.setAttribute("wlserv", wlserv);
            RequestDispatcher rd = request.getRequestDispatcher("UserSide/userprofile.jsp");
            rd.forward(request, response);
        }

        //to insert data of book in wish list bid, id of the current user using session
        if (action.equalsIgnoreCase("wishlist")) {
            HttpSession session = request.getSession();
            int uid = (int) session.getAttribute("uid");
            String title = request.getParameter("title");
                System.out.println(uid +title);
                new UserService().insertWishlist(uid, title);
            RequestDispatcher dispatcher = request.getRequestDispatcher("UserSide/userprofile.jsp");
            dispatcher.include(request, response);
        }

        //to logout
        if (action.equalsIgnoreCase("logout")) {
            new UserService().logout(request, response);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
            requestDispatcher.forward(request, response);
        }




    }
}