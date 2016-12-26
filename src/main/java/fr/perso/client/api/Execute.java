package fr.perso.client.api;

import feign.Feign;
import feign.jackson.JacksonDecoder;
import fr.perso.client.api.domain.Meteo;

public class Execute {

    public static void main(String[] args) {

        String city = args[0];
        String apikey = System.getProperty("apikey");

        System.out.println(city);
        System.out.println(apikey);

        ClientApi clientApi = Feign.builder()
                .decoder(new JacksonDecoder())
                .target(ClientApi.class, "http://api.openweathermap.org/data/2.5");

        Meteo meteo = clientApi.getMeteo(city, apikey);
        System.out.println("City : " + meteo.getName());
        System.out.println("Humidity : " + meteo.getMain().getHumidity());
        System.out.println("Actual Temperature : " + meteo.getMain().getTemp());
        System.out.println("Minimal Temperature : " + meteo.getMain().getTemp_min());
        System.out.println("Maximal Temperature : " + meteo.getMain().getTemp_max());
    }

}
