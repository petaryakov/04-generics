package ohm.softa.a04;

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
	default void addDefault(Class<T> clazz)throws IllegalAccessException, InstantiationException{
		//T def = clazz.newInstance();
		add(clazz.newInstance());
	}

	/**
	 * Generate a new list using the given filter instance.
	 * @return a new, filtered list
	 */
	default SimpleList<T> filter(SimpleFilter<T> filter) throws IllegalAccessException, InstantiationException {
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
		SimpleList<R> result;
		try{
			result = (SimpleList<R>) getClass().newInstance();
			for(T t : this){
				result.add((R)transform.apply(t));			// apply is important
			}
		}catch (InstantiationException | IllegalAccessException e) {
			result = new SimpleListImpl<>();
			System.out.println("in map, result = new SimpleListImpl<>()");
		}
		return result;
	}
}
