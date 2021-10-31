package com.evgeny13.basejava.storage;

public class ObjectStreamPathStorageTest extends AbstractStorageTest {

    public ObjectStreamPathStorageTest() {
        super(new PathStorage(STORAGE_DIR, new ObjectStreamSerialization()));
    }
}
