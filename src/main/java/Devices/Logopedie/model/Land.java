package Devices.Logopedie.model;

public class Land {
    private int id;
    private String category;
    private String name;
    private String description;

    public Land(int id, String category, String name, String description) {
        this.setId(id);
        this.setCategory(category);
        this.setName(name);
        this.setDescription(description);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}