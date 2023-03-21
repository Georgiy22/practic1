package com.company.test;

import com.company.io.FamilyReader;

public class TestRunner {
    public static void main(String[] args) {
        System.out.print("FamilyTree Test:\n");
        FamilyTreeTest treeTest = new FamilyTreeTest();
        treeTest.PairCreatorTest("C:\\Users\\gosha\\IdeaProjects\\practic\\src\\com\\company\\test.txt");
        treeTest.results();

        System.out.print("FamilyTree Correct Test:\n");
        FamilyReaderTest readerTest = new FamilyReaderTest();
        readerTest.CorrectFilePersonCreatorTest("C:\\Users\\gosha\\IdeaProjects\\practic\\src\\com\\company\\test.txt");

        System.out.print("FamilyTree Incorrect Test:\n");
        readerTest.IncorrectFilePersonCreatorTest("C:\\Users\\gosha\\IdeaProjects\\practic\\src\\com\\company\\incorrect.txt");
        readerTest.results();
    }
}
