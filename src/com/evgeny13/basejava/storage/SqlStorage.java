package com.evgeny13.basejava.storage;

import com.evgeny13.basejava.exception.NotExistStorageException;
import com.evgeny13.basejava.model.Resume;
import com.evgeny13.basejava.util.SqlHelper;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class SqlStorage implements Storage {
    private final SqlHelper sqlHelper;
    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());
    private static final String SELECT_RESUME = "SELECT * FROM resume WHERE uuid=?";
    private static final String DELETE_RESUMES = "DELETE FROM resume";
    private static final String INSERT_RESUME = "INSERT INTO resume (uuid, full_name) VALUES (?,?)";
    private static final String UPDATE_RESUME = "UPDATE resume SET full_name = ? WHERE uuid=?";
    private static final String DELETE_RESUME = "DELETE FROM resume WHERE uuid =?";
    private static final String SELECT_RESUMES = "SELECT * FROM resume ORDER BY full_name, uuid";
    private static final String COUNT_RESUMES = "SELECT COUNT(*) from resume";

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        sqlHelper = new SqlHelper(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

    @Override
    public void clear() {
        sqlHelper.execute(DELETE_RESUMES);
    }

    @Override
    public Resume get(String uuid) {
        LOG.info("Get " + uuid);

        return sqlHelper.doTransaction(connection -> {
            Resume resume;

            try (PreparedStatement ps = connection.prepareStatement(SELECT_RESUME)) {
                ps.setString(1, uuid);
                ResultSet rs = ps.executeQuery();

                if (!rs.next()) {
                    throw new NotExistStorageException(uuid);
                }

                resume = new Resume(uuid, rs.getString("full_name"));
            }
            return resume;
        });
    }

    @Override
    public void update(Resume resume) {
        LOG.info("Update " + resume);

        sqlHelper.doTransaction(connection -> {
            try (PreparedStatement ps = connection.prepareStatement(UPDATE_RESUME)) {
                ps.setString(1, resume.getFullName());
                ps.setString(2, resume.getUuid());

                if (ps.executeUpdate() != 1) {
                    throw new NotExistStorageException(resume.getUuid());
                }
            }
            return null;
        });
    }

    @Override
    public void save(Resume resume) {
        LOG.info("Save " + resume);

        sqlHelper.doTransaction(connection -> {
                    try (PreparedStatement ps = connection.prepareStatement(INSERT_RESUME)) {
                        ps.setString(1, resume.getUuid());
                        ps.setString(2, resume.getFullName());
                        ps.execute();
                    }
                    return null;
                }
        );
    }

    @Override
    public void delete(String uuid) {
        LOG.info("Delete " + uuid);

        sqlHelper.execute(DELETE_RESUME, ps -> {
            ps.setString(1, uuid);

            if (ps.executeUpdate() == 0) {
                throw new NotExistStorageException(uuid);
            }
            return null;
        });
    }

    @Override
    public List<Resume> getAllSorted() {
        return sqlHelper.doTransaction(conn -> {
            Map<String, Resume> resumes = new LinkedHashMap<>();

            try (PreparedStatement ps = conn.prepareStatement(SELECT_RESUMES)) {
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    String uuid = rs.getString("uuid");
                    resumes.put(uuid, new Resume(uuid, rs.getString("full_name")));
                }
            }

            return new ArrayList<>(resumes.values());
        });
    }

    @Override
    public int size() {
        return sqlHelper.execute(COUNT_RESUMES, st -> {
            ResultSet rs = st.executeQuery();
            return rs.next() ? rs.getInt(1) : 0;
        });
    }
}
