package com.practical.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cricket_matches")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int matchId;


    private String matchHeading; //e.g. Ind Vs Ned

    private String matchDescription; // 45th Match Today at Blr Chinaswamy stadium
    private String teamOne; // Ind
    private String teamOneScore; // 252-3 (40.5 Ovrs)

    private String teamTwo; // Ned
    private String teamTwoScore; // 34/6 (10 Ovs)

    private String matchLiveText; // India Opt to bat first  ... or India won by 100 runs
    private String matchLink; // Link to cricinfo
    private String matchComplete;
    @Enumerated
    private MatchStatus matchStatus; // Live or Completed

    private Date matchDate = new Date();


    public void setMatchStatus(){
        if (this.matchComplete.isBlank()){
            this.matchStatus = MatchStatus.LIVE;
        }
        else{
            this.matchStatus = MatchStatus.COMPLETED;
        }
    }


}
