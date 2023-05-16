import java.util.HashMap;
/*
 *
 *    Title: Hashmap syntax code
 *    Author: w3schools
 *    Availability: https://www.w3schools.com/java/java_hashmap.asp
 *
 */
public class mockDB {
    static HashMap<String, String> hMap;
    static HashMap<String, Integer> hMap2;
    public void mockDB() {
        hMap = new HashMap<>();
        hMap2 = new HashMap<>();
        hMap.put("name", "Zorro");
        hMap.put("name2","LinLin");
        hMap.put("wife","Isha");
        hMap.put("career", "Professor");
        hMap2.put("life_count", 8);
        hMap2.put("kids", 5);
        hMap2.put("house_money", 1200000);
        hMap2.put("bal", 800000);
        hMap2.put("bal2", 90000);
        hMap2.put("bal3",-10000000);
        hMap2.put("salary",100000);
        hMap2.put("debt",1000000);
        hMap2.put("turn",4);
        hMap2.put("lucky_no",8);
        hMap2.put("limit",60000);
        hMap2.put("gmoney",50000);
        hMap2.put("g_num",7);
    }
    public String gethMap(String key) {
        String val = hMap.get(key);
        return val;
    }
    public Integer gethMap2(String key) {
        int val = hMap2.get(key);
        return val;
    }
}
