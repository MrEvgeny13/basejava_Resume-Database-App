package com.evgeny13.webapp.storage;

import com.evgeny13.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {

    private static final int STORAGE_LIMIT = 10_000;

    private Resume[] storage = new Resume[STORAGE_LIMIT];
    private int size = 0;
    private int index;   // the index of the resume that matches the entered uuid

    private void findIndex(String uuid) {
        index = -1;      // starting value

        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                index = i;
                break;
            }
        }
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume r) {
        findIndex(r.getUuid());

        if (index != -1) {
            storage[index] = r;
            return;
        }

        System.out.println("Update failed: resume with uuid" + r.getUuid() + " was not found in the storage");
    }

    public void save(Resume r) {
        if (size == storage.length) {
            System.out.println("Saving failed: no storage space left");
            return;
        }

        findIndex(r.getUuid());

        if (index != -1) {
            System.out.println("Saving failed: resume with uuid" + r.getUuid() + " is already in the storage");
            return;
        }

        storage[size] = r;
        size++;
    }

    public Resume get(String uuid) {
        findIndex(uuid);

        if (index != -1) {
            return storage[index];
        }

        System.out.println("Requested resume with uuid" + uuid + " was not found in the storage");
        return null;
    }

    public void delete(String uuid) {
        findIndex(uuid);

        if (index != -1) {
            // deleting the requested resume
            storage[index] = null;
            size--;
            // eliminating null between resumes
            System.arraycopy(storage, index + 1, storage, index, size - index);
        } else {
            System.out.println("Deletion failed: requested resume with uuid" + uuid + " was not found in the storage");
        }
    }

    /**
     * @return array, contains only resumes in storage (without null)
     */
    public Resume[] getAll() {
        Resume[] allResume = new Resume[size];
        System.arraycopy(storage, 0, allResume, 0, size);
        return allResume;
    }

    public int size() {
        return size;
    }

    private int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}
