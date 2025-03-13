package pl.edu.pw.mini.ui.calendar;

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
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collections;

public class GoogleAuth {
    private static final String CLIENT_SECRET_FILE = "client_secret.json"; //należy zastąpić
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();

    private static Credential credential;
    private static Calendar calendarService;

    private static GoogleClientSecrets getClientSecrets() throws IOException {
        InputStream in = GoogleAuth.class.getResourceAsStream(CLIENT_SECRET_FILE);
        if (in == null) {
            throw new FileNotFoundException("Nie znaleziono pliku: client_secret.json");
        }
        return GoogleClientSecrets.load(
                JSON_FACTORY,
                new InputStreamReader(in)
        );
    }

    public static Credential authorize() throws IOException {
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                new NetHttpTransport(),
                JSON_FACTORY,
                getClientSecrets(),
                Collections.singletonList("https://www.googleapis.com/auth/calendar")
        )
                .setAccessType("offline")
                .build();

        // autoryzacja użytkownika
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        // poświadczenie użytkownika
        credential = new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
        initCalendar();
        return credential;
    }


    public static void initCalendar() {
        try {
            calendarService = new Calendar.Builder(
                    GoogleNetHttpTransport.newTrustedTransport(),
                    GsonFactory.getDefaultInstance(),
                    credential
            ).setApplicationName("Learnwords").build();
        } catch (GeneralSecurityException | IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static Calendar getCalendarService() {
        return calendarService;
    }

    public static void setCalendarService(Calendar calendarService) {
        GoogleAuth.calendarService = calendarService;
    }

    public static void addEvent(String eventSummary, LocalDateTime startDateTime, LocalDateTime endDateTime) throws Exception {
        ZonedDateTime startZoned = startDateTime.atZone(ZoneId.of("Europe/Warsaw"));
        ZonedDateTime endZoned = endDateTime.atZone(ZoneId.of("Europe/Warsaw"));

        DateTime startApiDateTime = new DateTime(startZoned.toInstant().toEpochMilli());
        DateTime endApiDateTime = new DateTime(endZoned.toInstant().toEpochMilli());
        Event event = new Event()
                .setSummary(eventSummary)
                .setStart(new EventDateTime()
                        .setDateTime(startApiDateTime)
                        .setTimeZone("Europe/Warsaw"))
                .setEnd(new EventDateTime()
                        .setDateTime(endApiDateTime)
                        .setTimeZone("Europe/Warsaw"));

        if (credential.getExpiresInSeconds() <= 60) {
            credential.refreshToken();
        }
        calendarService.events().insert("primary", event).execute();
    }
}
