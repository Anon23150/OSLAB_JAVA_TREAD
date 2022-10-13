
import java.util.*;

public class LabMatixMul {

    public static void main(String[] args) {
        int[][] inputA = { { 5, 6, 7 }, { 4, 8, 9 } };
        int[][] inputB = { { 6, 4 }, { 5, 7 }, { 1, 1 } };
        Mydata matA = new Mydata(inputA);
        Mydata matB = new Mydata(inputB);
        int matC_r = matA.data.length;
        int matC_c = matB.data[0].length;
        Mydata matC = new Mydata(matC_r, matC_c);
        MatrixMulThread tread = new MatrixMulThread(matC_r, matC_c, matA, matB, matC);
        tread.run();
        try {

            tread.wait();
            System.out.println("Finish cal "); // if print this parent not wait 

        } catch (Exception e) {
            matC.show();
        }

    }
}

class Mydata {
    int[][] data;

    Mydata(int[][] matrix) {
        data = matrix;
    }

    Mydata(int r, int c) {
        data = new int[r][c];
        for (int[] aRow : data) {
            Arrays.fill(aRow, 9);
        }

    }

    void show() {
        System.out.println(Arrays.deepToString(data));
    }
}

class MatrixMulThread implements Runnable {
    int processing_row;
    int processing_col;
    Mydata datA;
    Mydata datB;
    Mydata datC;

    MatrixMulThread(int tRow, int tCol, Mydata a, Mydata b, Mydata c) {
        processing_row = tRow;
        processing_col = tCol;
        datA = a;
        datB = b;
        datC = c;

    }

    public void run() {

        for (int i = 0; i < processing_row; i++) {
            for (int j = 0; j < processing_col; j++) {
                datC.data[i][j] = 0;
                for (int k = 0; k < datA.data[0].length; k++) {
                    datC.data[i][j] += datA.data[i][k] * datB.data[k][j];
                }
            }
        }
    }
}