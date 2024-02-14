package com.ankur.srpingboot.curddemo;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class Test {
    String input = "abcabbbabcadcbb";
    public static void main(String ar[]){

        Test t = new Test();
        t.findLargest(t.input);
    }

   // String Output :: abc
    LinkedHashMap<Character,Integer> countMap = new LinkedHashMap<>();
    void findLargest(String str){
        char arr [] = str.toCharArray();
        String output = "";

        for(int i=0;i<arr.length;i++){
            System.out.println(arr[i]);
            if(countMap.containsKey(arr[i])){
                System.out.println(arr[i]);
                final StringBuilder mapString = new StringBuilder("");
                countMap.forEach((k,v) ->  {
                    mapString.append(k);
                });

                if(output.length() < mapString.length()){
                    output = mapString.toString();
                }
                countMap.clear();
                countMap.put(arr[i],1);
            }
            else {
                System.out.println("inelse "+arr[i]);
                countMap.put(arr[i],1);
            }
        }

        System.out.print(output);
    }
}
