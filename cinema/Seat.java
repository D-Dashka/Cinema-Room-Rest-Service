package cinema;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.context.annotation.Bean;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class Seat {

    private int row;
    private int column;
    private int price;

    @JsonIgnore
    private UUID token;

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getPrice() { return price; }

    public void setPrice(int price) { this.price = price; }

    public void setToken() {
        this.token = token;
    }

    public UUID getToken() {
        return token;
    }

    public Seat(int row, int column, int price) {
        this.column = column;
        this.row = row;
        this.price = price;
    }

    public Seat(int row, int column, int price, UUID id) {
        this.column = column;
        this.row = row;
        this.price = price;
        this.token = id;
    }

    //to deserialize class
    public Seat() {
        super();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seat that = (Seat) o;
        return (row == that.row) && (column == that.column);
    }
}
