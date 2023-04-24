package Controller;

import Model.Student;
import Service.AdminService;
import Service.UserService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@MultipartConfig
@WebServlet(name = "helloServlet", urlPatterns = "/admin")
public class Admin extends HttpServlet {
    private String message;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();


        String action = request.getParameter("page");

        // to go to add book page
        if (action.equalsIgnoreCase("gotoaddbook")) {
            RequestDispatcher rd = request.getRequestDispatcher("/AdminPanel/addbook.jsp");
            try {
                rd.forward(request, response);
            } catch (ServletException e) {
                throw new RuntimeException(e);
            }
        }

        //to go to manage book page
        if (action != null && action.equalsIgnoreCase("gotomanagebook")) {
            List<Student> sortedNames = AdminService.getAllBooks();
            request.setAttribute("bookname", sortedNames);
            RequestDispatcher rd = request.getRequestDispatcher("/AdminPanel/managebook.jsp");
            rd.forward(request, response);
        }

        // to go to issue book page
        if (action.equalsIgnoreCase("gotoissuebook")) {
            RequestDispatcher rd = request.getRequestDispatcher("/AdminPanel/issuebook.jsp");
            try {
                rd.forward(request, response);
            } catch (ServletException e) {
                throw new RuntimeException(e);
            }
        }

        //to go to issued book page
        if (action.equalsIgnoreCase("gotoissuedbooks")) {
            RequestDispatcher rd = request.getRequestDispatcher("/AdminPanel/issuedbook.jsp");
            try {
                rd.forward(request, response);
            } catch (ServletException e) {
                throw new RuntimeException(e);
            }
        }

        //to go to user list page
        if (action != null && action.equalsIgnoreCase("gotomanageuser")) {
            List<Student> sortedNames = AdminService.getAllUsers();
            request.setAttribute("username", sortedNames);
            RequestDispatcher rd = request.getRequestDispatcher("/AdminPanel/userlist.jsp");
            rd.forward(request, response);
        }

        //to go to subs list page
        if (action != null && action.equalsIgnoreCase("gotomanagesubs")) {
            List<Student> sortedNames = AdminService.getAllSubs();
            request.setAttribute("subname", sortedNames);
            RequestDispatcher rd = request.getRequestDispatcher("/AdminPanel/subscriberlist.jsp");
            rd.forward(request, response);
        }

        //to go to edit book page
//        if (action.equalsIgnoreCase("gotoeditbook")) {
//            RequestDispatcher rd = request.getRequestDispatcher("/AdminPanel/editbook.jsp");
//            try {
//                rd.forward(request, response);
//            } catch (ServletException e) {
//                throw new RuntimeException(e);
//            }
//        }
        if (action.equalsIgnoreCase("gotoeditbook")) {

            int id = Integer.parseInt(request.getParameter("id"));

            System.out.println(id);
            Student student = new AdminService().getUserRow(id);
            request.setAttribute("id", id);
            request.setAttribute("student", student);
            RequestDispatcher rd = request.getRequestDispatcher("/AdminPanel/editbook.jsp");
                rd.forward(request, response);

        }


        // this controller will make the final changes in the editbook.jsp
        if (action.equalsIgnoreCase("editbook")) {

            Student student = new Student();

            int id = Integer.parseInt(request.getParameter("id"));

            student.setTitle(request.getParameter("title"));
            student.setAuthor(request.getParameter("author"));
            student.setIsbn(request.getParameter("isbn"));
            student.setPublisher(request.getParameter("publisher"));
            student.setPubYear(Integer.parseInt(request.getParameter("publication_year")));
            student.setGenre(request.getParameter("genre"));
            student.setLanguage(request.getParameter("language"));
            student.setPages(request.getParameter("number_of_pages"));
            student.setLocation(request.getParameter("location"));
            student.setSynopsis(request.getParameter("synopsis"));
//            student.setImage(request.getParameter("cover_image"));


            try {
                new AdminService().editbooks(id, student);
               List<Student> bookList = new AdminService().getBookList();
            request.setAttribute("bookList", bookList);
                RequestDispatcher rd = request.getRequestDispatcher("AdminPanel/managebook.jsp");
                rd.forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
                // handle the exception
            }
        }


