package com.company.io;

import com.company.io.FamilyReader;
import com.company.model.Person;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;

public class FamilyWriter {

    public static HashMap<String, Person> personMap = new HashMap<>();
    public static String filename;

    public FamilyWriter(HashMap<String, Person> personMap, String filename){
        this.filename = filename;
        this.personMap = personMap;
    }

    public static void savePersons() throws IOException {

        Writer input = new FileWriter(filename);
        Iterator<String> it = personMap.keySet().iterator();
        String f;

        while (it.hasNext()) {
            f = it.next();
            Person p = personMap.get(f);
            input.write(f + "\n");
            input.write(p.getName() + "\n");
            input.write(p.getSpouses()[0]);
            for (int i = 1; i < p.getSpouses().length; i++) {
                input.write( " " + p.getSpouses()[i]);
            }
            input.write("\n");
            input.write(p.getChild()[0]);
            for (int i = 1; i < p.getChild().length; i++) {
                input.write( " " + p.getChild()[i]);
            }
            input.write("\n");
            input.write(p.getInf());
            if ((it.hasNext()))
                input.write("\n");
        }
        input.close();
    }

}
