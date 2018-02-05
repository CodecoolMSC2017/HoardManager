package com.codecool;

import java.util.Scanner;
import java.lang.Runtime;
import java.util.List;
import java.util.ArrayList;

public class Main{

    public static void Main(String args[]) {
        private static Scanner scanner = new Scanner(System.in);
        public List<Storage> = new ArrayList<>();

        System.out.println("Welcome to HoardManager 2000 for the modern dragon!")

        while (true) {
            Runtime.getRuntime().exec("cls");
            
            System.out.println("Please select an option:")
            System.out.println("(C)reate new storage\n(V)iew storage stats\n(A)dd item to hoard\n(L)oad\n(S)ave\n(Q)uit")

            String line = scanner.nextLine();

            if ((line.toLowerCase()).equals("q")) {
                break;
            }else if ((line.toLowerCase()).equals("c")) {
                createStorage();
            }else if ((line.toLowerCase()).equals("v")) {
                viewStorages();
            }else if ((line.toLowerCase()).equals("a")) {
                addNewItem();
            }else if ((line.toLowerCase()).equals("l")) {
                loadData();
            }else if ((line.toLowerCase()).equals("s")) {
                saveData();
            }else {
                continue;
            }
        }
    }

    
}