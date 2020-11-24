package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

public class App extends JFrame {
   String select = "";
   int count = 0;
   TableOrder[] to = new TableOrder[10];
   TableOrdersManager tom = new TableOrdersManager();
   InternetOrdersManager iom = new InternetOrdersManager();
   JMenuBar menu = new JMenuBar();
   /////
   JPanel pnl = new JPanel();
   JLabel lbl = new JLabel("15.0");
   JLabel lbl2 = new JLabel("400.0");
   JLabel lbl3 = new JLabel("Выберите столик:");//
   JLabel lbl4 = new JLabel("Стоимость заказа: 0 рублей");
   JTextArea your_order = new JTextArea("Ваш заказ:");
   JButton[] addItem = new JButton[2];
   JButton complete = new JButton("Заказать");
   JButton complete2 = new JButton("Заказать");
   JButton ch = new JButton("check");
   JButton delbtn = new JButton("Удалить");
   JComboBox<DrinkTypeEnum> drinks = new JComboBox<>(DrinkTypeEnum.values());
   JComboBox dishes = new JComboBox(dish_menu().keySet().toArray());
   JComboBox<Double> volume = new JComboBox<>(new Double[]{0.1, 0.3, 0.5, 1.0});
   JComboBox<Integer> tables = new JComboBox<>(tom.freetableNumbers());//
   JComboBox del = new JComboBox();
   JTextArea ch_item = new JTextArea("Выберите напиток:\n\nВыберите блюдо:\n\nУдалить блюдо:");
   /////
   JLabel lbl5 = new JLabel("Выберите заказ:");
   JComboBox<Integer> close_tables = new JComboBox<>();
   JButton ok = new JButton("OK");
   JButton del_ord = new JButton("Удалить заказ");
   JButton save = new JButton("Сохранить");
   JTextArea ch_item2 = new JTextArea("Выберите напиток:\n\nВыберите блюдо:");
   /////
   JTextField[] adress_fld = new JTextField[9];
   JTextArea adr_lbl = new JTextArea("Имя\n\nФамилия\n\nВозраст\n\nГород\n\nКод\n\nУлица\n\nДом\n\nЛитера\n\nКвартира");
   JButton adr_ok = new JButton("ОК");
   ArrayList<InternetOrder> list = new ArrayList<>();
   /////
   JComboBox<String> adresses = new JComboBox<>();
   App(){
       super("Ресторан");
       menu.add(CreateOrder());
       for(int i = 0;i<10;i++)
           to[i] = new TableOrder();
       for(int i = 0;i<2;i++)
           addItem[i] = new JButton("Заказать");
       for(int i = 0;i<9;i++)
           adress_fld[i] = new JTextField("");
       menu.add(manager());
       setJMenuBar(menu);
       pnl.setLayout(null);
       comp_bounds();
       add(pnl);
       setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
       setSize(700, 570);
       setResizable(false);
       JDialog dialog = createDialog();
       addItem[0].addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               Order a = null;
               if(select.equals("Ресторан-заказ"))
                   a = to[(Integer) tables.getSelectedItem()];
               if(select.equals("Ресторан-менеджер"))
                   a = to[(Integer) close_tables.getSelectedItem()];
               if(select.equals("Интернет-заказ"))
                   a = list.get(list.size()-1);
               if(select.equals("Интернет-менеджер")) {
                   for(Order ord: iom.getOrders()){
                       if(ord != null)
                       if(ord.getAdr().equals(adresses.getSelectedItem().toString()))
                           a = ord;
                   }
               }
               your_order.setText(your_order.getText()+"\n"+drinks.getSelectedItem()+", объем "+volume.getSelectedItem()+", цена "+lbl.getText());
               a.add(new Drink(Objects.requireNonNull(drinks.getSelectedItem()).toString(),"описание", Double.parseDouble(lbl.getText()),
                       (DrinkTypeEnum)drinks.getSelectedItem(), (Double)volume.getSelectedItem()));
               lbl4.setText("Стоимость заказа: "+ a.CostTotal()+" рублей");
               complete.setEnabled(true);
               complete2.setEnabled(true);
               del.addItem(drinks.getSelectedItem().toString()+" "+volume.getSelectedItem().toString());
           }
       });
       addItem[1].addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               Order a = null;
               if(select.equals("Ресторан-заказ"))
                   a = to[(Integer) tables.getSelectedItem()];
               if(select.equals("Ресторан-менеджер"))
                   a = to[(Integer) close_tables.getSelectedItem()];
               if(select.equals("Интернет-заказ"))
                   a = list.get(list.size()-1);
               if(select.equals("Интернет-менеджер")) {
                   for(Order ord: iom.getOrders()){
                       if(ord != null)
                       if(ord.getAdr().equals(adresses.getSelectedItem().toString()))
                           a = ord;
                   }
               }
               your_order.setText(your_order.getText()+"\n"+dishes.getSelectedItem()+", цена "+lbl2.getText());
               complete.setEnabled(true);
               complete2.setEnabled(true);
               a.add(new Dish(Objects.requireNonNull(dishes.getSelectedItem()).toString(),"описание", Double.parseDouble(lbl2.getText())));
               lbl4.setText("Стоимость заказа: "+ a.CostTotal()+" рублей");
               del.addItem(dishes.getSelectedItem().toString());
           }
       });
       volume.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               double a = 0;
               for(DrinkTypeEnum en: DrinkTypeEnum.values())
                   if (en.name().equals(Objects.requireNonNull(drinks.getSelectedItem()).toString())) {
                       if(en.ordinal()<6)
                           a = Math.round(Double.parseDouble(Objects.requireNonNull(volume.getSelectedItem()).toString()) * (en.ordinal()+1) * 150);
                       if(en.ordinal()>=6 & en.ordinal()<11)
                           a = Math.round(Double.parseDouble(Objects.requireNonNull(volume.getSelectedItem()).toString()) * (en.ordinal()+1) * 125);
                       if(en.ordinal()>=11)
                           a = Math.round(Double.parseDouble(Objects.requireNonNull(volume.getSelectedItem()).toString()) * (18-en.ordinal()) * 125);
                       lbl.setText(String.valueOf(a));
                       lbl.revalidate();
                       lbl.repaint();
                   }
           }
       });
       drinks.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               double a = 0;
               for(DrinkTypeEnum en: DrinkTypeEnum.values())
                   if (en.name().equals(Objects.requireNonNull(drinks.getSelectedItem()).toString())) {
                       if(en.ordinal()<6)
                           a = Math.round(Double.parseDouble(Objects.requireNonNull(volume.getSelectedItem()).toString()) * (en.ordinal()+1) * 150);
                       if(en.ordinal()>=6 & en.ordinal()<11)
                           a = Math.round(Double.parseDouble(Objects.requireNonNull(volume.getSelectedItem()).toString()) * (en.ordinal()+1) * 125);
                       if(en.ordinal()>=11)
                           a = Math.round(Double.parseDouble(Objects.requireNonNull(volume.getSelectedItem()).toString()) * (18-en.ordinal()) * 125);
                       lbl.setText(String.valueOf(a));
                       lbl.revalidate();
                       lbl.repaint();
                   }
           }
       });
       dishes.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               lbl2.setText(String.valueOf(dish_menu().get(dishes.getSelectedItem())));
               lbl2.revalidate();
               lbl2.repaint();
           }
       });
       ch.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               for(MenuItem it: to[(int) tables.getSelectedItem()].getItems())
                   System.out.println(it.getName());
               System.out.println(to[(int) tables.getSelectedItem()].CostTotal());
               System.out.println(to[(int) tables.getSelectedItem()].itemsQuantity());
               System.out.println(Arrays.toString(tom.freetableNumbers()));
               System.out.println(Arrays.toString(tom.getOrder(0).ItemsNames()));
           }
       });
       complete.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               tom.add(to[(int) tables.getSelectedItem()], (Integer)tables.getSelectedItem());
               JOptionPane.showMessageDialog(null,"Ваш заказ принят!");
               your_order.setText("Ваш заказ:");
               del.removeAllItems();
               lbl4.setText("Стоимость заказа: 0 рублей");
               complete.setEnabled(false);
               tables.removeAllItems();
               for(Integer i:tom.freetableNumbers())
                   tables.addItem(i);
           }
       });
       complete2.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               dialog.setVisible(true);
               for(int i = 0;i<9;i++) {
                   adress_fld[i].setText("");
               }
           }
       });
       delbtn.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               String [] str = Objects.requireNonNull(del.getSelectedItem()).toString().split(" ");///////////////////
               Order a = null;
               if(select.equals("Ресторан-заказ"))
                   a = to[(int) tables.getSelectedItem()];
               if(select.equals("Интернет-заказ")) {
                   a = list.get(list.size()-1);
               }
               if(select.equals("Интернет-менеджер")) {
                   for(Order ord: iom.getOrders()){
                       if(ord != null)
                       if(ord.getAdr().equals(adresses.getSelectedItem().toString()))
                           a = ord;
                   }
               }
               if(select.equals("Ресторан-менеджер")){
                   a = to[(int) close_tables.getSelectedItem()];
               }
               if(dish_menu().get(del.getSelectedItem().toString()) != null)
                   a.remove(str[0]);
               else
                   a.remove(str[0], Double.parseDouble(str[1]));
               del.removeItem(del.getSelectedItem().toString());
               if(a.CostTotal() == 0.0) {
                   complete.setEnabled(false);
                   complete2.setEnabled(false);
               }
               your_order.setText("Ваш заказ:");
               for(MenuItem it: a.getItems()){
                   if(it instanceof Drink)
                       your_order.setText(your_order.getText()+"\n"+it.getName()+", объем "+((Drink) it).getAlcoholVol()+", цена "+it.getCost());
                   else
                       your_order.setText(your_order.getText()+"\n"+it.getName()+", цена "+it.getCost());
               }
               lbl4.setText("Стоимость заказа: "+a.CostTotal()+" рублей");
           }
       });
       save.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               pnl.removeAll();
               pnl.revalidate();
               pnl.repaint();
           }
       });
       del_ord.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               Order a = null;
               if(select.equals("Интернет-менеджер")) {
                   for(Order ord: iom.getOrders()){
                       if(ord != null)
                       if(ord.getAdr().equals(adresses.getSelectedItem().toString())) {
                           System.out.println(adresses.getSelectedItem().toString());
                           a = ord;
                       }
                   }
                   //assert a != null;
                   iom.removeadr(a.getAdr());
               }
               if(select.equals("Ресторан-менеджер")){
                   tom.remove((int)close_tables.getSelectedItem());
               }
               del.removeAllItems();
               your_order.setText("Ваш заказ:");
           }
       });
       ok.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               pnl_add();
               pnl.add(lbl);
               pnl.add(lbl2);
               pnl.add(ch_item2);
               pnl.add(save);
               pnl.add(del_ord);
               if(select.equals("Ресторан-менеджер")) {
                   your_order.setText("Заказ для столика №" + close_tables.getSelectedItem());
                   Order ord = tom.getOrder((Integer) Objects.requireNonNull(close_tables.getSelectedItem()));
                   System.out.println(Arrays.toString(tom.getOrder((Integer) close_tables.getSelectedItem()).ItemsNames()));
                   for (MenuItem it : ord.getItems()) {
                       if (it instanceof Drink)
                           your_order.setText(your_order.getText() + "\n" + it.getName() + ", объем " + ((Drink) it).getAlcoholVol() + ", цена " + it.getCost());
                       else
                           your_order.setText(your_order.getText() + "\n" + it.getName() + ", цена " + it.getCost());
                   }
               }
               if(select.equals("Интернет-менеджер")){
                   your_order.setText("Заказ по адресу " + adresses.getSelectedItem());
                   Order a = null;
                   String[] str = adresses.getSelectedItem().toString().split(" ");
                   for(Order ord: iom.getOrders()){
                       if(ord != null)
                       if(ord.getAdr().equals(adresses.getSelectedItem().toString()))
                           a = ord;
                   }
                   for (MenuItem it : a.getItems()) {
                       if (it instanceof Drink)
                           your_order.setText(your_order.getText() + "\n" + it.getName() + ", объем " + ((Drink) it).getAlcoholVol() + ", цена " + it.getCost());
                       else
                           your_order.setText(your_order.getText() + "\n" + it.getName() + ", цена " + it.getCost());
                   }
               }
               pnl.revalidate();
               pnl.repaint();
           }
       });
       adr_ok.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               boolean b = true;
               boolean age = false;
               for(int i = 0;i<9;i++){
                   if(adress_fld[i].getText().equals(""))
                        b = false;
               }
               for(MenuItem it: list.get(list.size()-1).getItems()){
                   if(it instanceof Drink ){
                       Drink dr = (Drink) it;
                       System.out.println(dr.getType());
                       if(!dr.isAlcoholDrink(dr.getType())) {
                           age = true;
                           System.out.println(age);
                       }
                   }
               }
               if(b) {
                   if((age & Integer.parseInt(adress_fld[2].getText())>17) || !age) {
                       Adress adr = new Adress(adress_fld[3].getText(), Integer.parseInt(adress_fld[4].getText()),
                               adress_fld[5].getText(), Integer.parseInt(adress_fld[6].getText()), adress_fld[7].getText().charAt(0),
                               Integer.parseInt(adress_fld[8].getText()), count);
                       count++;
                       list.get(list.size() - 1).setAdr("Ул. " + adr.getStreetName() + ", д. "
                               + adr.getBuildingNumber() + adr.getBuildingLetter() + ", кв. " + adr.getApartmentNumber()+" ,# "+ count);
                       list.get(list.size() - 1).setCustomer(new Customer(adress_fld[0].getText(), adress_fld[1].getText(), Integer.parseInt(adress_fld[2].getText()), adr));
                       JOptionPane.showMessageDialog(null, "Ваш заказ принят!", "заказ", 1);
                       dialog.setVisible(false);
                       dialog.dispose();
                       your_order.setText("Ваш заказ: ");
                       del.removeAllItems();
                       iom.add(list.get(list.size() - 1));
                       for (Order ord : iom.getOrders())
                           if (ord != null)
                               System.out.println(Arrays.toString(ord.ItemsNames()));
                       pnl.removeAll();
                       pnl.revalidate();
                       pnl.repaint();
                   }
                   else JOptionPane.showMessageDialog(null,"Вам нет 18!","э", 0);
               }
               else JOptionPane.showMessageDialog(null,"Введите все поля!","э", 0);
           }
       });
       int[] txt = {0, 1, 3, 5, 7};
       int[] num = {2, 4, 6, 8};
       for(int i: txt)
           adress_fld[i].addKeyListener(new KeyAdapter() {
               @Override
               public void keyTyped(KeyEvent e) {
                   char c = e.getKeyChar();
                   if(c<65 || (c>90 & c<97) || c>122)
                       e.consume();
               }
           });
       for(int i: num)
           adress_fld[i].addKeyListener(new KeyAdapter() {
               @Override
               public void keyTyped(KeyEvent e) {
                   char c = e.getKeyChar();
                   if(c < 48 || c > 57)
                       e.consume();
               }
           });
       setVisible(true);
   }
   private void pnl_add(){
       pnl.add(drinks);
       pnl.add(dishes);
       pnl.add(addItem[0]);
       pnl.add(addItem[1]);
       pnl.add(your_order);
       pnl.add(volume);
       pnl.add(ch_item);
       pnl.add(delbtn);
       pnl.add(del);
       del.removeAllItems();
   }
   private void comp_bounds(){
       ch_item.setFont(new Font("Verdana", Font.PLAIN, 12));
       ch_item.setOpaque(false);
       drinks.setBounds(145,47,100,25);
       volume.setBounds(260,47,50,25);
       lbl.setBounds(330,47,70,25);
       lbl.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
       lbl2.setBounds(330,80,70,25);
       lbl2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
       addItem[0].setBounds(410, 47,100,25);
       addItem[1].setBounds(410, 80,100,25);
       ch_item.setBounds(10,50,135,95);//
       dishes.setBounds(145,80,100,25);
       del.setBounds(145,113,100,25);
       delbtn.setBounds(410,113,100,25);
       your_order.setBounds(10,160,300,350);
       lbl3.setBounds(330,170,110,25);
       lbl4.setBounds(330,195,200,25);
       complete.setBounds(330,240,100,25);
       complete2.setBounds(330,240,100,25);
       complete2.setEnabled(false);
       tables.setBounds(450,170,60,25);
       ch.setBounds(550,420,100,25);
       your_order.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
       your_order.setEditable(false);
       complete.setEnabled(false);
       /////
       lbl5.setBounds(10,5,130,25);
       close_tables.setBounds(145,5,70,25);
       ok.setBounds(220,5,70,25);
       del_ord.setBounds(320, 200, 110, 25);
       save.setBounds(320, 160, 110,25);
       ch_item2.setBounds(10,50,135,65);
       ch_item2.setFont(new Font("Verdana", Font.PLAIN, 12));
       ch_item2.setOpaque(false);
       /////
       int y = 10;
       for(int i = 0;i<9;i++){
           adress_fld[i].setBounds(110,y,140,25);
           y+=36;
       }
       adr_lbl.setBounds(10,13,150, 400);
       adr_lbl.setOpaque(false);
       adr_lbl.setFont(new Font("Verdana", Font.PLAIN, 14));
       adr_lbl.setEditable(false);
       adr_ok.setBounds(189,331,60,30);
       /////
       adresses.setBounds(135,5,200,25);
   }
   private JMenu CreateOrder(){
       JMenu ord = new JMenu("Оформить заказ");
       JMenu man = new JMenu("Менеджер заказов");
       JMenuItem IntOrd = new JMenuItem("Интернет-заказ");
       JMenuItem TableOrd = new JMenuItem("Заказать за столик");
       JMenuItem IntOrd1 = new JMenuItem("С доставкой");
       JMenuItem TableOrd1 = new JMenuItem("В ресторане");
       ord.add(IntOrd);
       ord.addSeparator();
       ord.add(TableOrd);
       man.add(IntOrd1);
       man.addSeparator();
       man.add(TableOrd1);
       TableOrd.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               complete.setEnabled(false);
               lbl4.setText("Стоимость заказа: 0 рублей");
               select = "Ресторан-заказ";
               pnl.setVisible(true);
               pnl.removeAll();
               pnl_add();
               tables.removeAllItems();
               for(Integer i: tom.freetableNumbers())
                   tables.addItem(i);
               your_order.setText("Ваш заказ:");
               pnl.add(lbl);
               pnl.add(lbl2);
               pnl.add(lbl3);
               pnl.add(tables);
               pnl.add(lbl4);
               pnl.add(complete);
               pnl.add(ch);
               pnl.revalidate();
               pnl.repaint();
           }
       });
       IntOrd.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               complete2.setEnabled(false);
               lbl4.setText("Стоимость заказа: 0 рублей");
               select = "Интернет-заказ";
               pnl.setVisible(true);
               list.add(new InternetOrder());
               System.out.println(list.size());
               pnl.removeAll();
               pnl_add();
               your_order.setText("Ваш заказ:");
               pnl.add(lbl);
               pnl.add(lbl2);
               pnl.add(lbl4);
               pnl.add(complete2);
               pnl.revalidate();
               pnl.repaint();
           }
       });
       return ord;
   }
   private JMenu manager(){
       JMenu man = new JMenu("Менеджер заказов");
       JMenuItem IntOrd1 = new JMenuItem("С доставкой");
       JMenuItem TableOrd1 = new JMenuItem("В ресторане");
       man.add(IntOrd1);
       man.addSeparator();
       man.add(TableOrd1);
       TableOrd1.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               select = "Ресторан-менеджер";
               close_tables.removeAllItems();
               for(Integer i: tom.closetableNumbers())
                   close_tables.addItem(i);
               ok.setEnabled(close_tables.getSelectedItem() != null);
               pnl.removeAll();
               pnl.add(lbl5);
               pnl.add(ok);
               ok.setBounds(close_tables.getBounds().x+100,5,70,25);;
               pnl.add(close_tables);
               pnl.revalidate();
               pnl.repaint();
           }
       });
       IntOrd1.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               select = "Интернет-менеджер";
               adresses.removeAllItems();
               for(Order ord: iom.getOrders()){
                   if(ord != null)
                   adresses.addItem(ord.getAdr());
               }
               ok.setEnabled(adresses.getSelectedItem() != null);
               pnl.removeAll();
               pnl.add(adresses);
               pnl.add(lbl5);
               ok.setBounds(adresses.getBounds().x+220,5,70,25);;
               pnl.add(ok);
               pnl.revalidate();
               pnl.repaint();
           }
       });
       return man;
   }
   private HashMap<String, Double> dish_menu(){
       HashMap<String, Double> menu = new HashMap<>();
       menu.put("Борщ", 300.0);
       menu.put("Котлета", 150.0);
       menu.put("Мороженое", 70.0);
       menu.put("Блины", 100.0);
       menu.put("Пицца", 650.0);
       menu.put("Бургер", 400.0);
       menu.put("Картофель_фри", 80.0);
       return menu;
   }
   private JDialog createDialog()
   {
        JDialog dialog = new JDialog(this, "Введите данные", true);
        JPanel pnl2 = new JPanel();
        pnl2.setLayout(null);
        dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        dialog.setSize( 300, 411);
        for(int i = 0;i<9;i++) {
            pnl2.add(adress_fld[i]);
        }
        pnl2.add(adr_lbl);
        pnl2.add(adr_ok);
        dialog.add(pnl2);
        return dialog;
   }
}