package com.codecool;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private String name;
    private int size;
    private long fame = 0;
    private List<Hoard> contents = new ArrayList<>();

    public Storage(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public int getFreeSpace() {
        int spaceUsed = 0;

        for (Hoard item : contents) {
            spaceUsed = spaceUsed + item.getSize();
        }

        return size - spaceUsed;
    }

    public void addToStorage(Hoard item) {
        if (item.getSize() > getFreeSpace()) {
            System.out.println("I'm sorry Her Majesty, there is not enough room in the " + name + " for the " + item.getName() + ".");
            return;
        }else{
            contents.add(item);
            fame = fame + calculateFame(item);
            System.out.println("The item " + item.getName() + " will be stored in the " + name);
            return;
        }
    }

    public long calculateFame(Hoard item) {
        if (item instanceof Coins){
            return item.getValue();
        }else if (item instanceof Gems){
            return 2*item.getValue();
        }else if (item instanceof CommonMagicItem) {
            return 3*item.getValue();
        }else if (item instanceof UniqueItem){
            return 5*item.getValue();
        }
        return 0;
    }

    public void addNewItem() {
        NicePrinter np = new NicePrinter();
        Scanner sc = new Scanner(System.in);
        System.out.println("What kind of treasure you would like to add?");
        np.printTreasures();
        int choice = sc.nextInt();
        sc.nextLine();
        if (choice > 4) {
            System.out.println("Invalid option");
            return;
        }
        System.out.println("What is the name of this treasure?");
        String name = sc.nextLine();
        System.out.println("How much is it worth (Please provide in Ongai Gold)?");
        int value = sc.nextInt();
        sc.nextLine();
        System.out.println("How large is it?");
        int size = sc.nextInt();
        sc.nextLine();
        if (choice==1) {
            System.out.println("What type of gem is it?");
            String type = sc.nextLine();
            contents.add(new Gems(name, value, size, type));
        }else if (choice == 2) {
            System.out.println("What kind of gold is it?");
            String type = sc.nextLine();
            contents.add(new Coins(name, value, size, type));
        }else if (choice == 3 || choice == 4) {
            System.out.println("What abilities does this item have?");
            String description = sc.nextLine();
            if (choice == 3) {
                contents.add(new CommonMagicItem(name, value, size, description));
            }else if (choice == 4) {
                System.out.println("Who was the creator of this item?");
                String creator = sc.nextLine();
                contents.add(new UniqueItem(name, value, size, description, creator));
            }
        }

    }

    public void addHoard(Hoard hoard) {
        contents.add(hoard);
    }

    public String getName() {
        return name;
    }

    public long getFame() {
        return fame;
    }

    public int getSize() {
        return size;
    }

    public List<Hoard> getContents() {
        return contents;
    }

}