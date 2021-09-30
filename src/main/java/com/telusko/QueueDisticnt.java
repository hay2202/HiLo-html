package com.telusko;

import java.util.*;

public class QueueDisticnt<E> extends PriorityQueue<E>  {
    private Set<E> elements = new HashSet();

    @Override
    public boolean add(E e) {
        if (elements.contains(e)) return false;
        boolean added = super.add(e);
        if (added) elements.add(e);
        return added;
    }

    @Override
    public boolean remove(Object o) {
        boolean removed = super.remove(o);
        if (removed) elements.remove(o);
        return removed;
    }
}





