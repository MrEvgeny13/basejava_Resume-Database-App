package com.evgeny13.basejava.storage;

import com.evgeny13.basejava.model.Resume;

import java.util.*;

/**
 * Map based storage for Resumes
 */
public class MapStorageUuid extends AbstractStorage<String> {

    private Map<String, Resume> storage = new HashMap<>();

    protected void updateResume(Resume r, String key) {
        storage.put(key, r);
    }

    protected void saveResume(Resume r, String key) {
        storage.put(key, r);
    }

    protected Resume getResume(String key) {
        return storage.get(key);
    }

    protected void deleteResume(String key) {
        storage.remove(key);
    }

    public void clear() {
        storage.clear();
    }

    public List<Resume> getAll() {
        return new ArrayList<>(storage.values());
    }

    public int size() {
        return storage.size();
    }

    protected String searchKey(String uuid) {
        return uuid;
    }

    protected boolean isExist(String searchKey) {
        return storage.containsKey(searchKey);
    }
}