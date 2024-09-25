import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Station> station_list = Arrays.asList(
                new Station("Chennai"),
                new Station("Chengalpet"),
                new Station("Tindivanam"),
                new Station("Villupuram"),
                new Station("Trichy")
        );
        List<Coach> coach_list = Arrays.asList(new Coach(true, 5, 20, 5), new Coach(false, 10, 10, 5));
        Train train1 = new Train(1, coach_list, station_list);

        BookTicket obj = new BookTicket();
        obj.add_train(train1);
        Passenger p = new Passenger("Dhanush", 21);
        LocalDate date = LocalDate.now();
        Station src = new Station("Chennai");
        Station dest = new Station("Trichy");
        int ticketId1 = obj.book(p, true, date, src, dest, 1);
        if (ticketId1 != 0) obj.print_ticket(ticketId1);

        int ticketId2 = obj.book(p, false, date, src, dest, 1);
        if (ticketId2 != 0) obj.print_ticket(ticketId2);

        // Cancel one ticket
        obj.cancel_ticket(ticketId1);

        // Book another ticket after cancellation
        int ticketId3 = obj.book(p, true, date, src, dest, 1);
        if (ticketId3 != 0) obj.print_ticket(ticketId3);

        // Print the total revenue
        obj.print_revenue(date);
    }
}
