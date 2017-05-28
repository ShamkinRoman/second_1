package ru.job4j.calculator;
import java.util.Random;

/**
 * Created by Администратор on 23.05.2017.
 */
public class Tracker {
	/**Переменные класса.*/
	private Item[] items = new Item[100];
	/**Переменные класса.*/
	private int position = 0;
	/**Переменные класса.*/
	private static final Random RN = new Random();
	/**Метод добавления Item.
	 *@param item item
	 *@return item
	 */
	public Item add(Item item) {
		item.setId(generateId());
		this.items[position++] = item;
		return item;
	}
	/**Метод поиска по id.
	 *@param id id
	 *@return item
	 */
	protected Item findById(String id) {
		Item result = null;
		for (Item item : items) {
			if (item != null && item.getId().equals(id)) {
				result = item;
				break;
			}
		}
		return result;
	}
	/**Метод генерации id.
	 **@return id как строку
	 */
	String generateId() {
		return String.valueOf(System.currentTimeMillis() + RN.nextInt());
	}
	/**Мой собственный методод для тестов.
	 *@param item Item
	 **@return id как строку
	 */
	public String returnId(Item item) {
		return item.getId();
	}
	/**Метод update.
	 * @param item Item
	 */
	public void update(Item item) {
		Item ll = new Item();
		ll = findById(item.getId());
	}
	/**Метод delete.
	 * @param item Item
	 */
	public void delete(Item item) {
		for (int i = 0; i < this.position; i++) {
			if (items[i].equals(item)) {
				items[i] = null;
				break;
			}
		}
	}
	/**Метод поиска.
	 *@return result
	 */
	public Item[] findAll() {
		Item[] temp = new Item[this.position];
		int d = 0;
		for (int i = 0; i < this.position; i++) {
			if (items[i] != null) {
				temp[d] = items[i];
				d++;
			}
		}
		Item[] result = new Item[d];
		if (d != 0) {
			System.arraycopy(temp, 0, result, 0, d);
		} else {
			result = null;
		}
		return result;
	}
	/**Метод поиска по имени.
	 * @param key - name
	 *@return result
	 */
	public Item[] findByName(String key) {
		Item[] temp = new Item[this.position];
		int d = 0;
		for (int i = 0; i <= this.position; i++) {
			if ((this.items[i] != null) && (this.items[i].getName().equals(key))) {
				temp[d] = this.items[i];
				d++;
			}
		}
		Item[] result = new Item[d];
		if (d != 0) {
			System.arraycopy(temp, 0, result, 0, d);
		} else {
			result = null;
		}
		return result;
	}
}