package com.limepay.backenddevchallenge.service;

import org.springframework.stereotype.Service;

import java.util.List;

public interface DirectorService {
    public List<String> getDirectors(int threshold);
}
