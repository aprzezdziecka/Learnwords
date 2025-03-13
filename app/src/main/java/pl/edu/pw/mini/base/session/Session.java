package pl.edu.pw.mini.base.session;

public abstract class Session {
    protected static String username;
    protected boolean isLoggedIn;

    protected Session(){
        this.username = null;
        this.isLoggedIn = false;
    }
}
