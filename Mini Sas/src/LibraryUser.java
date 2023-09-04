public class LibraryUser {
    private int id;
    private String name;
    private String email;
    private String phone;


    public LibraryUser(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    //METHODS
    public String addUser(String name, String email , String phone) {
        //Create UserLogic

        return "Added successfully";
    }

    public String deleteUser(int userId) {
        //Delete User Logic
        return "Deleted successfully";
    }
}
