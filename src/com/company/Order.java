package com.company;

public interface Order {
    boolean add(MenuItem item);
    String[] ItemsNames();
    int itemsQuantity();
    int itemQuantity(String itemName);
    int itemQuantity(MenuItem item);
    MenuItem[] getItems();
    boolean remove(String ItemName);
    boolean remove(MenuItem item);
    int removeAll(String ItemName);
    int removeAll(MenuItem item);
    void removeAll();
    MenuItem[] SortedItemsByCostDesc();
    double CostTotal();
    String getAdr();
    Customer getCustomer();
    void setCustomer(Customer customer);
    boolean remove(String s, double parseDouble);
}

