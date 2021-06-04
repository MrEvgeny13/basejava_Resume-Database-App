/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                break;
            }
            else if (storage[i] instanceof Resume) {
                storage[i] = null;
            }
        }
    }

    void save(Resume r) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                storage[i] = r;
                break;
            }
        }
    }

    Resume get(String uuid) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i].toString().equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        // удаляем запрашиваемый элемент
        for (int i = 0; i < storage.length; i++) {
            if (storage[i].toString().equals(uuid)) {
                storage[i] = null;
                break;
            }
        }

        // ликвидируем образовавшийся null между резюме
        int k = 1;                                   // на сколько позиций сдвигаем элементы-резюме влево
        Resume[] storage_tmp = new Resume[k];
        for (int i = 0; i < storage.length; i++) {
            if (i < k) {
                storage_tmp[i] = storage[i];
                continue;
            }
            storage[i - k] = storage[i];
        }
        System.arraycopy(storage_tmp, 0, storage, storage.length - k, k);
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        // узнаем количество реальных резюме в массиве
        int count = 0;
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] instanceof Resume) {
                count++;
            }
            else
                break;
        }

        if (count == 0 ) {          // если реальных резюме нет, просто возвращаем нулевой массив
            return new Resume[0];
        }
        else {
            Resume[] real_storage = new Resume[count];
            System.arraycopy(storage, 0, real_storage, 0, count);
            return real_storage;
        }
    }

    int size() {
        int count = 0;

        for (int i = 0; i < storage.length; i++) {
            if (storage[i] instanceof Resume) {
                count++;
            }
            else
                break;
        }
        return count;
    }
}
