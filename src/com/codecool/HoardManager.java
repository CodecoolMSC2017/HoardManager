package com.codecool;

import java.util.Scanner;
import java.lang.Runtime;
import java.util.List;
import java.util.ArrayList;

public class HoardManager {
    List<Storage> hideouts = new ArrayList<>();
    NicePrinter np = new NicePrinter();
    FileWriter fw = new FileWriter();
    Scanner sc = new Scanner(System.in);

    public void createStorage() {
        System.out.println("Please provide the name for the new storage: ");
        String name = sc.nextLine();
        System.out.println("Please provide the size of this storage: ");
        int size = Integer.parseInt(sc.nextLine());
        Storage st = new Storage(name, size);
        hideouts.add(st);
    }

    public void viewStorages() {
        for(Storage storage : hideouts) {
            np.printStorageInfo(storage);
        }
    }

    public void addNewItem() {
        System.out.println("Where do you want to store new item?");
        for (int i=0;i<hideouts.size();i++) {
            System.out.println(i+1 + ") " + hideouts.get(i).getName());
        }
        int choice = Integer.parseInt(sc.nextLine());
        Storage current = hideouts.get(choice-1);
        System.out.println("Current state of this storage: ");
        np.printStorageInfo(current);
        current.addNewItem();
    }

    public void loadData() {
        System.out.println("Please provide your code name: ");
        String codeName = sc.nextLine();
        hideouts = fw.loadStorages(codeName);
    }

    public void saveData() {
        System.out.println("Please provide your code name: ");
        String codeName = sc.nextLine();
        fw.saveStorages(hideouts, codeName);
    }
}