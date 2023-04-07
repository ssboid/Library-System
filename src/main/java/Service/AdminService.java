package Service;

import DBConnection.DBConnection;
import Model.Student;
import jakarta.servlet.http.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminService {

    public Student getUser(String email, String password) {
        Student student = null;

        String query = "select * from user where email=? and password=?";
        PreparedStatement ps = new DBConnection().getStatement(query);

        try {
            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                student = new Student();

                student.setId(rs.getInt("id"));
                student.setUserName(rs.getString("userName"));

                student.setEmail(rs.getString("email"));
                student.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return student;
    }

    //add book
    public void addBook(Student student) {
        String query = "INSERT INTO books (btitle, bauthor, bisbn, bpublisher, bpubyear, bgenre, bpages, blanguage, bsynopsis, blocation, bimage) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement ps = new DBConnection().getStatement(query);
        System.out.println(query);

        try {
            ps.setString(1, student.getTitle());
            ps.setString(2, student.getAuthor());
            ps.setString(3, student.getIsbn());
            ps.setString(4, student.getPublisher());
            ps.setInt(5, student.getPubYear());
            ps.setString(6, student.getGenre());
            ps.setString(7, student.getPages());
            ps.setString(8, student.getLanguage());
            ps.setString(9, student.getLocation());
            ps.setString(10, student.getSynopsis());
            ps.setString(11, student.getImage());

            ps.executeUpdate();

            System.out.println(ps);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void deleteBook(int id) {
        String query = "delete from books where bid = ?";
        PreparedStatement ps = new DBConnection().getStatement(query);

        try {
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void deleteSubs(int id) {
        String query = "delete from subscribers where sid = ?";
        PreparedStatement ps = new DBConnection().getStatement(query);

        try {
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void deleteUser(int id) {
        String query = "delete from user where id = ?";
        PreparedStatement ps = new DBConnection().getStatement(query);

        try {
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public List<Student> getUserList() {
        System.out.println("getUserList");
        List<Student> userList = new ArrayList<>();
        String query = "select * from user";
        System.out.println(query);
        PreparedStatement pstm = new DBConnection().getStatement(query);
        try {
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setUserName(rs.getString("userName"));
                student.setEmail(rs.getString("email"));
                userList.add(student);
            }
            rs.close();
            pstm.close();
            System.out.println("userList.size()" + userList.size());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public List<Student> getSubscriberList() {
        System.out.println("getSubscriberList");
        List<Student> subscriberList = new ArrayList<>();
        String query = "select * from subscribers";
        System.out.println(query);
        PreparedStatement pstm = new DBConnection().getStatement(query);
        try {
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("sid"));
                student.setUserName(rs.getString("subsname"));
                student.setEmail(rs.getString("subsemail"));
                subscriberList.add(student);
            }
            rs.close();
            pstm.close();
            System.out.println("userList.size()" + subscriberList.size());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subscriberList;
    }

    public List<Student> getBookList() {
        System.out.println("getBookList");
        List<Student> bookListing = new ArrayList<>();
        String query = "select * from books";
        System.out.println(query);
        PreparedStatement pstm = new DBConnection().getStatement(query);
        try {
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("bid"));
                student.setTitle(rs.getString("btitle"));
                student.setAuthor(rs.getString("bauthor"));
                bookListing.add(student);
            }

            System.out.println("bookList.size()" + bookListing.size());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookListing;
    }

    public List<Student> getIssuedBookList() {
        System.out.println("getIssuedBookList");
        List<Student> issuedbookList = new ArrayList<>();
        String query = "select * from issuedbooks";
        System.out.println(query);
        PreparedStatement pstm = new DBConnection().getStatement(query);
        try {
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("ibid"));
                student.setTitle(rs.getString("ibtitle"));
                student.setUserName(rs.getString("ibuser"));
                issuedbookList.add(student);
            }
            rs.close();
            pstm.close();
            System.out.println("bookList.size()" + issuedbookList.size());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return issuedbookList;
    }


    public void logout(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
            session.invalidate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


//    For searching books

    public static List<Student> searchUsers(String query) {
        List<Student> search = new ArrayList< >();
        String sql = "SELECT * FROM books WHERE btitle LIKE ? OR bauthor LIKE ?";
        try (PreparedStatement ps = new DBConnection().getStatement(sql);) {
            ps.setString(1, "%" + query + "%");
            ps.setString(2, "%" + query + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    System.out.println(ps);
                    Student student = new Student();
                    student.setId(rs.getInt("bid"));
                    student.setTitle(rs.getString("btitle"));
                    student.setAuthor(rs.getString("bauthor"));
                    student.setIsbn(rs.getString("bisbn"));
                    student.setPublisher(rs.getString("bpublisher"));
//                    student.setPubYear(Integer.parseInt(rs.getString("bpupyear")));
                    student.setGenre(rs.getString("bgenre"));
                    student.setLanguage(rs.getString("blanguage"));
                    student.setPages(rs.getString("bpages"));
                    student.setLocation(rs.getString("blocation"));
                    student.setSynopsis(rs.getString("bsynopsis"));
                    student.setImage(rs.getString("bimage"));

                    search.add(student);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return search;
    }


}
