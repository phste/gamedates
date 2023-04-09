package api;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import model.Game;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;


class OAuthCalendarTest {

    @Test
    void dateTimeStart() {
        String date = "18.06.2023";
        String time = "09:30";
        Game game = new Game("Herren", "TC Karlsruhe-West 1", "Karlsruher ETV 3", date, time, true);
        DateTime expected = new DateTime("2023-06-18T09:30:00+02:00");
        assertThat(OAuthCalendar.getDateTime(game, false)).isEqualTo(expected);
    }

    @Test
    void dateTimeFalse() {
        String date = "15.11.2023";
        String time = "15:00";
        Game game = new Game("Herren", "TC Karlsruhe-West 1", "Karlsruher ETV 3", date, time, true);
        DateTime expected = new DateTime("2023-11-15T21:00:00+02:00");
        assertThat(OAuthCalendar.getDateTime(game, true)).isEqualTo(expected);
    }

    @Test
    void eventGeneration() {
        String date = "18.06.2023";
        String time = "09:30";
        Game game = new Game("Herren", "TC Karlsruhe-West 1", "Karlsruher ETV 3", date, time, true);

        DateTime start = new DateTime("2023-06-18T09:30:00+02:00");
        DateTime end = new DateTime("2023-06-18T15:30:00+02:00");
        Event expected = new Event();
        expected.setStart(new EventDateTime().setDateTime(start).setTimeZone("Europe/Berlin"));
        expected.setEnd(new EventDateTime().setDateTime(end).setTimeZone("Europe/Berlin"));
        expected.setSummary("Herren: TC Karlsruhe-West 1 - Karlsruher ETV 3 - [HEIM]");
        expected.setColorId("10");

        assertThat(OAuthCalendar.createEvent(game)).isEqualTo(expected);
    }

}