package commons;

import java.util.Arrays;

/**
 * 冒泡排序
 * @author Administrator
 *
 */

public class BubbleSort {

	public static void main(String[] args) {
		int[] arr = { 2, 6, 9, 4, 5, 7, 9, 0, 3, 2, 1 };
		int temp = 0;
		for (int i = arr.length; --i > 0;) {
			for (int j = 0; j < i; j++) {
				if (arr[j] > arr[j + 1]) {
					temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
		}
		System.out.println(Arrays.toString(arr));

	}

}
