package org.dynaresume.dao.mock.internal;

import java.util.Comparator;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

public class BeanComparator implements Comparator {

	private final Sort sort;

	public BeanComparator(Sort sort) {
		this.sort = sort;
	}

	public int compare(Object o1, Object o2) {
		if ((o1 instanceof Comparable) && (o2 instanceof Comparable)) {
			// Compare simple type like String, Integer etc
			Comparable c1 = ((Comparable) o1);
			Comparable c2 = ((Comparable) o2);
			return compare(c1, c2);
		}
		for (Order order : sort) {
			o1 = BeanPropertyHelper.getValue(o1, order.getProperty());
			o2 = BeanPropertyHelper.getValue(o2, order.getProperty());
			if ((o1 instanceof Comparable) && (o2 instanceof Comparable)) {
				// Compare simple type like String, Integer etc
				Comparable c1 = ((Comparable) o1);
				Comparable c2 = ((Comparable) o2);
				return compare(c1, c2);
			}
		}
		return 0;
	}

	private int compare(Comparable c1, Comparable c2) {
		for (Order order : sort) {
			if (order.isAscending()) {
				return c2.compareTo(c1);
			}
			return c1.compareTo(c2);
		}
		return 0;
	}

}
