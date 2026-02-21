import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.spi.PersistenceProvider;

import org.hibernate.jpa.HibernatePersistenceProvider;
import com.persistence.CustomPersistence;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) {

        PersistenceProvider provider = new HibernatePersistenceProvider();
//        EntityManagerFactory emf= Persistence.createEntityManagerFactory(
//                new CustomPersistence(),
//                new HashMap<>()
//        );
        EntityManagerFactory emf =provider.createContainerEntityManagerFactory(
                        new CustomPersistence(),
                        new HashMap<>()
                );

        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        // Your entity operations
        em.getTransaction().commit();

        em.close();
        emf.close();
    }
}