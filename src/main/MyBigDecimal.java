package main;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MyBigDecimal extends BigDecimal {
    public MyBigDecimal(BigDecimal bigDec) {
        super(String.valueOf(bigDec));
    }

    public MyBigDecimal divide(int divisor, int decimalPlaces) {
        return new MyBigDecimal(this.setScale(decimalPlaces, RoundingMode.HALF_UP).divide(new BigDecimal(divisor), RoundingMode.HALF_UP));
    }

    public MyBigDecimal subtract(int subtract) {
        return new MyBigDecimal(this.subtract(new BigDecimal(subtract)));
    }

    public MyBigDecimal multiply(int multiplicand) {
        return new MyBigDecimal(this.multiply(new BigDecimal(multiplicand)));
    }


    public MyBigDecimal multiply(double multiplicand) {
        return new MyBigDecimal(this.multiply(new BigDecimal(multiplicand)));
    }
}
