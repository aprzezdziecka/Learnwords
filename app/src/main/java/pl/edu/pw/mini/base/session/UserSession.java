package pl.edu.pw.mini.base.session;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.services.calendar.Calendar;
import pl.edu.pw.mini.base.manager.UserManager;
import pl.edu.pw.mini.ui.calendar.GoogleAuth;

import java.io.IOException;
import java.time.LocalDateTime;

public class UserSession extends Session {
    private static UserSession instance;

    private Credential userCredential = null;
    private Calendar calendarService = null;

    private final UserManager userManager;

    private UserSession(){
        super();
        this.userManager = new UserManager();
    }

    public static UserSession getInstance(){
        if(instance == null){
            instance = new UserSession();
        }
        return instance;
    }

    public boolean login(String givenusername, String givenpassword){
        if (userManager.authenticateUser(givenusername, givenpassword)){
            username = givenusername;
            this.isLoggedIn = true;
            /*try {
                userCredential = GoogleAuth.authorize();
                calendarService = GoogleAuth.getCalendarService();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }*/
            return true;
        }
        return false; //błąd logowania
    }

    public boolean googleAccount(){
        try {
            userCredential = GoogleAuth.authorize();
            calendarService = GoogleAuth.getCalendarService();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public void logout(){
        if(userManager.beforeLoggingOut()){
            this.isLoggedIn = false;
            username = null;
            userCredential = null;
            GoogleAuth.setCalendarService(null);
            GoogleAuth.setCalendarService(null);
        }
    }

    public boolean isLoggedIn(){
        return this.isLoggedIn;
    }

    public boolean register(String givenusername, String givenpassword){
        return userManager.registerUser(givenusername, givenpassword);
    }

    public boolean addEvent(String eventName, LocalDateTime startDateTime, LocalDateTime endDateTime){
        if(isLoggedIn()){
            try {
                GoogleAuth.addEvent(eventName, startDateTime, endDateTime);
                return true;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }

}
