package omarletona.org.androidchat.Login;

/**
 * Created by Omar on 29/06/2016.
 */
public interface LoginRepository {
    void signUp(String email, String password);
    void signIn(String email, String password);
    void checkSession();

}
