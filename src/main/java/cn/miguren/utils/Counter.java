package cn.miguren.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 一个简易计数器
 * 
 * @author neoliu6
 * @create 2018年7月31日 下午3:56:20
 * @param <T>
 */
public class Counter<T> {
	private Map<T, CountPair<T>> data = new LinkedHashMap<T, CountPair<T>>();
	private int increment = 1;

	/**
	 * 
	 * @return
	 */
	public static <T> Counter<T> create() {
		return new Counter<>();
	}

	/**
	 * 
	 * @return
	 */
	public static <T> Counter<T> create(List<T> list) {
		return new Counter<>(list);
	}

	/**
	 * Counter<br>
	 * 计数器 <br>
	 */
	private Counter() {
	}

	/**
	 * Counter<br>
	 * 计数器 <br>
	 * 
	 * @param list
	 */
	private Counter(List<T> list) {
		if (list != null && list.size() > 0) {
			for (T t : list) {
				this.incr(t, 1);
			}
		}
	}

	/**
	 * select data which appears times between min count and max count.
	 * 
	 * @param minCountInclusive
	 * @param maxCountExlusive
	 * @return
	 */
	public List<T> select(int minCountInclusive, int maxCountExlusive) {
		List<T> list = new ArrayList<T>();
		if (maxCountExlusive > minCountInclusive) {
			int count = 0;
			for (CountPair<T> countPair : data.values()) {
				count = countPair.getCount();
				if (count >= minCountInclusive && count < maxCountExlusive) {
					list.add(countPair.getT());
				}
			}
		}
		return list;
	}

	/**
	 * increase ${count} appear times .
	 * 
	 * @param t
	 * @param count
	 */
	public void incr(T t, Integer count) {
		synchronized (t) {
			if (count == null) {
				return;
			}
			CountPair<T> countPair = data.get(t);
			if (countPair == null) {
				countPair = new CountPair<T>(t, count);
			} else {
				countPair.count += count;
			}
			data.put(t, countPair);
		}
	}

	/**
	 * increase <b>${increment}</b> appear times . <br>
	 * If <b>${increment}</b> not set,1 as default.
	 * 
	 * @param t
	 */
	public void incrCount(T t) {
		incr(t, increment);
	}

	/**
	 * get result data.
	 * 
	 * @return
	 */
	public Map<T, Integer> getData() {
		return toMap();
	}

	/**
	 * get mapped result data.
	 * 
	 * @return
	 */
	private Map<T, Integer> toMap() {
		Map<T, Integer> simpleData = new LinkedHashMap<T, Integer>();
		for (T t : data.keySet()) {
			simpleData.put(t, data.get(t).getCount());
		}
		return simpleData;
	}

	/**
	 * get appear times of data.
	 * 
	 * @param t data
	 * @return
	 */
	public int getCount(T t) {
		CountPair<T> countPair = data.get(t);
		return countPair == null ? 0 : countPair.getCount();
	}

	/**
	 * 
	 * @param increment
	 */
	public Counter<T> defaultIncrement(int increment) {
		this.increment = increment;
		return this;
	}

	/**
	 * get data size. <br>
	 * 
	 * @return Statistic object size.
	 */
	public int size() {
		return data.size();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		List<CountPair<T>> list = new ArrayList<Counter.CountPair<T>>(data.values());
		Collections.sort(list);
		for (CountPair<T> cp : list) {
			builder.append(cp.getT()).append("\t").append(cp.getCount()).append("\r\n");
		}
		return builder.length() > 2 ? builder.substring(0, builder.length() - 2) : builder.toString();
	}

	/**
	 * 
	 * @author neoliu6
	 * @create 2018年7月31日 下午4:29:27
	 * @param <T>
	 */
	public static class CountPair<T> implements Comparable<CountPair<?>> {
		private int count;
		private T t;

		public CountPair(T t, int count) {
			this.t = t;
			this.count = count;
		}

		public T getT() {
			return t;
		}

		public int getCount() {
			return count;
		}

		public void incrCount(int incr) {
			this.count += incr;
		}

		public void incrCount() {
			this.count++;
		}

		@Override
		public int compareTo(CountPair<?> o) {
			return o == null ? 1 : (this.count > o.count ? 1 : (this.count < o.count ? -1 : 0));
		}
	}
}
