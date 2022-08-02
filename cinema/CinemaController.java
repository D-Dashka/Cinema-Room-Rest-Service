package cinema;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class CinemaController {

    private final CinemaRoom cinemaRoom;

    public CinemaController() {
        this.cinemaRoom = new CinemaRoom();
    }

    @ExceptionHandler(CustomExceptions.class)
    public ResponseEntity<Map<String, String>> handleException(CustomExceptions e) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", e.getLocalizedMessage());
        return new ResponseEntity<>(errorResponse, e.getStatus());
    }

    @GetMapping("/seats")
    public CinemaRoom cinemaRoom() {
        return cinemaRoom;
    }

    @PostMapping("/purchase")
    public Map<String, Object> bookSeat(@RequestBody Seat seat) {
        if (seat.getRow() > cinemaRoom.getTotalRows() || seat.getColumn() > cinemaRoom.getTotalColumns() ||
            seat.getRow() < 1 || seat.getColumn() < 1) {
            throw new CustomExceptions("The number of a row or a column is out of bounds!", HttpStatus.BAD_REQUEST);
        }
        if (cinemaRoom.checkBooking(seat)) {
            throw new CustomExceptions("The ticket has been already purchased!", HttpStatus.BAD_REQUEST);
        }
        Map<String, Object> seatMap = new ConcurrentHashMap<>();
        for (Seat iterSeat : cinemaRoom.getAvailableSeats()) {
            if (iterSeat.getRow() == seat.getRow() && iterSeat.getColumn() == seat.getColumn()) {
                BookedSeats bookedSeat = new BookedSeats(iterSeat, UUID.randomUUID());
                cinemaRoom.getBookedSeats().add(bookedSeat);
                seatMap = cinemaRoom.turnSeatIntoMap(iterSeat, bookedSeat);
                cinemaRoom.deleteSeat(iterSeat);
                return seatMap;
            }
        }
        return null;
    }

    @PostMapping("/return")
    public Map<String, Object> returnTicket(@RequestBody BookedSeats bookedSeat) {
        for (BookedSeats bSeat : cinemaRoom.getBookedSeats()) {
            if (bookedSeat.getToken().equals(bSeat.getToken())) {
                Seat seatTmp = bSeat.getBookedSeat();
                cinemaRoom.deleteBookedSeat(bSeat);
                cinemaRoom.addSeat(seatTmp);
                return Map.of("returned_ticket", seatTmp);
            }
        }
        throw new CustomExceptions("Wrong token!", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/stats")
    public ResponseEntity<Statistic> requestStats(@RequestParam(required = false) String password) {
        if (password != null) {
            return new ResponseEntity<>(cinemaRoom.setStat(), HttpStatus.OK);
        } else {
            throw new CustomExceptions("The password is wrong!", HttpStatus.UNAUTHORIZED);
        }
    }
}
