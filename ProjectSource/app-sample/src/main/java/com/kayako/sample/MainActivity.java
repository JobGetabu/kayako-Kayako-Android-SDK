package com.kayako.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.kayako.sdk.android.k5.core.Kayako;

import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private Locale selectedLocale = Locale.US;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText editText = (EditText) findViewById(R.id.edittext);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, Locale.getAvailableLocales()));

        spinner.setSelection(getPositionOfLocale(Locale.getAvailableLocales(), selectedLocale));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedLocale = Locale.getAvailableLocales()[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(editText.getText().toString())) {
                    Toast.makeText(MainActivity.this, "URL can not be blank", Toast.LENGTH_LONG).show();
                    return;
                } else if (!URLUtil.isValidUrl(editText.getText().toString())) {
                    Toast.makeText(MainActivity.this, "Please enter a valid URL", Toast.LENGTH_LONG).show();
                    return;
                }

                String url = editText.getText().toString();
                Kayako.getInstance().openHelpCenter(MainActivity.this, url, selectedLocale);
            }
        });

    }


    private int getPositionOfLocale(Locale[] locales, Locale locale) {
        int position = 0;
        for (Locale item : locales) {
            if (item.equals(locale)) {
                return position;
            }
            position++;
        }

        return position;
    }
}