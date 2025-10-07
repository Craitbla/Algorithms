public class MatrixTurn {

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
