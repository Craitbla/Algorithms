import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

//Найти все простые числа меньше или равные заданному числу N.


public class Main {
    public static void main(String[] args) {
        List<Integer> primeList = getPrime(24);
        for (Integer el:
             primeList) {
            System.out.println(el);
        }
    }

    public static List<Integer> getPrime(int num){
        boolean[] checkList = new boolean[num+1];
        Arrays.fill(checkList, true);
        for (int i = 2; i*i <= num; i++) {
            if(!checkList[i]){
                continue;
            }
            for (int j = i; i*j <= num; j++) {
                checkList[i*j] = false;
            }
        }
        List<Integer> isPrime = new ArrayList<>();
        for (int k = 2; k < num+1; k++) {
            if(checkList[k]){
                isPrime.add(k);
            }
        }
        return isPrime;
    }
}