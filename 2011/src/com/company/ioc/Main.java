package com.company.ioc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String n = bf.readLine();
        char[] digits = String.valueOf(n).toCharArray();
        long[] d = new long[5001];

        d[0] = 1;
        d[1] = 1;

        if (digits[0] == '0') {
            System.out.println(0);
            return;
        }

        for (int i = 1; i < digits.length; i++) {
            char prior = digits[i-1];
            if (digits[i] > '0' && digits[i] <= '9') {
                d[i+1] += d[i];
                d[i+1] %= 1000000;
            }
            if (!(prior == '0' || prior > '2' || (prior == '2' && digits[i] > '6'))) {
                d[i+1] += d[i-1];
                d[i+1] %= 1000000;
            }

        }

        System.out.println(d[digits.length]%1000000);


    }
}
