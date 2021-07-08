package com.evgeny13.webapp.storage;

import com.evgeny13.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    protected void fillDeletedResume(int index) {
        int movedIndexes = size - index - 1;

        if (movedIndexes > 0) {
            System.arraycopy(storage, index + 1, storage, index, movedIndexes);
        }
    }

    @Override
    protected void insertResume(Resume r, int index) {
        int insertIndex = -index - 1;

        System.arraycopy(storage, insertIndex, storage, insertIndex + 1, size - insertIndex);
        storage[insertIndex] = r;
    }
}