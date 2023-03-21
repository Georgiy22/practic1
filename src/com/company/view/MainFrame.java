package com.company.view;

import com.company.model.FamilyTree;

import com.company.io.FamilyWriter;
import com.company.io.FamilyReader;

import java.awt.*;
import java.io.IOException;
import javax.swing.*;

public class MainFrame extends JFrame {
    public MainFrame (String filename) {
        super("FlowLayout");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container c = getContentPane();

        FamilyTree tree = new FamilyTree(filename);
        PersonInfo per = new PersonInfo(tree);
        FamilyView fam = new FamilyView(tree, c);
        var drawPanel = new FamilyView.DrawPanel();
        c.add(drawPanel);
        setSize(1000, 700);
        setTitle("Family Tree");
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) throws IOException {
        MainFrame mf = new MainFrame("C:\\Users\\gosha\\IdeaProjects\\practic\\src\\com\\company\\tree.txt");
        mf.setVisible(true);
        /*FamilyReader r = new FamilyReader("C:\\Users\\gosha\\IdeaProjects\\practic\\src\\com\\company\\tree.txt");
        FamilyWriter w = new FamilyWriter(r.PersonMap, "C:\\Users\\gosha\\IdeaProjects\\practic\\src\\com\\company\\output.txt");
        w.savePersons();*/
    }
}
