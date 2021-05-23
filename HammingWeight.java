package test.week8;

public class HammingWeight {
    /**
     * 位运算
     * @param args
     */
    public static void main(String[] args) {


        System.out.print(hammingWeight(8));
    }

    static public int hammingWeight(int n) {
        int ret = 0;
        while (n != 0) {
            n &= n - 1;
            ret++;
        }
        return ret;
    }
}
