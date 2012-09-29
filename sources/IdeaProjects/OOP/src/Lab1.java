import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * File: Lab1
 * User: unit7
 * Date: 05.09.12
 * Time: 10:01
 */

public class Lab1 {
    public static void main(String[] args) {
        new Lab1().solve();
    }

    private void solve() {
        writer = new PrintWriter(new OutputStreamWriter(System.out));
        Random random = new Random(System.currentTimeMillis());
        for(int i = 0; i < SIZE; ++i) {
            for(int j = 0; j < SIZE; ++j) {
                matrix[i][j] = (byte)random.nextInt();
                writer.print(String.format("%5d", matrix[i][j]));
            }
            writer.println();
        }

        // Right diag
        int k = 0;
        for(int j = SIZE - 1; j >= 0; --j) {
            for(int i = 0, p = j; i < SIZE && p < SIZE; ++i, ++p)   arr[k++] = matrix[i][p];
        }
        for(int i = 1; i < SIZE; ++i) {
            for(int j = 0, p = i; j < SIZE && p < SIZE; ++j, ++p)   arr[k++] = matrix[p][j];
        }
        writer.print("\nRight diagonal:\t");
        out(arr);

        // Left diag
        k = 0;
        for(int j = 0; j < SIZE; ++j) {
            for(int i = 0, p = j; i < SIZE && p >= 0; ++i, --p)    arr[k++] = matrix[i][p];
        }
        for(int i = 1; i < SIZE; ++i) {
            for(int j = SIZE - 1, p = i; j >= 0 && p < SIZE; --j, ++p)  arr[k++] = matrix[p][j];
        }
        writer.print("Left diagonal:\t");
        out(arr);

        // Center spiral
        writer.print("Spiral from center:\t");
        out(getCentral());

        //Left top corner spiral
        writer.print("Spiral from upper left corner:\t");
        out(getUpperLeftCorner());

        // Exercise #2
        writer.println("\nExercise #2:");
        byte[][] arr2 = new byte[SIZE][];
        for(int i = 0; i < arr2.length; ++i)    arr2[i] = new byte[random.nextInt(SIZE - 1) + 1];

        for(int i = 0; i < arr2.length; ++i) {
            for(int j = 0; j < arr2[i].length; ++j) {
                arr2[i][j] = (byte)random.nextInt();
                writer.print(String.format("%5d", arr2[i][j]));
            }
            writer.println();
        }

        writer.close();
    }

    private byte[] getCentral() {
        byte[] arr = new byte[SIZE * SIZE];
        int n = 1, ii = SIZE >> 1, ij = ii, k = 0;
        while(n <= SIZE) {
            n += 1;
            for(int i = 0; i < n - 1 && check(ii, ij); ++i)  arr[k++] = matrix[ii][ij--];
            for(int i = 0; i < n - 1 && check(ii, ij); ++i)  arr[k++] = matrix[ii--][ij];
            n += 1;
            for(int i = 0; i < n - 1 && check(ii, ij); ++i)  arr[k++] = matrix[ii][ij++];
            for(int i = 0; i < n - 1 && check(ii, ij); ++i)  arr[k++] = matrix[ii++][ij];
        }
        return arr;
    }

    private byte[] getUpperLeftCorner() {
        byte[] arr = new byte[SIZE * SIZE];
        int n = SIZE - 1, ii = 0, ij = 0, k = 0;

        for(int i = 0; i < n && check(ii, ij); ++i) arr[k++] = matrix[ii][ij++];

        while(n > 0) {
            for(int i = 0; i < n && check(ii, ij); ++i)  arr[k++] = matrix[ii++][ij];
            for(int i = 0; i < n && check(ii, ij); ++i)  arr[k++] = matrix[ii][ij--];
            n -= 1;
            for(int i = 0; i < n && check(ii, ij); ++i)  arr[k++] = matrix[ii--][ij];
            for(int i = 0; i < n && check(ii, ij); ++i)  arr[k++] = matrix[ii][ij++];
            n -= 1;
        }
        arr[k] = matrix[ii][ij];

        return arr;
    }

    private boolean check(int i, int j) {
        return i >= 0 && j >= 0 && i < SIZE && j < SIZE;
    }

    private void out(byte[] arr) {
        for(int i = 0; i < arr.length; ++i) writer.print(String.format("%5d", arr[i]));
        writer.println();
    }

    private final int SIZE = 5;
    private byte[] arr = new byte[SIZE * SIZE];
    private byte[][] matrix = new byte[SIZE][SIZE];

    private PrintWriter writer;
}
