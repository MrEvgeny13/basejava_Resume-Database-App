package com.evgeny13.basejava.storage;

import com.evgeny13.basejava.Config;
import com.evgeny13.basejava.exception.ExistStorageException;
import com.evgeny13.basejava.exception.NotExistStorageException;
import com.evgeny13.basejava.model.Resume;
import com.evgeny13.basejava.model.ResumeTestData;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.assertEquals;

public abstract class AbstractStorageTest {
    protected static final File STORAGE_DIR = Config.get().getStorageDir();
    protected final Storage storage;

    private final Resume RESUME_1 = ResumeTestData.createResume("uuid1", "Иванов Иван");
    private final Resume RESUME_2 = ResumeTestData.createResume("uuid2", "Петров Петр");
    private final Resume RESUME_3 = ResumeTestData.createResume("uuid3", "Сидоров Михаил");
    private final Resume RESUME_WITH_SAME_UUID = ResumeTestData.createResume("uuid1", "Иванов Иван");
    private final Resume RESUME_WITH_SAME_NAME = ResumeTestData.createResume("uuid5", "Петров Петр");

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(RESUME_2);
        storage.save(RESUME_1);
        storage.save(RESUME_3);
    }

    @Test
    public void clearTest() {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    public void saveNewResumeTest() {
        storage.clear();
        storage.save(RESUME_1);
        assertEquals(1, storage.size());
        assertEquals(RESUME_1, storage.get(RESUME_1.getUuid()));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExistResumeTest() {
        storage.save(RESUME_1);
    }

    @Test
    public void getExistResumeTest() {
        assertEquals(RESUME_1, storage.get(RESUME_1.getUuid()));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExistResumeTest() {
        storage.get("Dummy");
    }

    @Test
    public void updateExistResumeTest() {
        storage.update(RESUME_WITH_SAME_UUID);
        assertEquals(RESUME_WITH_SAME_UUID, storage.get(RESUME_WITH_SAME_UUID.getUuid()));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExistResumeTest() {
        storage.update(new Resume(""));
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteExistResumeTest() {
        storage.delete(RESUME_2.getUuid());
        assertEquals(2, storage.size());
        storage.get(RESUME_2.getUuid());
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExistResumeTest() {
        storage.delete(new Resume("").getUuid());
    }

    @Test
    public void getAllTest() {
        List<Resume> resumes = Arrays.asList(RESUME_1, RESUME_2, RESUME_3, RESUME_WITH_SAME_NAME);
        resumes.sort(Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid));
        storage.save(RESUME_WITH_SAME_NAME);
        assertEquals(resumes, storage.getAllSorted());
    }
}