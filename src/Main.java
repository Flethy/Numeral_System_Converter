import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int sourceRadix = scanner.nextInt();
        String sourceNumber = scanner.next();
        int targetRadix = scanner.nextInt();
        convert(sourceNumber, sourceRadix, targetRadix);
    }

    public static void convert(String number, int sourceRad, int targetRad) {

        String intPart = "";
        String fractPart = "";

        // get parts of number
        if (number.contains(".")) {
            intPart = number.split("\\.")[0];
            fractPart = number.split("\\.")[1];
        } else {
            intPart = number;
        }

        // from integer part in source radix to integer part in decimal
        int intPartInDecimal = 0;
        if (sourceRad == 1) intPartInDecimal = number.length();
        else intPartInDecimal = Integer.parseInt(intPart, sourceRad);

        // from integer part in decimal to integer part in target radix
        String intPartInTarget;
        if (targetRad == 1) {
            intPartInTarget = "";
            while (intPartInDecimal > 0) {
                intPartInTarget += "1";
                intPartInDecimal--;
            }
        } else {
            // output the result
            intPartInTarget = Integer.toString(intPartInDecimal, targetRad);
        }

        // handle fractional part or end the program
        if (fractPart.equals((""))) {
            System.out.print(intPartInTarget);
            return;
        } else {
            // from fractional part in source radix to fractional part in decimal
            double fractPartInDecimal = 0;
            for (int i = 0; i < fractPart.length(); i++) {
                fractPartInDecimal += Integer.parseInt(fractPart.substring(i, i+1), sourceRad) / Math.pow(sourceRad, i+1);
            }

            // from fractional part in decimal to fractional part in target radix
            String fractPartInTarget = "";
            for (int i = 0; i < 5; i++) {
                fractPartInTarget += Integer.toString((int) (fractPartInDecimal * targetRad), targetRad);
                int redundantIntPart = (int) (fractPartInDecimal * targetRad);
                fractPartInDecimal = fractPartInDecimal * targetRad - redundantIntPart;
            }

            // output the result
            System.out.print(intPartInTarget + "." + fractPartInTarget);
        }

    }
}
