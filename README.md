# DVDRental

## Introduction <br>
    This repo contains the solution for the film recommendations absed ont he customer Id input.

## Logic <br>
    SprintBootMain class is the startingpoint of the execution. Request is passed to the controller FilmRecommendationController class. <br>
    Request is handled, validated and sent to the service class for actual processing FilmRecommendationService class. Based on the requirements <br>
    the results are queried thru the JPA repository FilmRepository class. Logic has been made to use the mixture of both the entity and the query to <br>
    to fetch the results. Once it's processed JPA limits it to the first 10 objects and then the response is sent to the client via the same input mechanisms.

## Test(s) <br>
    Appropriate unit test cases (6) and a single integration tests have been written for clarity and to demonstrate the results. <br>

## How to run
    1. Clone the repo to an IDE. It's public for timebeing. (Recommend to use Intellij).
    2. Start the postgres container using the following command
        docker run -p 5432:5432 --name dvdrental -d dvdrental

    3. Run 'SprintBootMain' class.
    4. Hit the endpoint using any client ie. browser, postman etc.
      http://localhost:8080/api/recommendations/1
