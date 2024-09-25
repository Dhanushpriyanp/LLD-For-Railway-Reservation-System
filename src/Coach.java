public class Coach {
    public boolean AC;
    public int seat_count;
    public int price;
    public static int surge_val;

    public Coach(boolean AC, int seat_count, int price, int surge_val) {
        this.AC = AC;
        this.seat_count = seat_count;
        this.price = price;
        this.surge_val = surge_val;
    }
}
