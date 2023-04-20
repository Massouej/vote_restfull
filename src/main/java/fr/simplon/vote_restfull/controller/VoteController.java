package fr.simplon.vote_restfull.controller;

import fr.simplon.vote_restfull.dao.impl.VoteRepository;
import fr.simplon.vote_restfull.entity.Vote;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Valid
@RestController
public class VoteController {
    private final VoteRepository repo;

    public VoteController(VoteRepository fr) {
         this.repo = fr;

         //this.repo.save(new Vote("achat d'une console pour le foyer","Qu'elle console on met ?", LocalDate.now(), LocalDate.of(2023,5,17), "Michel"));
         //this.repo.save(new Vote("Choix du repas de la semaine","Pizza ? mardi", LocalDate.now(), LocalDate.of(2023,6,18), "Mélanie"));
    }


    @GetMapping("/rest/votes")
    public List<Vote> getVotes() {
        return repo.findAll();
    }

    @GetMapping("/rest/votes/{id}")
    public Vote getVote(@PathVariable Long id) {
        return repo.findById(id).orElseThrow();
    }

    @PostMapping("/rest/votes")
    public Vote addVote(@RequestBody @Valid Vote newVote) {
        return repo.save(newVote);
    }

    @DeleteMapping("/rest/votes/{id}")
    public void delVote(@PathVariable long id) {
        repo.deleteById(id);
    }

    @PutMapping("/rest/votes/{id}")
    public Vote updateVote(@RequestBody @Valid Vote newVote, @PathVariable long id) {
        return repo.findById(id).map(vote -> {
            vote.setDescription(newVote.getDescription());
            vote.setQuestion(newVote.getQuestion());
            vote.setDate_creation(newVote.getDate_creation());
            vote.setDate_cloture(newVote.getDate_cloture());
            vote.setNom(newVote.getNom());
            return repo.save(vote);
        }).orElseGet(() -> {
            newVote.setId(id);
            return repo.save(newVote);
        });
    }

}

