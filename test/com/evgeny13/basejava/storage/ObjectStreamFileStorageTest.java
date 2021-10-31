package com.evgeny13.basejava.storage;

import com.evgeny13.basejava.storage.serialization.ObjectStreamSerialization;

public class ObjectStreamFileStorageTest extends AbstractStorageTest {

    public ObjectStreamFileStorageTest() {
        super(new FileStorage(STORAGE_DIR, new ObjectStreamSerialization()));
    }
}