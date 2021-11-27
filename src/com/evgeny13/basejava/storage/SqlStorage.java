package com.evgeny13.basejava.storage;

import com.evgeny13.basejava.exception.NotExistStorageException;
import com.evgeny13.basejava.model.Resume;
import com.evgeny13.basejava.util.SqlHelper;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
        sqlHelper.connectAndQuery(DELETE_RESUMES, ps -> {
            ps.execute();
            return null;
        });
    }

    @Override
    public Resume get(String uuid) {
        LOG.info("Get " + uuid);

        return sqlHelper.connectAndQuery(SELECT_RESUME, ps -> {
            try (ResultSet result = sqlHelper.doStatement(ps, uuid).executeQuery()) {
                if (result.next()) {
                    return new Resume(uuid, result.getString("full_name"));
                }

                LOG.warning("Resume " + uuid + " not exist");
                throw new NotExistStorageException(uuid);
            }
        });
    }

    @Override
    public void update(Resume resume) {
        LOG.info("Update " + resume);

        sqlHelper.connectAndQuery(UPDATE_RESUME, ps -> {
            PreparedStatement preparedStatement = sqlHelper.doStatement(ps, resume.getFullName(), resume.getUuid());
            executeStatement(preparedStatement, resume.getUuid());
            return null;
        });
    }

    @Override
    public void save(Resume resume) {
        LOG.info("Save " + resume);

        sqlHelper.connectAndQuery(INSERT_RESUME, ps -> {
            try {
                sqlHelper.doStatement(ps, resume.getUuid(), resume.getFullName()).execute();
            } catch (SQLException e) {
                sqlHelper.processException(resume, e);
            }

            return null;
        });
    }

    @Override
    public void delete(String uuid) {
        LOG.info("Delete " + uuid);

        sqlHelper.connectAndQuery(DELETE_RESUME, ps -> {
            PreparedStatement preparedStatement = sqlHelper.doStatement(ps, uuid);
            executeStatement(preparedStatement, uuid);
            return null;
        });
    }

    @Override
    public List<Resume> getAllSorted() {
        return sqlHelper.connectAndQuery(SELECT_RESUMES, ps -> {
            List<Resume> resumes = new ArrayList<>();

            try (ResultSet result = ps.executeQuery()) {
                while (result.next()) {
                    resumes.add(new Resume(result.getString("uuid").trim(),
                            result.getString("full_name")));
                }
            }

            return resumes;
        });
    }

    @Override
    public int size() {
        return sqlHelper.connectAndQuery(COUNT_RESUMES, ps -> {
            try (ResultSet selectCount = ps.executeQuery()) {
                return selectCount.next() ? selectCount.getInt("COUNT") : 0;
            }
        });
    }

    private void executeStatement(PreparedStatement ps, String uuid) throws SQLException {
        if (ps.executeUpdate() == 0) {
            throw new NotExistStorageException(uuid);
        }
    }
}
