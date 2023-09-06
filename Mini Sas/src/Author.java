public class Author {
    private int id;
    private String name;

    public Author(int id ) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    //METHODS

    public String addAuthor(String name){
        //
        return "Added successfully";
    }

    public String editAuthor(int id, String name){
        //
        return "Edited successfully";
    }

    public String deleteAuthor(int id){
        //
        return "Deleted successfully";
    }

}
