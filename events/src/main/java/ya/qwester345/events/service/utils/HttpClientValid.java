package ya.qwester345.events.service.utils;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.util.UUID;
@Component
public class HttpClientValid {
    private RestTemplate restTemplate;

    public HttpClientValid() {
        this.restTemplate = new RestTemplate();
    }

    public boolean isExistInClassifiers(String url, UUID uuid){
        URI getUrl = URI.create(url + uuid);

        HttpStatus status =null;
        Boolean isExist = false;
        try {
            status = restTemplate.getRequestFactory().createRequest(getUrl, HttpMethod.GET).execute().getStatusCode();
        } catch (IOException e) {
            e.getMessage();
        }
        if (status.is2xxSuccessful() ){
            isExist = true;
        }
           return  isExist;
    }
}
