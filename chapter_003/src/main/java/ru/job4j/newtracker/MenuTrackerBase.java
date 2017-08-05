package ru.job4j.newtracker;

/**
 * Created by Up on 29.06.2017.
 */

import java.util.ArrayList;
import java.util.List;

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
        String name = input.ask("Name: ");
        String description = input.ask("Description: ");
        boolean invalid = true;
        Long value = null;
        do {
            try {
                value = Long.parseLong(input.ask("Введите число:"));
                invalid = false;
            } catch (NumberFormatException lala) {
                System.out.println("Введите число, а не буквы!!!");
            }
        } while (invalid);
        tracker.add(new Item(name, description, value));
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
     *Коллекция с действиями с действиями.
     */
    private List<BaseAction> baseActionList = new ArrayList<>();

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
        baseActionList.add(new AddItem("Добавить заявку.", 1));
        baseActionList.add(new ShowItem("Показать все заявки.", 2));
        baseActionList.add(new EditItem("Редактировать заявку.", 3));
        baseActionList.add(new DeleteItem("Удалить заявку.", 4));
        baseActionList.add(new FindById("Найти заявку по Id.", 5));
        baseActionList.add(new FindByName("Найти заявки по имени.", 6));
        baseActionList.add(new ExitItem("Выход.", 7));
    }

    /**
     * Выводим на экран меню.
     */
    public void show() {
        for (BaseAction action : baseActionList) {
            System.out.println(action.info());

        }

    }

    /**
     * Выполняем действие по ключу.
     *
     * @param key ключ
     */
    public void select(int key) {
        this.baseActionList.get(--key).execute(this.input, tracker);
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

            List<Item> item = new ArrayList<Item>(tracker.findAll());

            if (item.size() == 0) {
                System.out.println("Нет заявок.");
            } else {
                for (Item value : item) {
                    String result = String.format("Имя %1$1s описание %2$1s создали %3$1s Id %4$1s", value.getName(), value.getDescription(), value.getCreate(), value.getId());
                    System.out.println(result);
                }
            }
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
            if (tracker.findById(id) != null) {
                String name = input.ask("Имя новое: ");
                String description = input.ask("описание новое: ");
                boolean invalid = true;
                Long value = null;
                do {
                    try {
                        value = Long.parseLong(input.ask("дату создания (число) новое:"));
                        invalid = false;
                    } catch (NumberFormatException lala) {
                        System.out.println("Введите число, а не буквы!!!");
                    }
                } while (invalid);
                Item item = new Item(name, description, value);
                item.setId(id);
                tracker.update(item);
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
            Item itemDelete = tracker.findById(idDelete);
            if (itemDelete != null) {
                tracker.delete(itemDelete);
            } else {
                System.out.println("Заявка с таким Id не найдена.");
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
            Item findIdItem = tracker.findById(input.ask("Введите Id заявки для поиска: "));
            if (findIdItem != null) {
                String result = String.format("Имя %1$1s описание %2$1s создали %3$1s Id %4$1s", findIdItem.getName(), findIdItem.getDescription(), findIdItem.getCreate(), findIdItem.getId());
                System.out.println(result);

            } else {
                System.out.println("Заявка с таким Id не найдена.");
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

            List<Item> itemsName = new ArrayList<Item>(tracker.findByName(name));
            if (itemsName.size() != 0) {
                String result;
                for (Item value : itemsName) {
                    result = String.format("Имя %1$1s описание %2$1s создали %3$1s Id %4$1s", value.getName(), value.getDescription(), value.getCreate(), value.getId());
                    System.out.println(result);
                }
            } else {
                System.out.println("Заявок с таким Name не найдены.");
            }
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
