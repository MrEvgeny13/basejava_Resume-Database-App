package com.evgeny13.basejava.storage;

import com.evgeny13.basejava.Config;

public class SqlStorageTest extends AbstractStorageTest {

    public SqlStorageTest() {
        super(new SqlStorage(Config.get().getProperties().getProperty("db.url")
                , Config.get().getProperties().getProperty("db.user")
                , Config.get().getProperties().getProperty("db.password")));
    }
}
