package lab9.task1;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.SortedSet;

public class SortedListSet<T extends Comparable<T>> extends LinkedList<T> implements SortedSet<T> {
    public static void main(String[] args) {
        SortedListSet<Integer> a = new SortedListSet<>();
        a.add(12);
        a.add(12);
        a.add(1);
        a.add(6);
        for(int i=0;i<4;i++){
            System.out.println(i + ": "+a.get(i));
        }
        SortedListSet<Integer> b = (SortedListSet<Integer>) a.subSet(5,7);
        for(int i=0;i<4;i++){
            System.out.println(i + ": "+b.get(i));
        }
    }
    Comparator<T> comparator;
    SortedListSet(Comparator<T> c){
        super();
        comparator = c;
    }
    SortedListSet(){
        super();
    }
    public boolean add(T o){
        int x =0;
        for(;x<super.size();x++){
            if(super.get(x).compareTo(o)>0)break;
        }
        super.add(x,o);
        return true;
    }
    public T first(){
        return super.get(0);
    }
    public T last(){
        return super.get(super.size()-1);
    }
    @Override
    public Comparator<? super T> comparator() {
        return comparator;
    }
    @Override
    public SortedSet<T> subSet(T fromElement, T toElement) {
        int x=0,y=0;
        for(;x<super.size();x++){
            if(super.get(x).compareTo(fromElement)>=0)break;
        }
        for(;y<super.size();y++){
            if(super.get(y).compareTo(toElement)>=0)break;
        }
        return (SortedListSet<T>) super.subList(x,y);
    }
    @Override
    public SortedSet<T> headSet(T toElement) {
        int y=0;
        for(;y<super.size();y++){
            if(super.get(y).compareTo(toElement)>0)break;
        }
        if(super.indexOf(toElement)==0) {
            return new SortedListSet<>();
        }
        return (SortedListSet<T>) super.subList(0, super.indexOf(toElement)-1);
    }

    @Override
    public SortedSet<T> tailSet(T fromElement) {
        if(super.indexOf(fromElement)==super.size()-1)
            return new SortedListSet<>();
        return (SortedListSet<T>) super.subList(super.indexOf(fromElement)+1,super.size()-1);
    }
}
