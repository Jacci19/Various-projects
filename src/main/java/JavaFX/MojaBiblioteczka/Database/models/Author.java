package JavaFX.MojaBiblioteczka.Database.models;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;

public class Author implements BaseModel{

    public Author() {                                                       //konstruktor
    }

    @DatabaseField(generatedId = true)                                      //pola tabeli
    private int id;

    @DatabaseField(columnName = "NAME", canBeNull = false)
    private String nameAndSurname;

    @ForeignCollectionField(eager = true)
    private ForeignCollection<Book> books;


    public int getId() {                                                    //Gettery
        return id;
    }
    public String getNameAndSurname() {
        return nameAndSurname;
    }
    public ForeignCollection<Book> getBooks() {
        return books;
    }
    public void setId(int id) {                                             //Settery
        this.id = id;
    }
    public void setNameAndSurname(String nameAndSurname) {
        this.nameAndSurname = nameAndSurname;
    }
    public void setBooks(ForeignCollection<Book> books) {
        this.books = books;
    }
}
