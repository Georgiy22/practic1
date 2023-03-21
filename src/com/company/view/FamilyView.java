package com.company.view;

import com.company.model.FamilyTree;
import com.company.model.FamilyTree.Pair;

import java.util.Iterator;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FamilyView extends JFrame{

    JButton button = new JButton();
    private FamilyTree tree;

    public FamilyView(FamilyTree tree, Container c){
        this.tree = tree;
        doButton(c);
    }

     static class DrawPanel extends JPanel {

        private FamilyTree tree;

        private void doDrawing(Graphics g) {

            Iterator<FamilyTree.Pair> it = tree.PairMap.values().iterator();
            FamilyTree.Pair f;

            var g2d = (Graphics2D) g;
            int w = 150, h = 60, z;
            z = w + 30;

            while (it.hasNext()) { //рисуем прямоугольники
                f = it.next();
                int x = f.getX(), y = f.getY();

                if (!(f.getHName().equals("0")) && !(f.getWName().equals("0"))) {
                    g2d.drawRect(x, y, w, h);
                    g2d.drawRect(x + z, y, w, h);
                    g2d.drawLine(x + w, y + h / 2, x + z, y + h / 2);
                    if(!(f.getKids()[0].equals("0"))){ //линии к детям
                        g2d.drawLine(x + (z + w) / 2, y + h / 2, x + (z + w) / 2, y + h);
                    }
                } else {
                    g2d.drawRect(x + z / 2, y, w, h);
                }
                if(!(f.getKids()[0].equals("0"))){
                    String[] k = f.getKids();
                    int i = 0;
                    while(i<k.length){
                        Pair p = tree.PairMap.get(k[i]);
                        if (!(p.getHName().equals("0")) && !(p.getWName().equals("0"))) { //куда рисовать линию
                            int EvenOdd = Integer.parseInt (k[i]);
                            if(EvenOdd % 2 == 1){
                                g2d.drawLine(x + (z + w) / 2, y + h, p.getX() + z + (w/2), p.getY());
                            } else {
                                g2d.drawLine(x + (z + w) / 2, y + h, p.getX() + (w/2), p.getY());
                            }
                        } else {
                            g2d.drawLine(x + (z + w) / 2, y + h, p.getX() + ((z + w)/2), p.getY() );
                        }
                        i++;
                    }
                }
            }
        }

        @Override
        public void paintComponent(Graphics g) {

            super.paintComponent(g);
            doDrawing(g);
        }

    }

    public void doButton(Container c) {

        class ButtonEventListener implements ActionListener {
            public String Inf;

            public ButtonEventListener(String Inf) {
                this.Inf = Inf;
            }

            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, Inf, "Information:", JOptionPane.PLAIN_MESSAGE);
            }
        }

        Iterator<Pair> et = tree.PairMap.values().iterator();
        Pair f;

        while (et.hasNext()) { //рисуем кнопки

            f = et.next();
            int x = f.getX(), y = f.getY();
            int w = 150, h = 60, z;
            z = w + 30;
            String name, inf;

            if (!(f.getHName().equals("0")) && !(f.getWName().equals("0"))) {
                name = f.getHName();
                inf = f.getHInf();
                button = new JButton(name);
                button.addActionListener(new ButtonEventListener(inf));
                button.setBounds(x + (z) + 10, y + 10, w-20, h-20);
                c.add(button);
                name = f.getWName();
                inf = f.getWInf();
                button = new JButton(name);
                button.addActionListener(new ButtonEventListener(inf));
                button.setBounds(x + 10, y + 10, w-20, h-20);
                c.add(button);
            } else {
                if (!(f.getHName().equals("0"))) {
                    name = f.getHName();
                    inf = f.getHInf();
                } else {
                    name = f.getWName();
                    inf = f.getWInf();
                }
                button = new JButton(name);
                button.addActionListener(new ButtonEventListener(inf));
                button.setBounds(x + (z / 2) + 10, y + 10, w-20, h-20);
                c.add(button);
            }
        }
    }
}
