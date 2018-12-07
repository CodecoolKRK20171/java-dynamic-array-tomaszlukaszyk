package com.codecool.dynamicArrayDojo;

import java.util.Arrays;

public class BinarySearchTree<T extends Comparable<T>> {

    private TreeNode<T> root;

    public BinarySearchTree(T[] data) {
        this.root = buildFromArray(data, null);
    }

    private TreeNode<T> buildFromArray(T[] array, TreeNode<T> parent) {
        if (array.length <= 0) return null;

        int middleIndex = (int) Math.floor(array.length /2);
        TreeNode<T> current = new TreeNode<>(array[middleIndex]);
        current.setParent(parent);
        current.setLeft(buildFromArray(Arrays.copyOfRange(array, 0, middleIndex), current));

        if (array.length > 2) {
            current.setRight(buildFromArray(Arrays.copyOfRange(array, middleIndex+1, array.length), current));
        }

        return current;
    }

    public boolean contains(T data) {
        TreeNode<T> current = getNodeByValue(data);
        return current != null;
    }

    private TreeNode<T> getNodeByValue(T value) {
        TreeNode<T> current = this.root;

        while (current != null) {
            if (current.getData().equals(value)) return current;

            if (current.getData().compareTo(value) > 0) {
                current = current.getLeft();
            } else {
                current = current.getRight();
            }
        }
        return null;
    }

    public void add(T data) {
        TreeNode<T> current = this.root;

        while (true) {
            if (current.getData().equals(data)) throw new IllegalArgumentException("Element exists in tree");

            if (current.getData().compareTo(data) > 0) {
                if (current.getLeft() != null) {
                    current = current.getLeft();
                } else {
                    TreeNode<T> newNode = new TreeNode<>(data);
                    newNode.setParent(current);
                    current.setLeft(newNode);
                    return;
                }
            } else {
                if (current.getRight() != null) {
                    current = current.getRight();
                } else {
                    TreeNode<T> newNode = new TreeNode<>(data);
                    newNode.setParent(current);
                    current.setRight(newNode);
                    return;
                }
            }
        }
    }

    public void remove(T data) {
        TreeNode<T> toRemove = getNodeByValue(data);

        if (toRemove == null) throw new IllegalArgumentException("Element does not exist in tree");

        removeNode(toRemove);
    }

    private void removeNode(TreeNode<T> toRemove) {
        if (toRemove.isLeaf()) {
            if (toRemove.getParent().getLeft() == toRemove) {
                toRemove.getParent().setLeft(null);
            } else {
                toRemove.getParent().setRight(null);
            }

        } else if (toRemove.hasSingleChild()) {
            TreeNode<T> child = getSingleChild(toRemove);
            toRemove.setData(child.getData());
            removeNode(child);

        } else {
            TreeNode<T> successor = getSuccessor(toRemove);
            toRemove.setData(successor.getData());
            removeNode(successor);
        }
    }

    private TreeNode<T> getSingleChild(TreeNode<T> node) {
        return node.getLeft() != null ? node.getLeft() : node.getRight();
    }

    private TreeNode<T> getSuccessor(TreeNode<T> node) {
        if (node.getRight() != null) return findMin(node.getRight());

        while (node != null && node.getParent().getRight() == node) {
            node = node.getParent();
        }

        return node;
    }

    private TreeNode<T> findMin(TreeNode<T> node) {
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
    }
}
