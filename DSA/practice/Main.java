package practice;

import practice.phase_a.Phase_A;
import practice.phase_b.Phase_B;

import java.util.HashMap;
import java.util.Scanner;
import static practice.utility.Printer.*;

public class Main {
    private static final HashMap<String, Runnable> practiceTable = new HashMap<>();

    static {
        practiceTable.put("A", Phase_A::run);
        practiceTable.put("B", Phase_B::run);
    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            runPractice(sc);
        }
    }

    private static void showMenu() {
        clearConsole();
        printHeader();
        print(YELLOW, "\nChoose Practice phase to Run:");
        practiceTable.keySet().forEach(phase -> print(CYAN, "[" + phase + "] Phase " + phase));

        print(RED, "[Q] Quit");
        print(GREEN, "Enter your choice: " + practiceTable.keySet() + " or [Q]: ");
    }

    private static void runPhase(String phase) {
        print(GREEN, "Running Phase: " + phase + "....");
        long start = System.currentTimeMillis();
        practiceTable.get(phase).run();
        long end = System.currentTimeMillis();
        print(YELLOW, "Duration: " + (end - start) + " ms\n");
    }

    private static void runPractice(Scanner sc) {
        while (true) {
            showMenu();
            String input = "";
            try {
                input = sc.nextLine().trim().toUpperCase();
            } catch (Exception e) {
                break;
            }
            if (input.equals("Q")) {
                break;
            } else if (practiceTable.containsKey(input)) {
                runPhase(input);
            } else if (!input.isEmpty()) {
                print(RED, "Invalid choice! Please enter valid option.");
            } else {
                continue;
            }
            print(YELLOW, "═══════════════════════════════════════");
            print(CYAN, "\nPress ENTER to continue...");
            try {
                sc.nextLine(); // pause before next menu
            } catch (Exception e) {
                break;
            }
        }
    }

    private static void printHeader() {
        print(GREEN, "╔══════════════════════════════════════╗");
        print(GREEN, "║             DSA Practice             ║");
        print(GREEN, "╚══════════════════════════════════════╝");
    }

    private static void clearConsole() {
        // ANSI escape code to clear screen and move cursor to top-left
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
