package fr.simplon.vote_restfull.dao.impl;

import fr.simplon.vote_restfull.dao.VoteDao;
import fr.simplon.vote_restfull.entity.Vote;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

public class VoteDaoImpl implements VoteDao {
    @Autowired
    private VoteRepository voteRepository;

    @Override
    public void saveVote(String description, String question, LocalDate date_creation, LocalDate date_cloture, String nom) {
        Vote vote = new Vote();
        vote.setDescription(description);
        vote.setQuestion(question);
        vote.setDate_creation(date_creation);
        vote.setDate_cloture(date_cloture);
        vote.setNom(nom);
        voteRepository.save(vote);
    }
}
