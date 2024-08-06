import allone.AllOne;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // all one data strcutre

        // getValue for key
        //get Maxkey
        //getMinkey in O(1-

        //AllOne() Initializes the object of the data structure.
        //inc(String key) Increments the count of the string key by 1. If key does not exist in the data structure, insert it with count 1.
        //dec(String key) Decrements the count of the string key by 1. If the count of key is 0 after the decrement, remove it from the data structure. It is guaranteed that key exists in the data structure before the decrement.
        //getMaxKey() Returns one of the keys with the maximal count. If no element exists, return an empty string "".
        //getMinKey() Returns one of the keys


        AllOne allone = new AllOne();
        System.out.println( allone.getIncr("A"));
        System.out.println( allone.getIncr("A"));
        System.out.println(allone.getMaxFrequency());
        System.out.println(allone.getMinFrequency());

        System.out.println( allone.getIncr("B"));
        System.out.println( allone.getIncr("B"));
        System.out.println( allone.getDecr("A"));
        System.out.println(allone.getMaxFrequency());
        System.out.println(allone.getMinFrequency());



        // sample input A, B ,C
        // incr(A) , incr(A0 getMaxKey incr(B) incr(B) incr(B) getMinkey getMaxkey






    }
}