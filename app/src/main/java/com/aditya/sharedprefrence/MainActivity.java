package com.aditya.sharedprefrence;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button button;
    TextView textView;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        button=findViewById(R.id.button);
        textView=findViewById(R.id.textView);
        editText=findViewById(R.id.editText);

        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                String msg=editText.getText().toString(); // In Android, the getText() method returns an object of type Editable, and calling toString() on it converts it to a String.
                SharedPreferences shrd=getSharedPreferences("demo",MODE_PRIVATE);
                SharedPreferences.Editor editor= shrd.edit();

                editor.putString("str",msg);
                editor.apply(); //it will be saved in shared prefrence only after editor.apply()
                textView.setText(msg);

            }
        });

        //getting the value of shared prefrence back
        SharedPreferences getShared=  getSharedPreferences("demo",MODE_PRIVATE);
        String value=getShared.getString("str","Save a Note first"); //we have to pass a default value in this case save a note first so that if str is not found
        textView.setText(value);
    }
}