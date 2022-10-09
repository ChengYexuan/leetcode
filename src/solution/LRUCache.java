package solution;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class LRUCache {
    public int capacity;
    private HashMap<Integer, Integer> storage = new HashMap<>();
    private Queue<Integer> s = new LinkedList<>();

    public LRUCache(int _capacity) {
        capacity = _capacity;
    }

    public int get(int key) {
        if(!storage.containsKey(key)){
            return -1;
        }
//        Storage contains the key.
        lately_used(key);
        return storage.get(key);
    }

    public void put(int key, int value) {
        if(!storage.containsKey(key)) {
//        put a new key-value
            storage.put(key, value);
            if (storage.size() > capacity) {
                storage.remove(s.poll());
            }
            s.offer(key);
        } else {
//        change an old key-value
            storage.put(key, value);
            lately_used(key);
        }
    }

    private void lately_used(int key) {
        Queue<Integer> temp = new LinkedList<>();
        while(s.peek() != key){
            temp.offer(s.poll());
        }
        Integer a = s.poll();
        while(!s.isEmpty()){
            temp.offer(s.poll());
        }
        temp.offer(a);
        s = temp;
    }
}
