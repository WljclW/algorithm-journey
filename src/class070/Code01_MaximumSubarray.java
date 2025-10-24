package class070;

// 子数组最大累加和
// 给你一个整数数组 nums
// 返回非空子数组的最大累加和
// 测试链接 : https://leetcode.cn/problems/maximum-subarray/
public class Code01_MaximumSubarray {

	// 动态规划
	/*
	dp[i] : 子数组必须以i位置的数做结尾，往左能延伸出来的最大累加和。。换句话说dp[i]表示以nums[i]结尾的子数组的最大和
	决策过程：以nums[i]结尾的子数组的最大和，取决于两种情况的最大值————
		情况1：当前nums[i]与前面的某一长度成为一个更大的子数组，此时dp[i]的计算 dp[i-1]+nums[i]
		情况2：当前nums[i]自己成为一个子数组，此时dp[i]的计算 nums[i]
		综上，dp[i] = max{dp[i-1]+nums[i] , nums[i]}
	优化：根据dp[i]的决策过程，可以看到，仅仅依赖于dp[i-1]，因此可以优化成O(1)的空间复杂度
	 */
	public static int maxSubArray1(int[] nums) {
		int n = nums.length;
		int[] dp = new int[n];
		dp[0] = nums[0];
		int ans = nums[0];
		for (int i = 1; i < n; i++) {
			dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
			ans = Math.max(ans, dp[i]);
		}
		return ans;
	}

	// 空间压缩。
	/*pre：代表一维dp中的dp[i-1]*/
	public static int maxSubArray2(int[] nums) {
		int n = nums.length;
		int ans = nums[0];
//		int pre = nums[0]; // pre变量声明在这里也可以，就不用再for循环条件中写了。
		for (int i = 1, pre = nums[0]; i < n; i++) {
			pre = Math.max(nums[i], pre + nums[i]);
			ans = Math.max(ans, pre);
		}
		return ans;
	}

	// 如下代码为附加问题的实现
	// 子数组中找到拥有最大累加和的子数组
	// 并返回如下三个信息:
	// 1) 最大累加和子数组的开头left
	// 2) 最大累加和子数组的结尾right
	// 3) 最大累加和子数组的累加和sum
	// 如果不止一个子数组拥有最大累加和，那么找到哪一个都可以
	/*说明：下面的三个变量标识的是返回信息。即整个数组最大子数组和的左边界、右边界、和*/
	public static int left;

	public static int right;

	public static int sum;

	// 找到拥有最大累加和的子数组
	// 更新好全局变量left、right、sum
	// 上游调用函数可以直接使用这三个变量
	// 相当于返回了三个值
	public static void extra(int[] nums) {
		sum = Integer.MIN_VALUE;
		/**说明：l,r,pre三个变量代表循环过程中r位置的最大子数组和的左边界、r位置最大子数组和的右边界、r位置的最大子数组和*/
		for (int l = 0, r = 0, pre = Integer.MIN_VALUE; r < nums.length; r++) {
			if (pre >= 0) {
				// 吸收前面的累加和有利可图
				// 那就不换开头
				pre += nums[r];
			} else {
				// 吸收前面的累加和已经无利可图
				// 那就换开头
				pre = nums[r];
				l = r;
			}
			/**如果r位置的最大子数组和大于全局的sum，就更新三个变量————更新这三个变量就等价于更新答案*/
			if (pre > sum) {
				sum = pre;
				left = l;
				right = r;
			}
		}
	}

}
