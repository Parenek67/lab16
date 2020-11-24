package com.company;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
    new App();
	/*MenuItem item1 = new Dish("Бургер","С говядиной", 300);
	MenuItem item2 = new Drink("Водка","Паленая",350, DrinkTypeEnum.vodka, 0.5);
	MenuItem item3 = new Dish("Закуска","К водке", 100);
	TableOrder ord1 = new TableOrder();
	TableOrder ord3 = new TableOrder();
	TableOrder ord4 = new TableOrder();
	System.out.println("------тестрирование TableOrder------");
	ord1.add(item1);
	ord1.add(item2);
	ord1.add(item3);
	ord1.add(item1);
	System.out.println(ord1.itemsQuantity());
	System.out.println(ord1.CostTotal());
	for(MenuItem it: ord1.getItems())
		System.out.println(it.getName()+" ");
	System.out.println(ord1.itemQuantity(item1));
	System.out.println(ord1.itemQuantity("Херовина"));
	System.out.println(Arrays.toString(ord1.ItemsNames()));
	for(MenuItem it: ord1.SortedItemsByCostDesc())
		System.out.println(it.getName()+", "+it.getCost()+";");
	ord1.removeAll(item1);
	ord1.removeAll("Закуска");
	System.out.println(ord1.itemsQuantity());
	System.out.println("------тестрирование InternetOrder------");
	InternetOrder ord5 = new InternetOrder();
	ord5.add(item3);
	ord5.add(item1);
	InternetOrder ord6 = new InternetOrder();
	ord6.add(item2);
	InternetOrder ord2 = new InternetOrder();
	ord2.add(item1);
	ord2.add(item1);
	ord2.add(item2);
	ord2.add(item3);
	System.out.println(ord2.itemsQuantity());
	System.out.println(ord2.CostTotal());
	for(MenuItem it: ord2.getItems())
		System.out.println(it.getName()+" ");
	System.out.println(ord2.itemQuantity(item1));
	System.out.println(ord2.itemQuantity("Херовина"));
	System.out.println(Arrays.toString(ord2.ItemsNames()));
	for(MenuItem it: ord2.SortedItemsByCostDesc())
		System.out.println(it.getName()+", "+it.getCost()+";");
	ord2.removeAll(item1);
	ord2.removeAll("Закуска");
	System.out.println(ord2.itemsQuantity());
	System.out.println("------тестрирование TableOrdersManager------");
	ord3.add(item2);
	ord3.add(item3);
	ord4.add(item1);
	ord4.add(item1);
	TableOrdersManager tom = new TableOrdersManager();
	tom.add(ord1, 1);
	tom.add(ord3, 2);
	tom.add(ord4, 4);
	for(Order ord: tom.getOrders())
		System.out.println(Arrays.toString(ord.ItemsNames()));
	System.out.println(Arrays.toString(tom.getOrder(1).ItemsNames()));
	System.out.println(tom.itemsQuantity("Водка"));//2
	System.out.println(tom.freeTableNumber());//0
	System.out.println(Arrays.toString(tom.freetableNumbers()));
	System.out.println(tom.ordersCostSummary());
	tom.addItem(item2, 2);
	System.out.println(tom.itemsQuantity(item2));//3
	tom.remove(4);
	tom.remove(ord3);
	tom.removeAll(ord1);
	System.out.println(Arrays.toString(tom.freetableNumbers()));
	System.out.println("------тестрирование InternetOrdersManager------");
	InternetOrdersManager iom = new InternetOrdersManager();
	iom.add(ord2);
	iom.add(ord5);
	iom.add(ord6);
	System.out.println(iom.ordersQuantity());
	System.out.println(iom.itemsQuantity(item1));//3
	System.out.println(iom.itemsQuantity("Водка"));//2
	System.out.println(iom.ordersCostSummary());
	for(Order ord: iom.getOrders())
		System.out.println(Arrays.toString(ord.ItemsNames()));
	iom.remove("Бургер");
	System.out.println(iom.itemsQuantity("Бургер"));*/
    }
}
