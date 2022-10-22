package ru.synergy.sharedpreferencesexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText et;
    Button btnSave, btnLoad;
    SharedPreferences sharedPreferences;
    final String SAVED_TEXT = "saved text";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = (EditText) findViewById(R.id.editText);
        btnSave = (Button) findViewById(R.id.buttonsave);
        btnLoad = (Button) findViewById(R.id.buttonload);
        loadText();

        et.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                saveText();
                Log.d(SAVED_TEXT, et.getText().toString());
                return true;
            }
        });
    }

    private void saveText() {
        sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SAVED_TEXT, et.getText().toString());
        editor.commit();
        Toast.makeText(this, "Text saved", Toast.LENGTH_LONG).show();
    }

    private void loadText() {
        sharedPreferences = getPreferences(MODE_PRIVATE);
        String savedText = sharedPreferences.getString(SAVED_TEXT, "Nothing to save");
        et.setText(savedText);
        Toast.makeText(this, "Text loaded", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveText();
    }
}