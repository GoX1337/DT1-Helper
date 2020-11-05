package com.gox.dt1helper.settings;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NavUtils;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.gox.dt1helper.R;

public class SettingsActivity extends AppCompatActivity {

    public Context context;
    public TextInputLayout et1;
    public TextInputLayout et2;
    public TextInputLayout et3;
    public static final String PREFS_KEY = "dt1.settings";
    private static final String BREAKFAST = "breakfast";
    private static final String LUNCH = "lunch";
    private static final String DINNER = "dinner";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_settings);
        setTitle("Paramètres");
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        et1 = findViewById(R.id.textField1);
        et2 = findViewById(R.id.textField2);
        et3 = findViewById(R.id.textField3);
        loadSettingsFromPrefs();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveSettingsPreferences((Activity)context, et1.getEditText().getText().toString(), et2.getEditText().getText().toString(), et3.getEditText().getText().toString());
                NavUtils.navigateUpFromSameTask((Activity)context);
                Toast.makeText(context, "Paramètres enregistrés !", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadSettingsFromPrefs() {
        SharedPreferences prefs = getSharedPreferences(PREFS_KEY, Context.MODE_PRIVATE);
        if(prefs != null){
            float f1 = prefs.getFloat(BREAKFAST, 0f);
            float f2 = prefs.getFloat(LUNCH, 0f);
            float f3 = prefs.getFloat(DINNER, 0f);
            et1.getEditText().setText(String.valueOf(f1));
            et2.getEditText().setText(String.valueOf(f2));
            et3.getEditText().setText(String.valueOf(f3));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                saveSettingsPreferences(this, et1.getEditText().getText().toString(), et2.getEditText().getText().toString(), et3.getEditText().getText().toString());
                NavUtils.navigateUpFromSameTask(this);
                Toast.makeText(context, "Paramètres enregistrés !", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public static void saveSettingsPreferences(Activity activity, String beakfast, String lunch, String dinner){
        SharedPreferences.Editor editor = activity.getSharedPreferences(PREFS_KEY, Context.MODE_PRIVATE).edit();
        editor.putFloat(BREAKFAST, Float.valueOf(beakfast));
        editor.putFloat(LUNCH, Float.valueOf(lunch));
        editor.putFloat(DINNER, Float.valueOf(dinner));
        editor.apply();
    }
}