package com.evgeny13.basejava.storage;

import com.evgeny13.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

/**
 * List based storage for Resumes
 */
public class ListStorage extends AbstractStorage {

    private List<Resume> list = new ArrayList<>();

    @Override
    protected Integer getIndex(String uuid) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUuid().equals(uuid)) {
                return i;
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
        list.set((Integer) resume, r);
    }

    @Override
    protected void saveResume(Resume r, Object resume) {
        list.add(r);
    }

    @Override
    protected Resume getResume(Object resume) {
        return list.get((Integer) resume);
    }

    @Override
    protected void deleteResume(Object resume) {
        list.remove(((Integer) resume).intValue());
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public Resume[] getAll() {
        return list.toArray(new Resume[list.size()]);
    }

    @Override
    public int size() {
        return list.size();
    }
}
