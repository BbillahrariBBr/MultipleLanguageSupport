package com.example.bakibillah.multiplelanguagesupport;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private  static Button english,bangla,french;
    private  static TextView chhoseText;
    private  static Locale myLocale;


    //Shared preferences Variable
    private  static final String Locale_Preference = "Locale Preference";
    private static final  String  Locale_KeyValue = "Saved Locale";
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupUIMain();
        setListeners();
        loadLocale();
    }

    private void loadLocale() {
        String language = sharedPreferences.getString(Locale_KeyValue,"");
        changeLocale(language);
    }

    private void setListeners() {
        english.setOnClickListener(this);
        bangla.setOnClickListener(this);
        french.setOnClickListener(this);
    }

    private void setupUIMain() {
        sharedPreferences = getSharedPreferences(Locale_Preference, Activity.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        chhoseText = (TextView)findViewById(R.id.textView);
        english = (Button)findViewById(R.id.buttonEnglish);
        bangla = (Button)findViewById(R.id.buttonBangla);
        french = (Button)findViewById(R.id.buttonFrench);

    }

    @Override
    public void onClick(View v) {
        String lang = "en"; // Defaul Language
        switch (v.getId()){
            case R.id.buttonEnglish:
                lang = "en";
                break;
            case R.id.buttonBangla:
                lang ="bn";
                break;
            case R.id.buttonFrench:
                lang ="fr";
                break;
        }
        changeLocale(lang);//Change Locale On selection
    }

    private void changeLocale(String lang) {
        if(lang.equalsIgnoreCase(""))
            return;
        myLocale = new Locale(lang); //set selected Language
        saveLocale(lang); //saved Selected Locale
        Locale.setDefault(myLocale); //set new Locale as default
        Configuration config = new Configuration();//get Cnfiguration
        config.locale = myLocale; //set config locale as selected locale
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());//update the config
        updateTexts();



    }

    private void updateTexts() {
        chhoseText.setText(R.string.tap_title);
        english.setText(R.string.btn_english);
        bangla.setText(R.string.btn_bangla);
        french.setText(R.string.btn_french);
    }

    private void saveLocale(String lang) {
        editor.putString(Locale_KeyValue,lang);
        editor.commit();
    }

}
