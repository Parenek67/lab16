package com.company;

public class InternetOrder implements Order {
    private final ListNode head;
    private int size;
    private Customer c = null;
    String adr;
    public InternetOrder(){
        this.head = new ListNode(null, null, null, 0);
        this.head.next = this.head.prev = this.head;
    }
    public InternetOrder(MenuItem[] mas){
        this();
        for (MenuItem ma : mas) add(ma);
    }
    public boolean add(MenuItem item) {
        if (item.getName() != null) {
            ListNode last = new ListNode(this.head, this.head.prev, item, item.getCost());
            this.head.prev = this.head.prev.next = last;
            this.size++;
            return true;
        }
        return false;
    }
    public String[] ItemsNames() {
        String[] mas = new String[size];
        for(int i = 0; i<size; i++){
            mas[i] = getNodeByIndex(i).value.getName();
        }
        return mas;
    }
    public int itemsQuantity() {
        return size;
    }
    public int itemQuantity(String itemName) {
        int a = 0;
        for(int i = 0;i<size;i++){
            if(itemName.equals(getNodeByIndex(i).value.getName()))
                a++;
        }
        return a;
    }
    public int itemQuantity(MenuItem item) {
        int a = 0;
        for(int i = 0;i<size;i++){
            if(item.equals(getNodeByIndex(i).value))
                a++;
        }
        return a;
    }
    public MenuItem[] getItems() {
        MenuItem[] d = new MenuItem[itemsQuantity()];
        for(int i = 0;i<size;i++){
            d[i]=getNodeByIndex(i).value;
        }
        return d;
    }
    public boolean remove(String ItemName) {//???
        for (ListNode node = this.head.next; node != this.head; node = node.next) {
            if(node.value != null) {
                if (node.value.getName().equals(ItemName)) {
                    node.prev.next = node.next;
                    node.next.prev = node.prev;
                    this.size--;
                    return true;
                }
            }
        }
        return false;
    }
    public boolean remove(MenuItem item) {
        for (ListNode node = this.head.next; node != this.head; node = node.next) {
            if (node.value.equals(item)) {
                node.prev.next = node.next;
                node.next.prev = node.prev;
                this.size--;
                return true;
            }
        }
        return false;
    }
    public boolean remove(String ItemName, double av) {
        for(int i = 0;i<size;i++)
            if(getNodeByIndex(i).value != null)
                if(getNodeByIndex(i).value instanceof Drink & getNodeByIndex(i).value.getName().equals(ItemName)) {
                    if(((Drink) getNodeByIndex(i).value).getAlcoholVol() == av) {
                        remove(getNodeByIndex(i).value);
                        return true;
                    }
                }
        for (ListNode node = this.head.next; node != this.head; node = node.next) {
            if(node.value != null) {
                if(node.value instanceof Drink & node.value.getName().equals(ItemName)) {
                    if(((Drink) node.value).getAlcoholVol() == av) {
                        node.prev.next = node.next;
                        node.next.prev = node.prev;
                        size--;
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public int removeAll(String ItemName) {
        int a = 0;
        for (ListNode node = this.head.next; node != this.head; node = node.next) {
            if (node.value.getName().equals(ItemName)) {
                node.prev.next = node.next;
                node.next.prev = node.prev;
                this.size--;
                a++;
            }
        }
        return a;
    }
    public int removeAll(MenuItem item) {
        int a = 0;
        for (ListNode node = this.head.next; node != this.head; node = node.next) {
            if (node.value.equals(item)) {
                node.prev.next = node.next;
                node.next.prev = node.prev;
                this.size--;
                a++;
            }
        }
        return a;
    }
    public void removeAll(){
        for (ListNode node = this.head.next; node != this.head; node = node.next) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            this.size--;
        }
    }
    public MenuItem[] SortedItemsByCostDesc() {
        MenuItem[] d = getItems();
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
        int a = 0;
        for (ListNode node = this.head.next; node != this.head; node = node.next) {
            if(node.price > 0.0)
                a+=node.price;
        }
        return a;
    }
    public void setAdr(String str){
        this.adr = str;
    }
    public String getAdr(){
        return adr;
    }
    public Customer getCustomer() {
        return c;
    }
    public void setCustomer(Customer customer) {
        c = customer;
    }
    public static class ListNode {//ыыыы
        private ListNode next;
        private ListNode prev;
        public MenuItem value;
        public double price;
        ListNode(ListNode next, ListNode prev, MenuItem value, double price) {
            this.next = next;
            this.prev = prev;
            this.value = value;
            this.price = price;
        }
    }
    public ListNode getNodeByIndex(int index) {//функциональная тема
        ListNode resultNode = null;
        int half = this.size / 2;
        int count = half > index ? index : this.size - index - 1;
        for (ListNode node = (half > index ? this.head.next : this.head.prev); node != this.head; node = (half > index ? node.next : node.prev)) {
            if (count-- == 0) {
                resultNode = node;
                break;
            }
        }
        return resultNode;
    }
}
