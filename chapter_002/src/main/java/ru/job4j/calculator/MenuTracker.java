package ru.job4j.calculator;

/**
 * Внешний класс Редактирование заявки.
 */
class EditItem implements UserAction {
    /**
     * Возврат значения ключа(меню).
     *
     * @return key
     */
    public int key() {
        return 3;
    }

    /**
     * .
     * Метод исполнения
     *
     * @param input   input
     * @param tracker tracer
     */
    public void execute(Input input, Tracker tracker) {
        String id = input.ask("Введите id редактируемой заявки: ");
        if (tracker.findById(id) != null) {
            String name = input.ask("Имя новое: ");
            String description = input.ask("описание новое: ");
            Long create = Long.parseLong(input.ask("дату создания (число) новое: "));
            Item item = new Item(name, description, create);
            item.setId(id);
            tracker.update(item);
        } else {
            System.out.println("Заявка с таким Id не найдена.");
        }
    }

    /**
     * .
     * Информация для чего создан класс.
     *
     * @return return
     */
    public String info() {
        return String.format("%s. %s", this.key(), "Редактировать заявку.");
    }
}

/**
 * .
 * Внешний класс для Удаления заявки.
 */
class DeleteItem implements UserAction {
    /**
     * Возврат значения ключа(меню).
     *
     * @return key
     */
    public int key() {
        return 4;
    }

    /**
     * .
     * Метод исполнения
     *
     * @param input   input
     * @param tracker tracer
     */
    public void execute(Input input, Tracker tracker) {
        String idDelete = input.ask("Введите Id заявки для удаления");
        Item itemDelete = tracker.findById(idDelete);
        if (itemDelete != null) {
            tracker.delete(itemDelete);
        } else {
            System.out.println("Заявка с таким Id не найдена.");
        }

    }

    /**
     * .
     * Информация для чего создан класс.
     *
     * @return return
     */
    public String info() {
        return String.format("%s. %s", this.key(), "Удалить заявку.");
    }
}

/**
 * Внешний класс для Выход из программы, ничего не делает, только выводит надпись.
 */
class ExitItem implements UserAction {
    /**
     * .
     * Возврат значения ключа(меню).
     *
     * @return key
     */
    public int key() {
        return 7;
    }

    /**
     * .
     * Метод исполнения
     *
     * @param input   input
     * @param tracker tracer
     */
    public void execute(Input input, Tracker tracker) {
        System.out.println("Выходим из программы.");
    }

    /**
     * .
     * Информация для чего создан класс.
     *
     * @return return
     */
    public String info() {
        return String.format("%s. %s", this.key(), "Выход.");
    }
}

/**
 * .
 * Created by Up on 26.06.2017.
 */
public class MenuTracker {
    /**
     * Создание трекера.
     */
    private Tracker tracker;
    /**
     * Создание ввода от пользователя.
     */
    private Input input;
    /**
     * Создание меню.
     */
    private UserAction[] userAction = new UserAction[7];

    /**
     * .
     * Дефолтный конструктор.
     *
     * @param input   input
     * @param tracker tracer
     */
    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * .
     * Вывод значений Меню на экран.
     */
    public void show() {
        for (UserAction action : this.userAction) {
            if (action != null) {
                System.out.println(action.info());
            }
        }
    }

    /**
     * .
     * Создание меню.
     */
    public void fillaction() {

        this.userAction[0] = this.new AddItem();
        this.userAction[1] = new MenuTracker.ShowItem();
        this.userAction[2] = new EditItem();
        this.userAction[3] = new DeleteItem();
        this.userAction[4] = this.new FindById();
        this.userAction[5] = new MenuTracker.FindByName();
        this.userAction[6] = new ExitItem();
    }

    /**
     * .
     * Выбор меню.
     *
     * @param key key
     */
    public void select(int key) {
        this.userAction[(--key)].execute(this.input, tracker);
    }

