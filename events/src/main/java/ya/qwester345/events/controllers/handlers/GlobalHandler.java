package ya.qwester345.events.controllers.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ya.qwester345.events.service.exeptions.InvalidDtoException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalHandler {

    @ExceptionHandler

    public List<Map<String, Object>> handle(InvalidDtoException exception) {
        List<Map<String,Object>> list= new ArrayList<>();

        list.add(Map.of(
                "logref", "error",
                "message", exception.getMessage()
        ));
        return list;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public List<Map<String, Object>> handle(RuntimeException exception) {
        List<Map<String,Object>> list= new ArrayList<>();

        list.add(Map.of(
                "logref", "error",
                "message", exception.getMessage()
        ));
        return list;
    }


}
