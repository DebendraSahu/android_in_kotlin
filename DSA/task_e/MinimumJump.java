package task_e;

public class MinimumJump {
    static int minJumps(int[] arr) {
        int currentJumpEnd = 0;
        int newJump = 0;
        int jumpCount = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            newJump = Math.max(newJump, i + arr[i]);
            if (currentJumpEnd == i) {
                jumpCount++;
                currentJumpEnd = newJump;
            }
        }
        return jumpCount;
    }


    public static void main(String[] args) {
//        int[] arr = {1, 3, 5, 8, 0, 2, 6, 7, 6, 8, 9};
        int[] arr = {2, 3, 1, 1, 4};//{1, 3, 5, 8, 0, 2, 6, 7, 6, 8, 9};
        int minJumps = minJumps(arr);
        System.out.println("Minimum jumps : " + minJumps);
    }
}
