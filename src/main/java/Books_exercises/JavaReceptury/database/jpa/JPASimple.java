package Books_exercises.JavaReceptury.database.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import domain.Person;

// BEGIN main
public class JPASimple {
    
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {

        System.out.println("JPASimple.main()");

        EntityManagerFactory entityMgrFactory = null;
        EntityManager entityManager = null;
        try {
            entityMgrFactory = Persistence.createEntityManagerFactory("jpademo");
            entityManager = entityMgrFactory.createEntityManager();
        
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();

            // Utworzenie encji w bazie danych.
            Person np = new Person("Tomek", "Bukowski");
            System.out.println(np);
            entityManager.persist(np);
            transaction.commit();
            
            int id = np.getId();
            System.out.println("Utworzono obiekt Person o Id = " + id);
            
            transaction = entityManager.getTransaction();
            transaction.begin();

            Query query = entityManager.createQuery(
                "select p from Person p order by p.lastName");

            List<Person> list = query.getResultList();
            System.out.println("W bazie jest " + list.size() + " obiektów Person:");
            list.forEach(p ->
                System.out.println(
                    p.getFirstName() + ' ' + p.getLastName())
            );
        } finally {    
            if (entityManager != null)
                entityManager.close();
            if (entityMgrFactory != null)
                entityMgrFactory.close();
        }
    }
}
// END main
