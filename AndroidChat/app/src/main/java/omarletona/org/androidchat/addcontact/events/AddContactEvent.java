package omarletona.org.androidchat.addcontact.events;

/**
 * Created by Omar on 05/07/2016.
 */
public class AddContactEvent {
    boolean error = false;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }
}
