package ru.job4j.calculator;

/**
 * Created by Администратор on 12.06.2017.
 */
public class StartUI {
    /**Создание трекера.*/
    private Tracker tracker = new Tracker();
    /**Создание ввода от пользователя.*/
    private Input input = new ConsoleInput();
    /**Конструктор.
	@param tracker tracer
	*/
    public StartUI(Tracker tracker) {
        this.tracker = tracker;
    }
    /**Дефолтный конструктор.*/
    public StartUI() {
	}
    /**Функция для вывода меню.*/
    public void printMenu() {
        String[] menu = {"0. Add new Item", "1. Show all items", "2. Edit item", "3. Delete item",
                "4. Find item by Id", "5. Find items by name", "6. Exit Program"};
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
        String idEdit = input.ask("Введите Id заявки для редактирования");
        Item editItem = this.tracker.findById(idEdit);
        if (editItem != null) {
            this.tracker.update(idEdit, addNewItem());
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
            new StartUI(tracker).printMenu();
            choose = input.ask("Select: ");
            switch (choose) {
                case "0":
                    this.tracker.add(addNewItem());
                    break;
                case "1":
                    new StartUI(tracker).showAll();
                    break;
                case "2":
                    new StartUI(tracker).editItem();
                    break;
                case "3":
                    new StartUI(tracker).itemDelete();
                    break;
                case"4":
                    new StartUI(tracker).findId();
                    break;
                case "5":
                    new StartUI(tracker).findName();
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
        new StartUI(tracker).select();
    }
}
