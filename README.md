# gamedates

## Config

Under pages in [application.yml](src/main/resources/application.yml) you can list all the links to the leagues.

Under teamswithindex you have to list all teams with a special index (since some leagues do have two additional fields
in their table).

If 'posting-enabled' is set to true (which is false by default) the posting to the google api is done.

## Run

Just run the main method in [FetchGameDates](src/main/java/api/FetchGameDates.java)

## Attention

The application creates an event for each gameday and posts it to the tc ka west google calendar.