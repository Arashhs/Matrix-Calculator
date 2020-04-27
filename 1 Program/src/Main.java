import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * This program is a calculator for matrix calculations
 *
 * @author Arash
 * @since 3/20/2018
 */

public class Main {
    public static void main(String[] args) {
        InputMatrix x = new InputMatrix();
        InputMatrix y = new InputMatrix();
        MatrixCalculations res;
        int xCoefficient;
        int yCoefficient;
        int tmpCoefficient; //For reversing
        int status = 0; //0: Starts with X, 1: Starts with Y
        Scanner scanner = new Scanner(System.in);
        System.out.println("Define the first matrix (X):");
        x.read(); //reads input
        x.makeMatrix(); //makes X matrix
        System.out.println("Define the second matrix (Y):");
        y.read(); //reads input
        y.makeMatrix(); //makes Y matrix
        while (true) {
            status = 0;
            System.out.println("Enter your polynomial expression:");
            String polynomial = scanner.nextLine(); //Reads the polynomial expression
            if (polynomial.contains("X+") || polynomial.contains("Y+")) { //When we have "X+" then its sum of matrices
                polynomial = polynomial.replaceAll("[()]", ""); //For whenever we have a negative coefficient
                if (polynomial.contains("Y+")) {
                    polynomial = polynomial.replaceAll("X", "Y");
                    polynomial = polynomial.replaceFirst("Y", "X");
                    status = 1;
                }
                String[] tmp = polynomial.split(Pattern.quote("X+"));//We extract X's coefficient
                if (!tmp[0].equals("")) { //When its "X" means coefficient is "1"
                    xCoefficient = Integer.parseInt(tmp[0]);
                } else
                    xCoefficient = 1;
                if (!tmp[1].equals("Y")) { //We extract Y's coefficient. When its +aX+Y means coefficient of Y is 1
                    yCoefficient = Integer.parseInt(tmp[1].split("Y")[0]);
                } else
                    yCoefficient = 1;
                if (status == 1) {
                    tmpCoefficient = xCoefficient;
                    xCoefficient = yCoefficient;
                    yCoefficient = tmpCoefficient;
                }
                res = new MatrixCalculations(x.getX(), x.getN(), x.getM(), y.getX(), y.getN(), y.getM(), xCoefficient, yCoefficient);
                res.updateMatrices(); //updates with coefficients
                res.sum();//Calculates the sum
            } else if (polynomial.contains("X-") || polynomial.contains("Y-")) { //When we have "aX-bY" its subtraction. Rest of the coding is exactly same as sum
                status = 0;
                polynomial = polynomial.replaceAll("[()]", "");
                if (polynomial.contains("Y-")) {
                    polynomial = polynomial.replaceAll("X", "Y");
                    polynomial = polynomial.replaceFirst("Y", "X");
                    status = 1;
                }
                String[] tmp = polynomial.split(Pattern.quote("X-"));
                if (!tmp[0].equals("")) {
                    xCoefficient = Integer.parseInt(tmp[0]);
                } else
                    xCoefficient = 1;
                if (!tmp[1].equals("Y")) {
                    yCoefficient = Integer.parseInt(tmp[1].split("Y")[0]);
                } else
                    yCoefficient = 1;
                if (status == 1) {
                    tmpCoefficient = xCoefficient;
                    xCoefficient = yCoefficient;
                    yCoefficient = tmpCoefficient;
                }
                if (status == 0) {
                    res = new MatrixCalculations(x.getX(), x.getN(), x.getM(), y.getX(), y.getN(), y.getM(), xCoefficient, yCoefficient);
                    res.updateMatrices();
                    res.sub();
                } else {
                    res = new MatrixCalculations(y.getX(), y.getN(), y.getM(), x.getX(), x.getN(), x.getM(), yCoefficient, xCoefficient);
                    res.updateMatrices();
                    res.sub();
                }
            } else if (polynomial.contains("X*") || polynomial.contains("Y*")) {//When we have "aX*bY" its multiplication. Rest of the coding is exactly same as sum
                status = 0;
                polynomial = polynomial.replaceAll("[()]", "");
                if (polynomial.contains("Y*")) {
                    polynomial = polynomial.replaceAll("X", "Y");
                    polynomial = polynomial.replaceFirst("Y", "X");
                    status = 1;
                }
                String[] tmp = polynomial.split(Pattern.quote("X*"));
                if (!tmp[0].equals("")) {
                    xCoefficient = Integer.parseInt(tmp[0]);
                } else
                    xCoefficient = 1;
                if (!tmp[1].equals("Y")) {
                    yCoefficient = Integer.parseInt(tmp[1].split("Y")[0]);
                } else
                    yCoefficient = 1;
                if (status == 1) {
                    tmpCoefficient = xCoefficient;
                    xCoefficient = yCoefficient;
                    yCoefficient = tmpCoefficient;
                }
                if (status == 0) {
                    res = new MatrixCalculations(x.getX(), x.getN(), x.getM(), y.getX(), y.getN(), y.getM(), xCoefficient, yCoefficient);
                    res.updateMatrices();
                    res.mult();
                } else {
                    res = new MatrixCalculations(y.getX(), y.getN(), y.getM(), x.getX(), x.getN(), x.getM(), yCoefficient, xCoefficient);
                    res.updateMatrices();
                    res.mult();
                }
            } else if ((polynomial.contains("X") && !polynomial.contains("Y")) || (polynomial.contains("Y") && !polynomial.contains("X")))
            { //Then there is only one matrix
                if (polynomial.contains("X")) {//There is only X
                    polynomial = polynomial.replaceAll("[()]", "");
                    polynomial = polynomial.replaceAll("X", "");
                    if (!polynomial.equals("")) { //When its X then coefficient is "1"
                        xCoefficient = Integer.parseInt(polynomial);
                    } else
                        xCoefficient = 1;
                    yCoefficient = 0;
                    res = new MatrixCalculations(x.getX(), x.getN(), x.getM(), y.getX(), y.getN(), y.getM(), xCoefficient, yCoefficient);
                    res.updateMatrices();
                    res.printMatrix(res.getX(), x.getN(), x.getM());

                }
                if (polynomial.contains("Y")) { //When there is only Y
                    polynomial = polynomial.replaceAll("[()]", "");
                    polynomial = polynomial.replaceAll("Y", "");
                    if (!polynomial.equals("")) {//If there is only Y means coefficint is 1
                        yCoefficient = Integer.parseInt(polynomial);
                    } else
                        yCoefficient = 1;
                    xCoefficient = 0;
                    res = new MatrixCalculations(x.getX(), x.getN(), x.getM(), y.getX(), y.getN(), y.getM(), xCoefficient, yCoefficient);
                    res.updateMatrices();
                    res.printMatrix(res.getY(), y.getN(), y.getM());

                }
            }
            else
                System.err.print("Wrong polynomial expression");

        }
    }
}
