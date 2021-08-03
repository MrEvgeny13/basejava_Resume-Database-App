package com.evgeny13.basejava.storage;

import com.evgeny13.basejava.model.Resume;

import java.util.HashMap;
import java.util.Map;

/**
 * Map based storage for Resumes
 */
public class MapStorage extends AbstractStorage {

    private Map<String, Resume> map = new HashMap<>();

    @Override
    protected String getIndex(String uuid) {
        for (Map.Entry<String, Resume> entry : map.entrySet()) {
            if (entry.getKey().equals(uuid)) {
                return entry.getKey();
            }
        }
        return null;
    }

    @Override
    protected boolean isExistResume(Object resume) {
        return resume != null;
    }

    @Override
    protected void updateResume(Resume r, Object resume) {
        map.put(((String) resume), r);
    }

    @Override
    protected void saveResume(Resume r, Object resume) {
        map.put(r.getUuid(), r);
    }

    @Override
    protected Resume getResume(Object resume) {
        return map.get((String) resume);
    }

    @Override
    protected void deleteResume(Object resume) {
        map.remove((String) resume);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Resume[] getAll() {
        return map.values().toArray(new Resume[map.size()]);
    }

    @Override
    public int size() {
        return map.size();
    }
}
