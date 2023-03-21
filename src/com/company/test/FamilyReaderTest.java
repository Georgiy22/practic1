package com.company.test;

import com.company.io.FamilyReader;
import com.company.model.FamilyTree;
import com.company.model.Person;

import java.security.spec.ECField;
import java.util.Arrays;
import java.util.HashMap;

public class FamilyReaderTest {

    private static int failedTests;
    private static int countTests;

    public FamilyReaderTest() {
        failedTests = 0;
        countTests = 0;
    }

    public void results() {
        System.out.print("всего тестов: ");
        System.out.println(countTests);
        System.out.print("провалилось:" );
        System.out.println(failedTests);
    }

    private static void check(boolean b) {
        countTests++;
        if (!b) {
            failedTests++;
            System.out.println("Failed");
        } else {
            System.out.println("Success...");
        }
    }

    public void CorrectFilePersonCreatorTest(String filename){


        try {
            FamilyReader f = new FamilyReader(filename);
        }catch (Exception e) {
            failedTests++;
        }
        countTests++;

        HashMap<String, Person> TestPersonMap = new HashMap<>();

        String arr1 = "1";
        String[] s1 = arr1.split(" ");
        String arr2 = "2";
        String[] s2 = arr2.split(" ");
        String arr3 = "3";
        String[] s3 = arr3.split(" ");
        String arr0 = "0";
        String[] s0 = arr0.split(" ");

        Person p1 = new Person("Генадий Быков", s2, s3, "Родился в 1978 году в деревне, переехал в город");
        TestPersonMap.put("1", p1);
        Person p2 = new Person("Анна Клинова", s1, s3, "Родилась в 1981 году в городе");
        TestPersonMap.put("2", p2);
        Person p3 = new Person("Антон Быков", s0, s0, "Родился в 2011 году");
        TestPersonMap.put("3", p3);

        check(FamilyReader.PersonMap.get("1").getName().equals(TestPersonMap.get("1").getName()));
        check(Arrays.equals(FamilyReader.PersonMap.get("1").getChild(), TestPersonMap.get("1").getChild()));
        check(Arrays.equals(FamilyReader.PersonMap.get("1").getSpouses(), TestPersonMap.get("1").getSpouses()));
        check(FamilyReader.PersonMap.get("1").getInf().equals(TestPersonMap.get("1").getInf()));

        check(FamilyReader.PersonMap.get("2").getName().equals(TestPersonMap.get("2").getName()));
        check(Arrays.equals(FamilyReader.PersonMap.get("2").getChild(), TestPersonMap.get("2").getChild()));
        check(Arrays.equals(FamilyReader.PersonMap.get("2").getSpouses(), TestPersonMap.get("2").getSpouses()));
        check(FamilyReader.PersonMap.get("2").getInf().equals(TestPersonMap.get("2").getInf()));

        check(FamilyReader.PersonMap.get("3").getName().equals(TestPersonMap.get("3").getName()));
        check(Arrays.equals(FamilyReader.PersonMap.get("3").getChild(), TestPersonMap.get("3").getChild()));
        check(Arrays.equals(FamilyReader.PersonMap.get("3").getSpouses(), TestPersonMap.get("3").getSpouses()));
        check(FamilyReader.PersonMap.get("3").getInf().equals(TestPersonMap.get("3").getInf()));
    }

    public void IncorrectFilePersonCreatorTest(String filename) {
        check(IncTest(filename));
    }

    private boolean IncTest(String filename) {
        try {
            FamilyReader f = new FamilyReader(filename);
        } catch (Exception e) {
            return true;
        }
        return false;
    }
}