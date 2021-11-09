# Movie Selector
An app designed to help you choose movies to watch for the evening. Includes the following features:
- Search for movies in the IMDb database
- Create your own list of movies
- Display movies by rating from IMDb
- Displaying a random movie from the list.

Includes intuitive terminal interface and possibility to run a web version (available from the terminal menu)

# Running
Setup DB (postgreSQL on docker)
> docker run -it --rm -p 5432:5432 -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres --name postgres -v $(pwd)/schema.sql:/docker-entrypoint-initdb.d/schema.sql postgres

Run
> mvn exec:java
 
# Technologies
- Maven
- IMDb API and
- postgreSQL and Docker
- Embedded Tomcat
- JUnit