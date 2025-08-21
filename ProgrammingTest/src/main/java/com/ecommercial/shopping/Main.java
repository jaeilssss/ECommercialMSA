package com.ecommercial.shopping;

import java.util.ArrayList;
public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();

        list.add(10);
        list.add(9);
        list.add(1);
        list.add(4);

        list.sort((a,b) -> Integer.compare(b,a));

        list.forEach(it -> {
            System.out.println(it);
        });
        System.out.println("Hello world!");
    }
}
