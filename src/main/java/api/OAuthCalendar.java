package api;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;

import com.google.api.client.util.DateTime;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import model.Game;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.*;

public class OAuthCalendar {
    /**
     * Application name.
     */
    private static final String APPLICATION_NAME = "gamedates";
    /**
     * Global instance of the JSON factory.
     */
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    /**
     * Directory to store authorization tokens for this application.
     */
    private static final String TOKENS_DIRECTORY_PATH = "tokens";

    /**
     * Global instance of the scopes required by this quickstart.
     * If modifying these scopes, delete your previously saved tokens/ folder.
     */
    private static final Set<String> SCOPES = CalendarScopes.all();
    private static final String CREDENTIALS_FILE_PATH = "/credentials.json";

    /**
     * Creates an authorized Credential object.
     *
     * @param HTTP_TRANSPORT The network HTTP Transport.
     * @return An authorized Credential object.
     * @throws IOException If the credentials.json file cannot be found.
     */
    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT)
            throws IOException {
        // Load client secrets.
        InputStream in = OAuthCalendar.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
        }
        GoogleClientSecrets clientSecrets =
                GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        //returns an authorized Credential object.
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }

    public static void generateAndPostEvent(Game game) throws IOException, GeneralSecurityException {
        // Build a new authorized API client service.
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        Calendar service =
                new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                        .setApplicationName(APPLICATION_NAME)
                        .build();

        // Create an event
        Event event = createEvent(game);

        String calendarId = "tcka.west@gmail.com";

        event = service.events().insert(calendarId, event).execute();
        System.out.printf("Event created: %s", event.getHtmlLink());
    }

    protected static Event createEvent(Game game) {
        Event event = new Event();
        DateTime startDateTime = getDateTime(game, false);
        DateTime endDateTime = getDateTime(game, true);
        EventDateTime start = new EventDateTime().setDateTime(startDateTime).setTimeZone("Europe/Berlin");
        EventDateTime end = new EventDateTime().setDateTime(endDateTime).setTimeZone("Europe/Berlin");
        event.setStart(start);
        event.setEnd(end);
        event.setSummary(game.getSummary());
        event.setColorId("10");
        return event;
    }

    protected static DateTime getDateTime(Game game, boolean isEnd) {
        String[] date = game.getDate().split("\\.");
        String[] time = game.getTime().split(":");
        // Day pattern: 18.06.2023
        String day = date[0];
        String month = date[1];
        String year = date[2];
        // Time pattern: 09:30
        String hour = time[0];
        if (isEnd) {
            // We calculate each game day with 6 hours
            hour = String.valueOf(Integer.parseInt(hour) + 6);
        }
        String minute = time[1];
        return new DateTime(String.format("%s-%s-%sT%s:%s:00+02:00", year, month, day, hour, minute));
    }
}