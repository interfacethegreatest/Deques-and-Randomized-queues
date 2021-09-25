/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;


public class Permutation {
    private static Deque permutations = new Deque();

    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        while (!StdIn.isEmpty()) {
            permutations.addLast(StdIn.readString());
        }
        for (int i = 0; i < k; i++) {
            StdOut.println(permutations.removeFirst());
        }

    }
}
