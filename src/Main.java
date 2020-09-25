import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // source radix
        System.out.print("Source radix: ");
        String sourceRadixStr = scanner.nextLine();
        int sourceRadix = 10;
        if ((sourceRadix = handleSourceRadix(sourceRadixStr)) == 0) return;

        // source number
        System.out.print("Source number: ");
        String sourceNumber = scanner.nextLine();
        if (!isSourceNumber(sourceNumber, sourceRadix)) return;

        // target radix
        System.out.print("Target radix: ");
        String targetRadixStr = scanner.nextLine();
        int targetRadix = 10;
        if ((targetRadix = handleTargetRadix(targetRadixStr)) == 0) return;


        convert(sourceNumber, sourceRadix, targetRadix);
    }

    private static void convert(String number, int sourceRad, int targetRad) {

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
        else {
            intPartInDecimal = Integer.parseInt(intPart, sourceRad);
        }

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

    // exception handler for source radix
    private static int handleSourceRadix(String sourceRadixStr) {
        int sourceRadix = 0;
        if (sourceRadixStr.matches("\\d+")) sourceRadix = Integer.parseInt(sourceRadixStr);
        else {
            System.out.println("error: source radix is not a number");
            return 0;
        }
        if (sourceRadix < 1 || sourceRadix > 36) {
            System.out.println("error: source radix should be in [1;36]");
            return 0;
        }
        return sourceRadix;
    }

    // exception handler for source number
    private static boolean isSourceNumber(String sourceNumber, int sourceRadix) {
        if (sourceRadix == 1) {
            for (int i = 0; i < sourceNumber.length(); i++) {
                if (sourceNumber.charAt(i) != '1') {
                    System.out.println("error: source number with a wrong base");
                    return false;
                }
            }
        } else {
            try {
                if (sourceNumber.contains(".")) {
                    Integer.parseInt(sourceNumber.split("\\.")[0], sourceRadix);
                    Integer.parseInt(sourceNumber.split("\\.")[1], sourceRadix);
                } else Integer.parseInt(sourceNumber, sourceRadix);
            } catch (NumberFormatException e) {
                System.out.println("error: source number with a wrong base");
                return false;
            }
        }
        return true;
    }

    // exception handler for target radix
    private static int handleTargetRadix(String targetRadixStr) {
        int targetRadix = 0;
        if (targetRadixStr.matches("\\d+")) targetRadix = Integer.parseInt(targetRadixStr);
        else {
            System.out.println("error: target radix is not a number");
            return 0;
        }
        if (targetRadix < 1 || targetRadix > 36) {
            System.out.println("error: source radix should be in [1;36]");
            return 0;
        }
        return targetRadix;
    }
}
