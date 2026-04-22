import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import com.example.entity.Student;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("my-persistence-unit");

        EntityManager em = emf.createEntityManager();

        try {

            // INSERT
            em.getTransaction().begin();
            Student student = new Student("Sanvi", 22);
            em.persist(student);
            em.getTransaction().commit();

            Long id = student.getId();
            System.out.println("Inserted: " + student);

            //  FIND after Insert
            Student s1 = em.find(Student.class, id);
            System.out.println("Found After Insert: " + s1);

            //  UPDATE
            em.getTransaction().begin();
            Student s2 = em.find(Student.class, id);   // find again before update
            if (s2 != null) {
                s2.setName("Ananya");
                s2.setAge(28);
            }
            em.getTransaction().commit();

            //  FIND after Update
            Student s3 = em.find(Student.class, id);
            System.out.println("Found After Update: " + s3);

            //  DELETE
            em.getTransaction().begin();
            Student s4 = em.find(Student.class, id);   // find before delete
            if (s4 != null) {
                em.remove(s4);
            }
            em.getTransaction().commit();

            System.out.println("Deleted student with id: " + id);

            //  FIND after Delete
            Student s5 = em.find(Student.class, id);
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