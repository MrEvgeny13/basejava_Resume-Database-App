package com.evgeny13.basejava.storage;

import com.evgeny13.basejava.exception.ExistStorageException;
import com.evgeny13.basejava.exception.NotExistStorageException;
import com.evgeny13.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected abstract Object getIndex(String uuid);

    protected abstract boolean isExistResume(Object resume);

    protected abstract void updateResume(Resume r, Object resume);

    protected abstract void saveResume(Resume r, Object resume);

    protected abstract Resume getResume(Object resume);

    protected abstract void deleteResume(Object resume);

    public void update(Resume r) {
        Object resume = getExistedSearchIndex(r.getUuid());
        updateResume(r, resume);
    }

    public void save(Resume r) {
        Object resume = getNotExistedSearchIndex(r.getUuid());
        saveResume(r, resume);
    }

    public Resume get(String uuid) {
        Object resume = getExistedSearchIndex(uuid);
        return getResume(resume);
    }

    public void delete(String uuid) {
        Object searchKey = getExistedSearchIndex(uuid);
        deleteResume(searchKey);
    }

    private Object getExistedSearchIndex(String uuid) {
        Object searchIndex = getIndex(uuid);

        if (!isExistResume(searchIndex)) {
            throw new NotExistStorageException(uuid);
        }

        return searchIndex;
    }

    private Object getNotExistedSearchIndex(String uuid) {
        Object searchIndex = getIndex(uuid);

        if (isExistResume(searchIndex)) {
            throw new ExistStorageException(uuid);
        }

        return searchIndex;
    }
}
