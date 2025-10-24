package class070;

import java.util.Arrays;

// 子矩阵最大累加和问题
// 给定一个二维数组grid，找到其中子矩阵的最大累加和
// 返回拥有最大累加和的子矩阵左上角和右下角坐标
// 如果有多个子矩阵都有最大累加和，返回哪一个都可以
// 测试链接 : https://leetcode.cn/problems/max-submatrix-lcci/
public class Code06_MaximumSubmatrix {

	// 如果行和列的规模都是n，时间复杂度O(n^3)，最优解了
	/**
	 	1. 思想：把矩形区域同一列的那些数加起来，最后举行会被压缩到一维（见下面举的例子），这个一维
	 的最大子数组和就是矩形区域所有数的和。
	 	2. 举个例子：
	 		某一时刻我们选定的矩形区域为如下的”3*6“的矩形区域————
	 				3	-2	1	0	-4	-5
	 				-2	9	2	4	-2	-1
	 				-6	-4	0	7	3	7
	 		然后我们把它压缩(同一列的所有数相加)为一维的数组，如下————
	 				(3-2-6)	  (-2+9-4)	(1+2+0)	 (0+4+7)	(-4-2+3)	(-5-1+7)
	 			即：-5	3	3	11	-3	1
	 	3. 感悟：这个题与85题是类似的，都是需要压缩到一维然后计算。。。但也有区别。
	 		85题中每一个位置如果有数，代表高度为一的这么个高度；但是这个题任何一个位置的数就代
	 	表一个实实在在的数
	 */
	public static int[] getMaxMatrix(int[][] grid) {
		int n = grid.length;
		int m = grid[0].length;
		int max = Integer.MIN_VALUE;
		int a = 0, b = 0, c = 0, d = 0;
		int[] nums = new int[m];
		/**变量up代表：当前研究到哪一行了*/
		for (int up = 0; up < n; up++) {
			Arrays.fill(nums, 0);
			/**down代表：从up行到down这一行形成的矩阵*/
			for (int down = up; down < n; down++) {
				// 如下代码就是题目1(见类Code01_MaximumSubarray的最后代码)的附加问题 :
				// 子数组中找到拥有最大累加和的子数组
				/**下面的for循环是依次计算形成矩阵的最大累加和，其中右下角就是坐标（down，r）*/
				for (int l = 0, r = 0, pre = Integer.MIN_VALUE; r < m; r++) {
					nums[r] += grid[down][r];
					if (pre >= 0) {
						pre += nums[r];
					} else {
						pre = nums[r];
						l = r;
					}
					/**如果当前的计算值pre大于全局的max，则更新max，初次还要更新左上角、右下角的坐标*/
					if (pre > max) {
						max = pre;
						a = up;
						b = l;
						c = down;
						d = r;
					}
				}
			}
		}
		return new int[] { a, b, c, d };
	}

}
