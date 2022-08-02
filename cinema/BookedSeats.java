package cinema;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.UUID;

public class BookedSeats {

    private UUID token;

    private Seat bookedSeat;

    public BookedSeats(Seat bookedSeat, UUID id) {
        this.bookedSeat = bookedSeat;
        this.token = id;
    }

    public void setBookedSeat(Seat bookedSeat) {
        this.bookedSeat = bookedSeat;
    }

    public Seat getBookedSeat() {
        return bookedSeat;
    }

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID id) {
        this.token = id;
    }

    public BookedSeats() {
        super();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookedSeats that = (BookedSeats) o;
        return bookedSeat.equals(that.bookedSeat);
    }
}
