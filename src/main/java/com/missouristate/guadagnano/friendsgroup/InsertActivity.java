package com.missouristate.guadagnano.friendsgroup;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class InsertActivity extends AppCompatActivity {
    DatabaseManager dbManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        dbManager = new DatabaseManager(this);

    }

    public void onAdd(View view) {
        //Get User input
        EditText nameEditText = findViewById(R.id.input_name);
        String name = nameEditText.getText().toString();
        EditText emailEditText = findViewById(R.id.input_email);
        String email = emailEditText.getText().toString();


        //Put into DB
        Friends friend = new Friends(0, name, email);
        dbManager.insert(friend);
        Toast.makeText(this, "Item added!", Toast.LENGTH_SHORT).show();

        //Clear Box
        nameEditText.setText("");
        emailEditText.setText("");
    }

    public void goBack( View v ) {
        Intent MainActivityIntent = new Intent(this, MainActivity.class);
        this.startActivity(MainActivityIntent);
    }

}


