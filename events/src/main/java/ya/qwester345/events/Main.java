package ya.qwester345.events;

import ya.qwester345.events.service.utils.HttpClientValid;

import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        HttpClientValid clientValid = new HttpClientValid();
        UUID uuid = UUID.fromString("16c6c898-d300-47c3-ac2a-5d6b5f13493f");
        Boolean existInClassifiers = clientValid.isExistInClassifiers("http://localhost:81/api/v1/classifier/concert/category/", uuid);
        System.out.println(existInClassifiers);
    }
}
