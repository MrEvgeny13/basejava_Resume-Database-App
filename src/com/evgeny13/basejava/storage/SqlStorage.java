package com.evgeny13.basejava.storage;

import com.evgeny13.basejava.exception.ExistStorageException;
import com.evgeny13.basejava.exception.NotExistStorageException;
import com.evgeny13.basejava.exception.StorageException;
import com.evgeny13.basejava.model.Resume;
import com.evgeny13.basejava.sql.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlStorage implements Storage {
    public final ConnectionFactory connectionFactory;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        connectionFactory = () -> DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }

    @Override
    public void clear() {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement("DELETE FROM resume")) {
            ps.execute();
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    @Override
    public Resume get(String uuid) {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM resume r WHERE r.uuid =?")) {
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                throw new NotExistStorageException(uuid);
            }

            return new Resume(uuid, rs.getString("full_name"));
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    @Override
    public void update(Resume resume) {
        get(resume.getUuid());

        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement ps
                    = connection.prepareStatement("UPDATE resume SET full_name = ? WHERE uuid=?");
            ps.setString(1, resume.getFullName());
            ps.setString(2, resume.getUuid());
            ps.execute();
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    @Override
    public void save(Resume resume) {
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement ps1 = connection.prepareStatement("SELECT * FROM resume WHERE uuid=?");
            ps1.setString(1, resume.getUuid());
            ResultSet resultSet = ps1.executeQuery();

            if (resultSet.next()) {
                throw new ExistStorageException(resume.getUuid());
            }

            PreparedStatement ps2 =
                    connection.prepareStatement("insert into resume (uuid, full_name) values (?,?)");
            ps2.setString(1, resume.getUuid());
            ps2.setString(2, resume.getFullName());
            ps2.execute();
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    @Override
    public void delete(String uuid) {
        get(uuid);
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement ps
                    = connection.prepareStatement("DELETE FROM resume WHERE uuid =?");
            ps.setString(1, uuid);
            ps.execute();
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    @Override
    public List<Resume> getAllSorted() {
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement ps
                    = connection.prepareStatement("SELECT * FROM resume ORDER BY full_name, uuid");
            ResultSet resultSet = ps.executeQuery();

            List<Resume> resumes = new ArrayList<>();

            while (resultSet.next()) {
                resumes.add(new Resume(resultSet.getString(1).trim()
                        , resultSet.getString(2)));
            }

            return resumes;
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    @Override
    public int size() {
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT COUNT(*) from resume");
            ResultSet resultSet = ps.executeQuery();

            if (!resultSet.next()) {
                throw new StorageException("There are no rows here");
            }

            return resultSet.getInt(1);
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }
}
