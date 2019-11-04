package com.missouristate.guadagnano.friendsgroup;

import android.graphics.Point;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class UpdateActivity extends AppCompatActivity {
    DatabaseManager dbManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbManager = new DatabaseManager(this);
        updateView();
    }

    //Build the view dynamically w/ all the candies
    private void updateView() {
        ArrayList<Friends> friend = dbManager.selectAll();


        //creates a scrollview and gridLayout
        if (friend.size() > 0) {
            ScrollView scrollView = new ScrollView(this);
            GridLayout grid = new GridLayout(this);
            grid.setRowCount(friend.size());
            grid.setColumnCount(4);

            //creates and array of components
            TextView[] ids = new TextView[friend.size()];
            EditText[][] namesAndPrices = new EditText[friend.size()][2];
            Button[] buttons = new Button[friend.size()];
            ButtonHandler bh = new ButtonHandler();

            //retrieves width of the screen
            Point size = new Point();
            getWindowManager().getDefaultDisplay().getSize(size);
            int width = size.x;

            //creates the textview for the candy's id
            int i = 0;

            for (Friends friends : friend) {
                ids[i] = new TextView(this);
                ids[i].setGravity(Gravity.CENTER);
                ids[i].setText("" + friends.getId());

                //creates the edittexts for the candy's name and price
                namesAndPrices[i][0] =  new EditText(this);
                namesAndPrices[i][1] = new EditText(this);
                namesAndPrices[i][0].setText(friends.getName());
                namesAndPrices[i][1].setText("" + friends.getEmail());
                namesAndPrices[i][1].setInputType(InputType.TYPE_CLASS_NUMBER);
                namesAndPrices[i][0].setId(10 * friends.getId());
                namesAndPrices[i][1].setId(10 * friends.getId() + 1);

                //Creates the button
                buttons[i] = new Button(this);
                buttons[i].setText("Update");
                buttons[i].setId(friends.getId());

                //setup the event handling
                buttons[i].setOnClickListener(bh);

                //adds the element to the grid
                grid.addView(ids[i], width/10, ViewGroup.LayoutParams.WRAP_CONTENT);
                grid.addView(namesAndPrices[i][0], (int) (width * .4),ViewGroup.LayoutParams.WRAP_CONTENT);
                grid.addView(namesAndPrices[i][1], (int) (width * .15), ViewGroup.LayoutParams.WRAP_CONTENT);
                grid.addView(buttons[i], (int) (width * .35), ViewGroup.LayoutParams.WRAP_CONTENT);

                i++;
            }
            scrollView.addView(grid);
            setContentView(scrollView);
        }
    }

    private class ButtonHandler implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            //retrieves names and emails
            int friendId = v.getId();
            EditText nameET = (EditText) findViewById(10 * friendId);
            EditText emailET = (EditText) findViewById(10 * friendId + 1);
            String name = nameET.getText().toString();
            String email = emailET.getText().toString();

            //updates friends in the database

            dbManager.updateById(friendId, name, email);
            Toast.makeText(UpdateActivity.this, "Candy updated", Toast.LENGTH_SHORT).show();

            // updates the screen
            updateView();

        }
    }
}

