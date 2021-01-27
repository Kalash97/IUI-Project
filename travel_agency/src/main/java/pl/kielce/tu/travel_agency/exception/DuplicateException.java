package pl.kielce.tu.travel_agency.exception;

public class DuplicateException extends Exception {
    public DuplicateException() {
    }

    public DuplicateException(String message) {
        super(message);
    }
}
