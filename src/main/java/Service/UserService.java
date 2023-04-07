package Service;

import DBConnection.DBConnection;
import Model.Student;
import jakarta.servlet.http.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


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
            preparedStatements.setString(1, student.getSubName());
            preparedStatements.setString(2, student.getSubEmail());

            preparedStatements.execute();


        } catch (
                SQLException e) {
            e.printStackTrace();
        }
    }


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

