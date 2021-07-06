package com.evgeny13.webapp.storage;

import com.evgeny13.webapp.model.Resume;

import java.util.Arrays;
import java.util.Vector;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);

        // before Arrays.binarySearch we need to get sorted storage
        createSorted(storage, size);

        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    /**
     * Source: https://www.geeksforgeeks.org/create-a-sorted-array-using-binary-search/
     */
    private void createSorted(Resume[] a, int n) {
        // Auxiliary Array
        Vector<Resume> b = new Vector<>();

        for (int j = 0; j < n; j++) {
            // if b is empty any element can be at
            // first place
            if (b.isEmpty())
                b.add(a[j]);
            else {
                // Perform Binary Search to find the correct
                // position of current element in the
                // new array
                int start = 0, end = b.size() - 1;

                // let the element should be at first index
                int pos = 0;

                while (start <= end) {

                    int mid = start + (end - start) / 2;

                    // if a[j] is already present in the new array
                    if (b.get(mid).equals(a[j])) {
                        // add a[j] at mid+1. you can add it at mid
                        b.add((Math.max(0, mid + 1)), a[j]);
                        break;
                    }

                    // if a[j]'s uuid is lesser than b[mid]'s uuid, go right side
                    else if (Integer.parseInt(b.get(mid).getUuid()) > Integer.parseInt(a[j].getUuid()))
                        // means pos should be between start and mid-1
                        pos = end = mid - 1;
                    else
                        // else pos should be between mid+1 and end
                        pos = start = mid + 1;

                    // if a[j] is the largest push it at last
                    if (start > end) {
                        pos = start;
                        b.add(Math.max(0, pos), a[j]);

                        // here max(0, pos) is used because sometimes
                        // pos can be negative as smallest duplicates
                        // can be present in the array
                        break;
                    }
                }
            }
        }
        // fill our storage from the sorted vector
        b.toArray(storage);
    }
}