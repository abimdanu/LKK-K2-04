import java.util.Scanner;
import java.util.ArrayList;

public class MCM_K2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // input data
        System.out.println("--  INPUT DATA  --\n");
        System.out.print("Jumlah matriks    : ");
        int n = sc.nextInt();
        int[] p = new int[n+1];
        System.out.println();

        for (int i = 0; i < p.length; i++) {
            System.out.printf("Dimensi ke-%d      : ", i+1);
            p[i] = sc.nextInt();
        }

        System.out.println("\n--  DATA TELAH DIMASUKKAN --\n");

        System.out.println("Akan dilakukan perkalian matriks dari matriks"
                            + "\nke-X hingga ke-Y. Masukkan X dan Y !");
        System.out.print("X (matriks awal)      : ");
        int lower = sc.nextInt();
        System.out.print("Y (matriks akhir)     : ");
        int upper = sc.nextInt();
        ArrayList<int[][]> arr = MCM(p);

        /*
         * TODO: SELESAIKAN & PANGGIL METHOD PRINT
         */
    }

    static ArrayList<int[][]> MCM(int[] p) {
        int n = p.length - 1;
        int[][] m = new int[n + 1][n + 1];
        int[][] s = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            m[i][i] = 0;
        }
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < s.length; j++) {
                m[i][j] = j;
                s[i][j] = j;
            }
        }
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j < 1; j++) {
                m[i][j] = i;
                s[i][j] = i;
            }
        }

        int j = 0;
        int q = 0;
        for (int l = 2; l <= n; l++) {
            for (int i = 1; i <= n - l + 1; i++) {
                j = i + l - 1;
                m[i][j] = Integer.MAX_VALUE;
                for (int k = i; k <= j - 1; k++) {
                    q = m[i][k] + m[k + 1][j] + p[i - 1] * p[k] * p[j];
                    if (q < m[i][j]) {
                        m[i][j] = q;
                        s[i][j] = k;
                    }
                }
            }
        }
        ArrayList<int[][]> result = new ArrayList<>();
        result.add(m);
        result.add(s);
        return result;
    }
}
