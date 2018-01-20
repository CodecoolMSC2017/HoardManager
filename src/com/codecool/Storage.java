public class Storage {
    private String name;
    private Integer size;
    private Integer fame;
    private Hoard[] contents;

    public Storage(String name, Integer size, Integer fame, Hoard[] contents) {
        this.name = name;
        this.size = size;
        this.fame = fame;
        this.contents = contents;
    }
}