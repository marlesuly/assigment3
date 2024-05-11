import java.util.Random;

public class HashTableTest {

    public static void main(String[] args) {
        // Testing MyHashTable
        MyHashTable<MyTestingClass, String> table = new MyHashTable<>();
        Random random = new Random();

        // Add 10000 random elements to the hash table
        for (int i = 0; i < 10000; i++) {
            MyTestingClass key = new MyTestingClass(random.nextInt(10000)); // Random key
            table.put(key, "Value" + i);
        }

        // Print the number of elements in each bucket
        printBucketSizes(table);

        // Testing BST
        BST<Integer, String> tree = new BST<>();

        // Add elements to the tree
        for (int i = 0; i < 10; i++) {
            tree.put(random.nextInt(100), "Value" + i);
        }

        // Iterate over the tree and print key-value pairs
        for (BST.Node node : tree) {
            System.out.println("Key is " + node.key + " and value is " + node.value);
        }
    }

    private static void printBucketSizes(MyHashTable<MyTestingClass, String> table) {
        int[] bucketSizes = new int[table.chainArray.length];
        for (int i = 0; i < table.chainArray.length; i++) {
            int count = 0;
            MyHashTable.HashNode<MyTestingClass, String> current = table.chainArray[i];
            while (current != null) {
                count++;
                current = current.next;
            }
            bucketSizes[i] = count;
        }

        // Print bucket sizes
        for (int i = 0; i < bucketSizes.length; i++) {
            System.out.println("Bucket " + i + ": " + bucketSizes[i] + " elements");
        }
    }

    private static class MyTestingClass {
        private int id;

        public MyTestingClass(int id) {
            this.id = id;
        }

        @Override
        public int hashCode() {
            // Custom hashCode implementation for uniform distribution
            return id % 1000; // Modulo 1000 to distribute hash codes evenly
        }
    }
}
