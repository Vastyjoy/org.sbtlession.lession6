package TestClass.ExtendsClass;

public class SuperLeft extends MegaSuper {

    public SuperLeft(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "SuperLeft{" +
                "name='" + name + '\'' +
                '}';
    }
}
