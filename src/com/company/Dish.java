package com.company;

public class Dish extends MenuItem{
    public Dish(String n, String d, double c){
        if(!n.equals("") & !d.equals("") & c >= 0) {
            name = n;
            description = d;
            cost = c;
        }
        else throw new IllegalArgumentException();
    }
    public Dish(String n, String d){
        if(!n.equals("") & !d.equals("")) {
            name = n;
            description = d;
            cost = 0.0;
        }
        else throw new IllegalArgumentException();
    }
}

