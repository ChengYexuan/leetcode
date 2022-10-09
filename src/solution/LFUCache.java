package solution;

import java.util.HashMap;

public class LFUCache {

    private static class Node {
        int key, val, cnt;
        Node prev, next;

        Node(int _key, int _val) {
            key = _key;
            val = _val;
            cnt = 1;
        }

        Node(int _key, int _val, int _cnt) {
            key = _key;
            val = _val;
            cnt = _cnt;
        }
    }

    private static class DLinkedList {
        private Node header, tail;

        public DLinkedList() {
            header = new Node(0, 0,-1);
            tail = new Node(0, 0, -1);
            header.next = tail;
            tail.prev = header;
        }

        private void addAfter(Node position, Node target) {
            position.next.prev = target;
            target.next = position.next;
            position.next = target;
            target.prev = position;
        }

        private void delete(Node position) {
            position.prev.next = position.next;
            position.next.prev = position.prev;
        }

        private void deleteFirst() {
            header.next.next.prev = header;
            header.next = header.next.next;
        }
    }

    HashMap<Integer, Node> map;
    HashMap<Integer, Node> cntMap;
    int capacity;
    DLinkedList cache;

    public LFUCache() {
    }

    public LFUCache(int _capacity) {
        capacity = _capacity;
        map = new HashMap<>();
        cntMap = new HashMap<>();
        cache = new DLinkedList();
    }

    public int get(int key) {
        if (map.isEmpty() || !map.containsKey(key)) {
            return -1;
        } else {
            Node n = map.get(key);
            makeLatestAmongCnt(n);
            return map.get(key).val;
        }
    }

    public void put(int key, int value) {
        if (map.isEmpty() || !map.containsKey(key)) {
            if(capacity == 0){
                return;
            }
            Node n = new Node(key, value, 0);
            if (map.size() == capacity) {
                // map: remove the first node's key
                map.remove(cache.header.next.key);
                // cntMap: also remove if it's in cntMap
                if(cntMap.get(cache.header.next.cnt) == cache.header.next) {
                    cntMap.remove(cache.header.next.cnt);
                }
                // cache: delete the first node
                cache.deleteFirst();
            }
            // map: add
            map.put(key, n);
            // cache: add
            cache.addAfter(cache.header, n);
            // cntMap: add
            cntMap.put(0, n);
            makeLatestAmongCnt(n);
        } else {
            Node n = map.get(key);
            n.val = value;
            makeLatestAmongCnt(n);
        }
    }

    /**
     * Move the target in cache.
     * @param target the old node before (cnt + 1)
     */
    private void makeLatestAmongCnt(Node target) {
        int cnt = target.cnt + 1;
        // new a node
        Node newNode = new Node(target.key, target.val, cnt);
        int old = cnt;
        if (!cntMap.containsKey(cnt)) {
            cnt--;
        }
        // cache: add the old node
        cache.addAfter(cntMap.get(cnt), newNode);
        // cntMap: replace or add the new node
        if(cntMap.get(old - 1) == target){
            if(target.prev.cnt == old - 1){
                cntMap.put(old - 1, target.prev);
            } else {
                cntMap.remove(old - 1);
            }
        }
        cntMap.put(old, newNode);
        // cache: delete the old node
        cache.delete(target);
        // map: replace the target with new node
        map.put(newNode.key, newNode);
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
