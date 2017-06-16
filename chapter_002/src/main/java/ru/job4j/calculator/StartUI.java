package ru.job4j.calculator;

/**
 * Created by Администратор on 12.06.2017.
 */
public class StartUI {
    /**Создание трекера.*/
    private Tracker tracker;
    /**Создание ввода от пользователя.*/
    private Input input;
    /**Конструктор.
     @param tracker tracer
     @param input input
     */
    public StartUI(Tracker tracker, Input input) {
        this.input = input;
        this.tracker = tracker;
    }
    /**Дефолтный конструктор.*/
    public StartUI() {
    }
    /**Функция для вывода меню.*/
    public void printMenu() {
        String[] menu = {"0. Добавить новую заявку", "1. Показать все заявки", "2. Редактировать заявку", "3. Удалить заявку",
                "4. Найти заявку по id", "5. Найти заявку по имени", "6. Выйти из программы"};
        int d = menu.length;
        for (int i = 0; i < d; i++) {
            System.out.println(menu[i]);
        }
    }
    /**Добавление новой заявки.
     *@return Item
     */
    public Item addNewItem() {
        System.out.println("Добавляем новую заявку");
        String name = input.ask("Name: ");
        String description = input.ask("Description: ");
        Long create = Long.parseLong(input.ask("Create in Long format: "));
        Item item = new Item(name, description, create);
        return item;
    }
    /**Вывод всех заявок.*/
    public void showAll() {
        Item[] item = new Item[this.tracker.findAll().length];
        item = this.tracker.findAll();
        int numbersItem = this.tracker.findAll().length;
        if (numbersItem == 0) {
            System.out.println("Нет заявок.");
        } else {
            for (int i = 0; i < numbersItem; i++) {
                String result = String.format("Имя %1$1s описание %2$1s создали %3$1s Id %4$1s", item[i].getName(), item[i].getDescription(), item[i].getCreate(), item[i].getId());
                System.out.println(result);
            }
        }
    }
    /**Редактирование заявок.*/
    public void editItem() {
        String id = input.ask("Введите id редактируемой заявки: ");
        if (this.tracker.findById(id) != null) {
            String name = input.ask("Имя новое: ");
            String description = input.ask("описание новое: ");
            Long create = Long.parseLong(input.ask("дату создания (число) новое: "));
            Item item = new Item(name, description, create);
            item.setId(id);
            this.tracker.update(item);
        } else {
            System.out.println("Заявка с таким Id не найдена.");
        }
    }
    /**Удаление заявки.*/
    public void itemDelete() {
        String idDelete = input.ask("Введите Id заявки для удаления");
        Item itemDelete = this.tracker.findById(idDelete);
        if (itemDelete != null) {
            this.tracker.delete(itemDelete);
        } else {
            System.out.println("Заявка с таким Id не найдена.");
        }
    }
    /**Поиск заявки по Id.*/
    public void findId() {
        Item findIdItem = this.tracker.findById(input.ask("Введите Id заявки для поиска: "));
        if (findIdItem != null) {
            String result = String.format("Имя %1$1s описание %2$1s создали %3$1s Id %4$1s", findIdItem.getName(), findIdItem.getDescription(), findIdItem.getCreate(), findIdItem.getId());
            System.out.println(result);

        } else {
            System.out.println("Заявка с таким Id не найдена.");
        }
    }
    /**Поиск заявки по имени.*/
    public void findName() {
        String name = input.ask("Введите name для поиска: ");
        Item[] itemsName = new Item[tracker.findByName(name).length];
        itemsName = this.tracker.findByName(name);
        int numberName = this.tracker.findByName(name).length;
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
    /**Обработчик выбора пользователя.*/
    public void select() {
        String choose;
        do {
            new StartUI(tracker, input).printMenu();
            choose = input.ask("Select: ");
            switch (choose) {
                case "0":
                    this.tracker.add(addNewItem());
                    break;
                case "1":
                    new StartUI(tracker, input).showAll();
                    break;
                case "2":
                    new StartUI(tracker, input).editItem();
                    break;
                case "3":
                    new StartUI(tracker, input).itemDelete();
                    break;
                case"4":
                    new StartUI(tracker, input).findId();
                    break;
                case "5":
                    new StartUI(tracker, input).findName();
                    break;
                case "6":
                    System.out.println("Выходим из программы.");
                    break;
                default:
                    System.out.println("Будьте внимательны!");
                    break;

            }
        } while (!choose.equals("6"));

    }
    /**main он и в Африке main, как есть.
     *@param args args
     */
    public static void main(String[] args) {
        Tracker tracker = new Tracker();
        Input input = new ConsoleInput();
        new StartUI(tracker, input).select();
    }
}