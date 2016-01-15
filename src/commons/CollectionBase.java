package commons;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

/**
 *
一、练习
1、基本方法操作
2、模拟购物车 
 1)、添加物品  add(e) 
 2)、删除物品
 3)、结账
3、深入:体会  修改元素 查找元素 不方便之处
请统计每个单词出现的次数，从键盘输入字符串 输出该单词 的次数
this is a cat and that is a mouse where is the food and so on and so on
4、深入思考 利用一元容器  二元容器 分别实现  一对多  -->归组
test@sina.com;test@sxt.com;xp@163.com;xp@sina.com
查询 某个域名存在 哪些会员
某个会员 注册了哪些域名
二、练习
1、容器指的是“可以容纳其他对象的对象”，这种说法对吗?
2、容器与数组的区别是什么?
3、Collection/Set/List的联系跟区别?
4、Set和List的特点跟区别?
5、请查看api,学习addAll() ,研究下面的代码，效果一致吗? 分析说明之。
	Collection c = new HashSet();	
	Collection c2 = new HashSet();
	Apple a = new Apple();	
	c.add(a);	
	c2.addAll(c);   //增加另一个容器中所有的元素！
    -----------------------------------------------------------------
	Collection c = new HashSet();  
	Collection c2 = new HashSet();
	Apple a = new Apple();	
	c.add(a);	
	c2.add(c);
6、想取两个容器中元素的交集，使用哪个方法?
7、说明isEmpty的作用，并说明下面代码有问题吗?
	Collection c = null;
	System.out.println(c.isEmpty()); 
8、随机生成20个数字，存放到容器中，从容器中获取每个数求和
9、创建购物车，添加、删除 物品，查看购物车的数量
10、我想定义一个容器。该容器既可以放：Dog对象、也可以放Cat对象、还可以放Integer对象，怎么定义?使用泛型如何定义?

 * @author liguoliang
 *
 */
public class CollectionBase {
	public static void main(String[] args) {

		Map<String, Integer> map = new HashMap<String, Integer>();

		for (int i = 0; i < args.length; i++) {
			Integer num = map.get(args[i]);
			//map.put(args[i], (num == null ? 1 : new Integer(num.intValue() + 1)));
			map.put(args[i], (num == null ? 1 : num + 1));
		}
		System.out.println(map);
		sort();
	}

	public static Map<String, Integer> sort() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		//分割字符串
		StringTokenizer token = new StringTokenizer("this is a cat and that is a mouse where is the food and so on and so on");
		while (token.hasMoreElements()) {
			String letter = token.nextToken();
			//统计  查看是否存在
			//1、存在 直接修改
			if (map.containsKey(letter)) {
				map.put(letter, map.get(letter) + 1); //覆盖
			} else {
				//2、不存在 创建 放入
				map.put(letter, 1);
			}
		}

		//查看 所有  key+get(key)
		Set<String> keys = map.keySet();
		Iterator<String> keysIt = keys.iterator();
		while (keysIt.hasNext()) {
			String key = keysIt.next();
			Integer value = map.get(key);
			System.out.print(key + "=" + value + ", ");
		}
		return map;
	}

	class Person implements Comparable<Object> {
		private String fristName;
		private String lastName;

		public Person() {
			// TODO Auto-generated constructor stub
		}

		public Person(String fristName, String lastName) {
			super();
			this.fristName = fristName;
			this.lastName = lastName;
		}

		public String getFristName() {
			return fristName;
		}

		public void setFristName(String fristName) {
			this.fristName = fristName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		/**
		 * 重写equals方法
		 */
		public boolean equals(Object obj) {
			if (obj instanceof Person) {
				Person name = (Person) obj;
				return fristName.equals(name.fristName) && lastName.equals(name.lastName);
			}
			return super.equals(obj);
		}

		/**
		 * 当这个对象成为索引时，重写hashCode方法
		 */
	
		@Override
		public int compareTo(Object o) {
			// TODO Auto-generated method stub
			Person name = (Person) o;
			int result = lastName.compareTo(name.lastName);
			return result != 0 ? result : fristName.compareTo(name.fristName);
		}

		@Override
		public int hashCode() {
			// TODO Auto-generated method stub
			return super.hashCode();
		}

	}
}