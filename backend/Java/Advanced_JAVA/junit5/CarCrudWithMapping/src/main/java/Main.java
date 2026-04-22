import com.example.entity.*;
import jakarta.persistence.*;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("my-persistence-unit");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        Car car1 = new Car("BMW", 5000000);
        Car car2 = new Car("Audi", 6000000);

        Owner owner = new Owner("Sanvi", Arrays.asList(car1, car2));

        // seting owner reference in cars
        car1.setOwner(owner);
        car2.setOwner(owner);

        em.persist(owner);

        tx.commit();

        em.close();
        emf.close();
    }
}