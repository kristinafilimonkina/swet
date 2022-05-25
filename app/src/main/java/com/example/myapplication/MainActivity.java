package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    LinearLayout b_1, b_2, b_3;
    private boolean start_stop = false;
    private int counter = 0;
    private Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b_1 = findViewById(R.id.bulb_1);
        b_2 = findViewById(R.id.bulb_2);
        b_3 = findViewById(R.id.bulb_3);
        b = findViewById(R.id.button1);
        b_1.setBackgroundColor(getResources().getColor(R.color.green));
        b_2.setBackgroundColor(getResources().getColor(R.color.grey));
        b_3.setBackgroundColor(getResources().getColor(R.color.grey));
    }

    public void onClickStart(View view)
    {
//Проверяем значение переменной start_stop
        if(!start_stop){
            b.setText(getString(R.string.stop));
            start_stop = true;
//Создаем новый поток
            new Thread(new Runnable() {
                @Override
                public void run() {
//Запускаем цикл
                    while (start_stop)
                    {
//Счетчик циклов
                        counter++;
//Переключатель цвета
                        switch (counter)
                        {
                            case 3:
                                b_1.setBackgroundColor(getResources().getColor(R.color.grey));
                                b_2.setBackgroundColor(getResources().getColor(R.color.yellow));
                                b_3.setBackgroundColor(getResources().getColor(R.color.grey));
                                break;
                            case 6:
                                b_1.setBackgroundColor(getResources().getColor(R.color.grey));
                                b_2.setBackgroundColor(getResources().getColor(R.color.grey));
                                b_3.setBackgroundColor(getResources().getColor(R.color.red));
                                break;
                            case 9:
                                b_1.setBackgroundColor(getResources().getColor(R.color.green));
                                b_2.setBackgroundColor(getResources().getColor(R.color.grey));
                                b_3.setBackgroundColor(getResources().getColor(R.color.grey));
//Сбрасываем счетчик на "0"
                                counter = 0;
                                break;
                        }

                        try {
//"Усыпляем" поток на 1 сек
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }
        else
        {
            b.setText(getString(R.string.start));
            start_stop = false;
            b_1.setBackgroundColor(getResources().getColor(R.color.green));
            b_2.setBackgroundColor(getResources().getColor(R.color.grey));
            b_3.setBackgroundColor(getResources().getColor(R.color.grey));
            counter = 0;

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//Останавливаем цикл
        start_stop = false;
    }
}