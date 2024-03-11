package com.example.hellokitty;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hellokitty.databinding.ActivityMainBinding;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        EditText emailEditText2 = findViewById(R.id.edit_text_email2);
        EditText passEditText2 = findViewById(R.id.edit_text_password2);



        User user = getIntent().getParcelableExtra("USER");

        //String emailReceived = getIntent().getStringExtra("EMAIL");
        //String passReceived = getIntent().getStringExtra("PASS");

        String emailReceived  = user.getEmail();
        String passReceived = user.getPassword();



        TextView emailTextView = findViewById(R.id.text_view_email);
        TextView passTextView = findViewById(R.id.text_view_password);

        emailTextView.setText(emailReceived);
        passTextView.setText(passReceived);

        Button b_send = findViewById(R.id.b_send);
        b_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String viewEmail2 = emailEditText2.getText().toString();
                String viewPass2 = passEditText2.getText().toString();
                User user = new User(viewEmail2,viewPass2);
                Intent intentToReturn = new Intent();
                intentToReturn.putExtra("USER",user);
                setResult(Activity.RESULT_OK,intentToReturn);
                finish();
            }
        });

        Button b_return = findViewById(R.id.b_return);
        b_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        }

        );
    }
}
