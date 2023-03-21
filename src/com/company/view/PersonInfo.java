package com.company.view;

import com.company.model.FamilyTree;

import java.util.Iterator;

public class PersonInfo {

    public PersonInfo(FamilyTree tree){
        CreateGrid(tree);
    }

    public void CreateGrid(FamilyTree tree){
        Iterator<FamilyTree.Pair> it = tree.PairMap.values().iterator();
        FamilyTree.Pair f;
        int maxKey = 0;

        while(it.hasNext()) {
            f = it.next();
            int key = f.getGenerationKey();
            if(key>maxKey)
                maxKey = key;
        }

        int x = 10, y = 15, z = 0, i = maxKey; //x y - изначальные отступы

        while(i>=0) {
            Iterator<FamilyTree.Pair> it1 = tree.PairMap.values().iterator();
            while (it1.hasNext()) {
                f = it1.next();
                int key = f.getGenerationKey();
                if (i == key) {
                    if (f.getX()==0) { //чтобы избежать повторенного значения
                        f.setX(x + (z * 360));
                        f.setY(y + ((maxKey - i) * 150));
                        z++;
                    }
                }
            }
            z = 0;
            i--;
        }
    }
}
