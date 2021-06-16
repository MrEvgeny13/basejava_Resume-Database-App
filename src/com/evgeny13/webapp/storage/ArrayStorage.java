package com.evgeny13.webapp.storage;

import com.evgeny13.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    public void update(Resume r) {
        for (int i = 0; i < size; i++) {
            if (storage[i].toString().equals(r.toString())) {
                storage[i] = r;
                return;
            }
        }
        System.out.println("Update failed: resume with uuid" + r.toString() + " was not found in the storage");
    }

    public void save(Resume r) {
        if (size == 10000) {
            System.out.println("Saving failed: no storage space left");
            return;
        }

        for (int i = 0; i < size; i++) {
            if (storage[i].toString().equals(r.toString())) {
                System.out.println("Saving failed: resume with uuid" + r.toString() + " is already in the storage");
                return;
            }
        }

        storage[size] = r;
        size++;
    }

    public Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].toString().equals(uuid)) {
                return storage[i];
            }
        }
        System.out.println("Requested resume with uuid" + uuid + " was not found in the storage");
        return null;
    }

    public void delete(String uuid) {
        /**
         * Deleting the requested element
         */
        int indexOfNullResume = 10000;     // the original value must be different from any index in storage
        for (int i = 0; i < size; i++) {
            if (storage[i].toString().equals(uuid)) {
                storage[i] = null;
                indexOfNullResume = i;     // the sequence number where null was formed
                break;
            }
        }

        /**
         * If indexOfNullResume has retained the original value (i.e. there's no requested uuid,
         * and the deletion of the element with the assignment of the deleted index wasn't performed)
         */
        if (indexOfNullResume == 10000) {
            System.out.println("Deletion failed: requested resume with uuid" + uuid + " was not found in the storage");
            return;
        }

        size--;

        /**
         * Eliminating null between resumes
         */
        System.arraycopy(storage, indexOfNullResume + 1, storage, indexOfNullResume, storage.length - indexOfNullResume - 1);
    }

    /**
     * @return array, contains only Resumes in storage (without null)
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
