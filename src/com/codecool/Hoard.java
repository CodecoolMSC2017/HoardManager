package com.codecool;

import java.security.acl.Owner;

public class Hoard {
    private String name;
    private long value;
    private int size;

    public Hoard(String name, Long value, int size) {
        this.name = name;
        this.value = value;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public long getValue() {
        return value;
    }

    public int getSize() {
        return size;
    }
}


class Gems extends Hoard {
    private String type;

    public Gems(String name, long value, int size, String type) {
        super(name, value, size);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String toString() {
        return "Gem\t[Name: " + getName() + "\tvalue: " + Long.toString(getValue()) + "\tsize: " + Integer.toString(getSize()) + "\t type " + type;
    }
}


class Coins extends Hoard {
    private String material;

    public Coins(String name, long value, int size, String material) {
        super(name, value, size);
        this.material = material;
    }

    public String getMaterial() {
        return material;
    }

    public String toString() {
        return "Coins\t[Name: " + getName() + "\tvalue: " + Long.toString(getValue()) + "\tsize: " + Integer.toString(getSize()) + "\t made: " + material;
    }
}


class CommonMagicItem extends Hoard {
    private String description;

    public CommonMagicItem(String name, long value, int size, String description) {
        super(name, value, size);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String toString() {
        return "Common Magic Item\t[Name: " + getName() + "\tvalue: " + Long.toString(getValue()) + "\tsize: " + Integer.toString(getSize()) + "\t description " + description;
    }
}

class UniqueItem extends Hoard {
    private String description;
    private String creator = "unknown";

    public UniqueItem(String name, long value, int size, String description, String creator) {
        super(name, value, size);
        this.description = description;
        this.creator = creator;
    }

    public String getDescription() {
        return description;
    }

    public String getCreator() {
        return creator;
    }

    public String toString() {
        return "Unique Item\t[Name: " + getName() + "\tvalue: " + Long.toString(getValue()) + "\tsize: " + Integer.toString(getSize()) + "\t description " + description + "\t creator: " + creator;
    }
}