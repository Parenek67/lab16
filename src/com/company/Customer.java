package com.company;
//+
public final class Customer {
    final String firstName;
    final String secondName;
    final int age;
    final Adress adress;
    Customer MATURE_UNKNOWN_CUSTOMER;
    Customer NOT_MATURE_UNKNOWN_CUSTOMER;
    public Customer(String fn, String sn, int a, Adress adress){
        firstName = fn;
        secondName = sn;
        age = a;
        this.adress = adress;
    }
    public Adress getAdress() {
        return adress;
    }
    public int getAge() {
        return age;
    }
    public String getSecondName() {
        return secondName;
    }
    public String getFirstName() {
        return firstName;
    }
}

