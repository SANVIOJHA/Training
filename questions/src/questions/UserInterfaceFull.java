package questions;

import java.util.Scanner;

/* =======================
   ABSTRACT PARENT CLASS
   ======================= */
abstract class GoodsTransport {

    protected String transportId;
    protected String transportDate;
    protected int transportRating;

    public GoodsTransport(String transportId, String transportDate, int transportRating) {
        this.transportId = transportId;
        this.transportDate = transportDate;
        this.transportRating = transportRating;
    }

    public String getTransportId() { return transportId; }
    public String getTransportDate() { return transportDate; }
    public int getTransportRating() { return transportRating; }

    protected float getDiscount(float price) {
        if (transportRating == 5) return price * 0.20f;
        if (transportRating == 3 || transportRating == 4) return price * 0.10f;
        return 0;
    }

    protected float getVehiclePrice(String vehicle) {
        switch (vehicle.toLowerCase()) {
            case "truck": return 1000;
            case "lorry": return 1700;
            default: return 3000;
        }
    }

    public abstract String vehicleSelection();
    public abstract float calculateTotalCharge();
}

/* =======================
   BRICK TRANSPORT
   ======================= */
class BrickTransport extends GoodsTransport {

    private int brickQuantity;
    private float brickPrice;

    public BrickTransport(String id, String date, int rating,
                          float brickSize, int brickQuantity, float brickPrice) {
        super(id, date, rating);
        this.brickQuantity = brickQuantity;
        this.brickPrice = brickPrice;
    }

    public int getBrickQuantity() { return brickQuantity; }
    public float getBrickPrice() { return brickPrice; }

    public String vehicleSelection() {
        return brickQuantity < 300 ? "Truck"
             : brickQuantity <= 500 ? "Lorry"
             : "MonsterLorry";
    }

    public float calculateTotalCharge() {
        float price = brickPrice * brickQuantity;
        float tax = price * 0.30f;
        return price + tax + getVehiclePrice(vehicleSelection())
                - getDiscount(price);
    }
}

/* =======================
   TIMBER TRANSPORT
   ======================= */
class TimberTransport extends GoodsTransport {

    private float length, radius, timberPrice;
    private String timberType;

    public TimberTransport(String id, String date, int rating,
                           float length, float radius,
                           String timberType, float timberPrice) {
        super(id, date, rating);
        this.length = length;
        this.radius = radius;
        this.timberType = timberType;
        this.timberPrice = timberPrice;
    }

    public String getTimberType() { return timberType; }
    public float getTimberPrice() { return timberPrice; }

    public String vehicleSelection() {
        double area = 2 * 3.147 * radius * length;
        return area < 250 ? "Truck"
             : area <= 400 ? "Lorry"
             : "MonsterLorry";
    }

    public float calculateTotalCharge() {
        double volume = 3.147 * radius * radius * length;
        float rate = timberType.equalsIgnoreCase("Premium") ? 0.25f : 0.15f;
        float price = (float) (volume * timberPrice * rate);
        float tax = price * 0.30f;

        return price + tax + getVehiclePrice(vehicleSelection())
                - getDiscount(price);
    }
}

/* =======================
   UTILITY CLASS
   ======================= */
class Utility {

    public boolean validateTransportId(String id) {
        if (id.matches("RTS[0-9]{3}[A-Z]")) return true;

        System.out.println("Transport id " + id + " is invalid");
        System.out.println("Please provide a valid record");
        return false;
    }

    public GoodsTransport parseDetails(String input) {

        String[] d = input.split(":");
        if (!validateTransportId(d[0])) return null;

        return d[3].equalsIgnoreCase("BrickTransport")
                ? new BrickTransport(d[0], d[1], Integer.parseInt(d[2]),
                        Float.parseFloat(d[4]),
                        Integer.parseInt(d[5]),
                        Float.parseFloat(d[6]))
                : new TimberTransport(d[0], d[1], Integer.parseInt(d[2]),
                        Float.parseFloat(d[4]),
                        Float.parseFloat(d[5]),
                        d[6],
                        Float.parseFloat(d[7]));
    }
}

/* =======================
   MAIN CLASS
   ======================= */
public class UserInterfaceFull {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Utility utility = new Utility();

        System.out.println("Enter the Goods Transport details");
        GoodsTransport gt = utility.parseDetails(sc.nextLine());

        if (gt == null) return;

        System.out.println("Transporter id : " + gt.getTransportId());
        System.out.println("Date of transport : " + gt.getTransportDate());
        System.out.println("Rating of the transport : " + gt.getTransportRating());

        if (gt instanceof BrickTransport) {
            BrickTransport b = (BrickTransport) gt;
            System.out.println("Quantity of bricks : " + b.getBrickQuantity());
            System.out.println("Brick price : " + b.getBrickPrice());
        } else {
            TimberTransport t = (TimberTransport) gt;
            System.out.println("Type of the timber : " + t.getTimberType());
            System.out.println("Timber price per kilo : " + t.getTimberPrice());
        }

        System.out.println("Vehicle for transport : " + gt.vehicleSelection());
        System.out.println("Total charge : " + gt.calculateTotalCharge());
    }
}
