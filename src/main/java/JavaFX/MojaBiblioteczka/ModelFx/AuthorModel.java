package JavaFX.MojaBiblioteczka.ModelFx;

import JavaFX.MojaBiblioteczka.Database.dao.AuthorDao;
import JavaFX.MojaBiblioteczka.Database.dbutils.DbManager;
import JavaFX.MojaBiblioteczka.Database.models.Author;
import JavaFX.MojaBiblioteczka.Utils.converters.ConverterAuthor;
import JavaFX.MojaBiblioteczka.Utils.exceptions.ApplicationException;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class AuthorModel {

    private ObjectProperty<AuthorFx> authorFxObjectProperty = new SimpleObjectProperty<>(new AuthorFx());           //w stworzonym SOP inicjalizujemy pustego authora

    public void saveAuthorInDataBase() throws ApplicationException {
        AuthorDao authorDao = new AuthorDao(DbManager.getConnectionSource());

        Author author = ConverterAuthor.convertAuthorFxToAuthor(this.getAuthorFxObjectProperty());

        authorDao.createOrUpdate(author);                                                                           //zapisujemy autora do BD
        DbManager.closeConnectionSource();
    }

    public AuthorFx getAuthorFxObjectProperty() {                                           //gettery i settety
        return authorFxObjectProperty.get();
    }
    public ObjectProperty<AuthorFx> authorFxObjectPropertyProperty() {                          //dostęp do całego obiektu
        return authorFxObjectProperty;
    }
    public void setAuthorFxObjectProperty(AuthorFx authorFxObjectProperty) {                    //dostęp do wartości tego obiektu
        this.authorFxObjectProperty.set(authorFxObjectProperty);
    }


}
