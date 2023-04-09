package Service;

import DBConnection.DBConnection;
import Model.Student;
import jakarta.servlet.http.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;


public class UserService {

    public void insertUser(Student student) {
        String query = "insert into user(userName, email, password)" + "values(?,?,?)"; // same as database

        PreparedStatement preparedStatements = new DBConnection().getStatement(query);  // execute parametrized query

        try {
            preparedStatements.setString(1, student.getUserName());
            preparedStatements.setString(2, student.getEmail());
            preparedStatements.setString(3, student.getPassword());

            preparedStatements.execute();

        } catch (
                SQLException e) {
            e.printStackTrace();
        }
    }


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
                student.setAdmin(rs.getBoolean("admin"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return student;
    }


    public void insertSubscriber(Student student) {
        String query = "insert into subscribers(subsname, subsemail)" + "values(?,?)"; // same as database

        PreparedStatement preparedStatements = new DBConnection().getStatement(query);  // execute parametrized query

        try {
            preparedStatements.setString(1, student.getSubsName());
            preparedStatements.setString(2, student.getSubsEmail());

            preparedStatements.execute();


        } catch (
                SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Student> getBookList(int bookId) {
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
                student.setIsbn(rs.getString("bisbn"));
                student.setPublisher(rs.getString("bpublisher"));
                student.setPubYear(Integer.parseInt(rs.getString("bpupyear")));
                student.setGenre(rs.getString("bgenre"));
                student.setLanguage(rs.getString("blanguage"));
                student.setPages(rs.getString("bpages"));
                student.setLocation(rs.getString("blocation"));
                student.setSynopsis(rs.getString("bsynopsis"));
                student.setImage(rs.getString("bimage"));
                student.setStatus(rs.getString("status"));
                bookListing.add(student);

                System.out.println(student.getImage());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookListing;
    }


    //for displaying
//    public String getbook(Student student) throws SQLException {
//        String base64Image = "";
//
//        // Retrieve the image data from the database
//        String query = "SELECT bimage  FROM books WHERE bid = ?";
//        PreparedStatement ps = new DBConnection().getStatement(query);
//        ps.setInt(1, student.getId());
//
//        System.out.println(ps);
//        try {
//            ResultSet rs = ps.executeQuery();
//            if (rs.next()) {
//                // Read the image file into a byte array
//                String filePath = rs.getString("bimage");
//                File file = new File(filePath);
//                byte[] fileContent = Files.readAllBytes(file.toPath());
//                System.out.println(filePath);
//                // Convert the byte array to a base64 string
//                base64Image = Base64.getEncoder().encodeToString(fileContent);
//                System.out.println(base64Image);
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        return base64Image;
//    }

    public HashMap<String, Object> showDetails(Student student) throws SQLException {
        HashMap<String, Object> details = new HashMap<>();
        String base64Image = "";

        // Retrieve the image data and student details from the database
        String query = "SELECT bimage, btitle, bauthor, bisbn, bpublisher, bpubyear, bgenre, blanguage, bpages, blocation, bsynopsis " +"FROM books " +"WHERE bid = ?";
        PreparedStatement ps = new DBConnection().getStatement(query);
        ps.setInt(1, student.getId());

        System.out.println(ps);

        try {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                // Read the image file into a byte array
                String filePath = rs.getString("bimage");
                File file = new File(filePath);
                byte[] fileContent = Files.readAllBytes(file.toPath());
                System.out.println(filePath);

                // Convert the byte array to a base64 string
                base64Image = Base64.getEncoder().encodeToString(fileContent);

                // Add the student details and image to the HashMap
                String btitle = rs.getString("btitle");
                String bauthor = rs.getString("bauthor");
                String bisbn = rs.getString("bisbn");
                String bpublisher = rs.getString("bpublisher");
                int bpubyear = rs.getInt("bpubyear");
                String bgenre = rs.getString("bgenre");
                String blanguage = rs.getString("blanguage");
                int bpages = rs.getInt("bpages");
                String blocation = rs.getString("blocation");
                String bsynopsis = rs.getString("bsynopsis");




                details.put("btitle", btitle);
                details.put("bauthor", bauthor);
                details.put("bisbn", bisbn);
                details.put("bpublisher", bpublisher);
                details.put("bpubyear", bpubyear);
                details.put("bgenre", bgenre);
                details.put("blanguage", blanguage);
                details.put("bpages", bpages);
                details.put("blocation", blocation);
                details.put("bsynopsis", bsynopsis);
                details.put("bimage", base64Image);

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return details;
    }



    //    For searching books

    public static List<Student> searchuBooks(String query) {
        List<Student> search = new ArrayList< >();
        String sql = "SELECT * FROM books WHERE btitle LIKE ? OR bauthor LIKE ? OR bgenre LIKE ?";
        try (PreparedStatement ps = new DBConnection().getStatement(sql);) {
            ps.setString(1, "%" + query + "%");
            ps.setString(2, "%" + query + "%");
            ps.setString(3, "%" + query + "%");
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
                    student.setStatus(rs.getString("status"));
                    search.add(student);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return search;
    }


    //change pass


    public Student changePassword(Student student, String username) {

        String query = "UPDATE user SET password = ? WHERE userName = ?";

        PreparedStatement ps = new DBConnection().getStatement(query);

        try {
            ps.setString(2, username);
            ps.setString(1, student.getNewpassword());

            ps.executeUpdate();

            System.out.println(ps);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return student;
    }


//    This is related to list user page
//    public List<Student> getUserList() {
//        List<Student> userList = new ArrayList<>();
//        String query = "select * from user";
//        System.out.println(query);
//        PreparedStatement pstm = new DBConnection().getStatement(query);
//        try {
//            ResultSet rs = pstm.executeQuery();
//            while (rs.next()) {
//                Student student = new Student();
//
//                student.setId(rs.getInt("id"));
//                student.setFullName(rs.getString("fullName"));
//                student.setUserName(rs.getString("userName"));
//                student.setPassword(rs.getString("password"));
//
//
//                userList.add(student);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return userList;
//    }
//
//    //    For getting user details in user list
//    public Student getUserRow(int id) {
//        Student student = new Student();
//        String query = "select * from user where id = ?";
//        PreparedStatement pstm = new DBConnection().getStatement(query);
//
//        try {
//            pstm.setInt(1, id);
//            ResultSet rs = pstm.executeQuery();
//            while (rs.next()) {
//                student.setId(rs.getInt("id"));
//                student.setFullName(rs.getString("fullName"));
//                student.setUserName(rs.getString("userName"));
//                student.setPassword(rs.getString("password"));
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return student;
//    }
//
//    // Delete User
//    public void deleteUser(int id) {
//        String query = "delete from user where id = ?";
//        PreparedStatement ps = new DBConnection().getStatement(query);
//
//        try {
//            ps.setInt(1, id);
//            ps.execute();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
////    For editing users
//public void editUser(int id, Student student) throws SQLException {
//
//    String query = "update user set fullName=?, userName=?, password=? where id=?";
//
//    try (PreparedStatement pstm = new DBConnection().getStatement(query)) {
//        pstm.setString(1, student.getFullName());
//        pstm.setString(2, student.getUserName());
//        pstm.setString(3, student.getPassword());
//        pstm.setInt(4, id);
//        pstm.executeUpdate();
//    }


    public void logout(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
            session.invalidate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

