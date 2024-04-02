package lab5;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

class MultiMapValue<K, V> {
    HashMap<K,List<V>> map;

    public MultiMapValue() {
        map = new HashMap<>();
    }

    public void add(K key, V value) {
        if(!map.containsKey(key)){
            ArrayList<V> x = new ArrayList<>();
            x.add(value);
            map.put(key,x);
        }else{
            map.get(key).add(value);
        }
    }

    public void addAll(K key, List<V> values) {
        if(!map.containsKey(key)){
            map.put(key,values);
        }else{
            map.get(key).addAll(values);
        }
    }

    public void addAll(MultiMapValue<K, V> map) {
        map.map.forEach((key,value) -> addAll(key,value));
    }

    public V getFirst(K key) {
        if(!map.containsKey(key)){
            return null;
        }
        return map.get(key).get(0);
    }

    public List<V> getValues(K key) {
        if(!map.containsKey(key)){
            return null;
        }
        return map.get(key);
    }

    public boolean containsKey(K key) {
        return map.containsKey(key);
    }

    public boolean isEmpty() {
        return map.isEmpty();
    }

    public List<V> remove(K key) {
        List<V> x = this.getValues(key);
        map.remove(key);
        return x;
    }

    public int size() {
        return map.size();
    }

}