package TestClass;

import TestClass.ExtendsClass.MegaSuper;

public class D {
    MegaSuper clazz;

    public D(MegaSuper clazz) {
        this.clazz = clazz;
    }

    public MegaSuper getClazz() {
        return clazz;
    }

    public void setClazz(MegaSuper clazz) {
        this.clazz = clazz;
    }

    @Override
    public String toString() {
        return "D{" +
                "clazz=" + clazz +
                '}';
    }
}
