import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class AllOne {
    private class Node {
        int count; // The count of occurrences for keys in this node.
        Set<String> keys; // Set of keys that have this count.
        Node prev, next; // References to the previous and next nodes in the doubly linked list.

        public Node(int count) {
            this.count = count;
            this.keys = new HashSet<>();
            this.prev = null;
            this.next = null;
        }
    }

    private Map<String, Integer> keyCount; // Maps each key to its occurrence count.
    private Map<Integer, Node> countKeys; // Maps each count to its corresponding node.
    private Node head, tail; // Head and tail of the doubly linked list.

    public AllOne() {
        keyCount = new HashMap<>();
        countKeys = new HashMap<>();
        head = null;
        tail = null;
    }

    /**
     * Inserts the newNode right after the node in the doubly linked list.
     *
     * @param node    The existing node after which the new node is to be added.
     * @param newNode The new node to be added into the list.
     */
    private void addNodeAfter(Node node, Node newNode) {
        newNode.prev = node; // Set the previous of newNode to node.
        newNode.next = node.next; // Set the next of newNode to the next of node.
        if (node.next != null) {
            node.next.prev = newNode; // Link the next node back to newNode.
        }
        node.next = newNode; // Link node to newNode.
        if (node == tail) {
            tail = newNode; // Update the tail if the node was the tail.
        }
    }

    private void removeNode(Node node) {
        if (node.prev != null) {
            node.prev.next = node.next;
        }
        if (node.next != null) {
            node.next.prev = node.prev;
        }
        if (node == head) {
            head = node.next;
        }
        if (node == tail) {
            tail = node.prev;
        }
    }

    private Node getOrCreateNode(int count, Node prevNode) {
        if (countKeys.containsKey(count)) {
            return countKeys.get(count);
        }
        Node newNode = new Node(count);
        countKeys.put(count, newNode);
        if (prevNode != null) {
            addNodeAfter(prevNode, newNode);
        } else if (head != null) {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        } else {
            head = tail = newNode;
        }
        return newNode;
    }

    public void inc(String key) {
        int currentCount = keyCount.getOrDefault(key, 0);
        int newCount = currentCount + 1;
        keyCount.put(key, newCount);

        Node newNode = getOrCreateNode(newCount, countKeys.get(currentCount));

        if (currentCount > 0) {
            Node currentNode = countKeys.get(currentCount);
            currentNode.keys.remove(key);
            if (currentNode.keys.isEmpty()) {
                removeNode(currentNode);
                countKeys.remove(currentCount);
            }
        }

        newNode.keys.add(key);
    }

    public void dec(String key) {
        if (!keyCount.containsKey(key)) {
            return;
        }

        int currentCount = keyCount.get(key);
        Node currentNode = countKeys.get(currentCount);
        int newCount = currentCount - 1;

        if (newCount > 0) {
            Node newNode = getOrCreateNode(newCount, currentNode.prev);
            newNode.keys.add(key);
            keyCount.put(key, newCount);
        } else {
            keyCount.remove(key);
        }

        currentNode.keys.remove(key);
        if (currentNode.keys.isEmpty()) {
            removeNode(currentNode);
            countKeys.remove(currentCount);
        }
    }

    public String getMaxKey() {
        return tail != null && !tail.keys.isEmpty() ? tail.keys.iterator().next() : "";
    }

    public String getMinKey() {
        return head != null && !head.keys.isEmpty() ? head.keys.iterator().next() : "";
    }

    public static void main(String[] args) {
        AllOne allOne = new AllOne();
        allOne.inc("hello");
        allOne.inc("hello");
        System.out.println(allOne.getMaxKey());  // "hello"
        System.out.println(allOne.getMinKey());  // "hello"
        allOne.inc("leet");
        System.out.println(allOne.getMaxKey());  // "hello"
        System.out.println(allOne.getMinKey());  // "leet"
    }
}

