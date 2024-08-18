package com.github.saiyamn.learningnavigator.service.hiddenfeature;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HiddenFeatureService {


    public String getFactsAboutARandomNumber(String number){

        String BASE_URL_PATH="http://numbersapi.com/";
        String url=BASE_URL_PATH+number;
        RestTemplate restTemplate=new RestTemplate();
        ResponseEntity<String> response=null;
        try{
            response=restTemplate.getForEntity(url,String.class);
        }catch(Exception e){
            return "Invalid Number";
        }

        // if status code is 200 then return response
        return response.getBody();
    }
}