        //to add book//
        if (action.equalsIgnoreCase("addbook")) {
            Student student = new Student();
            Part filePart = request.getPart("cover_image");
            String fileName = filePart.getSubmittedFileName();
            String filePathName ="C:\\Users\\Lenovo\\IdeaProjects\\libraryProject\\src\\main\\webapp\\CSS\\images\\bookimages\\" + fileName;
            for (Part part : request.getParts()) {
                part.write(filePathName);
            }
            try {
                PrintWriter out1 = response.getWriter();
                out1.print("Helllo here is error");
                student.setTitle(request.getParameter("title"));
                student.setAuthor(request.getParameter("author"));
                student.setIsbn(request.getParameter("isbn"));
                student.setPublisher(request.getParameter("publisher"));
                student.setPubYear(Integer.parseInt(request.getParameter("publication_year")));
                student.setGenre(request.getParameter("genre"));
                student.setPages(request.getParameter("number_of_pages"));
                student.setLanguage(request.getParameter("language"));
                student.setLocation(request.getParameter("location"));
                student.setSynopsis(request.getParameter("synopsis"));
                student.setImage(filePathName);

                new AdminService().addBook(student);

                RequestDispatcher rd = request.getRequestDispatcher("AdminPanel/addbook.jsp");
                rd.forward(request, response);
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        //to issuebook//
        if (action.equalsIgnoreCase("issue")){

        }

        // for deleting subscriber
        if (action.equalsIgnoreCase("deletesubs"))
        {
            int id = Integer.parseInt(request.getParameter("id"));
            AdminService adminService = new AdminService();
            adminService.deleteSubs(id);
            List<Student> subscriberList = new AdminService().getSubscriberList();
            request.setAttribute("subscriberList", subscriberList);
            RequestDispatcher rd = request.getRequestDispatcher("AdminPanel/subscriberlist.jsp");
            rd.forward(request, response);
        }

        // for deleting user
        if (action.equalsIgnoreCase("deleteuser"))
        {
            int id = Integer.parseInt(request.getParameter("id"));
            AdminService adminService = new AdminService();
            adminService.deleteUser(id);
            List<Student> userList = new AdminService().getUserList();
            request.setAttribute("userList", userList);
            RequestDispatcher rd = request.getRequestDispatcher("AdminPanel/userlist.jsp");
            rd.forward(request, response);
        }

        // for deleting book
        if (action.equalsIgnoreCase("deletebook"))
        {
            int id = Integer.parseInt(request.getParameter("id"));
            AdminService adminService = new AdminService();
            adminService.deleteBook(id);
            List<Student> bookList = new AdminService().getBookList();
            request.setAttribute("bookList", bookList);
            RequestDispatcher rd = request.getRequestDispatcher("AdminPanel/managebook.jsp");
            rd.forward(request, response);
        }

        //For Searching Book
        if (action.equalsIgnoreCase("bsearch")) {
            String query = request.getParameter("query");
            List<Student> searchResults = AdminService.searchBooks(query);
            request.setAttribute("searchResults", searchResults);
            request.setAttribute("query", query);
            RequestDispatcher dispatcher = request.getRequestDispatcher("AdminPanel/booksearchresult.jsp");
            dispatcher.forward(request, response);
        }

        //For Searching subscribers
        if (action.equalsIgnoreCase("ssearch")) {
            String query = request.getParameter("query");
            List<Student> subscribersearch = AdminService.searchSubs(query);
            request.setAttribute("subscribersearch", subscribersearch);
            request.setAttribute("query", query);
            RequestDispatcher dispatcher = request.getRequestDispatcher("AdminPanel/subsearchresult.jsp");
            dispatcher.forward(request, response);
        }

        //For Searching user
        if (action.equalsIgnoreCase("usearch")) {
            String query = request.getParameter("query");
            List<Student> usersearch = AdminService.searchUsers(query);
            request.setAttribute("usersearch", usersearch);
            request.setAttribute("query", query);
            RequestDispatcher dispatcher = request.getRequestDispatcher("AdminPanel/usersearchresult.jsp");
            dispatcher.forward(request, response);
        }




        // log out
        if (action.equalsIgnoreCase("logout")) {
            new UserService().logout(request, response);
            HttpSession session = request.getSession();
            session.invalidate();
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
            requestDispatcher.forward(request, response);

        }
    }


    public void destroy() {
    }
}