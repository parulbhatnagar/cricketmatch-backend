package com.practical.controller;

import com.practical.model.Match;
import com.practical.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * This controller class provides methods for retrieving all matches.
 */
@RestController
@RequestMapping("/cricketinformer")
public class MatchController {

    /**
     * The service object used to interact with the MatchRepository.
     */
    @Autowired
    MatchService matchService;

    /**
     * Retrieves all matches from the MatchRepository.
     *
     * @return a list of Match objects representing all matches.
     */
    @GetMapping("/matches")
    public ResponseEntity<List<Match>> getMatches() { // List<Match> getAllMatches() {
        // Delegate the actual retrieval of matches to the MatchService
        return new ResponseEntity<>(matchService.getAllMatches(), HttpStatus.OK);
    }

    @GetMapping("/live")
    public ResponseEntity<?> getLiveMatchScores() throws InterruptedException {
        System.out.println("getting live match");
        return new ResponseEntity<>(this.matchService.getLiveMatches(), HttpStatus.OK);
    }

}
