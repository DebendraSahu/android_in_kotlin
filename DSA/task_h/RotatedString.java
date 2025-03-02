package task_h;

public class RotatedString {
    public static void main(String[] args) {
        System.out.println(checkAreStringRotated("ab  cd", "cd ab  "));
        System.out.println(checkAreStringRotated("abcd", "cdab"));
    }

    private static boolean checkAreStringRotated(String s1, String s2) {
        if (s1 == null || s2 == null) return false;
        if (s1.length() != s2.length()) return false;
        String temp = s1 + s2;
        return temp.contains(s2);
    }
}
