package TestClass;

/**
 * класс не является чьим либо наследником, все поля содержат геттеры, кроме поля Age, но ни одно поле не соддержит сеттеры
 * Все поля идиентичны полям класса AGetSet
 */
public class BGet {
    Integer age;
    int yearBorn;
    boolean man;
    Boolean married;

    public BGet(Integer age, int yearBorn, boolean man, Boolean married) {
        this.age = age;
        this.yearBorn = yearBorn;
        this.man = man;
        this.married = married;
    }

    public Integer getAge() {
        return age;
    }

    public int getYearBorn() {
        return yearBorn;
    }

    public boolean isMan() {
        return man;
    }

    public Boolean getMarried() {
        return married;
    }

    @Override
    public String toString() {
        return "BGet{" +
                "age=" + age +
                ", yearBorn=" + yearBorn +
                ", man=" + man +
                ", married=" + married +
                '}';
    }
}
