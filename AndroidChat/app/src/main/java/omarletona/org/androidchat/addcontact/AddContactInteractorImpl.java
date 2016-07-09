package omarletona.org.androidchat.addcontact;

/**
 * Created by Omar on 05/07/2016.
 */
public class AddContactInteractorImpl implements AddContactInteractor {
    AddContactRepositoryImpl repository;

    public AddContactInteractorImpl() {
        this.repository = new AddContactRepositoryImpl();
    }

    @Override
    public void execute(String email) {
        repository.addContact(email);
    }
}
