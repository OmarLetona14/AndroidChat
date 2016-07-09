package omarletona.org.androidchat.Login;

import org.greenrobot.eventbus.Subscribe;

import omarletona.org.androidchat.lib.GreenRobotEventBus;
import omarletona.org.androidchat.Login.events.LoginEvent;
import omarletona.org.androidchat.Login.iu.LoginView;
import omarletona.org.androidchat.lib.EventBus;

/**
 * Created by Omar on 29/06/2016.
 */
public class LoginPresenterImpl implements LoginPresenter {
    private EventBus eventBus;
    private LoginView loginView;
    private LoginInteractor loginInteractor;

    public LoginPresenterImpl(LoginView loginView) {
        this.loginView = loginView;
        this.loginInteractor = new LoginInteractorImpl();
        this.eventBus = GreenRobotEventBus.getInstance();
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        loginView = null;
        eventBus.unregister(this);
    }

    @Override
    public void checkForAuthenticatedUser() {
        if(loginView != null){
            loginView.disableInputs();
            loginView.showProgress();
        }
        loginInteractor.checkSession();
    }

    @Override
    public void validateLogin(String email, String password) {
        if(loginView != null){
            loginView.disableInputs();
            loginView.showProgress();
        }
        loginInteractor.doSignIn(email, password);
    }

    @Override
    public void registrerNewUser(String email, String password) {
        if(loginView != null){
            loginView.disableInputs();
            loginView.showProgress();
        }
        loginInteractor.doSignUp(email, password);
    }

    @Override
    @Subscribe
    public void onEventMainThread(LoginEvent event) {
        switch (event.getEventType()){
            case LoginEvent.onSignInSuccess:
                onSignInSucess();
                break;
            case LoginEvent.onSignInError:
                onSignInError(event.getErrorMessage());
                break;
            case LoginEvent.onSignUpSucess:
                onSignUpSucess();
                break;
            case LoginEvent.onSignUpError:
                onSignUpError(event.getErrorMessage());
                break;
            case LoginEvent.onFailedToRecoverSession:
                OnFailedRecoverSession();
                break;
        }
    }

    private void OnFailedRecoverSession() {
        if(loginView != null){
            loginView.hideProgress();
            loginView.enableInputs();

        }
    }

    private void onSignInSucess(){
        if(loginView != null){
            loginView.navigateToMainScreen();
        }
    }
    private void onSignUpSucess(){
        if(loginView != null){
            loginView.newUserSucess();
        }
    }
    private void onSignInError(String error){
        if(loginView != null){
            loginView.hideProgress();
            loginView.enableInputs();
            loginView.loginError(error);
        }
    }
    private void onSignUpError(String error){
        if(loginView != null){
            loginView.hideProgress();
            loginView.enableInputs();
            loginView.newUserError(error);
        }
    }
}
