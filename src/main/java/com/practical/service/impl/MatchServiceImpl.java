package com.practical.service.impl;

import com.practical.model.Match;
import com.practical.repository.MatchRepo;
import com.practical.service.MatchService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class MatchServiceImpl implements MatchService {

    private MatchRepo matchRepo;

    @Autowired
    public MatchServiceImpl(MatchRepo matchRepo) {
        this.matchRepo = matchRepo;
    }

    @Override
    public List<Match> getAllMatches() {
        return matchRepo.findAll();
    }

    @Override
    public List<Match> getLiveMatches() {
        List<Match> matches = new ArrayList<>();
        try {
            String url = "https://www.cricbuzz.com/cricket-match/live-scores";
            Document document = Jsoup.connect(url).get();
            Elements liveScoreElements = document.select("div.cb-mtch-lst.cb-tms-itm");
            for (Element match : liveScoreElements) {
                HashMap<String, String> liveMatchInfo = new LinkedHashMap<>();
                String teamsHeading = match.select("h3.cb-lv-scr-mtch-hdr").select("a").text();
                String matchNumberVenue = match.select("span").text();
                Elements matchBatTeamInfo = match.select("div.cb-hmscg-bat-txt");
                String battingTeam = matchBatTeamInfo.select("div.cb-hmscg-tm-nm").text();
                String score = matchBatTeamInfo.select("div.cb-hmscg-tm-nm+div").text();
                Elements bowlTeamInfo = match.select("div.cb-hmscg-bwl-txt");
                String bowlTeam = bowlTeamInfo.select("div.cb-hmscg-tm-nm").text();
                String bowlTeamScore = bowlTeamInfo.select("div.cb-hmscg-tm-nm+div").text();
                String textLive = match.select("div.cb-text-live").text();
                String textComplete = match.select("div.cb-text-complete").text();
                //getting match link
                String matchLink = match.select("a.cb-lv-scrs-well.cb-lv-scrs-well-live").attr("href").toString();

                Match match1 = new Match();
                match1.setMatchHeading(teamsHeading);
                match1.setMatchDescription(matchNumberVenue);
                match1.setTeamOne(battingTeam);
                match1.setTeamOneScore(score);
                match1.setTeamTwo(bowlTeam);
                match1.setTeamTwoScore(bowlTeamScore);
                match1.setMatchLiveText(textLive);
                match1.setMatchLink(matchLink);
                match1.setMatchComplete(textComplete);
                match1.setMatchStatus();

                matches.add(match1);
//                update the match in database
                updateMatch(match1);


            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return matches;
    }

    private void updateMatch(Match match1) {

        Match match = this.matchRepo.findByMatchHeading(match1.getMatchHeading()).orElse(null);
        if (match == null) {
            this.matchRepo.save(match1);
        } else {

            match1.setMatchId(match.getMatchId());
            this.matchRepo.save(match1);
        }

    }

    @Override
    public List<Map<String, String>> getPointsTable() {
        return null;
    }
}
