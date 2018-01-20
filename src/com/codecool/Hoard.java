public class Hoard {
    private String name;
    private Long value;
    private Integer size;

    public Hoard(String name, Long value, Integer size) {
        this.name = name;
        this.value = value;
        this.size = size;
    }
}


class Gems extends Hoard {
    private String type;

    public Gems(String name, Long value, Integer size, String type) {
        super(name, value, size);
        this.type = type;
    }
}


class Coins extends Hoard {
    private String material;

    public Coins(String name, Long value, Integer size, String material) {
        super(name, value, size);
        this.material = material;
    }
}


class MagicTrinkets extends Hoard {
    private String description;
    private String owner = "unknown";

    public Coins(String name, Long value, Integer size, String description, String owner) {
        super(name, value, size);
        this.description = description;
        this.owner = owner;
    }
}