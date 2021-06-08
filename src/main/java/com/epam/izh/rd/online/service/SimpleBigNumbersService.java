package com.epam.izh.rd.online.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Objects;

public class SimpleBigNumbersService implements BigNumbersService {

    /**
     * Метод делит первое число на второе с заданной точностью
     * Например 1/3 с точностью 2 = 0.33
     *
     * @param range точность
     * @return результат
     */
    @Override
    public BigDecimal getPrecisionNumber(int a, int b, int range) {
        return new BigDecimal(a).divide(new BigDecimal(b), new MathContext(range, RoundingMode.HALF_UP));
    }

    /**
     * Метод находит простое число по номеру
     *
     * @param range номер числа, считая с числа 2
     * @return простое число
     */
    @Override
    public BigInteger getPrimaryNumber(int range) {
        int i = 0;
        int n = 2;
        BigInteger result = new BigInteger("2");
        while (i < range) {
            n++;
            int y = 0;
            for (int e = 2; e < n; e++) {
                if (n % e == 0) {
                    y++;
                }
                if (!Objects.equals(y, 0)) {
                    break;
                }

            }
            if (y == 0) {
                i++;
                result = BigInteger.valueOf(n);
            }

        }
        return result;
    }

}
