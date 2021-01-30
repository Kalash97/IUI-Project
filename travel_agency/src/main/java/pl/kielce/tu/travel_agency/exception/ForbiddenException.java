package pl.kielce.tu.travel_agency.exception;

public class ForbiddenException extends Exception {
    public ForbiddenException() {
    }

    public ForbiddenException(String message) {
        super(message);
    }
}
