package com.evgeny13.basejava.storage;

import com.evgeny13.basejava.exception.ExistStorageException;
import com.evgeny13.basejava.exception.NotExistStorageException;
import com.evgeny13.basejava.exception.StorageException;
import com.evgeny13.basejava.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10_000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public final void update(Resume r) {
        int index = getIndex(r.getUuid());

        if (index < 0) {
            throw new NotExistStorageException(r.getUuid());
        }

        storage[index] = r;
    }

    public final void save(Resume r) {
        int index = getIndex(r.getUuid());

        if (index >= 0) {
            throw new ExistStorageException(r.getUuid());
        }
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", r.getUuid());
        }

        insertElement(r, index);
        size++;
    }

    public final Resume get(String uuid) {
        int index = getIndex(uuid);

        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }

        return storage[index];
    }

    public final void delete(String uuid) {
        int index = getIndex(uuid);

        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }

        fillDeletedElement(index);
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

    protected abstract int getIndex(String uuid);

    protected abstract void fillDeletedElement(int index);

    protected abstract void insertElement(Resume r, int index);
}