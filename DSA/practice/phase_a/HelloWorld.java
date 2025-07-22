package practice.phase_a;

public class HelloWorld {

    public static void main(String[] args) {
        System.out.println("Hello, World!");
        helloWorld();
        showPrimitiveRanges();
    }

    public static void helloWorld() {
        System.out.println("Hello World");
    }

    public static void showPrimitiveRanges() {
        System.out.println("Primitive types and their ranges:");
        System.out.println("Byte: " + Byte.MIN_VALUE + " to " + Byte.MAX_VALUE);
        System.out.println("Short: " + Short.MIN_VALUE + " to " + Short.MAX_VALUE);
        System.out.println("Int: " + Integer.MIN_VALUE + " to " + Integer.MAX_VALUE);
        System.out.println("Long: " + Long.MIN_VALUE + " to " + Long.MAX_VALUE);
        System.out.println("Float: " + Float.MIN_VALUE + " to " + Float.MAX_VALUE);
        System.out.println("Double: " + Double.MIN_VALUE + " to " + Double.MAX_VALUE);
    }

}