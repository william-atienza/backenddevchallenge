package com.limepay.backenddevchallenge.controller;

import com.limepay.backenddevchallenge.service.DirectorService;
import com.limepay.backenddevchallenge.service.DirectorServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MainController {
    Logger log = LoggerFactory.getLogger(DirectorServiceImpl.class);

    @Autowired
    private DirectorService service;

    @GetMapping
    public String index(){
        return "Hi Limepay!";
    }

    @GetMapping(path = "/api/directors")
    public List<String> getDirectors(@RequestParam int threshold){
        return  service.getDirectors(threshold);
    }
}
