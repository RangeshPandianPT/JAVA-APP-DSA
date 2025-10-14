import java.util.*;

public class BinaryTreeArray {
    static final int CAP = 15;
    static Integer[] T = new Integer[CAP + 1]; // 1-based index

    // Build tree in level order
    static void buildLevelOrder(Integer[] L) {
        int n = Math.min(L.length - 1, CAP);
        for (int i = 1; i <= n; i++) {
            T[i] = (L[i] == -1) ? null : L[i];
        }
    }

    // Inorder traversal (LNR)
    static void inorder(int i) {
        if (i > CAP || T[i] == null) return;
        inorder(2 * i);
        System.out.print(T[i] + " ");
        inorder(2 * i + 1);
    }

    // Preorder traversal (NLR)
    static void preorder(int i) {
        if (i > CAP || T[i] == null) return;
        System.out.print(T[i] + " ");
        preorder(2 * i);
        preorder(2 * i + 1);
    }

    // Postorder traversal (LRN)
    static void postorder(int i) {
        if (i > CAP || T[i] == null) return;
        postorder(2 * i);
        postorder(2 * i + 1);
        System.out.print(T[i] + " ");
    }

    // Count leaf nodes
    static int leafCount(int i) {
        if (i > CAP || T[i] == null) return 0;
        if ((2 * i > CAP || T[2 * i] == null) && (2 * i + 1 > CAP || T[2 * i + 1] == null))
            return 1;
        return leafCount(2 * i) + leafCount(2 * i + 1);
    }

    // Find ancestors of a given index
    static void ancestors(int i) {
        List<Integer> list = new ArrayList<>();
        while (i >= 2) {
            i = i / 2;
            if (i <= CAP && T[i] != null)
                list.add(T[i]);
        }
        System.out.print("ANCESTORS → ");
        for (int x : list)
            System.out.print(x + " ");
        System.out.println();
    }

    // Check if index is valid (has value)
    static boolean isValid(int i) {
        return (i <= CAP && T[i] != null);
    }

    // ---------- MAIN ----------
    public static void main(String[] args) {
        // Test Case Input (Level Order)
        Integer[] L = { null, 10, 20, 30, 40, 50, -1, 70, -1, -1, 90, -1, -1, -1, -1, 100 };

        buildLevelOrder(L);

        System.out.print("IN → ");
        inorder(1);
        System.out.println();

        System.out.print("PRE → ");
        preorder(1);
        System.out.println();

        System.out.print("POST → ");
        postorder(1);
        System.out.println();

        System.out.println("LEAFCOUNT → " + leafCount(1));

        // Test Case 2
        System.out.println();
        System.out.print("ANCESTORS 11 → ");
        int i = 11;
        List<Integer> anc = new ArrayList<>();
        while (i >= 2) {
            i = i / 2;
            if (T[i] != null)
                anc.add(T[i]);
        }
        for (int x : anc) System.out.print(x + " ");
        System.out.println();

        System.out.println("ISVALID 6 → " + (isValid(6) ? "TRUE" : "FALSE"));
        System.out.println("ISVALID 12 → " + (isValid(12) ? "TRUE" : "FALSE"));
    }
}
