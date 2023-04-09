# Welcome to gamedates powered by TC Karlsruhe-West e.V.

## Config in [application.yml](src/main/resources/application.yml)

- pages: List to the leagues
- teamsWithIndex: List all teams with a special index (since some leagues do have two additional fields
  in their table)
- posting-enabled: if set to true, the posting to the google api is enabled (is false by default)

## Authentication

You have to download a credentials.json from google cloud console, therefore you have to be an authorized person.

## Run

Just run the main method in [FetchGameDates](src/main/java/api/FetchGameDates.java)

## Attention

- For the winter season you have to deal with timezones and time change