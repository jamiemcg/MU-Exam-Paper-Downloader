package me.jamiemcgowan.maynoothuniversityexampapers;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import java.io.File;
import java.util.Calendar;


public class MainActivity extends Activity {
    private boolean downloadRepeats = true; //Whether or not to download repeats

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Switch repeats_switch = (Switch)findViewById(R.id.repeats_switch);
        repeats_switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(downloadRepeats) {
                    downloadRepeats = false;
                }
                else {
                    downloadRepeats = true;
                }
            }
        });

        final EditText module_field = (EditText)findViewById(R.id.module_editText);
        module_field.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {}

            @Override
            public void afterTextChanged(Editable editable) {
                Button download_button = (Button)findViewById(R.id.download_button);
                download_button.setEnabled(true);
            }
        });

        final Button download_button = (Button)findViewById(R.id.download_button);
        download_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                download_button.setEnabled(false);

                File root = android.os.Environment.getExternalStorageDirectory();
                File dir = new File(root.getAbsolutePath() + "/ExamPapers");
                dir.mkdir();

                int start_year = Calendar.getInstance().get(Calendar.YEAR);
                int end_year = 2004;

                String [] input = module_field.getText().toString().split(",");
                for (String s : input) {
                    s = s.toUpperCase().replaceAll(" ", "");
                }

                for (String module : input) {
                    Downloader[] threads = new Downloader[start_year - end_year + 1];
                    int i = start_year;
                    while(i > end_year) {
                        threads[i - end_year] = new Downloader(i, module.toUpperCase().replaceAll(" ", ""), dir, downloadRepeats);
                        threads[i - end_year].start();
                        i--;
                    }
                    i = start_year;
                    while(i > end_year) {
                        try {
                            threads[i - end_year].join();   //Wait on all threads of current module to finish
                            //before starting to download new module
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                        i--;
                    }

                    Context context = getApplicationContext();
                    CharSequence text = "Downloaded " + module;
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }

                download_button.setEnabled(true);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
