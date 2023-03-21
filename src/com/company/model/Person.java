package com.company.model;

public class Person {

    String Name;
    String[] Spouses;
    String[] Child;
    String Information;

    public Person(){}

    public Person(String Name, String[] Spouses, String[] Child, String Information) {
        this.Name = Name;
        this.Spouses = Spouses;
        this.Child = Child;
        this.Information = Information;
    }
    public String[] getChild(){return Child;}
    public boolean IfChild(){
        return !(this.getChild()[0].equals("0"));
    }
    public String[] getSpouses(){return Spouses;}
    public boolean IfSpouses(){
        return !(this.getSpouses()[0].equals("0"));
    }
    public String getInf(){return Information;}
    public String getName(){return Name;}
}
