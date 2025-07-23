package practice.phase_a;

import practice.utility.Printer;

public class Phase_A {

    public static void run() {
        Printer.printDescription("Running Phase A");
        helloWorld();
        Printer.printDescription("PrimitiveRanges");
        showPrimitiveRanges();
        Printer.printDescription("NullBehavior");
        demonstrateNullBehavior();
        Printer.printDescription("OperatorsUsage");

        showOperatorsUsage();
    }

    // * Task: Print "Hello, World!" from a proper `main()` method.
    private static void helloWorld() {
        System.out.println("Hello World");
    }

    // * Task: Print min/max for: PrimitiveRanges
    private static void showPrimitiveRanges() {
        System.out.println("Primitive types and their ranges:");
        System.out.println("Byte: " + Byte.MIN_VALUE + " to " + Byte.MAX_VALUE);
        System.out.println("Short: " + Short.MIN_VALUE + " to " + Short.MAX_VALUE);
        System.out.println("Int: " + Integer.MIN_VALUE + " to " + Integer.MAX_VALUE);
        System.out.println("Long: " + Long.MIN_VALUE + " to " + Long.MAX_VALUE);
        System.out.println("Float: " + Float.MIN_VALUE + " to " + Float.MAX_VALUE);
        System.out.println("Double: " + Double.MIN_VALUE + " to " + Double.MAX_VALUE);
    }

    /*
     * Task: Create a reference variable, assign `null`, and try using it → show
     * `NullPointerException`.
     */
    @SuppressWarnings("null")
    private static void demonstrateNullBehavior() {
        Object object = new Object();
        System.out.println("The Object has instance" + object.toString());
        object = null;
        System.out.println("After this if object is used it will throw exception");
        try {
            System.out.println("The Object has instance" + object.toString());
        } catch (NullPointerException e) {
            System.out.println("Exception occur");
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
        System.out.println("Arithmetic Operation with " + 10 + " & " + 30);
        System.out.println(" 10 + 30 = " + (10 + 30) +
                ",\n 10 - 30 = " + (10 - 30) +
                ",\n 10 * 30 = " + (10 * 30) +
                ",\n 10 / 30 = " + (10 / 30) +
                ",\n 10 % 30 = " + (10 % 30));

        System.out.println("Comparison Operation with " + 10 + " & " + 30);
        System.out.println(" 10 == 30 = " + (10 == 30) +
                ",\n 10 != 30 = " + (10 != 30) +
                ",\n 10 > 30 = " + (10 > 30) +
                ",\n 10 < 30 = " + (10 < 30) +
                ",\n 10 >= 30 = " + (10 >= 30) +
                ",\n 10 <= 30 = " + (10 <= 30));

        System.out.println("Logical Operation with " + false + " & " + true);
        System.out.println(" And should give true if both one are true, true && true " + (true && true) +
                ",\n Or should give true if any one is true, false || true = " + (false || true) +
                ",\n Reverse the false,!false = " + (!false));

        System.out.println("Bitwise Operation with " + 10 + " & " + 30);
        System.out.println(" 10 & 0 = " + (10 & 0) + ",\n 10 | 30 = " + (10 | 30) + ",\n 10 ^ 30 = " + (10 ^ 30)
                + ",\n ~ 10 = " + (~10) + ",\n 10 << 30 = " + (10 << 30));
    }

}