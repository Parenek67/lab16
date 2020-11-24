package com.company;

public class InternetOrdersManager implements OrdersManager{
    private QueueNode head;
    private int size;
    public InternetOrdersManager(){
        this.head = new QueueNode(null, null, null);
        this.head.next = this.head.prev = this.head;
    }
    public boolean add(Order ord) {
        QueueNode last = new QueueNode(this.head, this.head.prev, ord);
        this.head.prev = this.head.prev.next = last;
        this.size++;
        return false;
    }
    public boolean remove(String itemName) {//???
        for (QueueNode node = this.head.next; node != this.head; node = node.next) {
            node.value.remove(itemName);
        }
        return false;
    }
    public void removeadr(String adr){
        for(int i = 0;i<ordersQuantity();i++){
            if(getNodeByIndex(i).value.getAdr().equals(adr)) {
                getNodeByIndex(i).value = null;
                //getNodeByIndex(i).prev.next = getNodeByIndex(i).next;
                //getNodeByIndex(i).next.prev = getNodeByIndex(i).prev;
                //this.size--;
            }
        }
    }
    public Order order(){
        return getNodeByIndex(0).value;
    }
    public int itemsQuantity(String itemName) {
        int a = 0;
        for(int i = 0;i<ordersQuantity();i++)
            a+=getNodeByIndex(i).value.itemQuantity(itemName);
        return a;
    }
    public int itemsQuantity(MenuItem item) {
        int a = 0;
        for(int i = 0;i<ordersQuantity();i++)
            a+=getNodeByIndex(i).value.itemQuantity(item);
        return a;
    }
    public Order[] getOrders() {
        Order[] d = new Order[ordersQuantity()];
        for(int i = 0;i<ordersQuantity();i++){
            d[i]=getNodeByIndex(i).value;
        }
        return d;
    }
    public double ordersCostSummary() {
        double a = 0;
        for(int i = 0;i<ordersQuantity();i++)
            a+=getNodeByIndex(i).value.CostTotal();
        return a;
    }
    public int ordersQuantity() {
        return size;
    }
    public static class QueueNode {//ыыыы
        private QueueNode next;
        private QueueNode prev;
        public Order value;
        QueueNode(QueueNode next, QueueNode prev, Order value) {
            this.next = next;
            this.prev = prev;
            this.value = value;
        }
    }
    public QueueNode getNodeByIndex(int index) {//функциональная тема
        QueueNode resultNode = null;
        int half = this.size / 2;
        int count = half > index ? index : this.size - index - 1;
        for (QueueNode node = (half > index ? this.head.next : this.head.prev); node != this.head; node = (half > index ? node.next : node.prev)) {
            if (count-- == 0) {
                resultNode = node;
                break;
            }
        }
        return resultNode;
    }
}
