package omarletona.org.androidchat.Login;

import omarletona.org.androidchat.Login.events.LoginEvent;

/**
 * Created by Omar on 29/06/2016.
 */
public interface LoginPresenter {
    void onCreate();
    void onDestroy();
    void checkForAuthenticatedUser();
    void validateLogin(String email, String password);
    void registrerNewUser(String email, String password);
    void onEventMainThread(LoginEvent event);
}