    /**
     * .
     * Внутренний класс Добавление заявки.
     */
    private class AddItem implements UserAction {
        /**
         * Возврат значения ключа(меню).
         *
         * @return key
         */
        public int key() {
            return 1;
        }

        /**
         * .
         * Метод исполнения
         *
         * @param input   input
         * @param tracker tracer
         */
        public void execute(Input input, Tracker tracker) {
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

        /**
         * .
         * Информация для чего создан класс.
         *
         * @return return
         */
        public String info() {
            return String.format("%s. %s", this.key(), "Добавить новую заявку.");
        }
    }

    /**
     * .
     * Внутренний класс Поиск завки по Id.
     */
    private class FindById implements UserAction {
        /**
         * .
         * Возврат значения ключа(меню).
         *
         * @return key
         */
        public int key() {
            return 5;
        }

        /**
         * .
         * Метод исполнения
         *
         * @param input   input
         * @param tracker tracer
         */
        public void execute(Input input, Tracker tracker) {
            Item findIdItem = tracker.findById(input.ask("Введите Id заявки для поиска: "));
            if (findIdItem != null) {
                String result = String.format("Имя %1$1s описание %2$1s создали %3$1s Id %4$1s", findIdItem.getName(), findIdItem.getDescription(), findIdItem.getCreate(), findIdItem.getId());
                System.out.println(result);

            } else {
                System.out.println("Заявка с таким Id не найдена.");
            }

        }

        /**
         * .
         * Информация для чего создан класс.
         *
         * @return return
         */
        public String info() {
            return String.format("%s. %s", this.key(), "Найти заявку по Id.");
        }
    }

    /**
     * .
     * Внутренний статический класс Показать все заявки.
     */
    private static class ShowItem implements UserAction {
        /**
         * .
         * Возврат значения ключа(меню).
         *
         * @return key
         */
        public int key() {
            return 2;
        }

        /**
         * .
         * Метод исполнения.
         *
         * @param input   input
         * @param tracker tracer
         */
        public void execute(Input input, Tracker tracker) {
            Item[] item = new Item[tracker.findAll().length];
            item = tracker.findAll();
            int numbersItem = tracker.findAll().length;
            if (numbersItem == 0) {
                System.out.println("Нет заявок.");
            } else {
                for (int i = 0; i < numbersItem; i++) {
                    String result = String.format("Имя %1$1s описание %2$1s создали %3$1s Id %4$1s", item[i].getName(), item[i].getDescription(), item[i].getCreate(), item[i].getId());
                    System.out.println(result);
                }
            }

        }

        /**
         * Информация для чего создан класс.
         *
         * @return return
         */
        public String info() {
            return String.format("%s. %s", this.key(), "Показать все заявки.");
        }
    }

    /**
     * Внутренний статический класс Поиск заявок по имени.
     */
    private static class FindByName implements UserAction {
        /**
         * Возврат значения ключа(меню).
         *
         * @return key
         */
        public int key() {
            return 6;
        }

        /**
         * Метод исполнения.
         *
         * @param input   input
         * @param tracker tracer
         */
        public void execute(Input input, Tracker tracker) {
            String name = input.ask("Введите name для поиска: ");
            Item[] itemsName = new Item[tracker.findByName(name).length];
            itemsName = tracker.findByName(name);
            int numberName = tracker.findByName(name).length;
            if (numberName != 0) {
                String result;
                for (int i = 0; i < numberName; i++) {
                    result = String.format("Имя %1$1s описание %2$1s создали %3$1s Id %4$1s", itemsName[i].getName(), itemsName[i].getDescription(), itemsName[i].getCreate(), itemsName[i].getId());
                    System.out.println(result);
                }
            } else {
                System.out.println("Заявок с таким Name не найдены.");
            }

        }

        /**
         * Информация для чего создан класс.
         *
         * @return return
         */
        public String info() {
            return String.format("%s. %s", this.key(), "Найти заявки по имени.");
        }
    }
}
