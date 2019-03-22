package com.khanchych.udemy.javaindepth.collections.task.treeset;
import java.util.Comparator;

public class PubDateAscComparator implements Comparator<Book> {
    public int compare(Book o1, Book o2) {
        int cmp = Integer.compare(o1.getYear(), o1.getYear());

        if (cmp == 0) {
            cmp = o1.compareTo(o2);
        }

        return cmp;
    }


}