# simplecounter
一个简单的计数类。
源于以前一个需要用到计数的需求。很简单。
```java
    // 创建计数器实例
    Counter<Integer> counter = Counter.create();
    // 开始计数
    for (int i = 0; i < 100; i++) {
    	// 增加次数(默认增量)
	counter.incr((int) (Math.random() * 5), 1);
    }
    //增加指定次数
    counter.incr(2, 10086);
    // 获取次数
    System.out.println("2 出现次数 : " + counter.getCount(3));
    // 转为次数表
    System.out.println("转为次数表：" + counter.getData());
```

貌似java8 stream之后,这个没啥意义了,,ԾㅂԾ,,
