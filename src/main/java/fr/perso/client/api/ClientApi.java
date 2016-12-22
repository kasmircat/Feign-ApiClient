package fr.perso.client.api;

import feign.Param;
import feign.RequestLine;

/**
 * Created by s.vidal on 22/12/2016.
 */
public interface ClientApi {

    //London,uk
    //fd398d44ff2bd0f8a7522942303aa4bd
    @RequestLine("GET /weather?q={city}&appid={apikey}&units=metric")
    public String getMeteo(@Param("city") String city, @Param("apikey") String apikey);

}
