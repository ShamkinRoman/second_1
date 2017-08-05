package ru.job4j.newtracker;

import java.util.ArrayList;

import java.util.List;
import java.util.Random;

/**
 * Created by Администратор on 23.05.2017.
 */
public class Tracker {
	/**Объявлем Tracker.*/
	private List<Item> items = new ArrayList<>();

	/**Генератор для generateId.*/
	private static final Random RN = new Random();
	/**Добавление заявок.
	 *@param item item
	 */
	public void add(Item item) {
		item.setId(generateId());
		//this.items[position++] = item;
		this.items.add(item);
		//System.out.println("Осталось "+ (100-this.position)+" свободных мест для заявок");
		//return item;
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
	 */
	public void update(Item item) {
		for (int i = 0; i < this.items.size(); i++) {
			if (this.items.get(i).getId().equals(item.getId())) {
				this.items.set(i, item);
				break;
			}
		}
	}
	/**Удаление заявок.
	 *@param item item
	 */
	public void delete(Item item) {
		for (int i = 0; i < this.items.size(); i++) {
			if (items.get(i).equals(item)) {
				items.remove(i);
				break;
			}
		}
	}
	/**Поиск всех заявок.
	 *@return  item[]
	 */
	public List<Item> findAll() {

		List<Item> temp = new ArrayList<>();

		for (int i = 0; i < this.items.size(); i++) {

				temp.add(items.get(i));
		}
		return temp;
	}
	/**Поиск заявок по имени.
	 *@param key key
	 *@return  item[]
	 */
	public List<Item> findByName(String key) {

		List<Item> temp = new ArrayList<>();

		for (int i = 0; i < this.items.size(); i++) {
			if (this.items.get(i).getName().equals(key)) {
				temp.add(this.items.get(i));

			}
		}
		return temp;
	}
}