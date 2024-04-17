package com.practical.service;

import com.practical.model.Match;

import java.util.List;
import java.util.Map;

public interface MatchService {

    // get all matches
    List<Match> getAllMatches();

    // get live matches
    List<Match> getLiveMatches();

    // get IPL points table
    List<Map<String,String>> getPointsTable();


}
