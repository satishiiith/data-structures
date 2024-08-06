package allone;



import org.w3c.dom.Node;

import java.util.*;

public class AllOne {
    Map<String, Integer> keyFrequecnyMap;

    Map<Integer, Set<String>> countKeysMap;
    int maxFrequency;
    int minFrequency;

    //maxFrequency ->incremernt
    //decrr(k1 )--> 15

    // 10 , 13 , 14 , 15[1]



    public AllOne() {
        this.keyFrequecnyMap = new HashMap<>();
        this.countKeysMap = new HashMap<>();
        this.maxFrequency = -99999; //max inf
        this.minFrequency = 99999;
    }

    private int  update(String key, int units){
        int prevFrequency = keyFrequecnyMap.getOrDefault(key,0);
        int currentFrequency = prevFrequency+units;
        keyFrequecnyMap.put(key,currentFrequency);



        // remove key from prevFrequency
       if(countKeysMap.containsKey(prevFrequency)){
           countKeysMap.get(prevFrequency).remove(key);

           // after removing its empty and that element is either max or  min
           if(countKeysMap.get(prevFrequency).isEmpty()){
                if(maxFrequency==prevFrequency){
                    maxFrequency = currentFrequency;
                }
                if(minFrequency==prevFrequency)
                    minFrequency=currentFrequency;
            }


        }
       // this is to handle defualt max and min
        maxFrequency = Math.max(maxFrequency,currentFrequency);
        minFrequency = Math.min(minFrequency,currentFrequency);


        Set<String> currentKeys = countKeysMap.getOrDefault(currentFrequency,new HashSet<>());
        currentKeys.add(key);
        countKeysMap.put(currentFrequency,currentKeys);


        return  currentFrequency;

    }

    public int getIncr(String key){
      return update(key,1);
    }



    public int getDecr(String key){
       return update(key, -1);
    }
    public  String getMaxFrequency(){
        if(countKeysMap.isEmpty())
            return "";
        Set<String > keysSet = countKeysMap.get(maxFrequency);
        for(String key:keysSet)
            return key;
        return  "";
    }

    public  String getMinFrequency(){
        if(countKeysMap.isEmpty())
            return "";
        Set<String > keysSet = countKeysMap.get(minFrequency);
        for(String key:keysSet)
            return key;
        return  "";
    }



    // s1->{k1,k2,k3}, s2 s3
    //k1 - 1M

    //s1 ->1M
    //s1 -k1
    //s2 ->k2

    //node ->set of keys



}
