package fr.perso.client.api;

import feign.Feign;
import feign.jackson.JacksonDecoder;
import fr.perso.client.api.domain.Meteo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class Execute implements Callable<Map<Integer,String>> {

    public int key;
    public Execute(int key){this.key=key;};

    public static void main(String[] args) {

        new Execute(-1).run();
/*        System.out.println("City : " + meteo.getName());
        System.out.println("Humidity : " + meteo.getMain().getHumidity());
        System.out.println("Actual Temperature : " + meteo.getMain().getTemp());
        System.out.println("Minimal Temperature : " + meteo.getMain().getTemp_min());
        System.out.println("Maximal Temperature : " + meteo.getMain().getTemp_max());*/

    }


    public void run(){
        ExecutorService executor = Executors.newCachedThreadPool();
        //newFixedThreadPool(100);
        List<Future<Map<Integer,String>>> list = new ArrayList<>();

        for(int i=0; i< 50; i++){
            Callable<Map<Integer,String>> callable = new Execute(i);
            Future<Map<Integer,String>> future = executor.submit(callable);
            list.add(future);
        }
        for(Future<Map<Integer,String>> fut : list){
            try {
                System.out.println(fut.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        executor.shutdown();

    }

    @Override
    public Map<Integer,String> call() throws Exception {

        //String city = args[0];
        //String apikey = System.getProperty("apikey");
        String city = "Gradignan,fr";
        String apikey = "fd398d44ff2bd0f8a7522942303aa4bd";

        System.out.println(city + " # " + apikey);

        ClientApi clientApi = Feign.builder()
                .decoder(new JacksonDecoder())
                .target(ClientApi.class, "http://api.openweathermap.org/data/2.5");
        Meteo meteo = clientApi.getMeteo(city, apikey);

        Map<Integer,String> map = new HashMap<>();
        map.put(key,meteo.getName());

        return map;
    }
}
