package com.example.hellokitty;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.contract.ActivityResultContracts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hellokitty.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    final String TAG = "MyApp";

    ActivityResultLauncher<Intent> resultSecondActivity = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    // do sm with the returned result here
                    if(result.getResultCode()== Activity.RESULT_OK){
                        TextView emailTextView2 = findViewById(R.id.text_view_email2);
                        TextView passTextView2 = findViewById(R.id.text_view_password2);

                        Intent receivedIntent = result.getData();
                        User user = receivedIntent.getParcelableExtra("USER");

                        String emailReceived = user.getEmail();
                        String passReceived = user.getPassword();

                        emailTextView2.setText(emailReceived);
                        passTextView2.setText(passReceived);

                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText emailEditText = (EditText)findViewById(R.id.edit_text_email);
        EditText passEditText = (EditText)findViewById(R.id.edit_text_password);

        TextView emailTextView2 = (TextView)findViewById(R.id.text_view_email2);
        TextView passTextView2 = (TextView)findViewById(R.id.text_view_password2);

        Button goToSecondActivity = findViewById(R.id.b_goToSecondActivity);
        goToSecondActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG,"Нажатие на кнопку. Программная реализация");
                String emailToPass = emailEditText.getText().toString();
                String passToPass = passEditText.getText().toString();

                User user = new User(emailToPass, passToPass);

                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("USER", user);
                startActivity(intent);

                resultSecondActivity.launch(intent);
            }
        });


        //Toast.makeText(this, "Процесс пошёл!", Toast.LENGTH_LONG).show();
    }
    @Override
    protected void onStart(){
        super.onStart();

       // Toast.makeText(this, "Стартовали!", Toast.LENGTH_LONG).show();
        Log.i(TAG, "Это мое сообщение для записи в журнале (Стартовали)");

    }
    @Override
    protected void onStop(){
        super.onStop();

        //Toast.makeText(this, "Остановочка!", Toast.LENGTH_LONG).show();
        Log.e(TAG, "Приложение остановлено");

    }
    @Override
    protected void onDestroy(){
        super.onDestroy();

       // Toast.makeText(this, "Уничтожение!", Toast.LENGTH_LONG).show();
        Log.v(TAG, "Это мое сообщение для записи в журнале");

    }
    @Override
    protected void onPause(){
        super.onPause();

       // Toast.makeText(this, "Пора на перерыв!", Toast.LENGTH_LONG).show();
    }
    @Override
    protected void onResume(){
        super.onResume();

       // Toast.makeText(this, "Возобновляемся!", Toast.LENGTH_LONG).show();
    }
    @Override
    protected void onRestart(){
        super.onRestart();

       // Toast.makeText(this, "Перезапускаемся!", Toast.LENGTH_LONG).show();
    }
    public void b_exitClick(View view) {
        System.exit(0);
        Log.i(TAG,"Выход из программы");

    }


    public void b_goToSecondActivity(View view) {
        Intent intent = new Intent(this,SecondActivity.class);
        intent.putExtra("key","message");
        startActivity(intent);
        Log.i(TAG,"Нажатие на кнопку. Переход на SecondActivity");
    }

}


