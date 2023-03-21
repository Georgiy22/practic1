package com.company.io;

import com.company.model.Person;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class FamilyReader {

    public static HashMap<String, Person> PersonMap = new HashMap<>();

    public FamilyReader(String filename){
        this.PersonMap = PersonCreate(filename);
    }

    private ArrayList<String> FamilyRead(String filename){
        ArrayList<String> list = new ArrayList<>();
        try (Scanner scan = new Scanner(new File(filename))) {
            while (scan.hasNextLine()) {
                list.add(scan.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    private HashMap<String, Person> PersonCreate(String filename) {

        ArrayList<String> list = FamilyRead(filename);
        ListIterator<String> it = list.listIterator();
        String f1, f2, f3, f4, f5;
        String[] words1, words2;

        while (it.hasNext()) {
            f1 = it.next();
            f2 = it.next();
            f3 = it.next();
            words1 = f3.split(" ");
            f4 = it.next();
            words2 = f4.split(" ");
            f5 = it.next();
            Person p = new Person(f2, words1, words2, f5);
            PersonMap.put(f1, p);
        }
        return PersonMap;
    }
}
