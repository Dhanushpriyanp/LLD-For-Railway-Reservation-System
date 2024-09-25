import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Train {
    public int id;
    HashMap<LocalDate, ArrayList<Ticket>> ticketMap = new HashMap<>();
    List<Coach> coach_list;
    List<Station> station_list;

    public Train(int id, List<Coach> coach_list, List<Station> station_list) {
        this.id = id;
        this.coach_list = coach_list;
        this.station_list = station_list;
    }

    public int get_total_seat(boolean isAc) {
        int count = 0;
        for (Coach c : coach_list) {
            if (c.AC == isAc) {
                count += c.seat_count;
            }
        }
        return count;
    }
}
