import java.util.Arrays;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.stream.Collectors;

// аналогия параметров метода с ключом от дома,
// внешняя переменная не меняется, мы внутри можем менять этот ключ,
// потому что это копия оригинального ключа

// Для примитивов изменить исходную переменную внутри метода невозможно.
// Для объектов можно изменить их внутреннее состояние,
// но нельзя переназначить исходную ссылку.

//List<Integer> immutableList = List.of(4, 2, 7); // Нельзя менять вообще
//List<Integer> mutableList = new ArrayList<>(List.of(4, 2, 7)); // Можно менять
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class Main {
    public static TreeNode invertTree(TreeNode root) {
        Deque<TreeNode> queue = new ArrayDeque<>();
        if (root != null) {
            queue.offerLast(root);
        }
        while (!queue.isEmpty()) {
            TreeNode cur = queue.pollFirst();
            if (cur.left == null && cur.right == null) {
                continue;
            }
            if (cur.left != null) {
                queue.offerLast(cur.left);
            }
            if (cur.right != null) {
                queue.offerLast(cur.right);
            }
            TreeNode temp = cur.left;
            cur.left = cur.right;
            cur.right = temp;
        }
        return root;
    }

    public static TreeNode buildTree(Integer[] values) {
        if (values == null || values.length == 0 || values[0] == null) return null;

        TreeNode root = new TreeNode(values[0]);
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offerLast(root);

        int i = 1;
        while (!queue.isEmpty() && i < values.length) {
            TreeNode current = queue.pollFirst();

            if (i < values.length && values[i] != null) {
                current.left = new TreeNode(values[i]);
                queue.offerLast(current.left);
            }
            i++;

            if (i < values.length && values[i] != null) {
                current.right = new TreeNode(values[i]);
                queue.offerLast(current.right);
            }
            i++;
        }
        return root;
    }

    public static void printTree(TreeNode root) {
        if (root == null) {
            System.out.println("[]");
            return;
        }

        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offerLast(root);
        System.out.print("[");

        while (!queue.isEmpty()) {
            TreeNode current = queue.pollFirst();
            if (current == null) {
                System.out.print("null, ");
                continue;
            }

            System.out.print(current.val + ", ");
            if (current.left != null) {
                queue.offerLast(current.left);
            }
            if (current.right != null) {
                queue.offerLast(current.right);
            }
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
//        Integer[] treeValues = {4, 2, 7, 1, 3, 6, 9};
        Integer[] treeValues = {4, 2};
        TreeNode root = buildTree(treeValues);


        System.out.println("Исходное дерево:");
        printTree(root);

        TreeNode invertedRoot = invertTree(root);

        System.out.println("Инвертированное дерево:");
        printTree(invertedRoot);
    }
}