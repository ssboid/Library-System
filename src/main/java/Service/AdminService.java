package Service;

import DBConnection.DBConnection;
import Model.Student;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AdminService {
    public static class SessionChecker {
        public void checkSession(HttpServletRequest request, HttpServletResponse response) throws IOException {
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("email") == null) {
                response.sendRedirect(request.getContextPath() + "/index.jsp");
            }
        }
    }


    //login
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
        String query = "INSERT INTO books (btitle, bauthor, bisbn, bpublisher, bpubyear, bgenre, bpages, blanguage, blocation, bsynopsis, bimage) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = new DBConnection().getStatement(query);
        System.out.println(query);
        try {
            ps.setString(1, student.getTitle());
            ps.setString(2, student.getAuthor());
            ps.setString(3, student.getIsbn());
            ps.setString(4, student.getPublisher());
            ps.setInt(5, Integer.parseInt(String.valueOf(student.getPubYear())));
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

    //deletebook
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

    //delete subscribers
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

    //delete users
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

    //create users list
    public static List<Student> getUserList() {
        System.out.println("getUserList");
        List<Student> userList = new ArrayList<>();
        String query = "SELECT * FROM user WHERE id <> 1;";
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

    //sort users alphabetically
    public static List<Student> getAllUsers() {
        List<Student> userList = getUserList();
        Collections.sort(userList, Comparator.comparing(Student::getUserName));
        return userList;
    }

    //create subscribers list
    public static List<Student> getSubscriberList() {
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

    //sort subscriberes alphabetically
    public static List<Student> getAllSubs() {
        List<Student> subList = getSubscriberList();
        Collections.sort(subList, Comparator.comparing(Student::getUserName));
        return subList;
    }

    //create books list
    public static List<Student> getBookList() {
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

    //sort books list alphabetically
    public static List<Student> getAllBooks() {
        List<Student> bookList = getBookList();
        Collections.sort(bookList, Comparator.comparing(Student::getTitle));
        return bookList;
    }

    //creaate issued books list
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

    //    For searching books
    public static List<Student> searchBooks(String query) {
        List<Student> search = new ArrayList<>();
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

    //    For searching subs
    public static List<Student> searchSubs(String query) {
        List<Student> subsearch = new ArrayList<>();
        String sql = "SELECT * FROM subscribers WHERE subsname LIKE ?";
        try (PreparedStatement ps = new DBConnection().getStatement(sql);) {
            ps.setString(1, "%" + query + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    System.out.println(ps);
                    Student student = new Student();
                    student.setId(rs.getInt("sid"));
                    student.setSubsName(rs.getString("subsname"));
                    student.setSubsEmail(rs.getString("subsemail"));
                    subsearch.add(student);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return subsearch;
    }

    //    For searching users
    public static List<Student> searchUsers(String query) {
        List<Student> usersearch = new ArrayList<>();
        String sql = "SELECT * FROM user WHERE (userName LIKE ? OR email = ?) AND id <> 1;";
        try (PreparedStatement ps = new DBConnection().getStatement(sql);) {
            ps.setString(1, "%" + query + "%");
            ps.setString(2, "%" + query + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    System.out.println(ps);
                    Student student = new Student();
                    student.setId(rs.getInt("id"));
                    student.setUserName(rs.getString("userName"));
                    student.setEmail(rs.getString("email"));
                    usersearch.add(student);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return usersearch;
    }

    public void editbooks(int id, Student student){

        String query = "UPDATE books SET btitle=?, bauthor=?, bisbn=?, bpublisher=?, bpubyear=?, bgenre=?, blanguage=?, bpages=?, blocation=?, bsynopsis=? WHERE bid=?";
            PreparedStatement pstm = new DBConnection().getStatement(query);
            try {
                pstm.setString(1, student.getTitle());
                pstm.setString(2, student.getAuthor());
                pstm.setString(3, student.getIsbn());
                pstm.setString(4, student.getPublisher());
                pstm.setString(5, String.valueOf(student.getPubYear()));
                pstm.setString(6, student.getGenre());
                pstm.setString(7, student.getLanguage());
                pstm.setString(8, student.getPages());
                pstm.setString(9, student.getLocation());


                pstm.setString(10,student.getSynopsis());

                pstm.setInt(11,id);
                pstm.executeUpdate();
                System.out.println(pstm);
            }catch (SQLException e){
                System.out.println(e);
            }

        }


//        this is related to gotoeditbook
    public Student getUserRow(int id) {
        Student student = new Student();
        String query = "select * from books where bid = ?";
        PreparedStatement pstm = new DBConnection().getStatement(query);
        System.out.println("this is for edit books ");
        System.out.println(pstm);

        try {
            pstm.setInt(1, id);
            System.out.println(id);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {

                student.setId(rs.getInt("bid"));
                student.setTitle(rs.getString("btitle"));
                student.setAuthor(rs.getString("bauthor"));
                student.setIsbn(rs.getString("bisbn"));
                student.setPublisher(rs.getString("bpublisher"));
                student.setPubYear(Integer.parseInt(rs.getString("bpubyear")));
                student.setGenre(rs.getString("bgenre"));
                student.setLanguage(rs.getString("blanguage"));
                student.setPages(rs.getString("bpages"));
                student.setLocation(rs.getString("blocation"));
                student.setSynopsis(rs.getString("bsynopsis"));
                student.setImage(rs.getString("bimage"));


            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return student;
    }

    //log out
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
            session.invalidate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
