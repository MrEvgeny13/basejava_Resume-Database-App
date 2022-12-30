package com.evgeny13.basejava.model;

import java.util.UUID;

public class TestData {
    public static final String UUID_1 = UUID.randomUUID().toString();
    public static final String UUID_2 = UUID.randomUUID().toString();
    public static final String UUID_3 = UUID.randomUUID().toString();
    public static final String UUID_4 = UUID.randomUUID().toString();

    public static final Resume R1;
    public static final Resume R2;
    public static final Resume R3;
    public static final Resume R4;

    static {
        R1 = ResumeTestData.resumeCreation(UUID_1, "Name1");
        R2 = ResumeTestData.resumeCreation(UUID_2, "Name2");
        R3 = ResumeTestData.resumeCreation(UUID_3, "Name3");
        R4 = ResumeTestData.resumeCreation(UUID_4, "Name4");
    }
}
