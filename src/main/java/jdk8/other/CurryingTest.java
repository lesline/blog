package jdk8.other;


import org.junit.Test;

import java.util.function.DoubleUnaryOperator;

public class CurryingTest {

    static double converter(double x, double conversionFactor, double baseline) {
        return x * conversionFactor + baseline;
    }

    static DoubleUnaryOperator curriedConverter(double y, double z) {
        return (double x) -> x * y + z;
    }

    @Test
    public void celsiusToFahrenheit() {
        double waterBoilingPointInF = converter(100, 9.0 / 5, 32);
        System.out.println(waterBoilingPointInF);

        DoubleUnaryOperator convertCtoF = curriedConverter(9.0 / 5, 32);//摄氏转为华氏温度
        DoubleUnaryOperator convertUSDtoGBP = curriedConverter(0.6, 0);//美元到人民币
        DoubleUnaryOperator convertKmtoMi = curriedConverter(0.6214, 0);//公里到英里

        System.out.println(convertCtoF.applyAsDouble(100.0));
        System.out.println(convertUSDtoGBP.applyAsDouble(100.0));
        System.out.println(convertUSDtoGBP.applyAsDouble(200.0));
    }


}