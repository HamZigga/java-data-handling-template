package com.epam.izh.rd.online.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleRegExpService implements RegExpService {

    /**
     * Метод должен читать файл sensitive_data.txt (из директории resources) и маскировать в нем конфиденциальную информацию.
     * Номер счета должен содержать только первые 4 и последние 4 цифры (1234 **** **** 5678). Метод должен содержать регулярное
     * выражение для поиска счета.
     *
     * @return обработанный текст
     */
    @Override
    public String maskSensitiveData() {
        String stringFile;
        try (BufferedReader read = new BufferedReader(new FileReader("src/main/resources/sensitive_data.txt"))) {
            stringFile = read.readLine();
        } catch (IOException exception) {
            return exception.getMessage();
        }
        Pattern pattern = Pattern.compile("(\\d{4}) (\\d{4}) (\\d{4}) (\\d{4})");
        Matcher matcher = pattern.matcher(stringFile);
        String maskedString = "****";
        while (matcher.find()) {
            stringFile = stringFile.replace(matcher.group(2), maskedString).replace(matcher.group(3), maskedString);
        }
        return stringFile;
    }

    /**
     * Метод должен считыввать файл sensitive_data.txt (из директории resources) и заменять плейсхолдер ${payment_amount} и ${balance} на заданные числа. Метод должен
     * содержать регулярное выражение для поиска плейсхолдеров
     *
     * @return обработанный текст
     */
    @Override
    public String replacePlaceholders(double paymentAmount, double balance) {
        String stringFile;
        try (BufferedReader read = new BufferedReader(new FileReader("src/main/resources/sensitive_data.txt"))) {
            stringFile = read.readLine();
        } catch (IOException e) {
            return e.getMessage();
        }
        stringFile = stringFile.replaceAll("\\$\\{payment_amount}", String.valueOf((int) paymentAmount));
        stringFile = stringFile.replaceAll("\\$\\{balance}", String.valueOf((int) balance));
        return stringFile;
    }
}
