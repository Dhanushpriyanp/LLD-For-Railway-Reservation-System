import java.time.LocalDate;

public class Ticket {
    public Passenger passenger;
    public int price;
    public LocalDate date;
    public Station src;
    public Station destination;
    public int booking_id;
    public int train_id;
    public boolean isAc;
    public static int count = 1;

    public Ticket(Passenger passenger, int price, LocalDate date, Station src, Station destination, int train_id, boolean isAc) {
        this.passenger = passenger;
        this.price = price;
        this.date = date;
        this.src = src;
        this.destination = destination;
        this.booking_id = count++;
        this.train_id = train_id;
        this.isAc = isAc;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "booking_id=" + booking_id +
                ", passenger=" + passenger +
                ", price=" + price +
                ", date=" + date +
                ", src=" + src +
                ", destination=" + destination +
                ", train_id=" + train_id +
                '}';
    }
}
