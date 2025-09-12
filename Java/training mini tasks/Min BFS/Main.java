import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Queue;

//Даны размеры доски, координаты амбара, количество коней
//координаты первого коня
//координаты второго и тд.

//Найти сумму минимальных ходов шахматных коней до амбара

// 8 8 8 8 2
// 8 4
// 1 1
// 8% ответ

//Выводы: было бы лучше для времени записывать минимальные пути для каждой клетки
//+идея о хранении данных в двумерном массиве вмесло напирмер setа из пар или map

public class Main {
    public static final int[][] MOVES = {
            { 2, 1 }, { 2, -1 }, { -2, 1 }, { -2, -1 },
            { 1, 2 }, { 1, -2 }, { -1, 2 }, { -1, -2 }
    };

    record IntPair(int x, int y) {
    };

    public static int countMinSteps(HashMap<IntPair, IntPair> roads, IntPair barnСoords) {
        if (roads.size() == 0) {
            return -1;
        }
        int counter = -1;
        IntPair curPair = barnСoords;
        while (curPair != null) {
            counter++;
            curPair = roads.get(curPair);
        }
        return counter;
    }

    public static boolean checkInDem(IntPair checking, IntPair dem) {
        return (checking.x <= dem.x && checking.y <= dem.y && checking.x > 0 && checking.y > 0);
    }

    public static int findbarn(IntPair boardDim, IntPair barnСoords, IntPair horseСoords) {
        if (horseСoords.x > boardDim.x || horseСoords.y > boardDim.y) {
            return -1;
        }
        if (horseСoords.equals(barnСoords)) {
            return 0;
        }
        HashMap<IntPair, IntPair> roads = new HashMap<>();
        HashSet<IntPair> visitedSet = new HashSet<>();
        Queue<IntPair> vertexQueue = new ArrayDeque<IntPair>();
        IntPair newPair = new IntPair(-1, -1), exploringPair;
        vertexQueue.add(horseСoords);
        visitedSet.add(horseСoords);

        while (!vertexQueue.isEmpty() && !newPair.equals(barnСoords)) { // исследуем все варианты, их неизвестное
                                                                        // количество
            exploringPair = vertexQueue.poll();
            visitedSet.add(exploringPair); // потому что точно существует, а значит именно сейчас проходится
            for (int i = 0; i < MOVES.length; i++) { // поиск всех других вершин от этой вершины
                newPair = new IntPair(exploringPair.x + MOVES[i][0], exploringPair.y + MOVES[i][1]);
                if (newPair.equals(barnСoords)) {
                    roads.put(newPair, exploringPair);
                    break;
                }
                if (checkInDem(newPair, boardDim) && !visitedSet.contains(newPair)) {
                    vertexQueue.add(newPair);
                    roads.put(newPair, exploringPair);
                }

            }
        }
        return countMinSteps(roads, barnСoords);

    }

    public static int parseAndFind(BufferedReader reader) throws IOException {
        String dataStr = reader.readLine();
        String[] splitedDataStr = dataStr.split(" ");
        int n = Integer.parseInt(splitedDataStr[0]); // размеры доски (отсчет начинается с 1);
        int m = Integer.parseInt(splitedDataStr[1]);
        int s = Integer.parseInt(splitedDataStr[2]); // координаты клетки — кормушки (номер строки и столбца
                                                     // соответственно);
        int t = Integer.parseInt(splitedDataStr[3]);
        int q = Integer.parseInt(splitedDataStr[4]); // количество коней на доске.
        if (s > n || t > m || q > n * m) {
            return -1;
        }
        int result = 0;
        int tmpCounter = 0;
        IntPair boardDimensions = new IntPair(n, m);
        IntPair barnСoordinates = new IntPair(s, t);
        IntPair horseСoordinates;
        for (int i = 0; i < q; i++) { // подаются и обрабатываются координаты коней
            dataStr = reader.readLine();
            splitedDataStr = dataStr.split(" ");
            horseСoordinates = new IntPair(Integer.parseInt(splitedDataStr[0]), Integer.parseInt(splitedDataStr[1]));

            tmpCounter = findbarn(boardDimensions, barnСoordinates, horseСoordinates);
            if (tmpCounter == -1) {
                return -1;
            } else {
                result += tmpCounter;
            }

        }
        return result;

    }

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        writer.write(Integer.toString(parseAndFind(reader)));

        reader.close();
        writer.close();
    }
}
