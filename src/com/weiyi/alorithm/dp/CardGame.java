package com.weiyi.alorithm.dp;

/**
 * 纸牌游戏，给定一个arr数组，数组里面每个元素是纸牌的点数，然后你和你的朋友没每人从最左或最右拿一张牌，
 * 直到牌被拿，假如你们两个都是绝顶聪明的人，每次都拿最优的牌，返回最终点数较大的牌
 * <p>
 * 解题思路：把最小的留给对方，如果必须要留最大的，拿当前能拿最大的
 *
 * @author weiyi
 * @date 2023-01-11
 */
public class CardGame {

    public static void main(String[] args) {
        CardGame cardGame = new CardGame();
        System.out.println(cardGame.PredictTheWinner(new int[]{1, 5, 7}));
    }

    public boolean PredictTheWinner(int[] nums) {
        int before = before(nums, 0, nums.length - 1);
        int after = after(nums, 0, nums.length - 1);
        return before >= after;
    }


    /**
     * 先手拿一个
     *
     * @param cards 卡牌数组
     * @param left  左边索引
     * @param right 右边索引位置
     * @return 最优解（让对方下一次拿的最小化）
     */
    public int before(int[] cards, int left, int right) {
        if (right == left) {
            // 只有一个，直接拿走
            return cards[left];
        }

        // 先手拿左边的最大值，等于左边的卡牌+对手拿卡牌之后后手拿牌的最大值
        int leftMax = cards[left] + after(cards, left + 1, right);

        // 右边最大值
        int rightMax = cards[right] + after(cards, left, right - 1);

        return Math.max(leftMax, rightMax);
    }

    /**
     * 后手拿牌，必然只拿当前位置能拿到的较小的，因为对手先手会让我们只能拿到最小的
     *
     * @param cards 卡牌数组
     * @param left  左边索引
     * @param right 右边索引
     * @return 当前能拿最小的
     */
    public int after(int[] cards, int left, int right) {
        if (left == right) {
            // 最后一个元素，会被对手先手拿，所以返回0
            return 0;
        }

        // 对手拿走左边元素
        int leftMax = before(cards, left + 1, right);

        // 对手拿走右边元素
        int rightMax = before(cards, left, right - 1);

        // 对手先拿，对手帮我们选择了最小值
        return Math.min(leftMax, rightMax);
    }

}
