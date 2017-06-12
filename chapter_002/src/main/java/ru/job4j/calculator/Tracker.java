package ru.job4j.calculator;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by Администратор on 23.05.2017.
 */
public class Tracker {
	/**Объявлем Tracker.*/
	private Item[] items = new Item[100];
	/**Счетчик заявок.*/
	private int position = 0;
	/**Генератор для generateId.*/
	private static final Random RN = new Random();
	/**Добавление заявок.
	 *@param item item
	 *@return  item
	 */
	public Item add(Item item) {
		item.setId(generateId());
		this.items[position++] = item;
		//System.out.println("Осталось "+ (100-this.position)+" свободных мест для заявок");
		return item;
	}
	/**Поиск заявок по Id.
	 *@param id id
	 *@return  item
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
	/**Генератор id.
	 *@return id
	 */
	String generateId() {
		return String.valueOf(System.currentTimeMillis() + RN.nextInt());

	}
	/**Редактирование заявок.
	 *@param item item
	 *@param id   id
	 */
	public void update(String id, Item item) {
		Item temp = new Item();
		temp = findById(id);
		if (temp != null) {
			int r = 0;
			for (int i = 0; i <= this.position; i++) {
				if (items[i].equals(temp)) {
					r = i;
					break;
				}
			}
			this.items[r] = item;
			this.items[r].setId(generateId());
		}
	}
	/**Удаление заявок.
	 *@param item item
	 */
	public void delete(Item item) {
		for (int i = 0; i < this.position; i++) {
			if (items[i].equals(item)) {
				items[i] = null;
				break;
			}
		}
		for (int i = 0; i < this.position - 1; i++) {
			if (this.items[i] == null) {
				items[i] = items[i + 1];
			}
		}
		this.position--;
	}
	/**Поиск всех заявок.
	 *@return  item[]
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
		return Arrays.copyOf(temp, d);
	}
	/**Поиск заявок по имени.
	 *@param key key
	 *@return  item[]
	 */
	public Item[] findByName(String key) {
		Item[] temp = new Item[this.position];
		int d = 0;
		for (int i = 0; i < this.position; i++) {
			if (this.items[i].getName().equals(key)) {
				temp[d] = this.items[i];
				d++;
			}
		}
		return Arrays.copyOf(temp, d);
	}
}