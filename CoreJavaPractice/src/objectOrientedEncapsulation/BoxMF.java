package objectOrientedEncapsulation;

public class BoxMF {

    double length;

    // private default constructor
    private BoxMF() {
        this.length = 0;
    }

    // private parameterized constructor
    private BoxMF(double length) {
        this.length = length;
    }

    // factory method - no length
    public static BoxMF createBox() {
        return new BoxMF();
    }

    // factory method - with length
    public static BoxMF createBox(double length) {
        return new BoxMF(length);
    }

    // display method ( for testing)
    public void show() {
        System.out.println("Length = " + length);
    }
}
