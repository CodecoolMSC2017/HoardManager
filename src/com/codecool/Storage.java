package com.codecool;
import java.util.ArrayList;

public class Storage {
    public String name;
    public int size;
    public int fame;
    private ArrayList<Hoard> contents;

    public Storage(String name, int size, int fame, Hoard[] contents) {
        this.name = name;
        this.size = size;
        this.fame = fame;
        this.contents = contents;
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

    public int calculateFame(Hoard item) {
        if (item instanceof Coins){
            return item.getValue();
        }else if (item instanceof Gems){
            return 2*item.getValue();
        }else if (item instanceof CommonMagicItem) {
            return 3*item.getValue();
        }else if (item instanceof UniqueItem){
            return 5*item.getValue();
        }
    }

}