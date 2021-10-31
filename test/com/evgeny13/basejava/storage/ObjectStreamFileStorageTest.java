package com.evgeny13.basejava.storage;

public class ObjectStreamFileStorageTest extends AbstractStorageTest {

    public ObjectStreamFileStorageTest() {
        super(new FileStorage(STORAGE_FILE, new ObjectStreamSerialization()));
    }
}