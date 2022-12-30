package com.evgeny13.basejava.storage;

import com.evgeny13.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

/**
 * List based storage for Resumes
 */
public class ListStorage extends AbstractStorage<Integer> {

    private List<Resume> storage = new ArrayList<>();

    public void clear() {
        storage.clear();
    }

    protected void updateResume(Resume r, Integer index) {
        storage.set(index, r);
    }

    protected void saveResume(Resume r, Integer index) {
        storage.add(r);
    }

    protected Resume getResume(Integer index) {
        return storage.get(index);
    }

    protected void deleteResume(Integer index) {
        storage.remove(index.intValue());
    }

    public List<Resume> getAll() {
        return new ArrayList<>(storage);
    }

    public int size() {
        return storage.size();
    }

    protected Integer searchKey(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }

    protected boolean isExist(Integer searchKey) {
        return searchKey != null;
    }
}