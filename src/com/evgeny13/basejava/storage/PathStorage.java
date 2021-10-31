package com.evgeny13.basejava.storage;

import com.evgeny13.basejava.exception.StorageException;
import com.evgeny13.basejava.model.Resume;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PathStorage extends AbstractStorage<Path> {
    private final Path storage;
    private final SerializationStrategy serializationStrategy;

    public PathStorage(String dir, SerializationStrategy serializationStrategy) {
        Objects.requireNonNull(dir, "storage must not be null");
        Objects.requireNonNull(serializationStrategy, "strategy must not be null");
        this.storage = Paths.get(dir);
        this.serializationStrategy = serializationStrategy;

        if (!Files.isDirectory(storage)) {
            throw new IllegalArgumentException(storage.getFileName() + " is not a directory");
        }
        if (!Files.isReadable(storage) || !Files.isWritable(storage)) {
            throw new IllegalArgumentException(storage.getFileName() + " is not readable or writeable");
        }
    }

    @Override
    protected Path getSearchKey(String uuid) {
        return Paths.get(storage + "\\" + uuid);
    }

    @Override
    protected boolean isExist(Path pointer) {
        return Files.exists(pointer);
    }

    @Override
    protected void doSave(Resume resume, Path path) {
        try {
            Files.createFile(path);
            serializationStrategy.doWrite(resume, new BufferedOutputStream(Files.newOutputStream(path)));
        } catch (IOException e) {
            throw new StorageException("Saving error", path.toString(), e);
        }
    }

    @Override
    protected Resume doGet(Path path) {
        try {
            return serializationStrategy.doRead(new BufferedInputStream(Files.newInputStream(path)));
        } catch (IOException e) {
            throw new StorageException("Reading error", path.toString(), e);
        }
    }

    @Override
    protected void doUpdate(Resume resume, Path path) {
        try {
            serializationStrategy.doWrite(resume, new BufferedOutputStream(Files.newOutputStream(path)));
        } catch (IOException e) {
            throw new StorageException("Updating error", path.toString(), e);
        }
    }

    @Override
    protected void doDelete(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new StorageException("Deleting error", path.toString(), e);
        }
    }

    @Override
    protected List<Resume> doCopyAll() {
        try {
            List<Path> files = Files.list(storage).collect(Collectors.toList());
            List<Resume> resumes = new ArrayList<>();

            for (Path file : files) {
                resumes.add(doGet(file));
            }

            return resumes;
        } catch (IOException e) {
            throw new StorageException("Copying error", null, e);
        }
    }

    @Override
    public void clear() {
        try {
            Files.list(storage).forEach(this::doDelete);
        } catch (IOException e) {
            throw new StorageException("Path's clearing error", null);
        }
    }

    @Override
    public int size() {
        return doCopyAll().size();
    }
}
