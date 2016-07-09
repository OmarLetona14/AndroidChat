package omarletona.org.androidchat.chat.chat;

import org.greenrobot.eventbus.Subscribe;

import omarletona.org.androidchat.chat.chat.entities.ChatMessage;
import omarletona.org.androidchat.chat.chat.events.ChatEvent;
import omarletona.org.androidchat.chat.chat.ui.ChatView;
import omarletona.org.androidchat.entities.User;
import omarletona.org.androidchat.lib.EventBus;
import omarletona.org.androidchat.lib.GreenRobotEventBus;

/**
 * Created by Omar on 05/07/2016.
 */
public class ChatPresenterImpl implements ChatPresenter {

    private EventBus eventBus;
    private ChatView view;
    private ChatInteractor chatInteractor;
    private ChatSessionInteractor sessionInteractor;


    public ChatPresenterImpl(ChatView view) {
        this.view = view;
        this.eventBus = GreenRobotEventBus.getInstance();
        this.chatInteractor = new ChatInteractorImpl();
        this.sessionInteractor = new ChatSessionInteractorImpl();
    }


    @Override
    public void onPause() {
        chatInteractor.unsubscribe();
        sessionInteractor.changeConnectionStatus(User.OFFLINE);
    }

    @Override
    public void onResume() {
        chatInteractor.subscribe();
        sessionInteractor.changeConnectionStatus(User.ONLINE);
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
        chatInteractor.destroyListener();
        view = null;
    }

    @Override
    public void setChatRecipient(String recipient) {
        chatInteractor.setRecipient(recipient);
    }

    @Override
    public void sendMessage(String msg) {
        chatInteractor.sendMessage(msg);
    }


    @Override
    @Subscribe
    public void onEventMainThread(ChatEvent event) {
        if (view != null) {
            ChatMessage msg = event.getMessage();
            view.onMessegaReceived(msg);
        }
    }
}
