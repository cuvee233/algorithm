package com.weiyi.leetcode.LinkedList.dp;

/**
 * 给你一个整数 n，请你帮忙统计一下我们可以按下述规则形成多少个长度为 n 的字符串：
 * <p>
 * 字符串中的每个字符都应当是小写元音字母（'a', 'e', 'i', 'o', 'u'）
 * 每个元音 'a' 后面都只能跟着 'e'
 * 每个元音 'e' 后面只能跟着 'a' 或者是 'i'
 * 每个元音 'i' 后面 不能 再跟着另一个 'i'
 * 每个元音 'o' 后面只能跟着 'i' 或者是 'u'
 * 每个元音 'u' 后面只能跟着 'a'
 * 由于答案可能会很大，所以请你返回 模 10^9 + 7 之后的结果。
 * <p>
 *  
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-vowels-permutation
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author weiyi
 * @date 2022/1/17
 */
public class CountVowelPermutation_1220 {

    public static void main(String[] args) {
        countVowelPermutation(3);
    }

    public static int countVowelPermutation(int n) {
        long mod = 1000000007;
        long[] dp = new long[5];
        long[] ndp = new long[5];
        for (int i = 0; i < 5; ++i) {
            dp[i] = 1;
        }
        for (int i = 2; i <= n; ++i) {
            /* a前面可以为e,u,i */
            ndp[0] = (dp[1] + dp[2] + dp[4]) % mod;
            /* e前面可以为a,i */
            ndp[1] = (dp[0] + dp[2]) % mod;
            /* i前面可以为e,o */
            ndp[2] = (dp[1] + dp[3]) % mod;
            /* o前面可以为i */
            ndp[3] = dp[2];
            /* u前面可以为i,o */
            ndp[4] = (dp[2] + dp[3]) % mod;
            System.arraycopy(ndp, 0, dp, 0, 5);
        }
        long ans = 0;
        for (int i = 0; i < 5; ++i) {
            ans = (ans + dp[i]) % mod;
        }
        return (int) ans;
    }


    char[] c = new char[]{'a','e','i','o','u'};
    int mod = 1000000007;

    public int dfs(char pre, int cur,int n) {
        if (cur == n)
            return 1;
        int sum = 0;
        for (int i = 0; i < 5; i++) {
            // 规则1
            if (pre == 'a' && c[i] == 'e') {
                sum += dfs(c[i], cur + 1,n);
            }
            // 规则2
            else if (pre == 'e' && (c[i] == 'a' || c[i] == 'i')) {
                sum += dfs(c[i], cur + 1,n);
            }
            // 规则3
            else if (pre == 'i' && c[i] != 'i') {
                sum += dfs(c[i], cur + 1,n);
            }
            // 规则4
            else if (pre == 'o' && (c[i] == 'i' || c[i] == 'u')) {
                sum += dfs(c[i], cur + 1,n);
            }
            // 规则5
            else if (pre == 'u' && c[i] == 'a') {
                sum += dfs(c[i], cur + 1,n);
            }
            sum = sum % mod;
        }
        return sum;
    }

    public int countVowelPermutation1(int n) {
        if (n == 1) return 5;
        int result = 0;
        for (int i = 0; i < 5; i++) {
            result += dfs(c[i], 1,n);
            result = result % mod;
        }
        return result;
    }

}
