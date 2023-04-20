package fr.simplon.vote_restfull.dao.impl;

import fr.simplon.vote_restfull.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {
}
