import TestClass.AGetSet;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class Work {

    public static final String MONDAY="MONDAY";

    /**
     * Выводит все методы класса, а также все методы родителя
     */
    public static void getAllMethod(Object object) {

        Class objectClass = object.getClass();
        Method[] methods = objectClass.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println("Название метода :" + method.getName());

        }

    }

    /**
     * Метод возвращает список геттеров Object from
     *
     * @param from
     * @return List<Method> список геттеров
     */
    private static void takeGetMethods(Object from) {
        Method[] methodsFrom = from.getClass().getMethods();
        for (Method method : methodsFrom) {
            if (method.getName().startsWith("get") || method.getName().startsWith("is"))
                System.out.println("Название метода :" + method.getName());

        }

    }
    /**
     * @param clazz
     * @return
     */
    private static boolean checkConst(Class clazz) throws IllegalAccessException {
        Field[] fields=clazz.getFields();
        for(Field field:fields){
            int modif=field.getModifiers();
            if(Modifier.isPublic(modif)&& Modifier.isStatic(modif)&& Modifier.isFinal(modif)&&field.getType().equals(String.class)){
                Object str=new String();
                str=field.get(str);

                if(!field.getName().equals(str))
                    return false;

            }
        }
        return true;
    }


    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        System.out.println( checkConst(TestWork.class));

    }

}
