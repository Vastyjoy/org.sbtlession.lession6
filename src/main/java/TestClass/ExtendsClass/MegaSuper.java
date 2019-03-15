package TestClass.ExtendsClass;

public class MegaSuper {


    protected String name;

    public MegaSuper(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "MegaSuper{" +
                "name='" + name + '\'' +
                '}';
    }
}
