package commons;

import java.util.Arrays;

/**
 *  吸血鬼数字是指位数为偶数的数字，可以由一对数字相乘而得到，而这对数字各包含乘积的一半位数的数字，
 *  其中从最初的数字中选取的数字可以任意排序。
	例如：
	1260 = 21 * 60 1827 = 21 * 87 2187 = 27 * 81
 * @author Lenovo
 *
 */
public class Vampire {
	public static void main(String[] args) {
		for (int x = 1000; x < 9999; x++) {
			if (x % 100 == 0)
				continue;
			int[] a = new int[4];
			a[0] = x / 1000;
			a[1] = (x - a[0] * 1000) / 100;
			a[2] = (x - a[0] * 1000 - a[1] * 100) / 10;
			a[3] = x - a[0] * 1000 - a[1] * 100 - a[2] * 10;
			for (int i = 0; i < 2; i++)
				for (int j = 0; j < 4; j++) {
					if (j != i) {
						for (int k = 0; k < 4; k++) {
							if ((k != j) && (k != i)) {
								for (int l = 0; l < 4; l++) {
									if ((l != k) && (l != i) && (l != j)) {
										int res = (a[i] * 10 + a[j]) * (a[k] * 10 + a[l]);
										if (res == x)
											System.out.println(a[i] + "" + a[j] + "*" + a[k] + "" + a[l] + "=" + x);
									}
								}
							}
						}
					}
				}
		}
	}
}

/**
 * 问题描述：
 *        吸血鬼数字是指位数为偶数的数字，可以由一对数字相乘而得到，而这对数字各包含乘积的一半位数的数字，
 *   其中从最初的数字中选取的数字可以任意排序。
 * 例如：
 *      1260 = 21 * 60 1827 = 21 * 87 2187 = 27 * 81
 * 要求输出所有四位数的吸血鬼数字。
 * 
 * @author heng.ai
 * 
 * 注：参考了CSDN一朋友的写法
 */

class VampireNumber {

	public static void main(String[] args) {
		for (int i = 1; i < 100; i++) {
			for (int j = i + 1; j < 100; j++) {
				//只要求输出四位数
				if (i * j >= 1000) {
					String a = i + "" + j;
					String b = i * j + "";
					if (equal(a, b)) {
						System.out.printf("%d * %d = %d", i, j, i * j);
						System.out.println();
					}
				}
			}
		}
	}

	//判断两个字符串包含的数字是否一致
	private static boolean equal(String a, String b) {
		//先排序
		char[] as = a.toCharArray();
		char[] bs = b.toCharArray();
		Arrays.sort(as); //排序
		Arrays.sort(bs); //排序
		if (Arrays.equals(as, bs)) {
			return true;
		}
		return false;
	}

}