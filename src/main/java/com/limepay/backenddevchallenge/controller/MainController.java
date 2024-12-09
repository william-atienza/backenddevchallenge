package com.limepay.backenddevchallenge.controller;

import com.limepay.backenddevchallenge.service.DirectorService;
import com.limepay.backenddevchallenge.service.DirectorServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class MainController {

    @Autowired
    private DirectorService service;

    @GetMapping
    public String index(){
        return "Hi Limepay!";
    }

    @GetMapping(path = "/api/directors")
    public List<String> getDirectors(@RequestParam int threshold){
        log.debug("threshold: {}", threshold);
        List<String> directors =  service.getDirectors(threshold);
        log.debug("directors: {}", directors);
        return directors;
    }
}
