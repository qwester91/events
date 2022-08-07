package ya.qwester345.classifier.controllers.Handlers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ya.qwester345.classifier.service.exeptions.InvalidDtoException;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalHandler {


    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handle(InvalidDtoException exception) {
        Map<String, Object> map = new HashMap<>();
        map.put("logref", "structured_error");

        List<Map<String, String>> errors = new ArrayList<>();

        errors.add(Map.of(
                "field", exception.getField(),
                "message", exception.getMessage()));



        map.put("errors", errors);

        return map;
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
