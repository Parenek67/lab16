package com.company;
//+
public final class Drink extends MenuItem implements Alcoholable {
    final private double alcoholVol;
    final private DrinkTypeEnum type;
    public Drink(String n, String d, double c, DrinkTypeEnum t, double av){
        if(!n.equals("") & !d.equals("") & c >= 0) {
            name = n;
            description = d;
            cost = c;
            type = t;
            alcoholVol = av;
        }
        else throw new IllegalArgumentException();
    }
    public Drink(String n, String d, DrinkTypeEnum t, double av){
        if(!n.equals("") & !d.equals("")) {
            name = n;
            description = d;
            cost = 0;
            type = t;
            alcoholVol = av;
        }
        else throw new IllegalArgumentException();
    }
    public boolean isAlcoholDrink(DrinkTypeEnum t) {
        return t.ordinal() >= 11;
    }
    public double getAlcoholVol() {
        return alcoholVol;
    }
}

