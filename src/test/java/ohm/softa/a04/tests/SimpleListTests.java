package ohm.softa.a04.tests;

import ohm.softa.a04.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Peter Kurfer
 * Created on 10/6/17.
 */
public class SimpleListTests {

	private final Logger logger = LogManager.getLogger();
	private SimpleList<Integer> testList;

	@BeforeEach
	void setup(){
		testList = new SimpleListImpl<>();

		testList.add(1);
		testList.add(2);
		testList.add(3);
		testList.add(4);
		testList.add(5);
	}

	@Test
	void testAddElements(){
		logger.info("Testing if adding and iterating elements is implemented correctly");
		int counter = 0;
		for(Integer o : testList){ //es geht auch mit object
			counter++;
		}
		assertEquals(5, counter);
	}

	@Test
	void testSize(){
		logger.info("Testing if size() method is implemented correctly");
		assertEquals(5, testList.size());
	}

	@Test
	void testFilterAnonymousClass() throws InstantiationException, IllegalAccessException {
		logger.info("Testing the filter possibilities by filtering for all elements greater 2");
		SimpleList<Integer> result = testList.filter(new SimpleFilter<Integer>() {
			@Override
			public boolean include(Integer item) {
				int current = (int)item;
				return current > 2;
			}
		});

		for(Object o : result){
			int i = (int)o;
			assertTrue(i > 2);
		}
	}

	@Test
	void testFilterLambda() throws InstantiationException, IllegalAccessException {
		logger.info("Testing the filter possibilities by filtering for all elements which are dividable by 2");
		SimpleList result = testList.filter(o -> ((int) o) % 2 == 0);
		for(Object o : result){
			int i = (int)o;
			assertTrue(i % 2 == 0);
		}
	}

	@Test
	void testAddDefault() throws Exception{
		logger.info("Testing if you can add an item with default value");
		SimpleListImpl<Person> result = new SimpleListImpl<Person>();
        logger.info("Size is: " + result.size());
		result.addDefault(Person.class);

		assertEquals(1, result.size());
	}

	/*@Test
	void testMapFunction() throws Exception{
		SimpleListImpl<Person> p = new SimpleListImpl<>();
		p.add(new Person("Petar", 20));
		p.add(new Person("Ivan", 24));
		p.add(new Person("Gavril", 28));

		SimpleListImpl<Student> s = p.map( (person) -> new Student(person.getName(), person.getAge()));
	}*/
}