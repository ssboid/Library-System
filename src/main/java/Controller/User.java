package Controller;

import java.io.*;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

//import Hashing.HashPassword;
import Model.Student;
import Service.UserService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", urlPatterns = "/user")
public class User extends HttpServlet {
    private String message;

//    public void init() {
//        message = "Hello World!";
//    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();


        String action = request.getParameter("page");

        if (action.equalsIgnoreCase("login")) {

            String email = request.getParameter("email");
            String password = (request.getParameter("password"));
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


//        To register a new account
        if (action.equalsIgnoreCase("register")) {
            Student student = new Student();

            student.setUserName(request.getParameter("username"));
            student.setEmail(request.getParameter("email"));
            student.setPassword((request.getParameter("password")));

            new UserService().insertUser(student);

            System.out.printf("Data Inserted");

            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            try {
                rd.forward(request, response);
            } catch (ServletException e) {
                throw new RuntimeException(e);
            }
        }


        //to subscribers//
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

        // to display book info
//        if (action.equalsIgnoreCase("getbook")) {
//
//            Student student = new Student();
//
//            try {
//                student.setId(Integer.parseInt(request.getParameter("id")));
//                String base64Image = new UserService().getbook(student);
//                List<Student> bookinfo = new UserService().getBookList(student.getId());
//                request.setAttribute("base64Image", base64Image);
//                request.setAttribute("bookinfo", bookinfo);
//            } catch (Exception e) {
//                request.setAttribute("errorMessage", "Error retrieving image: " + e.getMessage());
//            }
//
//            RequestDispatcher rd = request.getRequestDispatcher("UserSide/bookdisplay.jsp");
//            rd.forward(request, response);
//
//
//        }

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


        //        For Searching books.

        if (action.equalsIgnoreCase("userbsearch")) {
            String query = request.getParameter("query");
            List<Student> ubsearchResults = UserService.searchuBooks(query);
            request.setAttribute("ubsearchResults", ubsearchResults);
            request.setAttribute("query", query);
            RequestDispatcher dispatcher = request.getRequestDispatcher("UserSide/searchresults.jsp");
            dispatcher.forward(request, response);
        }

//        // For redirecting bookinfo adter user search book
//        if (action.equalsIgnoreCase("bookinfo")) {
//            List<Student> bookinfo = new UserService().getBookList(bookId);
//            request.setAttribute("bookinfo", bookinfo);
//            RequestDispatcher dispatcher = request.getRequestDispatcher("UserSide/bookdisplay.jsp");
//            dispatcher.forward(request, response);
//        }


        if (action.equalsIgnoreCase("changepassword")) {

//            out.print("error");

            Student student = new Student();
            HttpSession session = request.getSession();
            String username = (String) session.getAttribute("username");
            student.setPassword(request.getParameter("oldpassword"));
            student.setNewpassword(request.getParameter("newpassword"));
            new UserService().changePassword(student, username);

            RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
            requestDispatcher.forward(request, response);

        }
//        Show the user list in list user page
//        if (action.equalsIgnoreCase("listuser") ){
//
//            Student student = new Student();
//            List<Student> studentList = new UserService().getUserList();
//
//            request.setAttribute("student", student);
//            request.setAttribute("studentlist", studentList);
//            RequestDispatcher rd = request.getRequestDispatcher("Pages/listuser.jsp");
//            rd.forward(request, response);
//
//
//        }
//
////        For user_details
//        if (action.equalsIgnoreCase("userDetails"))
//        {
//            int id = Integer.parseInt(request.getParameter("id"));
//            Student student = new UserService().getUserRow(id);
//            request.setAttribute("id", id);
//            request.setAttribute("student", student);  //Why this
//
//            RequestDispatcher rd = request.getRequestDispatcher("Pages/user_details.jsp");
//            rd.forward(request, response);
//        }
//
//        // for deleting users in user_details page
//        if (action.equalsIgnoreCase("deleteUser"))
//
//        {
//            int id = Integer.parseInt(request.getParameter("id"));
//            UserService userService = new UserService();
//            userService.deleteUser(id);
//            List<Student> userList = new UserService().getUserList();
//            request.setAttribute("userList", userList);
//            RequestDispatcher rd = request.getRequestDispatcher("Pages/listuser.jsp");
//            rd.forward(request, response);
//        }
//
//
//        // for editing users
//        if (action.equalsIgnoreCase("userEdit"))
//
//        {
//            int id = Integer.parseInt(request.getParameter("id"));
//            System.out.println(id);
//            Student student = new UserService().getUserRow(id);
//            request.setAttribute("id", id);
//            request.setAttribute("student", student);
//            RequestDispatcher rd = request.getRequestDispatcher("Pages/update_user.jsp");
//            rd.forward(request, response);
//        }
//
//        if (action.equalsIgnoreCase("editUser"))
//
//        {
//            Student student = new Student();
//            int id = Integer.parseInt(request.getParameter("id"));
//            student.setFullName(request.getParameter("fullName"));
//            student.setUserName(request.getParameter("userName"));
//            student.setPassword(request.getParameter("password"));
//
//            try {
//                new UserService().editUser(id, student);
//            } catch ( SQLException e) {
//                e.printStackTrace();
//            }
//            List<Student> userList = new UserService().getUserList();
//            request.setAttribute("userList", userList);
//            RequestDispatcher rd = request.getRequestDispatcher("Pages/list_user.jsp");
//            rd.forward(request, response);
//        }

//        if(action.equalsIgnoreCase("logout")){
//            HttpSession session = request.getSession(false);
//            session.invalidate();
//
//            RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
//            requestDispatcher.forward(request, response);
//        }


//    }

        if (action.equalsIgnoreCase("logout")) {
            new UserService().logout(request, response);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
            requestDispatcher.forward(request, response);

        }


    }
}