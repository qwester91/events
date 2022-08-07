package ya.qwester345.events.service.utils.httpClients;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ya.qwester345.events.service.exeptions.InvalidDtoException;
import ya.qwester345.events.service.utils.api.IClientClassifiers;

import java.io.IOException;
import java.net.URI;
import java.rmi.AccessException;
import java.util.UUID;
@Component
public class HttpClientClassifiers implements IClientClassifiers {
    private RestTemplate restTemplate;
    @Value("${classifiers.category}")
    private String categoryUrl;
    @Value("${classifiers.country}")
    private String countryUrl;


    public HttpClientClassifiers() {
        this.restTemplate = new RestTemplate();
    }

    public boolean isCountryExistInClassifiers(UUID uuid) {
        URI getUrl = URI.create(countryUrl + uuid);
        return isExist(getUrl, uuid);
    }

    public boolean isCategoryExistInClassifiers(UUID uuid) {
        URI getUrl = URI.create(categoryUrl + uuid);
        return isExist(getUrl, uuid);
    }


    private boolean isExist(URI url,UUID uuid) {

        HttpStatus status =null;
        boolean isExist = false;
        try {
            status = restTemplate.getRequestFactory().createRequest(url, HttpMethod.GET).execute().getStatusCode();
        } catch (IOException e){
           throw new RuntimeException("не достучались к классифаеру");
        }
        if (status.is2xxSuccessful() ){
            isExist = true;
        }
           return  isExist;
    }
}
