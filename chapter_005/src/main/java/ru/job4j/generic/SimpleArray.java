package ru.job4j.generic;

/**
 * Класс ДЖЕНЕРИК.
 * @param <T> дженерик.
 */
public class SimpleArray<T> {

    /**
     * Начальный массив класса.
     */
    private Object[] object;
    /**
     * количество заполненых элементов в массиве.
     */
    private int position = 0;

    /**
     * Конструктор.
     *
     * @param size размер массива.
     */
    SimpleArray(int size) {
        this.object = new Object[size];
    }

    /**
     * Метод добавления объекта в массив.
     *
     * @param value добавляемый объект.
     */
    public void add(T value) {
        object[position++] = value;

    }

    /**
     * Метод получения элемента.
     *
     * @param index индекс.
     * @return объект.
     */
    public T get(int index) {
        return (T) object[index];
    }


    /**
     * Метод замены объекта.
     *
     * @param index    индекс.
     * @param newValue новое значение.
     */
    public void update(int index, T newValue) {
        object[index] = newValue;
    }


    /**
     * Метод удаления объекта.
     *
     * @param index индекс удаляемого объекта.
     */
    public void delete(int index) {

        System.arraycopy(this.object, index + 1, this.object, index, this.object.length - index - 1);

        // необходимо для обнуления элемента массива под индексом position и уменьшению position на 1,
        // т.к. добавилось одно место в массиве.
        object[--position] = null;

    }

    /**
     * Метод удаления объекта.
     *
     * @param value Удаляемый объект.
     */
    public void delete(T value) {
        for (int index = 0; index <= position; index++) {
            if (object[index].equals(value)) {
                System.arraycopy(this.object, index + 1, this.object, index, this.object.length - index - 1);
                object[--position] = null;
                break;
            }
        }
    }

    /**
     * Метод замены элемента.
     *
     * @param oldValue заменяемый объект.
     * @param newValue новый объект.
     */
    public void update(T oldValue, T newValue) {

        for (int index = 0; index <= position; index++) {
            if (object[index].equals(oldValue)) {
                object[index] = newValue;
                break;
            }
        }
    }

}
