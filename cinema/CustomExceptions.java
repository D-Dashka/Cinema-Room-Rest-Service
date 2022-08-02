package cinema;

import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

public class CustomExceptions extends RuntimeException {
   private String errorMessage;
   private HttpStatus status;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public CustomExceptions(String cause, HttpStatus status) {
        super(cause);
        this.status = status;
    }
}
