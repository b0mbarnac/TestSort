package com.company;

import java.lang.ref.SoftReference;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        int[] arr = genArray(500_000);
        long start=System.currentTimeMillis();


        int[]arr1 = new int[arr.length/2];
        int[] arr2 = new int[arr.length-arr.length/2];
        for (int i = 0; i <arr1.length; i++) {
            arr1[i]= arr[i];
        }
        for (int i = 0; i <arr2.length ; i++) {
            arr2[i]=arr[arr1.length+i];
        }
        Sorter s1 = new Sorter(arr1);
        Sorter s2 = new Sorter(arr2);
        Thread t1 = new Thread(s1);
        Thread t2 = new Thread(s2);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        int[] result = merge(arr1,arr2);
        System.out.println(Arrays.toString(result));
        long finish1 = System.currentTimeMillis();
        System.out.println("time: " + (finish1-start));

    }

    private static int[] merge(int[] arr1, int[] arr2) {
        int[] result = new int[arr1.length + arr2.length];
        int ind1 = 0;
        int ind2 = 0;
        while(ind1 < arr1.length && ind2 < arr2.length){
            if(arr1[ind1] < arr2[ind2]){
                result[ind1+ind2] = arr1[ind1];
                ind1++;
            }else{
                result[ind1 + ind2] = arr2[ind2];
                ind2++;
            }
        }
        if(ind1== arr1.length){
            for (int i = ind2; i < arr2.length; i++) {
                result[ind1+ind2]=arr2[i];
            }
        }else{
            for (int i = ind1; i < arr1.length; i++) {
                result[ind1+ind2]=arr2[i];
            }
        }
        return result;
    }

    public static void sort(int[] a){
        for (int i = 0; i <a.length; i++) {
            for (int j = 0; j <a.length; j++) {
                if (a[i] < a[j]){
                    int t = a[i];
                    a[i]=a[j];
                    a[j]=t;
                }
            }
        }
    }
    private static int[] genArray(int i){
        Random r = new Random();
        int[] array = new int[i];
        for (int j = 0; j <array.length ; j++) {
            array[j] = r.nextInt(1_000_000);
        }
        return array;
    }
}
