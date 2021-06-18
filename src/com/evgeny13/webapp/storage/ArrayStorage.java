package com.evgeny13.webapp.storage;

import com.evgeny13.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;
    private int uuidMatchIndex;   // the index of the resume that matches the entered uuid

    private void comparingStorageWithUuid(String uuid) {
        uuidMatchIndex = -1;      // starting value

        for (int i = 0; i < size; i++) {
            if (storage[i].toString().equals(uuid)) {
                uuidMatchIndex = i;
                break;
            }
        }
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume r) {
        comparingStorageWithUuid(r.toString());

        if (uuidMatchIndex != -1) {
            storage[uuidMatchIndex] = r;
            return;
        }

        System.out.println("Update failed: resume with uuid" + r + " was not found in the storage");
    }

    public void save(Resume r) {
        if (size == 10000) {
            System.out.println("Saving failed: no storage space left");
            return;
        }

        comparingStorageWithUuid(r.toString());

        if (uuidMatchIndex != -1) {
            System.out.println("Saving failed: resume with uuid" + r + " is already in the storage");
            return;
        }

        storage[size] = r;
        size++;
    }

    public Resume get(String uuid) {
        comparingStorageWithUuid(uuid);

        if (uuidMatchIndex != -1) {
            return storage[uuidMatchIndex];
        }

        System.out.println("Requested resume with uuid" + uuid + " was not found in the storage");
        return null;
    }

    public void delete(String uuid) {
        comparingStorageWithUuid(uuid);

        if (uuidMatchIndex != -1) {
            // deleting the requested resume
            storage[uuidMatchIndex] = null;
            size--;
            // eliminating null between resumes
            System.arraycopy(storage, uuidMatchIndex + 1, storage, uuidMatchIndex, storage.length - uuidMatchIndex - 1);
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
}
