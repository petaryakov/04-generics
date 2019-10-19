package ohm.softa.a04;

import javax.lang.model.element.Element;
import java.util.Iterator;
import java.util.logging.ConsoleHandler;

/**
 * @author Peter Kurfer
 * Created on 10/6/17.
 */
public class SimpleListImpl<T> implements SimpleList<T> {

	private ListElement<T> head;
	private int size;

	public SimpleListImpl() {
		head = null;
	}

	/**
	 * Add an object to the end of the list
	 * @param item item to add
	 */
	public void add(T item){
		/* special case empty list */
		if(head == null){
			head = new ListElement(item);
		}else {
			/* any other list length */
			ListElement current = head;
			while (current.getNext() != null){
				current = current.getNext();
			}
			current.setNext(new ListElement(item));
		}
		size++;
	}

	/**
	 * @return size of the list
	 */
	public int size() {
		return size;
	}

	/**
	 * Get a new SimpleList instance with all items of this list which match the given filter
	 * @param filter SimpleFilter instance
	 * @return new SimpleList instance
	 *//*
	public SimpleList filter(SimpleFilter filter){
		SimpleList result = new SimpleListImpl();
		for(Object o : this){
			if(filter.include(o)){
				result.add(o);
			}
		}
		return result;
	}*/

	/**
	 * @inheritDoc
	 */
	@Override
	public Iterator iterator() {
		return new SimpleIterator();
	}

	/**
	 * Helper class which implements the Iterator interface
	 * Has to be non static because otherwise it could not access the head of the list
	 */
	private class SimpleIterator<T> implements Iterator<T> {

		private ListElement current = head;

		/**
		 * @inheritDoc
		 */
		@Override
		public boolean hasNext() {
			return current != null;
		}

		/**
		 * @inheritDoc
		 */
		@Override
		public T next() {
			T tmp = (T)current.getItem();  // warum brauchen wir Cast, wenn getItem() T returns
			current = current.getNext();
			return tmp;
		}
	}

	/**
	 * Helper class for the linked list
	 * can be static because the ListElement does not need to access the SimpleList instance
	 */
	private static class ListElement<T> {
		private T item;
		private ListElement<T> next;

		ListElement(T item) {
			this.item = item;
			this.next = null;
		}

		/**
		 * @return get object in the element
		 */
		public T getItem() {
			return item;
		}

		/**
		 * @return successor of the ListElement - may be NULL
		 */
		public ListElement<T> getNext() {
			return next;
		}

		/**
		 * Sets the successor of the ListElement
		 * @param next ListElement
		 */
		public void setNext(ListElement next) {
			this.next = next;
		}
	}

	public static void main(String[] args) {
		SimpleListImpl<Integer> testList = new SimpleListImpl<>();

		try{
			testList.addDefault(Integer.class); // Warum geht es nicht mit Integer, aber es geht mit Person?
		}
		catch (Exception e){
			System.out.println("Exception message: " + e.getMessage());
		}

		System.out.println("Size is " + testList.size());

	}
}
