package com.company;

public class TableOrder implements Order{
    private int size;
    private MenuItem[] items = new MenuItem[10];
    public boolean add(MenuItem item) {
        for(int i = 0;i<items.length;i++)
            if(items[i] == null) {
                items[i] = item;
                return true;
        }
        return false;
    }
    public String[] ItemsNames() {
        int i = 0;
        String[] mas = new String[itemsQuantity()];
        for(MenuItem it: items)
            if(it != null) {
                mas[i] = it.getName();
                i++;
            }
        return mas;
    }
    public int itemsQuantity() {
        int q=0;
        for(MenuItem it: items)
            if(it != null)
                q++;
        return q;
    }
    public int itemQuantity(String itemName) {
        int q=0;
        for(MenuItem it: items)
            if(it != null)
                if(it.getName().equals(itemName))
                    q++;
        return q;
    }
    public int itemQuantity(MenuItem item) {
        int q=0;
        for(MenuItem it: items)
            if(it != null)
                if(it.equals(item))
                    q++;
        return q;
    }
    public MenuItem[] getItems() {
        int j = 0;
        MenuItem[] d = new MenuItem[itemsQuantity()];
        for(MenuItem it: items)
            if (it != null) {
                d[j] = it;
                j++;
            }
        return d;
    }
    public boolean remove(String ItemName) {
        for(int i = 0;i<items.length;i++)
            if(items[i] != null)
                if(items[i].getName().equals(ItemName)) {
                    items[i] = null;
                    return true;
                }
        return false;
    }
    public boolean remove(String ItemName, double av) {
        for(int i = 0;i<items.length;i++)
            if(items[i] != null)
                if(items[i] instanceof Drink & items[i].getName().equals(ItemName)) {
                    if(((Drink) items[i]).getAlcoholVol() == av) {
                        items[i] = null;
                        return true;
                    }
                }
        return false;
    }
    public void removeAll() {
        for(int i = 0;i<items.length;i++)
            if(items[i] != null)
                items[i] = null;
    }
    public boolean remove(MenuItem item) {
        for(int i = 0;i<items.length;i++)
            if(items[i].equals(item)) {
                items[i] = null;
                return true;
            }
        return false;
    }
    public int removeAll(String ItemName) {
        int q = 0;
        for(int i = 0;i<items.length;i++)
            if(items[i] != null)
            if(items[i].getName().equals(ItemName)) {
                items[i] = null;
                q++;
            }
        return q;
    }
    public int removeAll(MenuItem item) {
        int q = 0;
        for(int i = 0;i<items.length;i++)
            if(items[i] != null)
            if(items[i].equals(item)) {
                items[i] = null;
                q++;
            }
        return q;
    }
    public MenuItem[] SortedItemsByCostDesc() {
        int j = 0;
        MenuItem[] d = new MenuItem[itemsQuantity()];
        for(MenuItem it: items)
            if (it != null) {
                d[j] = it;
                j++;
            }
        boolean isSorted = false;
        MenuItem buf;
        while(!isSorted) {
            isSorted = true;
            for (int i = 0; i < d.length-1; i++) {
                if(d[i].getCost() < d[i+1].getCost()){
                    isSorted = false;
                    buf = d[i];
                    d[i] = d[i+1];
                    d[i+1] = buf;
                }
            }
        }
        return d;
    }
    public double CostTotal() {
        double price = 0;
        for(int i = 0;i<10;i++)
            if(items[i] != null)
                price += items[i].getCost();
        return price;
    }

    public String getAdr() {
        return null;
    }

    public Customer getCustomer() {
        return null;
    }
    public void setCustomer(Customer customer) {}
}
