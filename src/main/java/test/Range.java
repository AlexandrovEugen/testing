package test;

import java.util.*;

/**
 * Hello world!
 *
 */
public class Range {
    private long left;
    private long right;
    int listIndex=0;

    public Range(long left, long right) {
        if(left>right){
            throw new IllegalArgumentException();
        }
        this.left = left;
        this.right = right;
    }

    public boolean isBefore(Range otherRange){
        return this.right <= otherRange.getLeft();
    }

    public boolean isAfter(Range range) {
        return this.left >= range.getRight();
    }

    public boolean isConcurrent(Range range) {
        return !(this.isBefore(range) || this.isAfter(range));
    }

    public long getLowerBound(){
        return this.left;
    }

    public long getUpperBound(){
        return this.right;
    }

    public boolean contains(long value){
        return this.left<=value && this.right>=value;
    }

    public List<Long> asList(){
        List<Long> list = new LinkedList<Long>();
        for (long i = this.left; i<=this.right; i++) {
            list.add(i);
        }
        return list;
    }

    public Iterator<Long> asIterator(){
        return new RangeIterator(this.asList());
    }

    class RangeIterator implements Iterator<Long>{

        final List<Long> rangeList;

        RangeIterator(List<Long> rangeList) {
            this.rangeList = rangeList;
        }

        public boolean hasNext() {
            return !(rangeList.size()==listIndex);
        }
        public Long next() {
            if(hasNext()){
                return rangeList.get(listIndex++);
            }
            else{
                throw new NoSuchElementException();
            }
        }
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }


    public long getLeft() {
        return left;
    }

    public long getRight() {
        return right;
    }
}
