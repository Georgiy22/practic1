package com.company.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.company.io.FamilyReader;

public class FamilyTree {

    private static FamilyReader f;
    public static HashMap<String, Pair> PairMap = new HashMap<>();

    public FamilyTree(String filename){
        this.f = new FamilyReader(filename);
        PairCreator();
    }

    public static class Pair {

        private Integer x = 0;
        private Integer y = 0;
        private String WName;
        private String HName;
        private String WInf;
        private String HInf;
        private String[] Kids;
        private Integer GenerationKey;

        public String getWName() {
            return WName;
        }

        public String getHName() {
            return HName;
        }

        public String getWInf() {
            return WInf;
        }

        public String getHInf() {
            return HInf;
        }

        public void setY(Integer y) {
            this.y = y;
        }

        public void setX(Integer x) {
            this.x = x;
        }

        public Integer getY() {
            return y;
        }

        public Integer getX() {
            return x;
        }

        public String[] getKids() {
            return Kids;
        }

        public Integer getGenerationKey() {
            return GenerationKey;
        }

        public Pair(String WName, String HName, String WInf, String HInf, String[] Kids) {

            this.WName = WName;
            this.HName = HName;
            this.WInf = WInf;
            this.HInf = HInf;
            this.Kids = Kids;

            if (!(Kids[0].equals("0"))) {
                int i = 0, maxi = 0;
                while (i < Kids.length) {
                    int b = KeyFinder(Kids[i]) + 1;
                    if (b > maxi) {
                        maxi = b;
                    }
                    i++;
                }
                this.GenerationKey = maxi;
            } else {
                this.GenerationKey = 0;
            }
        }
    }

    private static Integer KeyFinder(String KidNum) {
        Person p = f.PersonMap.get(KidNum);
        if (p.IfChild()) {
            String[] a = p.getChild();
            int i = 0, maxi = 0;
            while (i != a.length) {
                int b = KeyFinder(a[i]) + 1;
                if (b > maxi) {
                    maxi = b;
                }
                i++;
            }
            return maxi;
        }
        return 0;
    }

    private void PairCreator() { //создание массива пар

        Iterator<String> at = f.PersonMap.keySet().iterator();
        String f1;

        while (at.hasNext()) {
            f1 = at.next();
            Person p = f.PersonMap.get(f1);
            int EvenOdd = Integer.parseInt(f1);
            if (p.IfSpouses()) {
                if (EvenOdd % 2 == 1) {
                    ArrayList<String> arr = new ArrayList<>();
                    String[] stringArr;
                    for (int i = 0; i < p.getSpouses().length; i++) { //проход по всем женам
                        Person W = f.PersonMap.get(p.getSpouses()[i]);
                        int counter = 0;
                        if (p.IfChild()) {
                            for (int j = 0; j < p.getChild().length; j++) { //массив общих детей
                                for (int l = 0; l < W.getChild().length; l++) {
                                    if (p.getChild()[j].equals(W.getChild()[l])) {
                                        arr.add(p.getChild()[j]);
                                        counter++;
                                    }
                                }
                            }
                            if (counter == 0)
                                arr.add("0");
                            stringArr = new String[arr.size()];
                            arr.toArray(stringArr); //преобразуем
                        } else {
                            stringArr = p.getChild();
                        }
                        Pair pair = new Pair(W.getName(), p.getName(), W.getInf(), p.getInf(), stringArr);
                        PairMap.put(f1, pair);
                        PairMap.put(p.getSpouses()[i], pair);
                    }
                }
            } else {
                Pair pair;
                if (EvenOdd % 2 == 1) {
                    pair = new Pair("0", p.getName(), "0", p.getInf(), p.getChild());
                } else {
                    pair = new Pair(p.getName(), "0", p.getInf(), "0", p.getChild());
                }
                PairMap.put(f1, pair);
            }
        }

        Iterator<String> it = f.PersonMap.keySet().iterator();
        String f2;

        while (it.hasNext()) { //поиск детей от 1 родителя у которого был/а/и или есть супруг/а/и
            f2 = it.next();
            Person p = f.PersonMap.get(f2);
            if ((p.IfChild())&&(p.IfSpouses())){
                String[] s = p.getSpouses();
                String[] c = p.getChild();
                for (int i = 0; i < s.length; i++) {
                    for (int j = 0; j < PairMap.get(s[i]).getKids().length; j++) {
                        for (int l = 0; l < c.length; l++) {
                            if (PairMap.get(s[i]).getKids()[j].equals(p.getChild()[l]))
                            c[l] = "0"; //массив с такими детьми (и нулями)
                        }
                    }
                }
                int counter = 0;
                for (int l = 0; l < c.length; l++) {
                    if (!(c[l].equals("0")))
                        counter++;
                }
                if(counter!=0){
                    ArrayList<String> arr = new ArrayList<>();
                    String[] stringArr;
                    for (int j = 0; j < c.length; j++) {
                        if (!(c[j].equals("0"))) {
                            arr.add(c[j]);
                        }
                    }
                    stringArr = new String[arr.size()];
                    arr.toArray(stringArr);
                    int EvenOdd = Integer.parseInt(f2);
                    Pair pair;
                    if (EvenOdd % 2 == 1) {
                        pair = new Pair("0", p.getName(), "0", p.getInf(), stringArr);
                    } else {
                        pair = new Pair(p.getName(), "0", p.getInf(), "0", stringArr);
                    }
                    PairMap.put(f2, pair);
                }
            }
        }
    }
}
