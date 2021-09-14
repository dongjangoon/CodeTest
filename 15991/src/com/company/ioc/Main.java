package com.company.ioc;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        int[] inputs = new int[t];

        for (int i = 0; i < t; i++) {
            inputs[i] = sc.nextInt();
        }

        long[] d = new long[100001];

        d[1] = 1;
        d[2] = 2;
        d[3] = 2;
        d[4] = 3;
        d[5] = 3;
        d[6] = 6;

        for (int i = 7; i < 100001; i++) {
            d[i] = (d[i-2] + d[i-4] + d[i-6]) % 1000000009;
        }

        for (int input: inputs) {
            System.out.println(d[input]);
        }

    }
}
