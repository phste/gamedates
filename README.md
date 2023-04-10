# Welcome to gamedates

## Config in [application.yml](src/main/resources/application.yml)

- pages: Link to the leagues (
  i.e.: https://baden.liga.nu/cgi-bin/WebObjects/nuLigaTENDE.woa/wa/groupPage?championship=B2+S+2023&group=15)
- teams-with-index: List all teams with a special index (since some leagues do have two additional fields
  in their table, 'Spielort', 'Platz')
- posting-enabled: if set to true, the posting to the google api is enabled (is false by default)
- calendar-id: The id of the wanted calendar, "primary" by default. Add your specific club e-mail here
- home-team: Name (or at least part of the name) of the home team

## Authentication

You have to download a credentials.json from google cloud console, therefore you have to be an authorized person.
Read the documentation by google (https://developers.google.com/calendar/api/quickstart/java) for more informations.

## Run

Just run the main method in [FetchGameDates](src/main/java/api/FetchGameDates.java)

## General Information

- For the winter season you have to deal with timezones and time change which is not implemented yet
- Feel free to contact me in case of questions, bug's for ideas for features.
