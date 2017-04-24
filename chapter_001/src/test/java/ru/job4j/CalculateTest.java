package ru.job4j;

import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.hamcrest.core.Is.is; /** из-за слова hamcreAst, долго не мог понять, почему не собирается проект*/
import static org.junit.Assert.assertThat;
/**
 *Тестирование класса 
 *@autor Shamkin Roman
 *@since 24.04.2017
*/
public class CalculateTest{
	/**
	*Добовляем Тест
	набирал ручками и в блокноте с док файла
	и как результат забыл написать @Test 
	потратил час времени чтобы понять, почему не работает
	*/
	@Test
	public void whenAddOneToTheTwo (){
		ByteArrayOutputStream out=new ByteArrayOutputStream(); /** А здесь забыл поставить скобки () */
		System.setOut(new PrintStream(out));
		Calculate.main(null);
		
		/** Пример с doc файла
		*	этот тест работает
		*
		 assertThat(out.toString(),is("Hello World\r\n"));
		*/
		
		/** Пример с сайта */
		
		/**Найди пять отличий и пойми почему верхняя строка не работает, а нижняя проходит на отлтчно(скопирована с сайта). 
		Верхняя набранная руками. 
		У меня не получилось
		закоментировал не рабочуу строку*/
		
		/*assertThat(out.toString(),is(String.format("Hello world%s",System.getProperty("line.separator"))));		*/
		/*assertThat(out.toString(),is(String.format("Hello world%s",System.getProperty("line.separator"))));	*/
		  assertThat(out.toString(),is(String.format("Hello World%s",System.getProperty("line.separator"))));				
		}
}


	
	
	
	