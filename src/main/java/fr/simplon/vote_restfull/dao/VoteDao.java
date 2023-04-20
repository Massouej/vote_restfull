package fr.simplon.vote_restfull.dao;

import java.time.LocalDate;

public interface VoteDao {
    void saveVote(String description, String question, LocalDate date_creation, LocalDate date_cloture, String nom);
}
