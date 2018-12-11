package Books_exercises.JavaReceptury.database.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

import domain.Person;

// BEGIN main
public class HibernateSimple {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {

        System.out.println("HibernateSimple.main()");

        Configuration cf = new AnnotationConfiguration();
        cf.configure();
        SessionFactory sf = null;
        Session session = null;
        try {
            sf = cf.buildSessionFactory();
            session = sf.openSession();

            Transaction tx = session.beginTransaction();

            // Utworzenie encji w bazie danych.
            Person np = new Person("Tomek", "Bukowski");
            System.out.println(np);
            session.save(np);
            tx.commit();

            int id = np.getId();
            System.out.println("Utworzono obiekt Person o Id = " + id);

            tx = session.beginTransaction();

            Query query = session.createQuery(
                "select p from Person p order by p.lastName");

            List<Person> list = query.list();
            System.out.println("W bazie jest " + list.size() + " obiektów Person:");
            list.forEach(p ->
                System.out.println(
                        p.getFirstName() + ' ' + p.getLastName())
            );
            System.out.println();
        } finally {
            if (session != null) {
                session.close();                
            }
        }
    }
}
// END main
