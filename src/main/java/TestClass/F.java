package TestClass;

import TestClass.ExtendsClass.NotChild;

public class F {
    NotChild clazz;

    public F(NotChild clazz) {
        this.clazz = clazz;
    }

    public NotChild getClazz() {
        return clazz;
    }

    public void setClazz(NotChild clazz) {
        this.clazz = clazz;
    }

    @Override
    public String toString() {
        return "F{" +
                "clazz=" + clazz +
                '}';
    }
}
