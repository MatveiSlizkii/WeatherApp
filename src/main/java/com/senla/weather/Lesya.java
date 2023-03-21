package com.senla.weather;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lesya {
    public static void main(String[] args) {
        List<String> spisok = new ArrayList<>();
        spisok.add("a");
        spisok.add("b");
        spisok.add("c");
        spisok.add("d");
        for (int i = 0; i < spisok.size(); i++) {
            spisok.remove(i);
        }
        System.out.println("Конечный вариант " + spisok);

        List<Integer> spisok1 = new ArrayList<>();
        spisok1.add(1);
        spisok1.add(3);
        spisok1.add(5);
        spisok1.add(2);
        spisok1.add(4);
        boolean sostoyanie = true;
        while (sostoyanie == true) {
            sostoyanie = false;
            for (int i = 0; i < spisok1.size()-1; i++) {
                if (spisok1.get(i) > spisok1.get(i + 1)){
                    int vremennaya = spisok1.get(i+1);
                    spisok1.set(i+1, spisok1.get(i));
                    spisok1.set(i, vremennaya);
                    sostoyanie = true;
                }
            }
            System.out.println(spisok1);
        }
        Collections.sort(spisok1);

    }

}
