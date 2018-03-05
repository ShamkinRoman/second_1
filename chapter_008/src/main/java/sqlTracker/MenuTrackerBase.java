package sqlTracker;

/**
 * Created by Up on 29.06.2017.
 */

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Внешний класс.
 * Крутил вертел, пока нашел как правильно
 */
class AddItem extends BaseAction {

    /**
     * Конструктор.
     *
     * @param name имя
     * @param key  ключ
     */
    AddItem(String name, int key) {
        super("Добавить заявку.", 1);
    }

    /**
     * Блок выполнения.
     *
     * @param input   input
     * @param tracker tracker
     */
    void execute(Input input, Tracker tracker) {
        LocalDateTime today = LocalDateTime.now();
        String name = input.ask("Name: ");
        String description = input.ask("Description: ");
        Timestamp create_time = new Timestamp(System.currentTimeMillis());
        tracker.add(new Item(name, description, create_time));
    }
}

/**
 * Собственно самый главный класс, откуда и пляшем.
 */
public class MenuTrackerBase {
    /**
     * тракер.
     */
    private Tracker tracker;
    /**
     * Инпут.
     */
    private Input input;
    /**
     * Массив с действиями.
     */
    private BaseAction[] baseActions = new BaseAction[7];

    /**
     * Конструктор.
     *
     * @param input   ff
     * @param tracker ff
     */
    public MenuTrackerBase(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Заполняем меню.
     */
    public void fill() {
        baseActions[0] = new AddItem("Добавить заявку.", 1);
        baseActions[1] = new ShowItem("Показать все заявки.", 2);
        baseActions[2] = new EditItem("Редактировать заявку.", 3);
        baseActions[3] = new DeleteItem("Удалить заявку.", 4);
        baseActions[4] = new FindById("Найти заявку по Id.", 5);
        baseActions[5] = new FindByName("Найти заявки по имени.", 6);
        baseActions[6] = new ExitItem("Выход.", 7);
    }

    /**
     * Выводим на экран меню.
     */
    public void show() {
        for (BaseAction action : baseActions) {
            if (action != null) {
                System.out.println(action.info());
            }
        }
    }

    /**
     * Выполняем действие по ключу.
     *
     * @param key ключ
     */
    public void select(int key) {
        this.baseActions[(--key)].execute(this.input, tracker);
    }

    /**
     * Показать Заявки.
     */
    private class ShowItem extends BaseAction {
        /**
         * конструктор.
         *
         * @param name имя
         * @param key  ключ
         */
        ShowItem(String name, int key) {
            super("Показать все заявки", 2);
        }

        /**
         * Блок выполнения.
         *
         * @param input   input
         * @param tracker tracker
         */
        void execute(Input input, Tracker tracker) {
            tracker.findAll();
        }
    }

    /**
     * Редактирование заявки.
     */
    private class EditItem extends BaseAction {
        /**
         * Конструктор.
         *
         * @param name имя
         * @param key  ключ
         */
        EditItem(String name, int key) {
            super("Редактирование заявки", 3);
        }

        /**
         * Блок выполнения.
         *
         * @param input   input
         * @param tracker tracker
         */
        void execute(Input input, Tracker tracker) {
            String id = input.ask("Введите id редактируемой заявки: ");

            if (tracker.findById(Integer.parseInt(id))) {
                String name = input.ask("Имя новое: ");
                String description = input.ask("описание новое: ");
                Timestamp create_time = new Timestamp(System.currentTimeMillis());
                Item item = new Item(name, description, create_time);
                tracker.update(Integer.parseInt(id), item);
            } else {
                System.out.println("Заявка с таким Id не найдена.");
            }
        }
    }

    /**
     * Удаление заявки.
     */
    private class DeleteItem extends BaseAction {
        /**
         * Конструктор.
         *
         * @param name имя
         * @param key  ключ
         */
        DeleteItem(String name, int key) {
            super("Удаление заявки", 4);
        }


        /**
         * Выполнение.
         *
         * @param input   input
         * @param tracker tracker
         */
        void execute(Input input, Tracker tracker) {
            String idDelete = input.ask("Введите Id заявки для удаления");
            if (!tracker.findById(Integer.parseInt(idDelete))) {
                System.out.println(String.format("Item with id = %s not found. ---> Заявка с id = %s отсутствует", idDelete, idDelete));
            } else {
                tracker.delete(Integer.parseInt(idDelete));
            }
        }
    }

    /**
     * Поиск по АйДи.
     */
    private class FindById extends BaseAction {
        /**
         * Конструктор.
         *
         * @param name имя
         * @param key  ключ
         */
        FindById(String name, int key) {
            super("Поиск заявки по Id", 5);
        }


        /**
         * Выполнение.
         *
         * @param input   input
         * @param tracker tracker
         */
        void execute(Input input, Tracker tracker) {
            String id = input.ask(String.format("Enter id for search. Введите Id заявки для поиска"));
            if (!tracker.findById(Integer.parseInt(id))) {
                System.out.println(String.format("Item with id = %s not found. ---> Заявка с id = %s отсутствует", id, id));
            } else {
                tracker.findById(Integer.parseInt(id));
            }
        }
    }

    /**
     * Поиск по имени.
     */
    private class FindByName extends BaseAction {
        /**
         * Конструктор.
         *
         * @param name имя
         * @param key  ключ.
         */
        FindByName(String name, int key) {
            super("Поиск заявок по имени", 6);
        }


        /**
         * Выполнение.
         *
         * @param input   input
         * @param tracker tracker
         */
        void execute(Input input, Tracker tracker) {
            String name = input.ask("Введите name для поиска: ");
            tracker.findByName(name);
        }
    }

    /**
     * Выход.
     */
    private class ExitItem extends BaseAction {

        /**
         * Конструктор.
         *
         * @param name имя
         * @param key  ключ
         */
        ExitItem(String name, int key) {
            super("Выход", 7);
        }


        /**
         * Выполнение.
         *
         * @param input   input
         * @param tracker tracker
         */
        void execute(Input input, Tracker tracker) {
            System.out.println("Выходим из программы");

        }
    }
}
