class MyHashMap {
    public static class Node {
        private final int key;
        private int value;
        Node next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }

        public int getKey() {
            return this.key;
        }

        public int getValue() {
            return this.value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    private static final int DEFAULT_CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.75F;
    Node[] array;
    private int size;
    private float loadFactor;

    public MyHashMap() {
        this.array = new Node[DEFAULT_CAPACITY];
        this.size = 0;
        this.loadFactor = DEFAULT_LOAD_FACTOR;
    }

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

    private int hash(int key) {
        return key & Integer.MAX_VALUE;
    }

    private int getIndex(int key) {
        return this.hash(key) % this.array.length;
    }

    public boolean containsValue(int value) {
        if (this.isEmpty()) {
            return false;
        } else {
            Node[] var2 = this.array;
            int var3 = var2.length;

            for (int var4 = 0; var4 < var3; ++var4) {
                for (Node node = var2[var4]; node != null; node = node.next) {
                    if (node.value == value) {
                        return true;
                    }
                }
            }

            return false;
        }
    }


    public int get(int key) {
        int index = this.getIndex(key);

        Node node = this.array[index];
        while (node != null) {
            if (node.key == key) {
                return node.value;
            }
            node = node.next;
        }

        return -1;
    }

    public boolean containsKey(int key) {
        int index = this.getIndex(key);

        for (Node node = this.array[index]; node != null; node = node.next) {
            if (node.key == key) {
                return true;
            }
        }

        return false;
    }


    public void put(int key, int value) {
        int index = this.getIndex(key);
        Node head = this.array[index];
        Node node = head;

        // if key exists, do update
        while (node != null) {
            if (node.key == key) {
                int oldValue = node.value;
                node.value = value;
                // return oldValue;
                return ;
            }
            node = node.next;
        }

        // key is not exist, create a new node
        Node newNode = new Node(key, value);
        newNode.next = head;
        this.array[index] = newNode;
        ++this.size;
        if (this.needRehashing()) {
            this.rehashing();
        }

        // return null;
    }

    private boolean needRehashing() {
        float ratio = ((float) this.size + 0.0F) / (float) this.array.length;
        return ratio >= this.loadFactor;
    }

    private void rehashing() {
        Node[] oldArray = this.array;
        float SCALE_FACTOR = 1.5F;
        this.array = new Node[(int) ((float) this.array.length * SCALE_FACTOR)];
        Node[] var3 = oldArray;
        int var4 = oldArray.length;

        Node next;
        for (int var5 = 0; var5 < var4; ++var5) {
            for (Node node = var3[var5]; node != null; node = next) {
                next = node.next;
                int index = this.getIndex(node.key);
                node.next = this.array[index];
                this.array[index] = node;
            }
        }

    }

    public void remove(int key) {
        int index = this.getIndex(key);
        Node node = this.array[index];

        Node pre = null;

        while (node != null) {
            if (node.key == key) {
                if (pre != null) {
                    pre.next = node.next;
                } else {
                    this.array[index] = node.next;
                }
                node.next = null;
                size--;
                // return node.value;
                return ;
            }
            pre = node;
            node = node.next;
        }
        // return null;
    }
}

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */
