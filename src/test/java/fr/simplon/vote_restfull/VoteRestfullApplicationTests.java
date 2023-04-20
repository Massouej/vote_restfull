package fr.simplon.vote_restfull;

import fr.simplon.vote_restfull.dao.impl.VoteRepository;
import fr.simplon.vote_restfull.entity.Vote;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = VoteRestfullApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class VoteRestfullApplicationTests {

	@LocalServerPort //port par défaut à 8080
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private VoteRepository repository;

	/**
	 * Cette méthode est executé avec la méthode @Test et vient supprimer toutes les données en BDD.
	 *
	 *@BeforeEach public void setUp()
	 */

	@BeforeEach
	public void setUp() {
		repository.deleteAll();
	}

	/**
	 * Cette méthode crée des données fictives et test la récupération de tous les sondages présents dans la BDD.
	 *
	 * @GetMapping("/rest/votes") @Test public void testGetVotes()
	 */
	@GetMapping("/rest/votes")
	@Test
	public void testGetVotes() {
		// Créer des sondages fictifs pour les tester
		Vote vote1 = new Vote("Vote 1", "Description du sondage 1", LocalDate.now(), LocalDate.now().plusDays(7), "John");
		Vote vote2 = new Vote("Vote 2", "Description du sondage 2", LocalDate.now(), LocalDate.now().plusDays(7), "Pierre");
		repository.saveAll(List.of(vote1, vote2));

		// Envoyer une requête GET à /rest/votes et récupérer la réponse
		ResponseEntity<Vote[]> responseEntity = restTemplate.getForEntity("http://localhost:" + port + "/rest/votes", Vote[].class);
		Vote[] votes = responseEntity.getBody();

		// Vérifier que la réponse contient les deux sondages créés
		assertEquals((HttpStatus.OK), responseEntity.getStatusCode());
	}

	/**
	 * Cette méthode vérifie si l’ajout d’un sondage fonctionne correctement dans la base de données.
	 *
	 * @PostMapping("/rest/votes") @Test void testAddVote()
	 */
	@PostMapping("/rest/votes")
	@Test
	void testAddVote() {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		Vote vote = new Vote("Test", "Test", LocalDate.now(), LocalDate.now().plusDays(7), "Test");
		HttpEntity<Vote> request = new HttpEntity<>(vote, headers);

		ResponseEntity<Vote> response = restTemplate.postForEntity("http://localhost:" + port + "/rest/votes", request, Vote.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody().getId());
		assertEquals("Test", response.getBody().getDescription());
	}

	/**
	 * Cette méthode crée un sondage dans la BDD , puis vérifie si la récupération d’un sondage à partir de la liste fonctionne.
	 *
	 * @GetMapping("/rest/votes/{id}") @Test void testGetVote()
	 */
	@GetMapping("/rest/votes/{id}")
	@Test
	void testGetVote() {
		RestTemplate restTemplate = new RestTemplate();
		Vote vote = new Vote("Test", "Test", LocalDate.now(), LocalDate.now().plusDays(7), "Test");
		Vote savedVote = repository.save(vote);
		ResponseEntity<Vote> response = restTemplate.getForEntity("http://localhost:" + port + "/rest/votes/" + savedVote.getId(), Vote.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals("Test", response.getBody().getNom());
	}

	/**
	 * Cette méthode crée un sondage dans la BDD pour les tests, puis vérifie si la suppression fonctionne correctement.
	 *
	 * @DeleteMapping("/rest/votes/{id}") @Test void testDelVote()
	 */
	@DeleteMapping("/rest/votes/{id}")
	@Test
	void testDelVote() {
		RestTemplate restTemplate = new RestTemplate();

		//Ajouter un sondage de test
		Vote votes = new Vote("Test", "Test", LocalDate.now(), LocalDate.now().plusDays(7), "Test");
		Vote savedVote = repository.save(votes);

		//Supprimer le sondage de test
		restTemplate.delete("http://localhost:" + port + "/rest/votes/" + savedVote.getId());

		//Vérifier que le sondage n'existe plus
		Optional<Vote> deletedVote= repository.findById(savedVote.getId());
		assertFalse(deletedVote.isPresent());
	}

	/**
	 * Requête de maj à l'endpoint spécifier
	 * sauf l'id et la date_creation
	 */
	@PutMapping("/rest/votes/{id}")
	@Test
	void testUpdateVote() {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		//Ajouter un sondage de test
		Vote votes = new Vote("Test", "Test", LocalDate.now(), LocalDate.now().plusDays(7), "Test");
		Vote savedVote = repository.save(votes);

		//Modifier le sondage de test
		savedVote.setDescription("Test modifié");
		HttpEntity<Vote> request = new HttpEntity<>(savedVote, headers);
		restTemplate.put("http://localhost:" + port + "/rest/votes/" + savedVote.getId(), request, Vote.class);

		//Vérifier que le sondage a été modifié
		Optional<Vote> updatedVote = repository.findById(savedVote.getId());
		assertTrue(updatedVote.isPresent());
		assertEquals("Test modifié", updatedVote.get().getDescription());
	}
}




