package com.evgeny13.basejava.storage;

import com.evgeny13.basejava.model.Resume;

import java.util.Arrays;
import java.util.Comparator;

public class SortedArrayStorage extends AbstractArrayStorage {
    /*
    private static class ResumeComparator implements Comparator<Resume> {
        @Override
        public int compare(Resume o1, Resume o2) {
            return o1.getUuid().compareTo(o2.getUuid());
        }
    }
*/

    private static final Comparator<Resume> RESUME_COMPARATOR = (o1, o2) -> o1.getUuid().compareTo(o2.getUuid());

    @Override
    protected void saveToArray(Resume r, int index) {
        int newIndex = -index - 1;
        int remainingElements = size - newIndex;
        System.arraycopy(storage, newIndex, storage, newIndex + 1, remainingElements);
        storage[newIndex] = r;
    }

    @Override
    protected void fillDeletedElement(int index) {
        int remainingElements = size - (index + 1);
        System.arraycopy(storage, index + 1, storage, index, remainingElements);
    }

    @Override
    protected Integer searchKey(String uuid) {
        Resume searchKey = new Resume(uuid, "name");
        return Arrays.binarySearch(storage, 0, size, searchKey, RESUME_COMPARATOR);
    }
}