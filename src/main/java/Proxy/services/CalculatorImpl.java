package Proxy.services;


public class CalculatorImpl implements Calculator {
    @Override
    public int calc(String name, int arg1) {
        int res = 1;
        for (int i = 1; i <= arg1; i++) {
            res*=i;
            try {
                Thread.sleep(1000);

            }catch (Exception x){
                x.printStackTrace();
            }
        }
        return res;
    }
}
