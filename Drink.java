package com.example.kalkulone;

public class Drink {
    private float vol;
    private float value;
    private float tri;

    public Drink() { }

    public Drink(float vol, float value, float tri) {
        this.vol = vol;
        this.value = value;
        this.tri= tri;
    }

    public float getVol() { return vol; }

    public void setVol(float vol) {
        this.vol = vol;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public float getTri() { return tri; }

    public void setTri(float tri) {
        this.tri = tri;
    }
}
