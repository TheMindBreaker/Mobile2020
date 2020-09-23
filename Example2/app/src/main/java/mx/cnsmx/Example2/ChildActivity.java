package mx.cnsmx.Example2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ChildActivity extends AppCompatActivity {

    Intent i;
    TextView myTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child);

        myTextView = findViewById(R.id.textView);

        i = getIntent();
        String auxString;

        auxString = i.getStringExtra("parametroExt");

        myTextView.setText(auxString);


    }
}