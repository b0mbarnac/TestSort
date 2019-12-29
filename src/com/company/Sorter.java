package com.company;

public class Sorter implements Runnable {

    private int[] arr;

    public Sorter(int[] arr) {
        this.arr = arr;
    }


    @Override
    public void run() {
        Main.sort(arr);
    }
}
