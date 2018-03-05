package sqlTracker;

import java.sql.Timestamp;

/**
 * Created by Администратор on 18.05.2017.
 * Change 05.03.2018
 */
public class Item {
	/**Переменные класса.*/
	private String id;
	/**Переменные класса.*/
	private String name;
	/**Переменные класса.*/
	private String description;
	/**Переменные класса.*/
	private Timestamp create;
	/**Конструктор класса.*/
	public Item() {
	}
	/**Конструктор класса.
	*@param name name
	 *@param description description
	 *@param create create*/
	public Item(String name, String description, Timestamp create) {
		this.name = name;
		this.description = description;
		this.create = create;
	}
	/**Метод получения name.
	 **@return name
	 */
	public String getName() {
		return this.name;
	}
	/**Метод получения description.
	 **@return description
	 */
	public String getDescription() {
		return this.description;
	}
	/**Метод получения create.
	 **@return create
	 */
	public Timestamp getCreate() {
		return this.create;
	}
	/**Метод получения id.
	 **@return id
	 */
	public String getId() {
		return this.id;
	}
	/**Метод задания id.
	*@param id id*/
	public void setId(String id) {
		this.id = id;
	}
}