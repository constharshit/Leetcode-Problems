class MyHashMap {
    class Node {
        int key;
        int val;
        Node next;
        public Node (int key, int value) {
            this.key = key;
            this.val = value;
        }
    }
    private Node[] storage;
   


    public MyHashMap() {
        this.storage = new Node [10000];
    }
    private int hashIdx(int key) {
        return key%10000;
    }
    private Node find(Node head,int key)
    {
        Node curr = head;
        Node prev = null;
        while (curr != null && curr.key!=key)
        {
            prev = curr;
            curr = curr.next;
        }
        return prev;
    }
    public void put(int key, int value) {
        int hash = hashIdx(key);
        if (storage[hash] == null)//putting the first if not present already as a dummy node
        {
            storage[hash] = new Node(-1,-1);
        }
        //if present then we need to find the last node and add the present node after the last node
        //or else if the key is already present then we need to update its value
        //for this we use a find function which iterates over the linked list and return last element if
        //key is not already present else it returns previous element of the already present key
        Node prev = find(storage[hash],key);
        if (prev.next == null)//this means we did not find the key in the linked list and our curr has reached null so add after prev
        {
           prev.next = new Node(key,value);
        }
        else { //we already have the key and update the value with new value.
            prev.next.val = value;
        }


    }
   
    public int get(int key) {
        int hash = hashIdx(key);
        if (storage[hash] == null)return -1;
        Node prev = find(storage[hash],key);
        if(prev.next == null)return -1;
        else {
            return prev.next.val;
        }
    }
   
    public void remove(int key) {
        int hash = hashIdx(key);
        if (storage[hash] == null)
        {
            return;
        }
        Node prev = find(storage[hash],key);
        if(prev.next == null)return;
        prev.next = prev.next.next;
    }
}


/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */
