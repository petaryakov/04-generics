package ohm.softa.a04;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.function.Function;

public interface SimpleList<T> extends Iterable<T> {
	/**
	 * Add a given object to the back of the list.
	 */
	void add(T o);

	/**
	 * @return current size of the list
	 */
	int size();

	/**
	 * Adds an item with default value to the list
	 * @param clazz
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	/*default void addDefault(Class<T> clazz) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
		//T def = clazz.newInstance();
		add((T)clazz.newInstance());
		this.add((T) clazz.getDeclaredConstructor().newInstance()); // get declared constructor moga da si
																	// sloja aargumenti v get declared constr
	}*/

    @SuppressWarnings("unchecked")
    default void addDefault(Class<T> clazz) {
        try {
            this.add(clazz.newInstance());
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

	/**
	 * Generate a new list using the given filter instance.
	 * @return a new, filtered list
	 */
	default SimpleList<T> filter(SimpleFilter<T> filter) throws IllegalAccessException, InstantiationException {

		//SimpleList<T> result = this.getClass().getDeclaredConstructor().newInstance();
		SimpleList<T> result;

		try {
			result = (SimpleList<T>) getClass().newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			result = new SimpleListImpl<>();
			System.out.println("in filter, result = new SimpleListImpl<>()");
		}
		return result;
	}

	default<R> SimpleList<R> map(Function<T, R> transform){
		SimpleList<R> result = new SimpleListImpl<R>();
		//try{
			//result = (SimpleList<R>) getClass().newInstance();
			for(T t : this){
				result.add(transform.apply(t));			// apply is important
			}
		/*}catch (InstantiationException | IllegalAccessException e) {
			result = new SimpleListImpl<>();
			System.out.println("in map, result = new SimpleListImpl<>()");
		}*/
		return result;
	}
}
