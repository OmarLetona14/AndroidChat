package omarletona.org.androidchat.contactlist;

import org.greenrobot.eventbus.Subscribe;

import omarletona.org.androidchat.contactlist.events.ContactListEvent;
import omarletona.org.androidchat.contactlist.ui.ContactListView;
import omarletona.org.androidchat.contactlist.ui.adapters.ContactListAdapter;
import omarletona.org.androidchat.entities.User;
import omarletona.org.androidchat.lib.EventBus;
import omarletona.org.androidchat.lib.GreenRobotEventBus;

/**
 * Created by Omar on 04/07/2016.
 */
public class ContactListPresenterImpl implements ContactListPresenter {
    private EventBus eventBus;
    private ContactListView view;
    private ContactListInteractor listInteractor;
    private ContactListSessionInteractor sessionInteractor;

    public ContactListPresenterImpl(ContactListView view) {
        this.view = view;
        eventBus = GreenRobotEventBus.getInstance();
        this.listInteractor = new ContactListInteractorImpl();
        this.sessionInteractor = new ContactListSessionInteractorImpl();

    }

    @Override
    public void onPause() {
        sessionInteractor.ChangeConnectionStatus(User.OFFLINE);
        listInteractor.unsubscribe();
    }

    @Override
    public void onResume() {
        sessionInteractor.ChangeConnectionStatus(User.ONLINE);
        listInteractor.subscribe();
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {

        eventBus.unregister(this);
        listInteractor.destroyListener();
        view = null;
    }

    @Override
    public void signOff() {
        sessionInteractor.ChangeConnectionStatus(User.OFFLINE);
        listInteractor.unsubscribe();
        listInteractor.destroyListener();
        sessionInteractor.signOff();
    }

    @Override
    public String getCurrentUserEmail() {
        return sessionInteractor.getCurrentUserEmail();
    }

    @Override
    public void removeContact(String email) {
        listInteractor.removeContact(email);
    }

    @Override
    @Subscribe
    public void onEventMainThread(ContactListEvent event) {
        User user = event.getUser();
        switch (event.getEventType()) {
            case ContactListEvent.onContactAdded:
                onContactAdded(user);
                break;
            case ContactListEvent.onContactChanged:
                onContactChanged(user);
                break;
            case ContactListEvent.onContactRemoved:
                onContactRemoved(user);
                break;
        }
    }

    public void onContactAdded(User user) {
        if (view != null) {
            view.onContactAdded(user);
        }
    }

    public void onContactChanged(User user) {
        if (view != null) {
            view.onContactChanged(user);
        }
    }

    public void onContactRemoved(User user) {
        if (view != null) {
            view.onContactRemoved(user);
        }
    }
}
