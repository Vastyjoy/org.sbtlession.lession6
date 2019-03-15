import TestClass.*;
import TestClass.ExtendsClass.ChildLeft;
import TestClass.ExtendsClass.MegaSuper;
import TestClass.ExtendsClass.NotChild;

import java.lang.reflect.InvocationTargetException;

public class Test {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        /**
         * Блок кода проверки на установку полей, где они в перемешку являются примитивами и нет
         * Но не являются наследниками
         */

        {
            AGetSet aGetSet=new AGetSet(30,1989,true,false);
            BGet bGet =new BGet(10,2009,false,true);
            CGetSet cGetSet=new CGetSet(20,1999);
            System.out.println("Изначальный");
            System.out.println(aGetSet.toString());
            System.out.println();
            System.out.println("Изначальный");
            System.out.println(bGet.toString());
            System.out.println();

            System.out.println("Изначальный");
            System.out.println(cGetSet.toString());
            System.out.println();

            //Попробуем установить все поля AGetSet В bGet
            //bGet не должен измениться, т.к. у него отсутствуют сеттеры

            System.out.println("_________________________________________________________________________");
            BeanUtils.assign(bGet,aGetSet);
            System.out.println("bGet после операции assign(bGet,aGetSet), bGet должен остаться неизменным");
            System.out.println(bGet.toString());

            //Пробуем установить все поля bGet в aGetSet
            //aGetSet должен полностью соответствовать bGet т.к. bGet содержит все поля и геттеры на них aGetSet
            System.out.println("_________________________________________________________________________");
            BeanUtils.assign(aGetSet,bGet);
            System.out.println("aGet после операции assign(aGetSet,bGet), aGetSet должен стать полностью идентичным bGet");
            System.out.println(aGetSet.toString());

            //Попробуем установить aGetSet в CGetSet
            //CGetSet содержит лишь половину полей AGetSet, т.е. только половина полей должна быть переприсвоена
            System.out.println("_________________________________________________________________________");
            BeanUtils.assign(aGetSet,cGetSet);
            System.out.println("cGet после операции assign(aGetSet,cGet), aGetSet должен стать наполовину идентичным cGet");
            System.out.println(aGetSet.toString());



        }

        /**
         * Блок кода для проверки установки полей с использованием полиморфизма
         */
        {

            D d =new D(new MegaSuper("MegaSuper"));
            E e =new E(new ChildLeft("ChildLeft"));
            F f =new F(new NotChild("NotChild"));

            System.out.println();
            System.out.println(d.toString());
            System.out.println(e.toString());
            System.out.println(f.toString());

            //Проверка установки классу e(который содержит потомка) полей класса d(который является супером для него)
            //Изменнений произойти не должно
            System.out.println("_________________________________________________________________________");
            BeanUtils.assign(e,d);
            System.out.println("e после операции assign(e,d), e должен Вывести name=ChildLeft");
            System.out.println(e.toString());


            //Проверка установки классу d полей класса f поля которого никак не относятся к d
            //Изменнений произойти не должно
            System.out.println("_________________________________________________________________________");
            BeanUtils.assign(d,f);
            System.out.println("d после операции assign(d,f), d должен Вывести name=MegaSuper т.к. поле является потомком MegaSuper");
            System.out.println(d.toString());

            //Проверка установки классу d(который содержит родителя) полей класса e(который является потмком)
            //Изменнений произойти не должно
            System.out.println("_________________________________________________________________________");
            BeanUtils.assign(d,e);
            System.out.println("d после операции assign(d,e), d должен Вывести name=ChildLeft т.к. поле является потомком MegaSuper");
            System.out.println(d.toString());

        }

    }
}
