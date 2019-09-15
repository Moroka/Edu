package edu.binarytree;

import java.util.*;

public final class BinaryTreeHelper {
    public static String treeToString(BinaryTreeNode node) {
        return treeToStringRecursive(node, "");
    }

    public static void visualizeTree(BinaryTreeNode node) {
        int depth = treeDepth(node);
        ArrayList<BinaryTreeNode> currentLevel = new ArrayList<>();
        ArrayList<BinaryTreeNode> nextLevel = new ArrayList<>();
        currentLevel.add(node);

        while (!currentLevel.isEmpty()) {
            for (BinaryTreeNode currentLevelNode : currentLevel) {
                final String currentSeparator = separator(depth + 1);
                if (currentLevelNode != null) {
                    nextLevel.add(currentLevelNode.getLeftNode());
                    nextLevel.add(currentLevelNode.getRightNode());
                }
                System.out.print(currentSeparator + (currentLevelNode != null ? currentLevelNode.getValue() : " ") + currentSeparator);
            }
            System.out.println();
            depth /= 2;
            currentLevel = nextLevel;
            nextLevel = new ArrayList<>();
        }
    }

    public static BinaryTreeNode generateTree(double desireToNullSpeed) {
        return generateTreeRecursive(1d, desireToNullSpeed);
    }

    public static boolean hasEqualSubtrees(BinaryTreeNode tree) {
        ArrayList<BinaryTreeNode> treeNodes = getTreeNodes(tree);
        return hasEqualSubtrees(treeNodes);
    }

    private static ArrayList<BinaryTreeNode> getTreeNodes(BinaryTreeNode tree) {
        return getTreeNodesRecursive(tree, new ArrayList<>());
    }

    private static ArrayList<BinaryTreeNode> getTreeNodesRecursive(BinaryTreeNode tree, ArrayList<BinaryTreeNode> treeNodes) {
        if (tree == null) {
            return treeNodes;
        }

        treeNodes.add(tree);

        treeNodes = getTreeNodesRecursive(tree.getLeftNode(), treeNodes);
        treeNodes = getTreeNodesRecursive(tree.getRightNode(), treeNodes);
        return treeNodes;
    }

    private static boolean hasEqualSubtrees(List<BinaryTreeNode> treeNodes) {
        HashMap<String, Integer> storage = new HashMap<>();

        for (BinaryTreeNode node : treeNodes) {
            final String str = treeUniqueChars(node);
            final Integer value = storage.get(str);
            if (value == null)
                storage.put(str, 1);
            else
                return true;
        }

        return false;
    }

    private static String treeUniqueChars(BinaryTreeNode tree) {
        String result = treeUniqueCharsRecursive(tree, "");
        char[] tempArray = result.toCharArray();
        Arrays.sort(tempArray);

        return new String(tempArray);
    }

    private static String treeUniqueCharsRecursive(BinaryTreeNode tree, String result) {
        if (tree == null) {
            return result;
        }

        if (result.indexOf(tree.getValue()) == -1)
            result += (tree.getValue());

        result = treeUniqueCharsRecursive(tree.getLeftNode(), result);
        result = treeUniqueCharsRecursive(tree.getRightNode(), result);
        return result;
    }

    private static BinaryTreeNode generateTreeRecursive(double chance, double desireToNullSpeed) {
        final Random random = new Random();
        char c = generateChar(chance, random);

        if (c == '_')
            return null;

        final BinaryTreeNode leftNode = generateTreeRecursive(chance - desireToNullSpeed, desireToNullSpeed);
        final BinaryTreeNode rightNode = generateTreeRecursive(chance - desireToNullSpeed, desireToNullSpeed);

        return new BinaryTreeNode(c, leftNode, rightNode);
    }

    private static char generateChar(double chance, Random random) {
        if (random.nextDouble() <= chance) {
            return (char) ('a' + random.nextInt(26));
        } else
            return '_';
    }

    private static String treeToStringRecursive(BinaryTreeNode tree, String result) {
        if (tree == null) {
            result += "_";
            return result;
        }

        result += tree.getValue();
        result = treeToStringRecursive(tree.getLeftNode(), result);
        result = treeToStringRecursive(tree.getRightNode(), result);
        return result;
    }

    private static int treeDepth(BinaryTreeNode tree) {
        return tree == null ? 0 : 1 + Math.max(treeDepth(tree.getLeftNode()), treeDepth(tree.getRightNode()));
    }

    private static String separator(int n) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            sb.append("  ");
        }
        return sb.toString();
    }
}
