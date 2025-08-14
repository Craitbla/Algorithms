    // 1)поворот в той же матрице
    // 2)StringBuilder создает изменяемую последовательность символов

    // Когда использовать StringBuilder?
    // Любая конкатенация в циклах
    // Сборка сложных строк из множества частей
    // Когда важна производительность (логирование, обработка файлов)

public class MatrixTurn { // поворот матрицы на 90 гардусов вправо

    public static void main(String[] args) {
        int[][] oldArray = new int[][] { { 0, 1, 2, 3, 55 }, { 4, 5, 6, 7, 66 }, { 8, 9, 10, 11, 77 },
                { 12, 13, 14, 15, 88 } };
        printMatrix(oldArray);
        int rows = oldArray.length;
        int cols = oldArray[0].length;
        int[][] newArray = new int[cols][rows];
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                newArray[i][j] = oldArray[rows - 1 - j][i];
            }
        }

        printMatrix(newArray);
    }

    // public static void badPrintMatrix(int[][] arr) {
    //     String newString = "";
    //     for (int[] str : arr) {
    //         for (int num : str) {
    //             newString += num;
    //             newString += " ";
    //         }
    //         System.out.println(newString);
    //         newString = "";
    //     }
    //     System.out.println("\n");
    // }

    public static void printMatrix(int[][] arr) {
        StringBuilder strB = new StringBuilder();
        for (int[] str : arr) {
            for (int num : str) {
                strB.append(String.format("%-6d", num));
            }
            strB.append("\n");
        }
        System.out.println(strB);
    }

}
