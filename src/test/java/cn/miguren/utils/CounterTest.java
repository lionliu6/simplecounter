/**
 * 
 */
package cn.miguren.utils;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * @author neoliu6
 * @create 2018年7月31日 下午4:30:16
 */
@RunWith(JUnit4.class)
public class CounterTest {
	@Test
	public void test() {
		Counter<Integer> counter = Counter.create();
		//
		System.out.println(">> Count random numbers:");
		for (int i = 0; i < 100; i++) {
			counter.incr((int) (Math.random() * 5), 1);
		}
		//
		System.out.println(">> Number appears:");
		System.out.println(counter);
		//
		System.out.println(">> Incr 2 10086:");
		counter.incr(2, 10086);
		System.out.println(">> Number appears:");
		System.out.println(counter);
		//
		System.out.println(">> Count items in list :");
		System.out.println(Counter.create(Arrays.asList(new String[]{
				"孙悟空",		
				"八戒",		
				"八戒",		
				"孙悟空",		
				"孙悟空",		
				"沙悟净",		
				"八戒",		
				"唐僧",		
				"",		
		})));
	}
}
