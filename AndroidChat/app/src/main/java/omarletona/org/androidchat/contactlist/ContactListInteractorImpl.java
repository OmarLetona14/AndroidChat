package omarletona.org.androidchat.contactlist;

/**
 * Created by Omar on 04/07/2016.
 */
public class ContactListInteractorImpl implements ContactListInteractor {
    ContactListRepositoryImpl repository;

    public ContactListInteractorImpl() {
        repository = new ContactListRepositoryImpl();
    }

    @Override
    public void subscribe() {
        repository.subscribeToContactListEvent();
    }

    @Override
    public void unsubscribe() {
        repository.unsubscribeToContactListEvent();
    }

    @Override
    public void destroyListener() {
        repository.destroyListener();
    }

    @Override
    public void removeContact(String email) {
        repository.removeContact(email);
    }
}
