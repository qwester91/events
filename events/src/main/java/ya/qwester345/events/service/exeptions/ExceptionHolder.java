package ya.qwester345.events.service.exeptions;

import java.util.ArrayList;
import java.util.List;

public class ExceptionHolder extends Exception {
    private List<Exception> exceptionList;

    public ExceptionHolder() {
        this.exceptionList = new ArrayList<>();
    }

    public void addException(Exception e){
        exceptionList.add(e);
    }

    public List<Exception> getExceptionList() {
        return exceptionList;
    }


}
