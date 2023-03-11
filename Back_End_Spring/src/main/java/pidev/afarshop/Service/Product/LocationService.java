package pidev.afarshop.Service.Product;

import pidev.afarshop.Entity.ClientLocationRequest;
import pidev.afarshop.Entity.User;
import pidev.afarshop.Repository.UserRepository;
import pidev.afarshop.Repository.clientLocationRequestRepository;
import pidev.afarshop.Service.ICRUDService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@AllArgsConstructor
public class LocationService implements ILocationService {
    //clientLocationRequestRepository clientLocationRequestRepository;

    public String getGeolocation() throws JsonProcessingException {
        WebClient webClient = WebClient.create();
        String url = "https://www.googleapis.com/geolocation/v1/geolocate?key=AIzaSyAvHALSEQwNE3b-b7eHSZIDv-KK1wr7CRQ";
        Mono<String> result = webClient.post()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class);
        String response = result.block();

        /*
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response);
        double latitude = root.get("location").get("lat").asDouble();
        double longitude = root.get("location").get("lng").asDouble();
        */

       /*
       ClientLocationRequest clientLocationRequest = new ClientLocationRequest();
        clientLocationRequest.setLatitude(latitude);
        clientLocationRequest.setLongitude(longitude);
        clientLocationRequestRepository.save(clientLocationRequest);
       */

        //return "Latitude: " + latitude + " Langitude : " + longitude;
        return response;
    }
}