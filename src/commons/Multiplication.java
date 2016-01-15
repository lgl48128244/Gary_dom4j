package commons;

public class Multiplication {

	public static void main(String[] args) {
		test3();
	}

	public static void test1() {
		System.out.println("九九乘法表啊~~~");
		int i = 1;//循环的初始条件
		while (i < 10) {//当i<10时，执行循环体
			int j = 1;
			while (j <= i) {//当j<=i时，执行循环体
				//System.out.print(j+"x"+i+"="+i*j+"，"+'\t');   不换行输出,\n为换行符,\t为TAB符,'\r'为回车符。
				System.out.print(j + "x" + i + "=" + i * j + "\t");
				j++;
			}
			System.out.print("\n");//每一行的换行
			i++;
		}
	}

	public static void test2() {
		for (int i = 1; i < 10; i++) {
			for (int j = 1; j <= 9; j++) {
				if (i > j) {
					System.out.print(j + "x" + i + "=" + i * j + "\t");
				}
				if (i == j) {
					System.out.print(j + "x" + i + "=" + i * j + "\n");
				}
			}
		}
	}

	public static void test3() {
		for (int i = 1; i < 10; i++) {
			for (int j = 1; j <= i; j++) {
				System.out.print(j + "x" + i + "=" + i * j + "\t");
			}
			System.out.println();
		}
	}
}
