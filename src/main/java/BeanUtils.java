import TestClass.AGetSet;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class BeanUtils {

    /**
     * Метод возвращает список геттеров Object from
     * @param from
     * @return List<Method> список геттеров
     */
    private static List<Method> takeGetMethods(Object from) {
        Method[] methodsFrom = from.getClass().getMethods();
        List<Method> methods = new ArrayList<Method>();
        for (Method method : methodsFrom) {
            if (method.getName().startsWith("get") || method.getName().startsWith("is"))
                methods.add(method);
        }
        return methods;
    }

    /**
     * Метод возвращает список сеттеров Object from
     * @param from
     * @return List<Method> список сеттеров
     */
    private static List<Method> takeSetMethods(Object from) {
        Method[] methodsFrom = from.getClass().getMethods();
        List<Method> methods = new ArrayList<Method>();
        for (Method method : methodsFrom) {
            if (method.getName().startsWith("set"))
                methods.add(method);
        }
        return methods;
    }

    /**
     * Scans object "from" for all getters. If object "to"
     * contains correspondent setter, it will invoke it
     * to set property value for "to" which equals to the property
     * of "from".
     * <p/>
     * The type in setter should be compatible to the value returned
     * by getter (if not, no invocation performed).
     * Compatible means that parameter type in setter should
     * be the same or be superclass of the return type of the getter.
     * <p/>
     * The method takes care only about public methods.
     *
     * @param to   Object which properties will be set.
     * @param from Object which properties will be used to get values.
     */

    public static void assign(Object to, Object from) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {

        // Получаем все геттеры из From: get* или is*
        List<Method> toSetMethods = takeSetMethods(to);

        //
        List<Method> fromGetMethods = takeGetMethods(from);

        for (Method setMethod : toSetMethods) {
            // Получаем имя филда устанавливаемого сеттером
            String setFieldName = setMethod.getName().substring(3);
            // Сеттеры содержат по соглашению лишь 1 парамметр, поэтому строго забиваем это значение
            Class[] setMethodParams=setMethod.getParameterTypes();
            Class setMethodParam;
            //Костыль, для того чтобы не вылетела ошибка, если появится сеттер типа setDefault(){} т.е. без парраметров.
            if(setMethodParams.length==0)continue;
            else setMethodParam=setMethodParams[0];
            for (Method getMethod : fromGetMethods) {
                Class getReturnClass =getMethod.getReturnType();
                String nameGetMethod=getMethod.getName();
                //Получаем имя филда, которое возвращает значение, если тип бул то обрезаем is иначе обрезаем get
                String getFieldName = (getReturnClass.equals(boolean.class)) ? nameGetMethod.substring(2): nameGetMethod.substring(3);
                if (setFieldName.equals(getFieldName))
                    if (getReturnClass.isPrimitive() && getReturnClass.equals(setMethodParam))
                        setMethod.invoke(to, getMethod.invoke(from));
                    else if (checkSuper(getReturnClass, setMethodParam))
                        setMethod.invoke(to, getMethod.invoke(from));

            }


        }
    }


    /**
     * Рекурсивный метод. Проверяет является ли класс from наследником класса to
     *
     * @param from
     * @param to
     * @return
     */
    static boolean checkSuper(Class from, Class to) {
        if (from == null) return false;
        if (to.equals(from)) return true;
        return checkSuper(from.getSuperclass(), to);
    }


    public static void main(String[] args) throws NoSuchMethodException {
        Method[] methods = AGetSet.class.getMethods();
        for (Method method : methods) {
            System.out.println(method.getName());
            System.out.println(method.getParameterTypes().length);
        }
    }
}
