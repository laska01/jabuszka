package com.example.jabuszka;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

import android.media.Image;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    CountDownTimer countDownTimer;
    int czas = 10;
    int punkty1 = 0;
    Random losowa = new Random();
    boolean bad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.button);
        TextView czass = findViewById(R.id.textView2);
        TextView punkty = findViewById(R.id.textView3);

        ImageView izdj1 = findViewById(R.id.imageView1);
        ImageView izdj2 = findViewById(R.id.imageView2);
        ImageView izdj3 = findViewById(R.id.imageView3);
        ImageView izdj4 = findViewById(R.id.imageView4);
        ImageView izdj5 = findViewById(R.id.imageView5);
        ImageView izdj6 = findViewById(R.id.imageView6);
        ImageView izdj7 = findViewById(R.id.imageView7);
        ImageView izdj8 = findViewById(R.id.imageView8);
        ImageView izdj9 = findViewById(R.id.imageView9);
        ArrayList<ImageView> kasa = new ArrayList<>();
        kasa.add(izdj1);
        kasa.add(izdj2);
        kasa.add(izdj3);
        kasa.add(izdj4);
        kasa.add(izdj5);
        kasa.add(izdj6);
        kasa.add(izdj7);
        kasa.add(izdj8);
        kasa.add(izdj9);



        CountDownTimer countDownTimer1 = new CountDownTimer(1000, 1000) {
            @Override
            public void onFinish() {
                losuj(kasa, this);
            }

            @Override
            public void onTick(long l) {

            }
        };

        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        czas=11;
                        punkty.setText(String.valueOf(punkty1));

                        countDownTimer = new CountDownTimer(1000*czas, 1000) {
                            @Override
                            public void onFinish() {
                                countDownTimer1.cancel();

                                for(int i = 0; i<kasa.size(); i++){
                                    kasa.get(i).setVisibility(INVISIBLE);
                                }

                                this.cancel();
                            }

                            @Override
                            public void onTick(long l) {
                                czas = (int)l/1000;
                                czass.setText("czas: "+ czas);
                            }
                        };
                        countDownTimer.start();
                        countDownTimer1.start();
                    }
                }
        );
        for (int i = 0; i < kasa.size(); i++) {
            kasa.get(i).setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(bad){
                                losuj(kasa, countDownTimer1);
                                punkty1--;
                                punkty.setText(String.valueOf(punkty1));


                            }
                            else{
                                losuj(kasa,countDownTimer1);
                                punkty1++;
                                punkty.setText(String.valueOf(punkty1));
                            }
                        }
                    }
            );

        }

    }
    public void losuj(ArrayList<ImageView> kasa, CountDownTimer countDownTimer1){
        int losowakasa = losowa.nextInt(9);
        int losowy = losowa.nextInt(2);

        for (int i = 0; i < kasa.size(); i++) {
            kasa.get(i).setVisibility(INVISIBLE);
        }
        if(losowy==0){
            kasa.get(losowakasa).setImageResource(R.drawable.zlodziej);
            bad = true;
        }
        else{
            kasa.get(losowakasa).setImageResource(R.drawable.money);
            bad= false;

        }
        kasa.get(losowakasa).setVisibility(VISIBLE);
        countDownTimer1.cancel();
        countDownTimer1.start();
    }
}