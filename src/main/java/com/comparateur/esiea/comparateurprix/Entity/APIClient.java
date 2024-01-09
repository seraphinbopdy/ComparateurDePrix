package com.comparateur.esiea.comparateurprix.Entity;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
public class APIClient {


    private static final String API_URL = "https://bus-api.blablacar.com/v3/stops";
    private static final String API_URL_Fares = "https://bus-api.blablacar.com/v1/fares";

    private static final String API_TOKEN = "N6xBKHvPylJPmLLzK0fnOw";
    private static OffsetDateTime offsetDateTime;

    public static CityTrajet getIDCity(String villeD, String villeA){

        CityTrajet cityTrajet = new CityTrajet();


        HttpURLConnection connection = null;

        try {
            // Create URL
            URL url = new URL(API_URL);

            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "Token " + API_TOKEN);

            int responseCode = connection.getResponseCode();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    ObjectMapper objectMapper = new ObjectMapper();
                    JsonNode jsonResponse = objectMapper.readTree(reader);

                    for (JsonNode field : jsonResponse) {
                        if (field.isArray()) {
                            for (JsonNode location : field) {

                                if(location.get("short_name").asText().toLowerCase().contains(villeD.toLowerCase())){
                                    CityStop cityStopStart = new CityStop();
                                    cityStopStart.setCityCode(location.get("id").asInt());
                                    cityStopStart.setShortName(location.get("short_name").asText());
                                    cityTrajet.getCityStopDepart().add(cityStopStart);

                                }

                                if(location.get("short_name").asText().toLowerCase().contains(villeA.toLowerCase())){
                                    CityStop cityStopEnd = new CityStop();
                                    cityStopEnd.setCityCode(location.get("id").asInt());
                                    cityStopEnd.setShortName(location.get("short_name").asText());
                                    cityTrajet.getCityStopArrive().add(cityStopEnd);
                                }
                            }


                        }
                    }
                } else {
                    System.out.println("Request Error. Code: " + responseCode);
                    // Handle errors here if needed
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }

        return cityTrajet;
    }


    public static List<AfficheTrajet> getTrajetVoyage(String VilleDepart, String VilleArrive, String dateTrajet){
        HttpURLConnection connection = null;
        List<AfficheTrajet> afficheTrajetList = new ArrayList<>();
        CityTrajet cityTrajetList = getIDCity(VilleDepart,VilleArrive);
        System.out.println("ListeTrajet"+ cityTrajetList);

        try {
            for(CityStop depart : cityTrajetList.getCityStopDepart()) {

                for (CityStop arrive : cityTrajetList.getCityStopArrive()) {

                    URL url = new URL(API_URL_Fares + "?date=" + dateTrajet.toString() + "&origin_id=" + depart.getCityCode() +
                            "&destination_id=" + arrive.getCityCode());
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setRequestProperty("Authorization", "Token " + API_TOKEN);

                    int responseCode = connection.getResponseCode();


                    try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                        if (responseCode == HttpURLConnection.HTTP_OK) {
                            ObjectMapper objectMapper = new ObjectMapper();
                            JsonNode jsonResponse = objectMapper.readTree(reader);
                            for (JsonNode field : jsonResponse) {
                                if (field.isArray()) {
                                    for (JsonNode location : field) {
                                        AfficheTrajet afficheTrajet =
                                                new AfficheTrajet(
                                                        depart.getShortName(),
                                                        arrive.getShortName(),
                                                        location.get("departure").asText(),
                                                        location.get("arrival").asText(),
                                                        location.get("price_cents").asInt()/100);
                                        afficheTrajetList.add(afficheTrajet);
                                        System.out.println(afficheTrajet);
                                    }
                                }
                            }
                        } else {
                            System.out.println("Request Error. Code: " + responseCode);
                        }
                    }

                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'  'HH:mm");
        DateTimeFormatter formatter_ = DateTimeFormatter.ofPattern("yyyy-MM-dd'  'HH:mm");
        for( AfficheTrajet aff : afficheTrajetList){
            OffsetDateTime offsetDateTime = OffsetDateTime.parse(aff.getDateDepart());
            String formattedDateTime = offsetDateTime.format(formatter);
            aff.setDateDepart(formattedDateTime);
            OffsetDateTime offsetDateTime_ = OffsetDateTime.parse(aff.getDateArrive());
            String formattedDateTime_ = offsetDateTime_.format(formatter_);
            aff.setDateArrive(formattedDateTime_);
        }
        return afficheTrajetList;
    }


/** Bloque de test de Récuperation des trajet
    public static void main(String[] args) {
        String dateTraget = "2024-01-20";

        List<AfficheTrajet> val = getTrajetVoyage("paris","marseille",dateTraget);
        System.out.println("Valeur Liste"+ val);
    }
*/

}
