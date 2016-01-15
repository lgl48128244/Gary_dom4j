package commons;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Map.Entry;
/**
 * 统计一句话中各个字符的个数
 * 1.遍历每一个字符串
 * 2.创建一个Map，key就是出现的字符，而value就是出现的次数
 * 3.在存入Map时有两种情况
 *   3.1：该字符作为key在Map中不存在：
 *        说明这个字符第一次统计，那么出现的次数就是1
 *   3.2：该字符作为key已经在Map中存在：
 *       说明这个字符已经统计过了，只需要对出现的次数累加1次即可
 */
public class StatisticalFigures {
 
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
 
        Map<Character,Integer> map = new LinkedHashMap<Character,Integer>();
 
        for(int i=0;i<str.length();i++){
            char c = str.charAt(i);
            if(map.containsKey(c)){
                map.put(c,map.get(c)+1);
            }else
                map.put(c,1);
        }
        System.out.println(map);
        Set<Entry<Character,Integer>> entrySet = map.entrySet();
        for(Entry<Character,Integer> e:entrySet){
            char k=e.getKey();
            int v= e.getValue();
            System.out.println(k+":"+v);
        }
 
    }
}
