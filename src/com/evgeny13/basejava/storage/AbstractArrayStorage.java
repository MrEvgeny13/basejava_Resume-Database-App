package com.evgeny13.basejava.storage;

import com.evgeny13.basejava.exception.StorageException;
import com.evgeny13.basejava.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage {

    protected static final int STORAGE_LIMIT = 10_000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];

    protected int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    protected void updateResume(Resume r, Object resume) {
        storage[(Integer) resume] = r;
    }

    @Override
    protected void saveResume(Resume r, Object resume) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", r.getUuid());
        } else {
            insertElement(r, (Integer) resume);
            size++;
        }
    }

    @Override
    public Resume getResume(Object resume) {
        return storage[(Integer) resume];
    }

    @Override
    public void deleteResume(Object resume) {
        fillDeletedElement((Integer) resume);
        storage[size - 1] = null;
        size--;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public int size() {
        return size;
    }

    @Override
    public boolean isExistResume(Object resume) {
        return (Integer) resume >= 0;
    }

    protected abstract Integer getIndex(String uuid);

    protected abstract void fillDeletedElement(int index);

    protected abstract void insertElement(Resume r, int index);
}