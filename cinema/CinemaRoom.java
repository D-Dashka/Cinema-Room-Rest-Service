package cinema;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class CinemaRoom {
    private final int totalRows = 9;
    private final int totalColumns = 9;
    private List<Seat> availableSeats;
    @JsonIgnore
    private List<BookedSeats> bookedSeats;
    @JsonIgnore
    private Statistic stat;

    public int getTotalRows() {
        return totalRows;
    }

    public int getTotalColumns() {
        return totalColumns;
    }

    public void setAvailableSeats(List<Seat> availableSeats) {
        this.availableSeats = availableSeats;
    }

    public List<Seat> getAvailableSeats() {
        return availableSeats;
    }

    public List<BookedSeats> getBookedSeats() {
        return bookedSeats;
    }

    public void deleteSeat(Seat seat) {
        availableSeats.remove(seat);
    }
    public void addSeat(Seat seat) {
        availableSeats.add(seat);
    }

    public void deleteBookedSeat(BookedSeats bookedSeat) {
        bookedSeats.remove(bookedSeat);
    }

    public CinemaRoom() {
        this.availableSeats = createCinemaRoom();
        this.bookedSeats = new ArrayList<>();
    }

    private List<Seat> createCinemaRoom() {
        int r, c, price;
        List<Seat> seats = new ArrayList<>();
        for (r = 0; r < totalRows; r++) {
            for (c = 0; c < totalColumns; c++) {
                price = r + 1 <= 4 ? 10 : 8;
                Seat seat = new Seat(r + 1, c + 1, price);
                seats.add(seat);
            }
        }
        return seats;
    }

    public boolean checkBooking(Seat seat) {
        int row = seat.getRow();
        int column = seat.getColumn();
        for (BookedSeats bSeat : bookedSeats) {
            if (bSeat.getBookedSeat().getRow() == row &&
                bSeat.getBookedSeat().getColumn() == column) {
                return true;
            }
        }
       return false;
    }

    public Map<String, Object> turnSeatIntoMap(Seat seat, BookedSeats bookedSeat) {
        Map<String, Object> newSeatMap = new ConcurrentHashMap<>();
        newSeatMap.put("ticket", seat);
        newSeatMap.put("token", bookedSeat.getToken());
        return newSeatMap;
    }

    public Statistic setStat() {
        long income = 0;
        int availableSeats = this.availableSeats.size();
        long purchasedTickets = this.bookedSeats.size();
        for (BookedSeats bSeats : this.bookedSeats) {
            income += bSeats.getBookedSeat().getPrice();
        }
        return new Statistic(income, availableSeats, purchasedTickets);
    }

}
