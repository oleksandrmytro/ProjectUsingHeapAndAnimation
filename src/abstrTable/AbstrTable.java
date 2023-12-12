package abstrTable;

import enumsTypy.eProchazeni;
import list.ListException;

import java.util.*;

public class AbstrTable<K extends Comparable<K>, V> {
    private Node<K, V> root;

    public AbstrTable() {
        root = null;
    }

    public void zrus() {
        root = null;
    }

    public boolean jePrazdny() {
        return root == null;
    }

    public V najdi(K Key) {
        Node<K, V> current = root;
        while (current != null) {
            int comparison = Key.compareTo(current.key);
            if (comparison == 0) {
                return current.value;
            } else if (comparison < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return null;
    }

    public void vloz(K key, V value) throws Exception {
        root = insertRecursively(root, key, value);
    }

    private Node<K, V> insertRecursively(Node<K, V> current, K key, V value) throws Exception {
        if (current == null) {
            return new Node<>(key, value);
        }

        int comparison = key.compareTo(current.key);
        if (comparison < 0) {
            current.left = insertRecursively(current.left, key, value);
        } else if (comparison > 0) {
            current.right = insertRecursively(current.right, key, value);
        } else {
            throw new Exception("Key already exists");
        }

        return current;
    }

    public V odeber(K key) {
        Node<K, V> odebrat = root;
        if (odebrat == null) {
            return null;
        }
        root = deleteRecursively(root, key);
        return odebrat.value;
    }

    private Node<K, V> deleteRecursively(Node<K, V> current, K key) {
        if (current == null) {
            return null;
        }

        int comparison = key.compareTo(current.key);
        if (comparison == 0) {
            if (current.left == null && current.right == null) {
                return null;
            } else if (current.right == null) {
                return current.left;
            } else if (current.left == null) {
                return current.right;
            } else {
                Node<K, V> smallestValue = findSmallestValue(current.right);
                current.key = smallestValue.key;
                current.value = smallestValue.value;
                current.right = deleteRecursively(current.right, smallestValue.key);
                return current;
            }
        } else if (comparison < 0) {
            current.left = deleteRecursively(current.left, key);
            return current;
        } else {
            current.right = deleteRecursively(current.right, key);
            return current;
        }
    }

    private Node<K, V> findSmallestValue(Node<K, V> root) {
        return root.left == null ? root : findSmallestValue(root.left);
    }


    public Iterator<V> createIterator(eProchazeni typEProchazeni) {
        switch (typEProchazeni) {
            case HLOUBKA:
                return new HloubkaProch();
            case SIRKA:
                return new SirkaProch();
            default:
                throw new IllegalArgumentException("Unexpected typ " + typEProchazeni);
        }
    }

    private class HloubkaProch implements Iterator<V> {
        private AbstrLIFO<Node<K, V>> stack = new AbstrLIFO<>();

        public HloubkaProch() {
            Node<K, V> node = root;
            while (node != null) {
                stack.vloz(node);
                node = node.left;
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.jePrazdny();
        }

        @Override
        public V next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Node<K, V> node = null;
            try {
                node = stack.odeber();
            } catch (ListException e) {
                throw new RuntimeException(e);
            }
            V result = node.value;

            Node<K, V> right = node.right;
            while (right != null) {
                stack.vloz(right);
                right = right.left;
            }
            return result;
        }
    }

    private class SirkaProch implements Iterator<V> {
        private AbstrFIFO<Node<K, V>> queue = new AbstrFIFO<>();

        public SirkaProch() {
            if (root != null) {
                queue.vloz(root);
            }
        }

        @Override
        public boolean hasNext() {
            return !queue.jePrazdny();
        }

        @Override
        public V next() {
            Node<K, V> node = null;
            try {
                node = queue.odeber();
            } catch (ListException e) {
                throw new RuntimeException(e);
            }
            if (node.left != null) {
                queue.vloz(node.left);
            }
            if (node.right != null) {
                queue.vloz(node.right);
            }
            return node.value;

        }
    }


    class Node<K extends Comparable<K>, V> {
        K key;
        V value;
        Node<K, V> left, right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
        }
    }
}

