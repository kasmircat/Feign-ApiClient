package fr.perso.client.api;

import feign.Param;
import feign.RequestLine;
import fr.perso.client.api.domain.Meteo;

public interface ClientApi {

    //London,uk
    //fd398d44ff2bd0f8a7522942303aa4bd
    @RequestLine("GET /weather?q={city}&appid={apikey}&units=metric")
    Meteo getMeteo(@Param("city") String city, @Param("apikey") String apikey);

}
