package com.example.kalkulone;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<Drink> listOfDrink=new ArrayList<Drink>();
    DrinkAdapter drinkAdapter=new DrinkAdapter(listOfDrink);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView)findViewById(R.id.recycler);
        listOfDrink.add(new Drink());//по умолчанию добавляем один элемент
        drinkAdapter.notifyItemInserted(0);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(drinkAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

   /* @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        switch (id){
            case R.id.action_recommendation:
                Intent intent=new Intent(MainActivity.this,AboutActivity.class);
                startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }*/

    public void onFABClick(View view) {
        listOfDrink.add(new Drink());
        drinkAdapter.notifyItemInserted(listOfDrink.size()-1);
    }

    public void onCalculateClick(View view) {
        boolean checked=((RadioButton)findViewById(R.id.radioButton)).isChecked();
        float promil=0;
        double timeRemoval=0;
        float w=0.6f;
        if (checked)
            w=0.7f;
        float massa=Integer.parseInt(((EditText)findViewById(R.id.editText)).getText().toString());
        float allAlc=0;
        for (int i=0;i<listOfDrink.size();i++) {
            allAlc += (((listOfDrink.get(0).getVol()/100 * listOfDrink.get(0).getValue())*0.79384)/massa*w)-(listOfDrink.get(i).getTri()*0.15);

        }

        String output = "Степень опьянения:";
        if (0<allAlc && allAlc<=0.3)
            output = " Отсутствие влияния алкоголя";
        if (0.3<allAlc && allAlc<= 0.5)
            output=" Незначительное влияние аклоголя";
        if (0.5<allAlc && allAlc<=1.5)
            output=" Легкое опьянение";
        if (1.5<allAlc && allAlc<=2.5)
            output=" Опьянение средней степени";
        if(2.5<allAlc && allAlc<=3)
            output=" ильное опьянение";
        if (3<allAlc && allAlc<=5)
            output=" Возможно тяжелое отравление алкоголем";
        if(5<allAlc && allAlc<=6)
            output=" Смертельная доза";
        if(allAlc>=6)
            output="Скореее всего вы мертвы";

        promil=allAlc;
        timeRemoval=(promil/0.15);
        int res = (int)timeRemoval;
        int res2 = (int)((timeRemoval-(int)timeRemoval)*60);

        String outMesseng="Концентрация алкоголя в крови: ";
        if (promil<=0)
            outMesseng="Вы трезвы";
        else
            outMesseng = "Концентрация алкоголя в крови "+ promil + " промилль\n" + "Время выведения из организма " + res + "часов" + res2 + "минут\n"+"Степень опьянения:"+ output;


        AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Итого, по предостваленным Вами данным.").setMessage(outMesseng).setCancelable(false).setNegativeButton("Завершить",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
