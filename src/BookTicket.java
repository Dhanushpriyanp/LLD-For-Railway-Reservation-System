import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;

public class BookTicket {
    public static HashMap<LocalDate, Integer> totalRevenue = new HashMap<>();
    public static HashMap<Integer, Ticket> ticketMap = new HashMap<>();
    public static ArrayList<Train> trainList = new ArrayList<>();
    public static HashMap<Integer, Train> trainMap = new HashMap<>();
    public static ArrayList<Ticket> ticketList = new ArrayList<>();

    public void add_train(Train train1) {
        trainList.add(train1);
        trainMap.put(train1.id, train1);
    }

    public void add_ticket(Ticket t) {
        ticketList.add(t);
        ticketMap.put(t.booking_id, t);
    }

    public int book(Passenger p, boolean isAc, LocalDate date, Station src, Station dest, int id) {
        if (!trainMap.containsKey(id)) {
            System.out.println("No train available");
            return 0;
        }

        Train train = trainMap.get(id);
        int tot_seats = train.get_total_seat(isAc);
        ArrayList<Ticket> booked_ticket = train.ticketMap.get(date);
        int available = tot_seats - check_avail(booked_ticket, train, src, dest, isAc);

        System.out.println("Available tickets are: " + available);

        if (available > 0) {
            Ticket.count++;
            Ticket t = new Ticket(p, calculate_price(isAc), date, src, dest, id, isAc);
            train.ticketMap.computeIfAbsent(date, k -> new ArrayList<>()).add(t);
            add_ticket(t);
            add_revenue(date, t.price);
            return t.booking_id;
        } else {
            System.out.println("No available ticket");
            return 0;
        }
    }

    public int check_avail(ArrayList<Ticket> booked_ticket, Train train, Station src, Station dest, boolean isAc) {
        int count = 0;
        if (booked_ticket != null) {
            for (Ticket ticket : booked_ticket) {
                if (!(train.station_list.indexOf(ticket.destination) < train.station_list.indexOf(src) ||
                        train.station_list.indexOf(ticket.src) > train.station_list.indexOf(dest)) && ticket.isAc == isAc) {
                    count++;
                }
            }
        }
        return count;
    }

    public void cancel_ticket(int id) {
        Ticket t = ticketMap.get(id);
        if (t != null) {
            Train train = trainMap.get(t.train_id);
            train.ticketMap.get(t.date).remove(t);
            if (!t.isAc) {
                System.out.println("Full refund processed for ticket: " + t.booking_id);
            } else {
                System.out.println("AC ticket cancelled. Surge charge not refunded.");
            }
            ticketMap.remove(id);
        } else {
            System.out.println("Ticket not found.");
        }
    }

    public void print_ticket(int id) {
        Ticket t = ticketMap.get(id);
        if (t != null) {
            System.out.println(t);
        } else {
            System.out.println("Ticket not found");
        }
    }

    private int calculate_price(boolean isAc) {
        return isAc ? 20 + Coach.surge_val++ : 10;
    }

    private void add_revenue(LocalDate date, int price) {
        totalRevenue.put(date, totalRevenue.getOrDefault(date, 0) + price);
    }

    public void print_revenue(LocalDate date) {
        System.out.println("Revenue for the month: " + Month.from(date) + " is " + calculate_monthly_revenue(date));
        System.out.println("Revenue for the year: " + date.getYear() + " is " + calculate_yearly_revenue(date));
    }

    private int calculate_monthly_revenue(LocalDate date) {
        int revenue = 0;
        for (LocalDate d : totalRevenue.keySet()) {
            if (d.getYear() == date.getYear() && d.getMonth() == date.getMonth()) {
                revenue += totalRevenue.get(d);
            }
        }
        return revenue;
    }

    private int calculate_yearly_revenue(LocalDate date) {
        int revenue = 0;
        for (LocalDate d : totalRevenue.keySet()) {
            if (d.getYear() == date.getYear()) {
                revenue += totalRevenue.get(d);
            }
        }
        return revenue;
    }
}
