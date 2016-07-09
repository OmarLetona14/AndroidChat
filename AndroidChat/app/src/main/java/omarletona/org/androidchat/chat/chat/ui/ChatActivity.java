package omarletona.org.androidchat.chat.chat.ui;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import omarletona.org.androidchat.R;
import omarletona.org.androidchat.chat.chat.ChatPresenter;
import omarletona.org.androidchat.chat.chat.ChatPresenterImpl;
import omarletona.org.androidchat.chat.chat.adapters.ChatAdapter;
import omarletona.org.androidchat.chat.chat.entities.ChatMessage;
import omarletona.org.androidchat.domain.AvatarHelper;
import omarletona.org.androidchat.lib.GlideImageLoader;
import omarletona.org.androidchat.lib.ImageLoader;

public class ChatActivity extends AppCompatActivity implements ChatView {


    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.txtUser)
    TextView txtUser;
    @Bind(R.id.txtStatus)            TextView txtStatus;
    @Bind(R.id.editTxtMessage)
    EditText editTxtMessage;
    @Bind(R.id.messageRecyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.imgAvatar)
    CircleImageView imgAvatar;
    private ChatAdapter adapter;
    private ChatPresenter presenter;

    public final static String EMAIL_KEY = "email";
    public final static String ONLINE_KEY = "online";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);
        setupAdapter();
        setupRecyclerView();
        presenter = new ChatPresenterImpl(this);
        presenter.onCreate();

        setupToolbar(getIntent());

    }

    private void setupToolbar(Intent i) {
        String recipient = i.getStringExtra(EMAIL_KEY);
        presenter.setChatRecipient(recipient);

        boolean online = i.getBooleanExtra(ONLINE_KEY, false);
        String status = online ? "online" : "offline";
        int color = online ? Color.GREEN : Color.RED;

        txtUser.setText(recipient);
        txtStatus.setText(status);
        txtStatus.setTextColor(color);
        ImageLoader imageLoader = new GlideImageLoader(getApplicationContext());

        imageLoader.load(imgAvatar, AvatarHelper.getAvatarUrl(recipient));
        setSupportActionBar(toolbar);
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void setupAdapter() {
        adapter = new ChatAdapter(this, new ArrayList<ChatMessage>());
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.onPause();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }
    @Override
    public void onMessegaReceived(ChatMessage msg) {
        adapter.add(msg);
        recyclerView.scrollToPosition(adapter.getItemCount() - 1);
    }

    @OnClick(R.id.btnSendMessage)
    public void sendMessage(){
        presenter.sendMessage(editTxtMessage.getText().toString());
        editTxtMessage.setText("");
    }
}
