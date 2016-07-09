package omarletona.org.androidchat.contactlist;

/**
 * Created by Omar on 04/07/2016.
 */
public class ContactListSessionInteractorImpl implements ContactListSessionInteractor {
    ContactListRepositoryImpl repository;

    public ContactListSessionInteractorImpl() {
        repository = new ContactListRepositoryImpl();
    }

    @Override
    public void signOff() {
        repository.signOff();
    }

    @Override
    public String getCurrentUserEmail() {
        return repository.getCurrentUserEmail();
    }

    @Override
    public void ChangeConnectionStatus(boolean online) {
        repository.ChangeConnectionStatus(online);
    }
}
