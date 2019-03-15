package TestClass;

/**
 * Класс A, не является чьим либо наследником
 * все поля класса имеют геттеры и сеттеры
 * класс для теста основной функции
 */
public class AGetSet {
    Integer age;
    int yearBorn;
    boolean man;
    Boolean married;

    public AGetSet(Integer age, int yearBorn, boolean man, Boolean married) {
        this.age = age;
        this.yearBorn = yearBorn;
        this.man = man;
        this.married = married;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public int getYearBorn() {
        return yearBorn;
    }

    public void setYearBorn(int yearBorn) {
        this.yearBorn = yearBorn;
    }

    public boolean isMan() {
        return man;
    }

    public void setMan(boolean man) {
        this.man = man;
    }

    public Boolean getMarried() {
        return married;
    }

    public void setMarried(Boolean married) {
        this.married = married;
    }

    public void setDefault(){}
    @Override
    public String toString() {
        return "AGetSet{" +
                "age=" + age +
                ", yearBorn=" + yearBorn +
                ", man=" + man +
                ", married=" + married +
                '}';
    }
}
