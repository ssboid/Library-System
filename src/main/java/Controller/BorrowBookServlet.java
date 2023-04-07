package Controller;

import DBConnection.DBConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "helloissue", urlPatterns = "/borrowbook")
public class BorrowBookServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("page");

        if (action.equalsIgnoreCase("issue"))
        {
            String Title = request.getParameter("title");
            String Author = request.getParameter("author");
            String User = request.getParameter("username");

            PrintWriter out = response.getWriter();
            out.print("Error");

            try {

                PrintWriter out1 = response.getWriter();
                out1.print("Errccr");

                // Check if the book is available
                String query = "SELECT * FROM books WHERE title = ? AND author = ?";
                PreparedStatement ps = new DBConnection().getStatement(query);

                ps.setString(1, Title);
                ps.setString(2, Author);
                ResultSet bookResult = ps.executeQuery();
                System.out.println(ps);

                if (!bookResult.next()) {
                    request.setAttribute("message", "Book not found or not available.");
                    request.getRequestDispatcher("issuebook.jsp").forward(request, response);
                    return;
                }

                // Check if the user exists
                String query1 = "SELECT * FROM user WHERE id = ?";
                PreparedStatement ps1 = new DBConnection().getStatement(query1);
                ps1.setString(1, User);
                ResultSet userResult = ps1.executeQuery();
                System.out.println(ps1);

                if (!userResult.next()) {
                    request.setAttribute("message", "User not found.");
                    request.getRequestDispatcher("admin/managebook.jsp").forward(request, response);
                    return;
                }

                // Insert a new record in the borrowing_history table

                String query2 = "INSERT INTO borrow_history (bbook, bauthor) VALUES (?, ?)";
                PreparedStatement ps2 = new DBConnection().getStatement(query2);
                ps2.setString(1, Title);
                ps2.setString(2, Author);
                int rowsInserted = ps2.executeUpdate();
                System.out.println(ps2);

                if (rowsInserted > 0) {
                    request.setAttribute("message", "Book borrowed successfully.");
                } else {
                    request.setAttribute("message", "Error borrowing book.");
                }

                // Insert a new record in the borrowing_history table

                String query3 = "INSERT INTO issuedbooks (ibtitle, ibuser) VALUES (?, ?)";
                PreparedStatement ps3 = new DBConnection().getStatement(query3);

                ps3.setString(1, Title);
                ps3.setString(2, User);
                int rowsInserted2 = ps3.executeUpdate();
                System.out.println(ps3);

                if (rowsInserted > 0) {
                    request.setAttribute("message", "Book borrowed successfully.");
                } else {
                    request.setAttribute("message", "Error borrowing book.");
                }
            } catch (SQLException e) {
                request.setAttribute("message", "Error: " + e.getMessage());
            }


            request.getRequestDispatcher("issuebook.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        doPost(req, resp);
    }
}
