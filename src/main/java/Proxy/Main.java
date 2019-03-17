package Proxy;



import Proxy.cacheproxy.CacheProxy;
import Proxy.services.Calculator;
import Proxy.services.CalculatorImpl;
import Proxy.services.ListGenerator;
import Proxy.services.ListGeneratorImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Calculator proxyCalculator;
        proxyCalculator = CacheProxy.cacheFactory(new CalculatorImpl(),
                "C:\\Users\\Alex\\IdeaProjects\\lession6");

        System.out.println(proxyCalculator.calc("3!", 3));
        System.out.println(proxyCalculator.calc("2!", 2));
        System.out.println(proxyCalculator.calc("5!", 5));
        System.out.println(proxyCalculator.calc("1!", 1));

        ListGenerator listGenerator = CacheProxy.cacheFactory(new ListGeneratorImpl(),
                "C:\\Users\\Alex\\IdeaProjects\\lession6");
        List<Integer> list = listGenerator.generate(1_000_000);

   //     System.out.println(list);
    }
}
