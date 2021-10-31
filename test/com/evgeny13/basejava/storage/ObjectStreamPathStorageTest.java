package com.evgeny13.basejava.storage;

import com.evgeny13.basejava.storage.serialization.ObjectStreamSerialization;

public class ObjectStreamPathStorageTest extends AbstractStorageTest {

    public ObjectStreamPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new ObjectStreamSerialization()));
    }
}
