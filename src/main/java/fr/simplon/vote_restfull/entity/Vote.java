package fr.simplon.vote_restfull.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

import jakarta.validation.constraints.*;

@Entity
@Table(name = "votes")
public class Vote {
    public Vote() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public LocalDate getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(LocalDate date_creation) {
        this.date_creation = date_creation;
    }

    public LocalDate getDate_cloture() {
        return date_cloture;
    }

    public void setDate_cloture(LocalDate date_cloture) {
        this.date_cloture = date_cloture;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Vote(String description, String question, LocalDate date_creation, LocalDate date_cloture, String nom) {
        this.description = description;
        this.question = question;
        this.date_creation = date_creation;
        this.date_cloture = date_cloture;
        this.nom = nom;
    }

    @NotBlank
    @Size(min = 3, max = 120)
    private String description;
    //@NotNull
    @Size(max = 120)
    private String question;
    @NotNull
    @Column(name = "date_creation", updatable = false)
    private LocalDate date_creation;
    @NotNull
    @Future
    private LocalDate date_cloture;
    @NotBlank
    @Size(max = 64)
    private String nom;
}


