package com.example.exam1project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText toEditText;
    private EditText messageEditText;
    private EditText subjectEditText;
    private boolean handled;
    String messageText;
    Toast toast;
    Button send;
    Button cancel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toEditText = findViewById(R.id.toTextField);

        messageEditText = findViewById(R.id.messageTextField);

        messageEditText.setOnEditorActionListener(

            new TextView.OnEditorActionListener()
            {
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event)
                {
                    handled = false;
                    if(actionId == EditorInfo.IME_ACTION_SEND)
                    {
                        sendMessage();
                        handled = true;
                    }
                    return handled;
                }
            });


        send = findViewById(R.id.sendButton);

        send.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                sendMessage();
            }
        });

        cancel = findViewById(R.id.cancelButton);

        cancel.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                cancelMessage();
            }
        });


        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.hide:
            {
                InputMethodManager key = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);

                key.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                return true;
            }

            case R.id.toast:
            {
                sendMessage();
                return true;
            }
        }
        return true;
    }


    protected void sendMessage()
    {
        InputMethodManager key = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);

        key.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

        toEditText = findViewById(R.id.toTextField);
        subjectEditText = findViewById(R.id.subjectTextField);

        messageText = "Sending a message to " + toEditText.getText() + "\n"
        + "Subject: " + subjectEditText.getText() + "\n" +
        messageEditText.getText();

        int duration = Toast.LENGTH_SHORT;
        toast = Toast.makeText(this, messageText, duration);
        toast.show();
    }

    protected void cancelMessage()
    {
        InputMethodManager key = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);

        key.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

        toEditText.getText().clear();
        messageEditText.getText().clear();
        subjectEditText.getText().clear();
    }

}
