package TestClass.ExtendsClass;

public class ChildLeft extends SuperLeft {
    public ChildLeft(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "ChildLeft{" +
                "name='" + name + '\'' +
                '}';
    }
}
