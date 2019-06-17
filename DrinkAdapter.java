package com.example.kalkulone;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DrinkAdapter extends RecyclerView.Adapter<DrinkAdapter.DrinkViewHolder> {
    ArrayList<Drink> drinks;

    public DrinkAdapter (ArrayList<Drink> drinks){
        this.drinks=drinks;
    }

    public class DrinkViewHolder extends RecyclerView.ViewHolder{
        EditText vol;
        EditText mill;
        TextView num;
        EditText time;
        public DrinkViewHolder (View view){
            super(view);
            vol=(EditText) view.findViewById(R.id.editText4);
            mill=(EditText) view.findViewById(R.id.editText5);
            num=(TextView) view.findViewById(R.id.textView4);
            time=(EditText) view.findViewById(R.id.editText3);
        }
    }

    @NonNull
    @Override
    public DrinkAdapter.DrinkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new DrinkViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DrinkAdapter.DrinkViewHolder holder, final int position) {

        holder.num.setText("Напиток "+ (position+1));
        holder.vol.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length() > 0) {
                    drinks.get(position).setVol(Integer.parseInt(s.toString()));
                } else {
                    drinks.get(position).setVol(0);
                }
            }
        });
        holder.mill.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length() > 0) {
                    drinks.get(position).setValue(Integer.parseInt(s.toString()));
                } else {
                    drinks.get(position).setValue(0);
                }
            }
        });
        holder.time.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length() > 0) {
                    drinks.get(position).setTri(Integer.parseInt(s.toString()));
                } else {
                    drinks.get(position).setTri(0);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return drinks.size();
    }
}
