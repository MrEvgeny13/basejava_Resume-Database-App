package com.evgeny13.basejava.storage;

import com.evgeny13.basejava.storage.serializer.DataStreamSerializer;

public class DataPathStorageTest extends AbstractStorageTest {

    public DataPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new DataStreamSerializer()));
    }
}
