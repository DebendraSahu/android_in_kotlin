package first_archive.task_g;

public class DuckNumber {
    public static void main(String[] args) {
        String num = "00000";
        System.out.println(num + " is DuckNumber " + checkDuckNumber(num));

    }


    private static boolean checkDuckNumber(String num) {
        if (num == null || num.trim().isEmpty()) return false;

        int i = 0, n = num.length();

        while (i < n && num.charAt(i) == '0') i++;

        // Check remaining digits
        while (i < n) {
            if (num.charAt(i) == '0') return true;
            i++;
        }
        return false;
    }

}
