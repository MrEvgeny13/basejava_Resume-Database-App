package com.evgeny13.basejava.storage;

import com.evgeny13.basejava.exception.StorageException;
import com.evgeny13.basejava.model.Resume;
import org.junit.Assert;
import org.junit.Test;

public class AbstractArrayStorageTest extends AbstractStorageTest {

    protected AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    /*
     Only for arrays, not for lists and maps
     */
    @Test(expected = StorageException.class)
    public void saveOverflow() throws Exception {
        try {
            for (int i = 4; i <= AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume("fullName" + i));
            }
        } catch (StorageException e) {
            Assert.fail("Premature array overflow");
        }
        storage.save(new Resume("Overflow value"));
    }
}