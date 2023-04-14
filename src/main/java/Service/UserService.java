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
import java.util.*;


public class UserService {

    public static class SessionChecker {
        public void checkSession(HttpServletRequest request, HttpServletResponse response) throws IOException {
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("email") == null) {
                response.sendRedirect(request.getContextPath() + "/index.jsp");
            }
        }
    }

    //register
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
                student.setAdmin(rs.getBoolean("admin"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return student;
    }

    //subscribe
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

    //create book list
    public static List<Student> getBookList() {
        System.out.println("getBookList");
        List<Student> bookLists = new ArrayList<>();
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
                student.setGenre(rs.getString("bgenre"));
                student.setStatus(rs.getString("status"));
                bookLists.add(student);
            }
            System.out.println("bookList.size()" + bookLists.size());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookLists;
    }

    //sort books alphabetically
    public static List<Student> getAllBooks() {
        List<Student> bookList = getBookList();
        Collections.sort(bookList, Comparator.comparing(Student::getTitle));
        return bookList;
    }

    //sort books by count
    public List<Student> getBookListbyPop() {
        System.out.println("getBookListbyPop");
        List<Student> bookLists = new ArrayList<>();
        String query = "select * from books ORDER BY count DESC;";
        System.out.println(query);
        PreparedStatement pstm = new DBConnection().getStatement(query);
        try {
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("bid"));
                student.setTitle(rs.getString("btitle"));
                student.setAuthor(rs.getString("bauthor"));
                student.setGenre(rs.getString("bgenre"));
                student.setStatus(rs.getString("status"));
                bookLists.add(student);
            }
            System.out.println("bookList.size()" + bookLists.size());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookLists;
    }

    //sort books by new
    public List<Student> getBookListbyNew() {
        System.out.println("getBookListbyNew");
        List<Student> bookLists = new ArrayList<>();
        String query = "select * from books ORDER BY bid DESC;";
        System.out.println(query);
        PreparedStatement pstm = new DBConnection().getStatement(query);
        try {
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("bid"));
                student.setTitle(rs.getString("btitle"));
                student.setAuthor(rs.getString("bauthor"));
                student.setGenre(rs.getString("bgenre"));
                student.setStatus(rs.getString("status"));
                bookLists.add(student);
            }
            System.out.println("bookList.size()" + bookLists.size());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookLists;
    }

    //show book info
    public HashMap<String, Object> showDetails(Student student) throws SQLException {
        HashMap<String, Object> details = new HashMap<>();
        String base64Image = "";
        // Retrieve the image data and student details from the database
        String query = "SELECT bimage, btitle, bauthor, bisbn, bpublisher, bpubyear, bgenre, blanguage, bpages, blocation, bsynopsis, bid  " + "FROM books " + "WHERE bid = ?";
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
                int id = rs.getInt("bid");

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
                details.put("id", id);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return details;
    }

    //    For searching books
    public static List<Student> searchuBooks(String query) {
        List<Student> search = new ArrayList<>();
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

    // filter by author
    public static List<Student> searchBooksByAuthor(String author) {
        List<Student> searchResults = new ArrayList<>();
        String sql = "SELECT * FROM books WHERE bauthor LIKE ?";
        try (PreparedStatement ps = new DBConnection().getStatement(sql)) {
            ps.setString(1, "%" + author + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Student student = new Student();
                    student.setId(rs.getInt("bid"));
                    student.setTitle(rs.getString("btitle"));
                    student.setAuthor(rs.getString("bauthor"));
                    student.setIsbn(rs.getString("bisbn"));
                    student.setPublisher(rs.getString("bpublisher"));
                    student.setGenre(rs.getString("bgenre"));
                    student.setLanguage(rs.getString("blanguage"));
                    student.setPages(rs.getString("bpages"));
                    student.setLocation(rs.getString("blocation"));
                    student.setSynopsis(rs.getString("bsynopsis"));
                    student.setImage(rs.getString("bimage"));
                    student.setStatus(rs.getString("status"));
                    searchResults.add(student);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return searchResults;
    }

    //filter by new
    public static List<Student> searchBooksByGenre(String genre) {
        List<Student> searchResults = new ArrayList<>();
        String sql = "SELECT * FROM books WHERE bgenre LIKE ?";
        try (PreparedStatement ps = new DBConnection().getStatement(sql)) {
            ps.setString(1, "%" + genre + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Student student = new Student();
                    student.setId(rs.getInt("bid"));
                    student.setTitle(rs.getString("btitle"));
                    student.setAuthor(rs.getString("bauthor"));
                    student.setIsbn(rs.getString("bisbn"));
                    student.setPublisher(rs.getString("bpublisher"));
                    student.setGenre(rs.getString("bgenre"));
                    student.setLanguage(rs.getString("blanguage"));
                    student.setPages(rs.getString("bpages"));
                    student.setLocation(rs.getString("blocation"));
                    student.setSynopsis(rs.getString("bsynopsis"));
                    student.setImage(rs.getString("bimage"));
                    student.setStatus(rs.getString("status"));
                    searchResults.add(student);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return searchResults;
    }

    //show by popular
    public HashMap<String, Object> showPopular() throws SQLException, IOException {
        HashMap<String, Object> details = new HashMap<>();
        // Retrieve the image data and title of up to 4 popular books
        String query = "SELECT bimage, btitle, bid FROM books WHERE count > 0 ORDER BY count DESC LIMIT 4";
        PreparedStatement ps = new DBConnection().getStatement(query);
        ResultSet rs = ps.executeQuery();
        System.out.println(ps);
        int i = 0;
        while (rs.next()) {
            // Read the image file into a byte array
            String filePath = rs.getString("bimage");
            File file = new File(filePath);
            byte[] fileContent = Files.readAllBytes(file.toPath());
            // Convert the byte array to a base64 string
            String base64Image = Base64.getEncoder().encodeToString(fileContent);
            // Add the book details to the HashMap
            String btitle = rs.getString("btitle");
            int bid = rs.getInt("bid");
            details.put("btitle" + i, btitle);
            details.put("bimage" + i, base64Image);
            details.put("bid" +i, bid);
            i++;
        }
        return details;
    }

    //show by new
    public HashMap<String, Object> showNew() throws SQLException, IOException {
        HashMap<String, Object> details = new HashMap<>();
        // Retrieve the image data and title of up to 4 popular books
        String query = "SELECT bimage, btitle, bid FROM books ORDER BY bid DESC LIMIT 4";
        PreparedStatement ps = new DBConnection().getStatement(query);
        ResultSet rs = ps.executeQuery();
        System.out.println(ps);
        int i = 0;
        while (rs.next()) {
            // Read the image file into a byte array
            String filePath = rs.getString("bimage");
            File file = new File(filePath);
            byte[] fileContent = Files.readAllBytes(file.toPath());
            // Convert the byte array to a base64 string
            String base64Image = Base64.getEncoder().encodeToString(fileContent);
            // Add the book details to the HashMap
            String btitle = rs.getString("btitle");
            int bid = rs.getInt("bid");
            details.put("btitle" + i, btitle);
            details.put("bimage" + i, base64Image);
            details.put("bid" +i, bid);
            i++;

        }
        return details;
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

    // this method for storing the wishlist data
    public void insertWishlist(int uid, String title) {
        String query = "INSERT INTO wishlist (id, btitle) VALUES (?, ?)";
        try {
            PreparedStatement ps = new DBConnection().getStatement(query);
            ps.setInt(1, uid);
            ps.setString(2, title);
            ps.executeUpdate();
            System.out.println(ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // for showing wishlist in the profile
    public List<Student> getwishlist(HttpSession session) throws SQLException {
        System.out.println("getwishlist");
        List<Student> wishlist = new ArrayList<>();
        String query = "SELECT books.bid, books.btitle, books.bauthor, books.status, wishlist.wid " +
                "FROM wishlist " +
                "INNER JOIN books ON wishlist.btitle = books.btitle " +
                "WHERE wishlist.id = ?";
        PreparedStatement pstm = new DBConnection().getStatement(query);
        System.out.println(pstm);
        int uid = (int) session.getAttribute("uid");
        pstm.setInt(1, uid);
        System.out.println(uid);
        try {
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("bid"));
                student.setTitle(rs.getString("btitle"));
                student.setAuthor(rs.getString("bauthor"));
                student.setStatus(rs.getString("status"));
                student.setWid(Integer.parseInt(rs.getString("wid")));
                wishlist.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return wishlist;
    }

    //deleting books from wishlist
    public void deleteWishlist(int wid) {
        String query = "delete from wishlist where wid = ?";
        PreparedStatement ps = new DBConnection().getStatement(query);
        System.out.println(ps);
        try {
            ps.setInt(1, wid);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //logout
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
            session.invalidate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

