package omarletona.org.androidchat.addcontact;

import org.greenrobot.eventbus.Subscribe;

import omarletona.org.androidchat.addcontact.AddContactInteractor;
import omarletona.org.androidchat.addcontact.AddContactPresenter;
import omarletona.org.androidchat.addcontact.events.AddContactEvent;
import omarletona.org.androidchat.addcontact.ui.AddContactView;
import omarletona.org.androidchat.lib.EventBus;
import omarletona.org.androidchat.lib.GreenRobotEventBus;

/**
 * Created by Omar on 05/07/2016.
 */
public class AddContactPresenterImpl implements AddContactPresenter {
    EventBus eventBus;
    AddContactView view;
    AddContactInteractor interactor;

    public AddContactPresenterImpl(AddContactView view) {
        this.view = view;
        this.eventBus = GreenRobotEventBus.getInstance();
        this.interactor = new AddContactInteractorImpl();
    }
    @Override
    @Subscribe
    public void onShow() {
        eventBus.register(this);
    }

    @Override
    @Subscribe
    public void onDestroy() {
        view = null;
        eventBus.unregister(this);
    }

    @Override
    @Subscribe
    public void addContact(String email) {
        view.hideInput();
        view.showProgress();
        this.interactor.execute(email);
    }

    @Override
    @Subscribe
    public void onEventMainThread(AddContactEvent event) {
        if (view != null) {
            view.hideProgress();
            view.showInput();

            if (event.isError()) {
                view.contactNoAdded();
            } else {
                view.contactAdded();
            }
        }
    }
}
