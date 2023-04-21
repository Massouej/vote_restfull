## Projet Service Web de gestion de Sondages(vote)

### 1 - Description du projet

Concevoir et développer les points d'accès pour les fonctionnalités CRUD des sondages 
(et seulement les sondages, pas le système de votes). 
Permet de créer le backend en intégralité avec la base de données MySQL, 
le modèle de données JPA et le serveur Spring Boot.

### 2 - Pages du site :
Ce projet contient l'ensemble des endpoints nécessaires pour créer, lire, mettre à jour et supprimer 
des sondages de type "question : oui/non".


### 3 - Outils de réalisation :
Code réalisé avec : Intellij
Outils collaboratifs : GitHub
Framework utilisé : Spring Boot

### Bibliothèques utilisées :
- Spring Boot DevTools
- Spring Web
- Mysql driver
- Spring Data JPA
- Spring Validation
- Spring DOC

### 4 - Installation du projet :
Logiciel requis : Intellij, Google Chrome (Nécessaire pour réaliser les tests de validation)
1. Cloner le dépôt git
2. Se rendre dans le répertoire du projet
3. Lancer l'application VoteRestfullApplication.java pour exécuter l'API. 
4. Elle se lancera sur votre serveur local au port 8080.
5. Si votre port par défaut (8080) est déjà utilisé, vous pouvez venir le modifier directement
pour cela, il suffit d'aller dans "application.properties" qui se trouve 
dans le package "ressources" et de saisir :
server.port=numéro_de_port_souhaiter
exemple : server.port=8000

6. Test de validation à l'adresse suivante une fois le serveur lancé :
http://localhost:8080/swagger-ui/index.html#/

### Points d'entrée de l'API

- GET /rest/votes/ : Récupère la liste de tous les sondages dont la date de cloture est dans le futur
- GET /rest/votes/{id} : Lecture du sondage {id} et retour au format JSON
- POST /rest/votes/ : Création d'un nouveau sondage puis réponse HTTP 200
- PUT /rest/votes/{id} : Modification du sondage {id} puis réponse HTTP 200
- DELETE /rest/votes/{id} : Suppression du sondage {id} puis réponse HTTP 200

### 5 - Equipes :
Projet réalisé seul mais, évaluation par les pairs

Merci d'avoir pris le temp de lire le ReadMe