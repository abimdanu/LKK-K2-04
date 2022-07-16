import java.util.Scanner;
import java.util.ArrayList;

public class LCS_K2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // input X dan Y sebagai rangkaian string yang ingin
        // dicari LCS-nya
        System.out.print("Input X   : ");
        String X_input = sc.next();
        System.out.print("Input Y   : ");
        String Y_input = sc.next();

        // mengubah input String ke array String
        String X[] = new String[X_input.length()];
        for (int i = 0; i < X.length; i++) {
            X[i] = X_input.substring(i, i+1);
        }
        String Y[] = new String[Y_input.length()];
        for (int i = 0; i < Y.length; i++) {
            
            Y[i] = Y_input.substring(i, i+1);
        }

        LCS_length(X, Y);
        /*
         * TODO: METHOD LCS_length MASIH ERROR !!
         * (array out o/ bounds, akses idx 7 padahal size 7)
         */
    }

    static ArrayList<int[][]> LCS_length(String[] X, String[] Y) {
        int m = X.length;
        int n = Y.length;

        // inisialisasi array arah/panah dan array nilai
        int[][] direction = new int[m+1][n+1];
        int[][] c = new int[m+1][n+1];

        for (int i = 1; i <= m; i++) {
            c[i][0] = 0;
        }
        for (int j = 1; j <= n; j++) {
            c[0][j] = 0;
        }

        // ALGORITMA LCS
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (X[i].equals(Y[j])) {
                    c[i][j] = c[i-1][j-1] + 1;
                    direction[i][j] = 6;
                } else if (c[i-1][j] >= c[i][j-1]) {
                    c[i][j] = c[i-1][j];
                    direction[i][j] = 7;
                } else {
                    c[i][j] = c[i][j-1];
                    direction[i][j] = 5;
                }
            }
        }

        // kedua tabel dimasukkan ke array list agar
        // dapat di-return keduanya
        ArrayList<int[][]> result = new ArrayList<>();
        result.add(c);
        result.add(direction);
        return result;
    }

    static void Print_Matrices(int[][] direction_arr, int[][] c_arr) {
        System.out.println("\n===============================");
        System.out.println("   Tabel C : ");
        for (int i = 0; i < c_arr.length; i++) {
            for (int j = 0; j < c_arr[i].length; j++) {
                System.out.printf("%4d", c_arr[i][j]);
            }
            System.out.println();
        }
        System.out.println("===============================\n");
        
        System.out.println("\n===============================");
        System.out.println("   Tabel B (arah) : ");
        for (int i = 0; i < direction_arr.length; i++) {
            for (int j = 0; j < direction_arr[i].length; j++) {
                if (direction_arr[i][j] == 5) {
                    System.out.printf("%4s", "<<");
                } else if (direction_arr[i][j] == 6) {
                    System.out.printf("%4s", "\\\\");
                } else if (direction_arr[i][j] == 7) {
                    System.out.printf("%4s", "^^");
                } else {
                    if (i == 0 && j == 0)
                        System.out.print("    ");
                    else 
                        System.out.printf("%4d", direction_arr[i][j]);
                }
            }
            System.out.println();
        }
        System.out.println("===============================");
    }

}