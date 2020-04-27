/**
 * This class calculates all calculations needed for matrices and prints the results
 * @author Arash
 * @since 3/20.2018
 */
public class MatrixCalculations {
    private int[][] x; //X matrix
    private int xn, xm; // rows and colomns of X
    private int[][] y; //Y matrix
    private int yn, ym; // rows and colomns of Y
    private int xCoefficient;//Coefficient of X in polynomial expression
    private int yCoefficient;//Coefficient of Y in polynomial expression
    private int res[][];//Result matrix
    private int resM, resN;//colomns and rows of Result matrix

    /**
     *
     * @param x is the X matrix
     * @param xn rows of X
     * @param xm colomns of X
     * @param y is the Y matrix
     * @param yn rows of Y
     * @param ym colomns of Y
     * @param xCoefficient Coefficient of X in polynomial expression
     * @param yCoefficient Coefficient of Y in polynomial expression
     */
    public MatrixCalculations(int[][] x, int xn, int xm, int[][] y, int yn, int ym, int xCoefficient, int yCoefficient) {
        this.x = new int[100][100]; // Makes a new 2D array and copys X matrix (in main) elements to it so as not to change the original elements
        for(int i=0; i<x.length; i++)
            for(int j=0; j<x[i].length; j++)
                this.x[i][j]=x[i][j];
        this.xn = xn;
        this.xm = xm;
        this.y = new int[100][100]; //// Makes a new 2D array and copys Y matrix (in main) elements to it so as not to change the original elements
        for(int i=0; i<y.length; i++)
            for(int j=0; j<y[i].length; j++)
                this.y[i][j]=y[i][j];
        this.yn = yn;
        this.ym = ym;
        this.xCoefficient = xCoefficient;
        this.yCoefficient = yCoefficient;
        res = new int[100][100]; //A new array for result matrix
    }

    /**
     * This method updates X and Y matrices with their coefficients.
     * X and Y are new matrices in this class so this won't change original matrices
     * We use these two matrices for our calculations
     */
    public void updateMatrices() {
        for (int i = 0; i < xn; i++) {
            for (int j = 0; j < xm; j++)
                x[i][j] *= xCoefficient;
        }
        for (int i = 0; i < yn; i++) {
            for (int j = 0; j < ym; j++)
                y[i][j] *= yCoefficient;
        }
    }

    /**
     * Calculates and prints the summation of two matrices
     */
    public void sum() {
        if (xm != ym || xn != yn) {
            System.err.println("Two matrices should have the same size for sum");
            return;
        }
        for (int i = 0; i < xn; i++) {
            for (int j = 0; j < xm; j++) {
                res[i][j] = x[i][j] + y[i][j];
            }
        }
        resM = xm;
        resN = xn;
        printMatrix(res,resN,resM);
    }

    /**
     * Calculates and prints the subtraction of two matrices
     */
    public void sub() {
        if (xm != ym || xn != yn) {
            System.err.println("Two matrices should have the same size for sum");
            return;
        }
        for (int i = 0; i < xn; i++) {
            for (int j = 0; j < xm; j++) {
                res[i][j] = x[i][j] - y[i][j];
            }
        }
        resM = xm;
        resN = xn;
        printMatrix(res,resN,resM);
    }

    /**
     * calculates and prints the Multiplication of two matrices
     */
    public void mult() {
        if (xm != yn) {
            System.err.println("Multiplication is not possible for these two matrices");
            return;
        }
        resN = xn;
        resM = ym;
        for (int i = 0; i < resN; i++) {
            for (int j = 0; j < resM; j++) {
                res[i][j] = 0;
            }
        }
        for (int i = 0; i < resN; i++) {
            for (int j = 0; j < resM; j++) {
                for (int k = 0; k < xm; k++) {
                    res[i][j] += x[i][k] * y[k][j];
                }
            }
        }
        printMatrix(res,resN,resM);
    }

    /**
     * prints the result matrix
     * @param res is the result matrix
     * @param resN is the number of rows of result matrix
     * @param resM is the number of colomns of result matrix
     */
    public void printMatrix(int[][] res , int resN, int resM){
        System.out.println("Result:");
        for (int i = 0; i < resN; i++) {
            for (int j = 0; j < resM - 1; j++) {
                System.out.print(res[i][j] + ",");
            }
            System.out.print(res[i][resM - 1]);
            System.out.println();
        }
        System.out.println();
    }

    /**
     *
     * @return new X matrix (After updating it with coefficients)
     */
    public int[][] getX() {
        return x;
    }

    /**
     *
     * @return new Y matrix (After updating it with coefficients)
     */
    public int[][] getY() {
        return y;
    }
}

