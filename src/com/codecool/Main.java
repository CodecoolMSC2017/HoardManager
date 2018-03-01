package com.codecool;

import java.util.Scanner;

public class Main{

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HoardManager hm = new HoardManager();

        System.out.println("Welcome to HoardManager 2000 for the modern dragon!");

        while (true) {            
            System.out.println("Please select an option:");
            System.out.println("(C)reate new storage\n(V)iew storage stats\n(A)dd item to hoard\n(L)oad\n(S)ave\n(Q)uit");

            String line = sc.nextLine();

            if ((line.toLowerCase()).equals("q")) {
                break;
            }else if ((line.toLowerCase()).equals("c")) {
                hm.createStorage();
            }else if ((line.toLowerCase()).equals("v")) {
                hm.viewStorages();
            }else if ((line.toLowerCase()).equals("a")) {
                hm.addNewItem();
            }else if ((line.toLowerCase()).equals("l")) {
                hm.loadData();
            }else if ((line.toLowerCase()).equals("s")) {
                hm.saveData();
            }else {
                System.out.print("That's not a valid option. Hit enter and try again!");
                sc.nextLine();
                continue;
            }
        }
    }
}