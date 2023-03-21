package com.company.test;

import com.company.model.FamilyTree;
import com.company.model.FamilyTree.Pair;
import com.company.io.FamilyReader;

import java.util.Arrays;
import java.util.HashMap;

public class FamilyTreeTest {

    private static int failedTests;
    private static int countTests;

    public FamilyTreeTest() {
        failedTests = 0;
        countTests = 0;
    }

    public void results(){
        System.out.print("всего тестов: ");
        System.out.println(countTests);
        System.out.print("провалилось: ");
        System.out.println(failedTests);
    }

    private void check(boolean b){
        countTests++;
        if(!b){
            failedTests++;
            System.out.println("Failed");
        } else {
            System.out.println("Success...");
        }
    }

    public void PairCreatorTest(String filename){

        FamilyTree treeTest1 = new FamilyTree(filename);

        HashMap<String, FamilyTree.Pair> TestPairMap = new HashMap<>();

        String arr1 = "3";
        String[] s1 = arr1.split(" ");
        Pair p1 = new Pair("Анна Клинова","Генадий Быков","Родилась в 1981 году в городе","Родился в 1978 году в деревне, переехал в город",s1);
        TestPairMap.put("1", p1);
        TestPairMap.put("2", p1);

        String arr2 = "0";
        String[] s2 = arr2.split(" ");
        Pair p2 = new Pair("0", "Антон Быков", "0", "Родился в 2011 году", s2);
        TestPairMap.put("3", p2);

        check(Arrays.equals(treeTest1.PairMap.get("1").getKids(), TestPairMap.get("1").getKids()));
        check(treeTest1.PairMap.get("1").getHName().equals(TestPairMap.get("1").getHName()));
        check(treeTest1.PairMap.get("1").getWName().equals(TestPairMap.get("1").getWName()));
        check(treeTest1.PairMap.get("1").getHInf().equals(TestPairMap.get("1").getHInf()));
        check(treeTest1.PairMap.get("1").getWInf().equals(TestPairMap.get("1").getWInf()));

        check(Arrays.equals(treeTest1.PairMap.get("2").getKids(), TestPairMap.get("2").getKids()));
        check(treeTest1.PairMap.get("2").getHName().equals(TestPairMap.get("2").getHName()));
        check(treeTest1.PairMap.get("2").getWName().equals(TestPairMap.get("2").getWName()));
        check(treeTest1.PairMap.get("2").getHInf().equals(TestPairMap.get("2").getHInf()));
        check(treeTest1.PairMap.get("2").getWInf().equals(TestPairMap.get("2").getWInf()));

        check(Arrays.equals(treeTest1.PairMap.get("3").getKids(), TestPairMap.get("3").getKids()));
        check(treeTest1.PairMap.get("3").getHName().equals(TestPairMap.get("3").getHName()));
        check(treeTest1.PairMap.get("3").getWName().equals(TestPairMap.get("3").getWName()));
        check(treeTest1.PairMap.get("3").getHInf().equals(TestPairMap.get("3").getHInf()));
        check(treeTest1.PairMap.get("3").getWInf().equals(TestPairMap.get("3").getWInf()));
    }

}
