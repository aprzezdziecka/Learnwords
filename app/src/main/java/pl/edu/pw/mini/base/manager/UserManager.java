package pl.edu.pw.mini.base.manager;


import pl.edu.pw.mini.base.User;
import pl.edu.pw.mini.base.session.UserSession;

public class UserManager extends Manager{

    public boolean registerUser(String username, String password) {
        if (username == null || password == null || findUser(username) != null) {
            return false;
        }
        users.add(new User(username, password));
        saveUsers();
        return true;
    }

    public boolean authenticateUser(String username, String password) {
        User user = findUser(username);
        return user != null && user.getPassword().equals(password);
    }

    public boolean beforeLoggingOut(){
        if(UserSession.getInstance().isLoggedIn()){
            saveUsers();
            return true;
        }
        return false;
    }
}
