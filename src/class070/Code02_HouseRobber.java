package class070;

// 数组中不能选相邻元素的最大累加和
// 给定一个数组，可以随意选择数字
// 但是不能选择相邻的数字，返回能得到的最大累加和
// 测试链接 : https://leetcode.cn/problems/house-robber/
public class Code02_HouseRobber {

	// 动态规划
	/**
	dp[i] : nums[0...i]范围上可以随意选择数字，但是不能选相邻数，能得到的最大累加和
	base case：如果只有一个数就只能选它；如果有两个数则任选其一，当然选择更大的那个
	决策过程：到达任何一个位置，怎么决策呢？
		情况1：不使用当前的数，则从这个数之前的所有数中选，即dp[i-1]；
		情况2：使用当前的数，则前面的那个数不能选，前面的前面的那个数可以选，即dp[i-2] + nums[i]
	 	综上，dp[i] = max{dp[i-1] , dp[i-2]+nums[i]}
	优化： 从上面的决策过程可以看出，dp[i]会依赖于dp[i-1]以及dp[i-2]，因此可以优化成使用两个变量记录前
	 	面两个位置的dp值就可以了
	 */
	public static int rob1(int[] nums) {
		int n = nums.length;
		if (n == 1) {
			return nums[0];
		}
		if (n == 2) {
			return Math.max(nums[0], nums[1]);
		}
		int[] dp = new int[n];
		dp[0] = nums[0];
		dp[1] = Math.max(nums[0], nums[1]);
		for (int i = 2; i < n; i++) {
			dp[i] = Math.max(dp[i - 1], Math.max(nums[i], dp[i - 2] + nums[i]));
		}
		return dp[n - 1];
	}

	// 空间压缩
	public static int rob2(int[] nums) {
		int n = nums.length;
		if (n == 1) {
			return nums[0];
		}
		if (n == 2) {
			return Math.max(nums[0], nums[1]);
		}
		int prepre = nums[0]; /**prepre就相当于一维中的dp[i-2]*/
		int pre = Math.max(nums[0], nums[1]); /**pre就相当于一维中的dp[i-1]*/
		for (int i = 2, cur; i < n; i++) {
			cur = Math.max(pre, Math.max(nums[i], prepre + nums[i]));
			prepre = pre;
			pre = cur;
		}
		return pre;
	}

}
