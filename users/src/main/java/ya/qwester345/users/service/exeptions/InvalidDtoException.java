package ya.qwester345.users.service.exeptions;

public class InvalidDtoException extends IllegalArgumentException{

   private String field;
   private String message;

    public InvalidDtoException(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    @Override
    public String getMessage() {
        return message;
    }
}