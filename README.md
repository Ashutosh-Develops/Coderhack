# Coderhack
Coderhack is RESTful api service that manages leaderboard for coding platform.

## Application features
  * Enables a user to register and participate in the contest.
  * User is assigned badges based on the score achieved in the contest.
    * 1 <= Score < 30 Then Code Ninja
    * 30 <= Score < 60 Then Code Champ
    * 60 <= Score <= 100 Then Code Master
  * Leaderboard display leading participants based on  their score.

## Tech stack
  * Java, Spring boot, MongoDB, Mockito , Junit

## Getting Started

  * Download the repository on local machine.
  * Import the downloaded project in your editor, ex- Intellij, Eclipse, or VSCode.
  * The project uses MongoDB as the database, so make sure to have a running instance on MongoDB on your localhost or have access to cloud instance.
  * Make changes to the application.properties file to add your database credentials.
  * The project is setup using embedded tomcat container, so project can be run directly through editor without setting up external application server.
  * Check the link below to understand api endpoints.
    * [Coderhack Postman collection](https://documenter.getpostman.com/view/37617583/2sA3s4mqYa)
