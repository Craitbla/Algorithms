import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();

        System.out.println(sol.isIsomorphic("fgegg", "socoo"));
    }
}
class Solution {
    public boolean isIsomorphic(String s, String t) {
        Map<Character, Character> checkMap = new HashMap<Character, Character>();
        char curCharS = '\0', curCharT = '\0';
        for (int i = 0; i < s.length(); i++) {
            curCharS = s.charAt(i);
            curCharT = t.charAt(i);
            if(checkMap.containsKey(curCharS)){
                if(checkMap.get(curCharS)!=curCharT){
                    return  false;
                }
            } else {
                checkMap.put(curCharS, curCharT);
            }

        }
        return true;

    }
}
