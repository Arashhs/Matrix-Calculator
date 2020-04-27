import java.util.Scanner;

/**
 * This class makes a matrix from console input
 * @author Arash
 * @since 3/20.2018
 */

public class InputMatrix {
    private int[][] x; //Saves matrix's elements
    private String[] tmp; //A temporary String array to save inputs from console
    private int n; //number of rows of matrix
    private int m; //number of collomns of matrix
    private Scanner scanner; //A scanner for reading console input

    /**
     * Constructor makes a 2D array for matrix's elements
     */
    public InputMatrix() {
        x = new int[100][100];
        tmp = new String[100];
        scanner = new Scanner(System.in);
    }

    /**
     * Completely reads every line in input with no changes at all
     * Number of lines = number of matrix's rows = n
     */
    public void read() {
        for (n = 0; ; n++) {
            tmp[n] = scanner.nextLine();
            if (tmp[n].equals(""))
                break;
        }
    }

    /**
     * Converts string to a matrix by splitting and casting input's string
     */
    public void makeMatrix() {
        if(tmp[0].equals("")){ //When matrix is empty
            System.err.println("Error! Input is not a matrix\nEnter matrix's elements again");
        }
        for (int i = 0; i < n; i++) {
            int j = 0;
            String[] str = tmp[i].split(",");
            for (j = 0; j < str.length; j++){
                x[i][j] = Integer.parseInt(str[j]);
            }
            if(m != j && i>0) { //Rows must have equal elements
                System.err.println("Error! Input is not a matrix\nEnter matrix's elements again");
                break;
            }
            m = j; //number of colomns

        }
    }

    /**
     *
     * @return Matrix array
     */
    public int[][] getX() {
        return x;
    }

    /**
     *
     * @return number of rows
     */
    public int getN() {
        return n;
    }

    /**
     *
     * @return number of colomns
     */
    public int getM() {
        return m;
    }
}
