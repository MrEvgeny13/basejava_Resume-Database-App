/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int size = 0;

    void clear() {
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                storage[i] = null;
            }
            size = 0;
        }
    }

    void save(Resume r) {
        storage[size] = r;
        size++;
    }

    Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].toString().equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        // удаляем запрашиваемый элемент
        for (int i = 0; i < size; i++) {
            if (storage[i].toString().equals(uuid)) {
                storage[i] = null;
                break;
            }
        }
        size--;

        // определяем индекс, где образовался null между резюме
        int indexOfNullResume = 0;
        for (int i = 0; i < size + 1; i++) {
            if (storage[i] == null) {
                indexOfNullResume = i;
                break;
            }
        }

        // ликвидируем null между резюме
        for (int i = indexOfNullResume + 1; i < size; i++) {
            storage[i - 1] = storage[i];
            storage[i] = null;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        if (size > 0) {
            Resume[] real_storage = new Resume[size];
            System.arraycopy(storage, 0, real_storage, 0, size);
            return real_storage;
        }
        return null;
    }

    int size() {
        return size;
    }
}
