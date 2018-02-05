package com.codecool;

public class NicePrinter {

    public void printStorageInfo(Storage storage) {

        System.out.format("%15Amount%15Item Name%15Value%15Size\n");
        for (Hoard row : storage.getContents()) {
            System.out.format("%15s%15s%15s%15s\n", storage.getContents().getName());
    }
}