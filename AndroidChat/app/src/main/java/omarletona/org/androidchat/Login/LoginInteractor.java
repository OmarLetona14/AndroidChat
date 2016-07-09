package omarletona.org.androidchat.Login;

/**
 * Created by Omar on 29/06/2016.
 */
public interface LoginInteractor {
    void checkSession();
    void doSignUp(String email, String password);
    void doSignIn(String email, String password);
}
