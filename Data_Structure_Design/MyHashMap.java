import java.util.Arrays;

public class MyHashMap<K, V> {
    public static class Node<K, V> {
        private final K key;
        private V value;
        Node<K, V> next;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return this.key;
        }

        public V getValue() {
            return this.value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }

    private static final int DEFAULT_CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.75F;
    Node<K, V>[] array;
    private int size;
    private float loadFactor;

    @SuppressWarnings("unchecked")
    public MyHashMap() {
        this.array = new Node[DEFAULT_CAPACITY];
        this.size = 0;
        this.loadFactor = DEFAULT_LOAD_FACTOR;
    }

    @SuppressWarnings("unchecked")
    public MyHashMap(int cap, float loadFactor) {
        if (cap <= 1) {
            throw new IllegalArgumentException("cap can not be <= 1");
        } else {
            this.array = new Node[cap];
            this.size = 0;
            this.loadFactor = loadFactor;
        }
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public void clear() {
        Arrays.fill(this.array, (Object) null);
    }

    private int hash(K key) {
        return key == null ? 0 : key.hashCode() & Integer.MAX_VALUE;
    }

    private int getIndex(K key) {
        return this.hash(key) % this.array.length;
    }

    public boolean containsValue(V value) {
        if (this.isEmpty()) {
            return false;
        } else {
            Node<K, V>[] var2 = this.array;
            int var3 = var2.length;

            for (int var4 = 0; var4 < var3; ++var4) {
                for (Node<K, V> node = var2[var4]; node != null; node = node.next) {
                    if (this.equalsValue(node.value, value)) {
                        return true;
                    }
                }
            }

            return false;
        }
    }

    private boolean equalsValue(V v1, V v2) {
        if (v1 == null && v2 == null) {
            return true;
        } else if (v1 == null || v2 == null) {
            return false;
        }
        return v1.equals(v2);
    }

    public V get(K key) {
        int index = this.getIndex(key);

        Node<K, V> node = this.array[index];
        while (node != null) {
            if (this.equalsKey(node.key, key)) {
                return node.value;
            }
            node = node.next;
        }

        return null;
    }

    public boolean containsKey(K key) {
        int index = this.getIndex(key);

        for (Node<K, V> node = this.array[index]; node != null; node = node.next) {
            if (this.equalsKey(node.key, key)) {
                return true;
            }
        }

        return false;
    }

    private boolean equalsKey(K k1, K k2) {
        if (k1 == null && k2 == null) {
            return true;
        } else {
            return k1 != null && k2 != null ? k1.equals(k2) : false;
        }
    }

    public V put(K key, V value) {
        int index = this.getIndex(key);
        Node<K, V> head = this.array[index];
        Node<K, V> node = head;

        // if key exists, do update
        while (node != null) {
            if (this.equalsKey(node.key, key)) {
                V oldValue = node.value;
                node.value = value;
                return oldValue;
            }
            node = node.next;
        }

        // key is not exist, create a new node
        Node<K, V> newNode = new Node<>(key, value);
        newNode.next = head;
        this.array[index] = newNode;
        ++this.size;
        if (this.needRehashing()) {
            this.rehashing();
        }

        return null;
    }

    private boolean needRehashing() {
        float ratio = ((float) this.size + 0.0F) / (float) this.array.length;
        return ratio >= this.loadFactor;
    }

    @SuppressWarnings("unchecked")
    private void rehashing() {
        Node<K, V>[] oldArray = this.array;
        float SCALE_FACTOR = 1.5F;
        this.array = new Node[(int) ((float) this.array.length * SCALE_FACTOR)];
        Node<K, V>[] var3 = oldArray;
        int var4 = oldArray.length;

        Node<K, V> next;
        for (int var5 = 0; var5 < var4; ++var5) {
            for (Node<K, V> node = var3[var5]; node != null; node = next) {
                next = node.next;
                int index = this.getIndex(node.key);
                node.next = this.array[index];
                this.array[index] = node;
            }
        }

    }

    public V remove(K key) {
        int index = this.getIndex(key);
        Node<K, V> node = this.array[index];

        Node<K, V> pre = null;

        while (node != null) {
            if (this.equalsKey(node.key, key)) {
                if (pre != null) {
                    pre.next = node.next;
                } else {
                    this.array[index] = node.next;
                }
                node.next = null;
                size--;
                return node.value;
            }
            pre = node;
            node = node.next;
        }
        return null;
    }

}
