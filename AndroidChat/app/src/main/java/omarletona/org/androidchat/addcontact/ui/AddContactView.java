package omarletona.org.androidchat.addcontact.ui;

/**
 * Created by Omar on 05/07/2016.
 */
public interface AddContactView {

    void showInput();
    void hideInput();
    void showProgress();
    void hideProgress();

    void contactAdded();
    void contactNoAdded();

}
