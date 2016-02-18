import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Program that estimates a given constant using numbers provided by a user.
 * Utilizes the "de Jager formula."
 *
 * @author Jacob Walters
 *
 */
public final class ABCDGuesser2 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private ABCDGuesser2() {
    }

    /**
     * Method that asks user for and obtains an array of 4 personal numbers
     * greater than 0 but not equal to 1.
     *
     * @param in
     *            A SimpleReader variable
     * @param out
     *            A SimpleWriter variable
     * @return array of personal numbers
     *
     * @requires [SimpleWriter component and SimpleReader component be imported]
     * @ensures [An array of 4 personal numbers, with each number > 0 && != 1]
     */
    private static double[] getPersonalNumbers(SimpleReader in,
            SimpleWriter out) {
        double[] userNumbers = new double[4];

        //loop to fill the personal number array
        for (int i = 0; i <= 3; i++) {
            out.print("Enter a personal number " + (i + 1) + ": ");
            userNumbers[i] = in.nextDouble();
            if (userNumbers[i] == 1 || userNumbers[i] <= 0) {
                out.println(
                        "ERROR: Personal numbers must be greater than zero and not equal to one. Try again.");
                i--;
            }
        }
        return userNumbers;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        out.print("Enter a constant to be estimated: ");
        double constant = in.nextDouble();

        //array to store personal numbers entered
        double[] personalNumbers = getPersonalNumbers(in, out);

        //assign personal numbers to variables for w,x,y,z
        double numberW = personalNumbers[0];
        double numberX = personalNumbers[1];
        double numberY = personalNumbers[2];
        double numberZ = personalNumbers[3];

        //declare and instantiate a-d values
        double aValue = 0;
        double bValue = 0;
        double cValue = 0;
        double dValue = 0;

        //declare and instantiate error and estimation variables
        double error = 0;
        double myEstimation = 0;
        double finalEstimation = 0;

        //create a deJager array
        double[] jagers = { -5, -4, -3, -2, -1, -.5, -.333333333333, -.25, 0,
                .25, .333333333333, .5, 1, 2, 3, 4, 5 };

        /*
         * A loop will need to be created for each personal number W-Z. Each de
         * Jager power will be tested with each personal number and saved as the
         * best estimate if and only if it is the least of the estimates looped.
         *
         */
        for (int a = 0; a < jagers.length; a++) {
            double number1 = Math.pow(numberW, jagers[a]);
            for (int b = 0; b < jagers.length; b++) {
                double number2 = Math.pow(numberX, jagers[b]);
                for (int c = 0; c < jagers.length; c++) {
                    double number3 = Math.pow(numberY, jagers[c]);
                    for (int d = 0; d < jagers.length; d++) {
                        double number4 = Math.pow(numberZ, jagers[d]);
                        myEstimation = number1 * number2 * number3 * number4;
                        if (Math.abs(constant - myEstimation) < Math
                                .abs(constant - finalEstimation)) {
                            finalEstimation = myEstimation;
                            aValue = jagers[a];
                            bValue = jagers[b];
                            cValue = jagers[c];
                            dValue = jagers[d];
                        }
                    }
                }
            }
        }

        //Print each exponential value A-D
        out.println();
        out.println("Value of exponent A: " + aValue);
        out.println("Value of exponent B: " + bValue);
        out.println("Value of exponent C: " + cValue);
        out.println("Value of exponent D: " + dValue);
        out.println();

        //print the closest estimate to the console
        out.println("The closest estimate to " + constant
                + " using the personal numbers provided is: "
                + finalEstimation);

        //use printf statement to format the relative error to 2 decimal places.
        //SimpleWriter does not support printf.
        System.out.printf("The relative error of the estimate is: %.2f",
                (Math.abs(constant - finalEstimation) / constant) * 100);
        out.print(" %");

        in.close();
        out.close();
    }

}
