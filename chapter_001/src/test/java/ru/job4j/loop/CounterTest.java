package ru.job4j.loop;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 *Test Condition.
 *@author Shamkin Roman.
 *@since 03.05.2017.
 */
 public class CounterTest {
	 /**
	*Test 1.
	*start=0 finish=10 count=30
	*/
	@Test
	public void whenStart0Finish10That30() {
		Counter count = new Counter();
		int result = count.add(0, 10);
		int expected = 30;
		assertThat(result, is(expected));
	}
	/**
	*Test 2.
	*start=8 finish=10 count=18
	*/
	@Test
	public void whenStart8Finish10That18() {
		Counter count = new Counter();
		int result = count.add(8, 10);
		int expected = 18;
		assertThat(result, is(expected));
	}
	/**
	*Test 3.
	*start=8 finish=2 count=0
	*/
	@Test
	public void whenStart8Finish2That0() {
		Counter count = new Counter();
		int result = count.add(8, 2);
		int expected = 0;
		assertThat(result, is(expected));
	}
}