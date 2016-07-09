package omarletona.org.androidchat.Login.iu;

/**
 * Created by Omar on 29/06/2016.
 */
public interface LoginView {

    void enableInputs();
    void disableInputs();
    void showProgress();
    void hideProgress();

    void handleSignUp();
    void handleSignIn();

    void navigateToMainScreen();
    void loginError(String error);

    void newUserSucess();
    void newUserError(String error);
}
