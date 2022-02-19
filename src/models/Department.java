package models;

public class Department {
    private int id;
    private String name;
    private int manager;
    private int location;

    public Department() {

    }

    public Department(int id, String name, int manager, int location) {
        this.setId(id);
        this.setName(name);
        this.setManager(manager);
        this.setLocation(location);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getManager() {
        return manager;
    }

    public void setManager(int manager) {
        this.manager = manager;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", manager=" + getManager() +
                ", location=" + getLocation() +
                '}';
    }
}
