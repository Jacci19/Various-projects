package JavaFX.MojaBiblioteczka.Utils.converters;

import JavaFX.MojaBiblioteczka.Database.dao.AuthorDao;
import JavaFX.MojaBiblioteczka.Database.models.Author;
import JavaFX.MojaBiblioteczka.ModelFx.AuthorFx;

public class ConverterAuthor {

    public static Author convertAuthorFxToAuthor(AuthorFx authorFx){
        Author author = new Author();
        author.setName(authorFx.getName());
        author.setSurname(authorFx.getSurname());
        return author;
    }
}
