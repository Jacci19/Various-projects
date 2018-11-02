package JavaFX.MojaBiblioteczka.Database.models;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;

public class Author implements BaseModel{

    public Author() {                                                       //konstruktor
    }

    @DatabaseField(generatedId = true)                                      //pola tabeli
    private int id;

    @DatabaseField(columnName = "NAME", canBeNull = false, unique = true)               //takie unique nie są dobre bo Jan Nowak i Jan Kowalski :)
    private String name;

    @DatabaseField(columnName = "SURNAME", canBeNull = false, unique = true)
    private String surname;

    @ForeignCollectionField(eager = true)
    private ForeignCollection<Book> books;


    public int getId() {                                                    //Gettery
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public ForeignCollection<Book> getBooks() {
        return books;
    }
    public void setId(int id) {                                             //Settery
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setBooks(ForeignCollection<Book> books) {
        this.books = books;
    }
}
