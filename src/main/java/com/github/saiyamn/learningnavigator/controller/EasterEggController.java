package com.github.saiyamn.learningnavigator.controller;

import com.github.saiyamn.learningnavigator.service.hiddenfeature.HiddenFeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hidden-feature")
public class EasterEggController {

    @Autowired
    private HiddenFeatureService hiddenFeatureService;

    @GetMapping(value = "/{number}")
    public ResponseEntity<String> hiddenFeature(@PathVariable String number){
        return new ResponseEntity<String>(hiddenFeatureService.getFactsAboutARandomNumber(number), HttpStatus.OK);
    }
}
