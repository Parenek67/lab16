package com.company;

import java.util.Arrays;

public class TableOrdersManager implements OrdersManager {
    Order[] orders = new Order[10];
    void add(Order order, Integer tableNumber){
        if (orders[tableNumber] == null)
            orders[tableNumber] = order;
    }
    void addItem(MenuItem item, Integer tableNumber){//
        if (orders[tableNumber] != null)
            orders[tableNumber].add(item);
    }
    Integer freeTableNumber(){
        for(int i = 0; i<orders.length; i++)
            if(orders[i] == null)
                return i;
        return 0;
    }
    Integer[] freetableNumbers(){//
        Integer[] mas = new Integer[0];
        for (int i = 0;i<orders.length;i++) {
            if (orders[i] == null) {
                mas = Arrays.copyOf(mas, mas.length + 1);
                mas[mas.length-1] = i;
            }
        }
        return mas;
    }
    Integer[] closetableNumbers(){//
        Integer[] mas = new Integer[0];
        for (int i = 0;i<orders.length;i++) {
            if (orders[i] != null) {
                mas = Arrays.copyOf(mas, mas.length + 1);
                mas[mas.length-1] = i;
            }
        }
        return mas;
    }
    public Order getOrder(Integer tableNumber){
        return orders[tableNumber];
    }
    public void remove(Integer tableNumber){//
        if(orders[tableNumber] != null)
        orders[tableNumber] = null;
    }
    public int remove(Order order){
        for(int i = 0;i < orders.length;i++)
            if(orders[i] != null)
            if(orders[i].equals(order)) {
                orders[i] = null;
                return i;
            }
        return 0;
    }
    public int removeAll(Order order){
        int a = 0;
        for(int i = 0;i < orders.length;i++)
            if(orders[i] != null)
            if(orders[i].equals(order)) {
                orders[i] = null;
                a++;
            }
        return a;
    }
    public int itemsQuantity(String itemName) {
        int a = 0;
        for(Order ord: orders){
            if(ord != null)
                a+=ord.itemQuantity(itemName);
        }
        return a;
    }
    public int itemsQuantity(MenuItem item) {
        int a = 0;
        for(Order ord: orders){
            if(ord != null)
                a+=ord.itemQuantity(item);
        }
        return a;
    }
    public Order[] getOrders() {
        int j = 0;
        Order[] d = new Order[orders.length-freetableNumbers().length];
        for(Order it: orders)
            if (it != null) {
                d[j] = it;
                j++;
            }
        return d;
    }
    public double ordersCostSummary() {
        double a = 0;
        for(Order ord: orders){
            if(ord != null)
                a+=ord.CostTotal();
        }
        return a;
    }
    public int ordersQuantity() {
        Order[] a = getOrders();
        return a.length;
    }

}
