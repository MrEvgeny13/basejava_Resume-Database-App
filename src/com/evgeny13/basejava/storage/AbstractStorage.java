package com.evgeny13.basejava.storage;

import com.evgeny13.basejava.exception.ExistStorageException;
import com.evgeny13.basejava.exception.NotExistStorageException;
import com.evgeny13.basejava.model.Resume;

import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<SK> implements Storage {

    //    protected final Logger LOG = Logger.getLogger(getClass().getName());
    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    protected abstract void updateResume(Resume r, SK key);

    protected abstract void saveResume(Resume r, SK key);

    protected abstract Resume getResume(SK key);

    protected abstract void deleteResume(SK key);

    protected abstract SK searchKey(String uuid);

    protected abstract boolean isExist(SK searchKey);

    protected abstract List<Resume> getAll();

    public void update(Resume r) {
        LOG.info("Update " + r);
        SK key = receiveKeyIfExist(r.getUuid());
        updateResume(r, key);
    }

    public void save(Resume r) {
        LOG.info("Save " + r);
        SK key = receiveKeyIfNotExist(r.getUuid());
        saveResume(r, key);
    }

    public Resume get(String uuid) {
        LOG.info("Get " + uuid);
        SK key = receiveKeyIfExist(uuid);
        return getResume(key);
    }

    public void delete(String uuid) {
        LOG.info("Delete " + uuid);
        SK key = receiveKeyIfExist(uuid);
        deleteResume(key);
    }

    public List<Resume> getAllSorted() {
        LOG.info("getAllSorted");
        List<Resume> sortedList = getAll();
        sortedList.sort(Resume::compareTo);
        return sortedList;
    }

    private SK receiveKeyIfExist(String uuid) {
        SK searchKey = searchKey(uuid);
        if (!isExist(searchKey)) {
            LOG.warning("Resume " + uuid + " not exist");
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private SK receiveKeyIfNotExist(String uuid) {
        SK searchKey = searchKey(uuid);
        if (isExist(searchKey)) {
            LOG.warning("Resume " + uuid + " already exist");
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }
}