import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import com.example.entity.Car;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("my-persistence-unit");

        EntityManager em = emf.createEntityManager();

        try {

            // INSERT
            em.getTransaction().begin();
            Car Car = new Car("Audi", 2458);
            em.persist(Car);
            em.getTransaction().commit();

            Long id = Car.getId();
            System.out.println("Inserted: " + Car);

            //  FIND after Insert
            Car s1 = em.find(Car.class, id);
            System.out.println("Found After Insert: " + s1);

            //  UPDATE
            em.getTransaction().begin();
            Car s2 = em.find(Car.class, id);   // find again before update
            if (s2 != null) {
                s2.setBrandname("benz");
                s2.setPrice(2845);
            }
            em.getTransaction().commit();

            //  FIND after Update
            Car s3 = em.find(Car.class, id);
            System.out.println("Found After Update: " + s3);

            //  DELETE
            em.getTransaction().begin();
            Car s4 = em.find(Car.class, id);   // find before delete
            if (s4 != null) {
                em.remove(s4);
            }
            em.getTransaction().commit();

            System.out.println("Deleted Car with id: " + id);

            //  FIND after Delete
            Car s5 = em.find(Car.class, id);
            System.out.println("Found After Delete: " + s5);  // will print null

        } catch (Exception e) {
            e.printStackTrace();
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            em.close();
            emf.close();
        }
    }
}