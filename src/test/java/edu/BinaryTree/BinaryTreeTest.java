package edu.BinaryTree;

import org.junit.Test;

import static org.junit.Assert.*;

public class BinaryTreeTest {
    @Test
    public void repetitiveCharsTest() {
        /*
                  a
             b          c
           d   e      g   a
                 f  i
        */
        assertFalse(BinaryTree.hasEqualSubtrees("abcdefgai"));
    }

    @Test
    public void equalSubtreesTest() {
        /*
                  a
             b          b
           d   e      d   e
                 f  i
        */
        assertFalse(BinaryTree.hasEqualSubtrees("abbdefdei"));
    }
}
