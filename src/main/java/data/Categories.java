package data;

public enum Categories {
    ALL("All Categories"),
    DESCTOPS("Desktops"),
    LAPTOPS("Laptops & Notebooks"),
    PHONES("Phones & PDAs"),
    CAMERAS("Cameras");

    private String name;

    private Categories(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
