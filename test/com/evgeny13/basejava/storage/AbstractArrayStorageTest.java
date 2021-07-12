package com.evgeny13.basejava.storage;

import com.evgeny13.basejava.exception.ExistStorageException;
import com.evgeny13.basejava.exception.NotExistStorageException;
import com.evgeny13.basejava.exception.StorageException;
import com.evgeny13.basejava.model.Resume;
import org.junit.Before;
import org.junit.Test;

import static com.evgeny13.basejava.storage.AbstractArrayStorage.STORAGE_LIMIT;
import static org.junit.Assert.assertEquals;


public abstract class AbstractArrayStorageTest {
    private Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";           // uuid for test Resume

    private static final Resume R_1 = new Resume(UUID_1);
    private static final Resume R_2 = new Resume(UUID_2);
    private static final Resume R_3 = new Resume(UUID_3);
    private static final Resume R_4 = new Resume(UUID_4);    // test Resume (for some test methods)

    protected AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(R_1);
        storage.save(R_2);
        storage.save(R_3);
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    public void update() throws Exception {
        Resume r1Update = new Resume(UUID_1);
        storage.update(r1Update);
        assertEquals(r1Update, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateWithNotExistStorageException() throws Exception {
        storage.update(R_4);
    }

    @Test
    public void save() throws Exception {
        storage.save(R_4);
        assertEquals(4, storage.size());
    }

    @Test(expected = ExistStorageException.class)
    public void saveWithExistStorageException() throws Exception {
        storage.save(R_1);
    }

    @Test(expected = StorageException.class)
    public void saveWithStorageException() throws Exception {
        storage.save(R_4);

        for (int i = 5; i < STORAGE_LIMIT + 2; i++) {
            storage.save(new Resume("uuid" + i));
        }
    }

    @Test
    public void get() throws Exception {
        assertEquals(R_3, storage.get("3"));
    }

    @Test(expected = NotExistStorageException.class)
    public void getWithNotExistStorageException() throws Exception {
        storage.get("dummy");
    }

    @Test
    public void delete() throws Exception {
        storage.delete("3");
        assertEquals(2, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteWithNotExistStorageException() throws Exception {
        storage.delete("dummy");
    }

    @Test
    public void getAll() throws Exception {
        assertEquals(3, storage.getAll().length);
    }

    @Test
    public void size() throws Exception {
        assertEquals(3, storage.size());
    }
}