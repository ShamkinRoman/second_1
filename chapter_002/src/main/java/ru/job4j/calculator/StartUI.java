package ru.job4j.calculator;

/**
 * Created by Администратор on 12.06.2017.
 */
public class StartUI {
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
     *@param input input
	*@return Item
	*/
    public Item addNewItem(Input input) {
        System.out.println("Добавляем новую заявку");
        String name = input.ask("Имя: ");
        String description = input.ask("Описание: ");
        Long create = Long.parseLong(input.ask("дата создания (число): "));
        Item item = new Item(name, description, create);
        return item;
    }
    /**Вывод всех заявок.
     *@param tracker tracker
     */
    public void showAll(Tracker tracker) {
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
    /**Редактирование заявок.
     *@param tracker tracker
     *@param input input
     */
    public void editItem(Tracker tracker, Input input) {
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

    /**Удаление заявки.
     *@param tracker tracker
     *@param input input
     */
    public void itemDelete(Tracker tracker, Input input) {
        String idDelete = input.ask("Введите Id заявки для удаления");
        Item itemDelete = tracker.findById(idDelete);
        if (itemDelete != null) {
            tracker.delete(itemDelete);
        } else {
            System.out.println("Заявка с таким Id не найдена.");
        }
    }
    /**Поиск заявки по Id.
     *@param tracker tracker
     *@param input input
     */
    public void findId(Tracker tracker, Input input) {
        Item findIdItem = tracker.findById(input.ask("Введите Id заявки для поиска: "));
        if (findIdItem != null) {
            String result = String.format("Имя %1$1s описание %2$1s создали %3$1s Id %4$1s", findIdItem.getName(), findIdItem.getDescription(), findIdItem.getCreate(), findIdItem.getId());
            System.out.println(result);

        } else {
            System.out.println("Заявка с таким Id не найдена.");
        }
    }
    /**Поиск заявки по имени.
     *@param tracker tracker
     *@param input input
     */
    public void findName(Tracker tracker, Input input) {
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
            System.out.println("Заявок с таким Именем не найдены.");
        }
    }
    /**Обработчик выбора пользователя.
     *@param tracker tracker
     *@param input input
     */
    public void start(Tracker tracker, Input input) {
        String choose;
        final String add = "0";
        final String showAll = "1";
        final String editItem = "2";
        final String itemDelete = "3";
        final String findId = "4";
        final String findName = "5";
        final String exit = "6";
        do {
            new StartUI().printMenu();
            choose = input.ask("Ваш выбор: ");
            switch (choose) {
                case add:
                    tracker.add(addNewItem(input));
                    break;
                case showAll:
                    new StartUI().showAll(tracker);
                    break;
                case editItem:
                    new StartUI().editItem(tracker, input);
                    break;
                case itemDelete:
                    new StartUI().itemDelete(tracker, input);
                    break;
                case findId:
                    new StartUI().findId(tracker, input);
                    break;
                case findName:
                    new StartUI().findName(tracker, input);
                    break;
                case exit:
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
        new StartUI().start(tracker, input);
    }
}
