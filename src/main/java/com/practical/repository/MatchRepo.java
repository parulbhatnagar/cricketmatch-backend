package com.practical.repository;

import com.practical.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface MatchRepo extends JpaRepository<Match,Integer> {
    // fetch match for a specific team
    Optional<Match> findByMatchHeading(String teamHeading);
}
