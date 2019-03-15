package TestClass.ExtendsClass;

public class NotChild {
    String name;

    public String getName() {
        return name;
    }

    public NotChild(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "NotChild{" +
                "name='" + name + '\'' +
                '}';
    }
}
