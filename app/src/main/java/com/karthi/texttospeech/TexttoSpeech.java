package com.karthi.texttospeech;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class TexttoSpeech extends AppCompatActivity {
    TextToSpeech t1;
    EditText ed1;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_to_speech);
        ed1=findViewById(R.id.editText);
        b1=findViewById(R.id.button);

        t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                   int result= t1.setLanguage(Locale.US);
                   if (result==TextToSpeech.LANG_MISSING_DATA ||
                           result==TextToSpeech.LANG_NOT_SUPPORTED){
                       Log.e("error", "This Language is not supported");
                   }
                }
            }
        });



        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toSpeak = ed1.getText().toString();
                Toast.makeText(getApplicationContext(), toSpeak,Toast.LENGTH_SHORT).show();
                t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
            }
        });
    }

    public void onPause(){
        if(t1 !=null){
            t1.stop();
            t1.shutdown();
        }
        super.onPause();


    }

    public void onHelp(View view) {
        Toast.makeText(getApplicationContext(),getString(R.string.help) ,Toast.LENGTH_SHORT).show();
        t1.speak(getString(R.string.help),TextToSpeech.QUEUE_FLUSH, null);

    }
}
