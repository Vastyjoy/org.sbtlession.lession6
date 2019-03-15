package TestClass;

/**
 * класс содержит половину полей класса A
 * имеются все сеттеры и геттеры для полей
 */
public class CGetSet{
    Integer age;
    int yearBorn;

    public CGetSet(Integer age, int yearBorn) {
        this.age = age;
        this.yearBorn = yearBorn;
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

    @Override
    public String toString() {
        return "CGetSet{" +
                "age=" + age +
                ", yearBorn=" + yearBorn +
                '}';
    }
}
