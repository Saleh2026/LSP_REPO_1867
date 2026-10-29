package org.howard.edu.lsp.assignment5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A reusable class representing a mathematical set of integers.
 * Sets cannot contain duplicate values. Supports standard and advanced set operations,
 * guaranteeing that operations returning an IntegerSet produce a new, independent set
 * without modifying either input set.
 *
 * @author [Saleh]
 */
public class IntegerSet {
    private final List<Integer> set;

    /**
     * Default constructor that initializes an empty IntegerSet.
     */
    public IntegerSet() {
        this.set = new ArrayList<>();
    }

    /**
     * Clears all elements from the set, leaving it empty.
     */
    public void clear() {
        set.clear();
    }

    /**
     * Returns the number of unique elements in the set.
     *
     * @return the number of elements
     */
    public int length() {
        return set.size();
    }

    /**
     * Checks if this set is equal to another IntegerSet.
     * Two sets are equal if they contain exactly the same elements, regardless of order.
     *
     * @param b the other IntegerSet to compare against
     * @return true if both sets contain identical elements; false otherwise
     */
    public boolean equals(IntegerSet b) {
        if (b == null) {
            return false;
        }
        if (this == b) {
            return true;
        }
        if (this.length() != b.length()) {
            return false;
        }
        for (int item : set) {
            if (!b.contains(item)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the set contains the specified integer value.
     *
     * @param value the integer value to check
     * @return true if the value is in the set; false otherwise
     */
    public boolean contains(int value) {
        return set.contains(value);
    }

    /**
     * Returns the largest integer value in the set.
     *
     * @return the maximum integer in the set
     * @throws IllegalStateException if the set is empty
     */
    public int largest() {
        if (isEmpty()) {
            throw new IllegalStateException("Cannot find largest element in an empty set.");
        }
        return Collections.max(set);
    }

    /**
     * Returns the smallest integer value in the set.
     *
     * @return the minimum integer in the set
     * @throws IllegalStateException if the set is empty
     */
    public int smallest() {
        if (isEmpty()) {
            throw new IllegalStateException("Cannot find smallest element in an empty set.");
        }
        return Collections.min(set);
    }

    /**
     * Adds an integer item to the set if it is not already present.
     * Duplicate values are ignored.
     *
     * @param item the integer to add
     */
    public void add(int item) {
        if (!set.contains(item)) {
            set.add(item);
        }
    }

    /**
     * Removes an integer item from the set if it is present.
     * If the item is absent, this method has no effect.
     *
     * @param item the integer to remove
     */
    public void remove(int item) {
        set.remove(Integer.valueOf(item));
    }

    /**
     * Computes the union of this set and another IntegerSet.
     * Returns a new IntegerSet containing all unique elements in either set.
     * Neither input set is modified.
     *
     * @param b the other IntegerSet
     * @return a new IntegerSet representing the union
     */
    public IntegerSet union(IntegerSet b) {
        IntegerSet result = new IntegerSet();
        for (int item : this.set) {
            result.add(item);
        }
        if (b != null) {
            for (int item : b.set) {
                result.add(item);
            }
        }
        return result;
    }

    /**
     * Computes the intersection of this set and another IntegerSet.
     * Returns a new IntegerSet containing only elements shared by both sets.
     * Neither input set is modified.
     *
     * @param b the other IntegerSet
     * @return a new IntegerSet representing the intersection
     */
    public IntegerSet intersect(IntegerSet b) {
        IntegerSet result = new IntegerSet();
        if (b != null) {
            for (int item : this.set) {
                if (b.contains(item)) {
                    result.add(item);
                }
            }
        }
        return result;
    }

    /**
     * Computes the set difference (this \ b).
     * Returns a new IntegerSet containing elements in the current set but not in b.
     * Neither input set is modified.
     *
     * @param b the other IntegerSet to subtract
     * @return a new IntegerSet representing (this \ b)
     */
    public IntegerSet diff(IntegerSet b) {
        IntegerSet result = new IntegerSet();
        for (int item : this.set) {
            if (b == null || !b.contains(item)) {
                result.add(item);
            }
        }
        return result;
    }

    /**
     * Computes the complement (b \ this) according to assignment specification.
     * Returns a new IntegerSet containing elements in b but not in the current set.
     * Neither input set is modified.
     *
     * @param b the other IntegerSet
     * @return a new IntegerSet representing (b \ this)
     */
    public IntegerSet complement(IntegerSet b) {
        IntegerSet result = new IntegerSet();
        if (b != null) {
            for (int item : b.set) {
                if (!this.contains(item)) {
                    result.add(item);
                }
            }
        }
        return result;
    }

    /**
     * Determines if this set is a subset of another IntegerSet.
     * Returns true if every element in the current set is present in b.
     * An empty set is a subset of every set.
     *
     * @param b the potential superset
     * @return true if this set is a subset of b; false otherwise
     */
    public boolean isSubsetOf(IntegerSet b) {
        if (this.isEmpty()) {
            return true;
        }
        if (b == null) {
            return false;
        }
        for (int item : this.set) {
            if (!b.contains(item)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Determines if this set and another IntegerSet are disjoint.
     * Returns true if the two sets share no common elements.
     * An empty set is disjoint from all sets.
     *
     * @param b the other IntegerSet
     * @return true if sets share no elements; false otherwise
     */
    public boolean isDisjoint(IntegerSet b) {
        if (this.isEmpty() || b == null || b.isEmpty()) {
            return true;
        }
        for (int item : this.set) {
            if (b.contains(item)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Computes the symmetric difference of this set and another IntegerSet.
     * Returns a new IntegerSet containing elements that occur in exactly one of the two sets.
     * Neither input set is modified.
     *
     * @param b the other IntegerSet
     * @return a new IntegerSet representing the symmetric difference
     */
    public IntegerSet symmetricDiff(IntegerSet b) {
        IntegerSet result = new IntegerSet();
        for (int item : this.set) {
            if (b == null || !b.contains(item)) {
                result.add(item);
            }
        }
        if (b != null) {
            for (int item : b.set) {
                if (!this.contains(item)) {
                    result.add(item);
                }
            }
        }
        return result;
    }

    /**
     * Checks if the set is empty.
     *
     * @return true if the set contains zero elements; false otherwise
     */
    public boolean isEmpty() {
        return set.isEmpty();
    }

    /**
     * Returns a string representation of the set with elements sorted in ascending numerical order,
     * separated by a comma and space, enclosed in square brackets.
     * Example: {@code [-3, 0, 8, 15]} or {@code []} for an empty set.
     *
     * @return formatted string representation of the set
     */
    @Override
    public String toString() {
        if (set.isEmpty()) {
            return "[]";
        }
        List<Integer> sorted = new ArrayList<>(set);
        Collections.sort(sorted);
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < sorted.size(); i++) {
            sb.append(sorted.get(i));
            if (i < sorted.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
