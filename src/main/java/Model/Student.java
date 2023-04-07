package Model;

public class Student {

    private int id;

    private String email;

    private String userName;

    private String password;

    private String newpassword;

    private String subsname;


    private String subsemail;

    private String title;

    private String author;

    private String isbn;

    private String publisher;

    private int publication_year;

    private String genre;

    private String language;

    private String number_of_pages;

    private String location;

    private String synopsis;

    private String cover_image;

    private boolean admin;

    public boolean getAdmin(){return admin;}

    public void setAdmin(boolean admin) {this.admin = admin;}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSubName() {
        return subsname;
    }

    public void setSubName(String subsname) {
        this.subsname = subsname;
    }

    public String getSubEmail() {
        return subsemail;
    }

    public void setSubEmail(String subsemail) {
        this.subsemail = subsemail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getPubYear() {
        return publication_year;
    }

    public void setPubYear(int publication_year) {
        this.publication_year = publication_year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPages() {
        return number_of_pages;
    }

    public void setPages(String number_of_pages) {
        this.number_of_pages = number_of_pages;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getImage() {
        return cover_image;
    }

    public void setImage(String cover_image) {
        this.cover_image = cover_image;
    }

    public String getNewpassword() {
        return newpassword;
    }

    public void setNewpassword(String newpassword) {
        this.newpassword = newpassword;
    }


}
