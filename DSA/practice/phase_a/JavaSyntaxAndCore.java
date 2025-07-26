package practice.phase_a;

import practice.utility.Printer;

public class JavaSyntaxAndCore {

    public static void run() {
        Printer.printDescription("Running Java Syntax And Core");
        helloWorld();
        Printer.printDescription("PrimitiveRanges");
        showPrimitiveRanges();
        Printer.printDescription("NullBehavior");
        demonstrateNullBehavior();
        Printer.printDescription("OperatorsUsage");
        showOperatorsUsage();
        Printer.printDescription("CastingExamples");
        showCastingExamples();
        Printer.printDescription("Comparing String vs StringBuilder Performance");
        compareStringPerformance();
        Printer.printDescription("String Formatting Examples");
        showFormattingExamples();
    }

    // * Task: Print "Hello, World!" from a proper `main()` method.
    private static void helloWorld() {
        Printer.printInfo("Hello World");
    }

    // * Task: Print min/max for: PrimitiveRanges
    private static void showPrimitiveRanges() {
        Printer.printInfo("Primitive types and their ranges:");
        Printer.printInfo("Byte: " + Byte.MIN_VALUE + " to " + Byte.MAX_VALUE);
        Printer.printInfo("Short: " + Short.MIN_VALUE + " to " + Short.MAX_VALUE);
        Printer.printInfo("Int: " + Integer.MIN_VALUE + " to " + Integer.MAX_VALUE);
        Printer.printInfo("Long: " + Long.MIN_VALUE + " to " + Long.MAX_VALUE);
        Printer.printInfo("Float: " + (-Float.MAX_VALUE) + " to " + Float.MAX_VALUE);
        Printer.printInfo("Double: " + (-Double.MAX_VALUE) + " to " + Double.MAX_VALUE);
    }

    /*
     * Task: Create a reference variable, assign `null`, and try using it → show
     * `NullPointerException`.
     */
    @SuppressWarnings("null")
    private static void demonstrateNullBehavior() {
        Object object = new Object();
        Printer.printInfo("The Object has instance" + object.toString());
        object = null;
        Printer.printInfo("Trying to use null reference; it should throw NullPointerException.");
        try {
            Printer.printInfo("The Object has instance" + object.toString());
        } catch (NullPointerException e) {
            Printer.printError("Exception occur");
        }
    }

    /*
     * Task: Demonstrate Operators:
     * Arithmetic (`+`, `-`, `*`, `/`, `%`)
     * Comparison (`<`, `>`, `==`, `!=`, `>=` `<=`)
     * Logical (`&&`, `||`, `!`)
     * Bitwise (`&`, `|`, `^`, `~`, `<<`, `>>`)
     */
    private static void showOperatorsUsage() {
        Printer.printInfo("Arithmetic Operation with " + 10 + " & " + 30);
        Printer.printInfo(" 10 + 30 = " + (10 + 30) +
                ",\n 10 - 30 = " + (10 - 30) +
                ",\n 10 * 30 = " + (10 * 30) +
                ",\n 10 / 30 = " + (10 / 30) +
                ",\n 10 % 30 = " + (10 % 30));

        Printer.printInfo("Comparison Operation with " + 10 + " & " + 30);
        Printer.printInfo(" 10 == 30 = " + (10 == 30) +
                ",\n 10 != 30 = " + (10 != 30) +
                ",\n 10 > 30 = " + (10 > 30) +
                ",\n 10 < 30 = " + (10 < 30) +
                ",\n 10 >= 30 = " + (10 >= 30) +
                ",\n 10 <= 30 = " + (10 <= 30));

        Printer.printInfo("Logical Operation with " + false + " & " + true);
        Printer.printInfo(" And should give true if both one are true, true && true " + (true && true) +
                ",\n Or should give true if any one is true, false || true = " + (false || true) +
                ",\n Reverse the false,!false = " + (!false));

        Printer.printInfo("Bitwise Operation with " + 10 + " & " + 30);
        Printer.printInfo(" 10 & 0 = " + (10 & 0) + ",\n 10 | 30 = " + (10 | 30) + ",\n 10 ^ 30 = " + (10 ^ 30)
                + ",\n ~ 10 = " + (~10) + ",\n 10 << 30 = " + (10 << 30));
    }

    private static void showCastingExamples() {
        Printer.printWarning("Implicit type casting of primitive dataType");
        int intValue = Integer.MAX_VALUE;
        float floatValue = Float.MAX_VALUE;
        Printer.printInfo("int to long, (long) " + intValue + " : " + (long) intValue);
        Printer.printInfo("float to double, (double) " + floatValue + " : " + (double) floatValue);
        Printer.printWarning("Explicit type casting of primitive dataType");

        double doubleValue = Double.MAX_VALUE;
        long longValue = Long.MAX_VALUE;
        Printer.printInfo("long to int, (int)  " + longValue + " : " + (int) longValue);
        Printer.printInfo("double to float, (float) " + doubleValue + " : " + (float) doubleValue);
    }

    private static void compareStringPerformance() {
        final int LIMIT = 2000;

        // Using String (immutable)
        String immutableResult = "";
        Printer.printInfo("Starting concatenation using String (immutable)");

        long start = System.nanoTime();
        for (int i = 1; i <= LIMIT; i++) {
            immutableResult += "," + i;
        }
        long durationString = System.nanoTime() - start;

        Printer.printInfo("Completed String concatenation.");
        Printer.printInfo("Time taken (nano): " + durationString);
        Printer.printWarning("--------------------------------------------");

        // Using StringBuilder (mutable)
        StringBuilder builderResult = new StringBuilder();
        Printer.printInfo("Starting concatenation using StringBuilder (mutable)");

        start = System.nanoTime();
        for (int i = 1; i <= LIMIT; i++) {
            builderResult.append(",").append(i);
        }
        long durationBuilder = System.nanoTime() - start;

        Printer.printInfo("Completed StringBuilder concatenation.");
        Printer.printInfo("Time taken (ms): " + durationString / 1_000_000.0);
        Printer.printWarning("--------------------------------------------");

        // Comparison Summary
        Printer.printInfo("Difference: " + (durationString - durationBuilder) + " nanoseconds");
    }

    // format a float to 2 decimal places, format integers with padding
    private static void showFormattingExamples() {
        String formattedString = String.format("int with commas : %,d, float : %,.02f, int with padding %,09d", 153529,
                2768943.34512f, 3225);
        Printer.printInfo(formattedString);
    }

}
