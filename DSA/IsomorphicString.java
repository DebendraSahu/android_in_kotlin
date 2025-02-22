public class IsomorphicString {
    public static void main(String[] args) {
        System.out.println("is isomorphic :" + isIsomorphic("aac", "eeg"));
    }

    public static boolean isIsomorphic(String s, String t) {
        int[] mapping = new int[128];
        boolean[] excl = new boolean[128];
        for (int i = 0; i < s.length(); i++) {
            char sc = s.charAt(i), tc = t.charAt(i);
            int tr = mapping[sc];
            if (tr == 0) {
                if (excl[tc]) {
                    return false;
                }
                excl[tc] = true;
                mapping[sc] = 1 + tc;
            } else if (tr != 1 + tc) {
                return false;
            }
        }
        return true;
    }
}
