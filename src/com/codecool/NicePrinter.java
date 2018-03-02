package com.codecool;

public class NicePrinter {

    public void printStorageInfo(Storage storage) {
        String adventure = "";
        if (storage.getFame() < 1001) {
            adventure = "Adventurers are not likely to appear (less than 5%)";
        } else if (storage.getFame() > 1000 && storage.getFame() < 5000) {
            adventure = "Adventurers are somewhat likely to appear (5 - 25%)";
        } else if (storage.getFame() > 4999 && storage.getFame() < 15000) {
            adventure = "Adventurers might appear (25 - 50%)";
        } else if (storage.getFame() > 14999 && storage.getFame() < 50000) {
            adventure = "Adventurers are quite likely to appear (50 - 75%)";
        } else if (storage.getFame() > 49999 && storage.getFame() < 100000) {
            adventure = "Adventurers very likely to appear (75 - 95%)";
        } else if (storage.getFame() > 99999) {
            adventure = "Make sure to have your beasts prepared. Adventurers are coming";
        }

        System.out.println("Storage: " + storage.getName());
        System.out.println("Current fame: " + storage.getFame() + "\tAvailable space: " + storage.getFreeSpace());
        System.out.println(adventure);
        System.out.println();
        System.out.format("%30s%15s%15s%n", "Item Name", "Value", "Size");
        for (Hoard row : storage.getContents()) {
            System.out.format("%30s%15d%15d\n", row.getName(), row.getValue(), row.getSize());
        }
        System.out.println("\n\n"); 
    }

    public void printTreasures() {
        System.out.println("1) Gems");
        System.out.println("2) Coins");
        System.out.println("3) Common Magic Item");
        System.out.println("4) Unique Item");
    }
}