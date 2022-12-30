package com.evgeny13.basejava.storage;

import com.evgeny13.basejava.exception.StorageException;
import com.evgeny13.basejava.model.Resume;

import java.util.Arrays;
import java.util.List;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {
    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    protected void saveResume(Resume r, Integer index) {
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", r.getUuid());
        }
        saveToArray(r, index);
        size++;
    }

    protected abstract void saveToArray(Resume r, int index);

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    protected void updateResume(Resume r, Integer index) {
        storage[index] = r;
    }

    public List<Resume> getAll() {
        Resume[] copyArray = Arrays.copyOf(storage, size);
        return Arrays.asList(copyArray);
    }

    public int size() {
        return size;
    }

    protected Resume getResume(Integer index) {
        return storage[index];
    }

    protected void deleteResume(Integer index) {
        fillDeletedElement(index);
        storage[size - 1] = null;
        size--;
    }

    protected boolean isExist(Integer index) {
        return index > -1;
    }

    protected abstract void fillDeletedElement(int index);

    protected abstract Integer searchKey(String uuid);
}