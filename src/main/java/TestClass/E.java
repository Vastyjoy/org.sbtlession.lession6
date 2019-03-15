package TestClass;

import TestClass.ExtendsClass.ChildLeft;

public class E  {
    ChildLeft clazz;

    public ChildLeft getClazz() {
        return clazz;
    }

    public void setClazz(ChildLeft clazz) {
        this.clazz = clazz;
    }

    public E(ChildLeft clazz) {
        this.clazz = clazz;
    }

    @Override
    public String toString() {
        return "E{" +
                "clazz=" + clazz +
                '}';
    }
}
