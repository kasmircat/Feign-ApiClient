package fr.perso.client.api;

import feign.Feign;
import feign.gson.GsonDecoder;

/**
 * Created by s.vidal on 22/12/2016.
 */
public class Execute {

    public static void main(String[] args) {

        String city = args[0];
        String apikey = System.getProperty("apikey");

        System.out.println(city);
        System.out.println(apikey);

        ClientApi clientApi = Feign.builder()
                .decoder(new GsonDecoder())
                .target(ClientApi.class, "http://api.openweathermap.org/data/2.5");

        System.out.println(clientApi.getMeteo(city, apikey));
    }

}
