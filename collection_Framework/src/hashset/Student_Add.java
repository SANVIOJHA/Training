package hashset;

public class Student_Add {
    private int id;
    private String name;

    public Student_Add(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Student_Add s = (Student_Add) obj;
        return id == s.id && name.equals(s.name);
    }

    @Override
    public int hashCode() {
        return id + name.hashCode();
    }

    @Override
    public String toString() {
        return id + " - " + name;
    }
}
