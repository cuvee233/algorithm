package com.weiyi.leetcode.map;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * StockPrice() 初始化对象，当前无股票价格记录。
 * void update(int timestamp, int price) 在时间点 timestamp 更新股票价格为 price 。
 * int current() 返回股票 最新价格 。
 * int maximum() 返回股票 最高价格 。
 * int minimum() 返回股票 最低价格 
 */
class StockPrice {

    /**
     * key -> price
     * val -> count 当前value出现的次数
     */
    private final TreeMap<Integer, Integer> priceMap;
    /**
     * key -> timestamp
     * val -> price
     */
    private final Map<Integer, Integer> stockMap;
    /**
     * 最新时间戳
     */
    private int maxTimestamp;

    public StockPrice() {
        priceMap = new TreeMap<>();
        stockMap = new HashMap<>();
    }

    public void update(int timestamp, int price) {

        // maxTimestamp
        maxTimestamp = Math.max(timestamp, maxTimestamp);

        // 去重
        Integer prePrice = stockMap.getOrDefault(timestamp, 0);
        if (prePrice > 0) {
            // 此次操作是更新
            Integer count = priceMap.get(prePrice);
            // 之前的price次数减一，等于0时移除price
            if (--count == 0) {
                priceMap.remove(prePrice);
            } else {
                priceMap.put(prePrice, count);
            }
        }

        // 加入集合
        stockMap.put(timestamp, price);
        priceMap.put(price, priceMap.getOrDefault(price, 0) + 1);
    }

    public int current() {
        return stockMap.get(maxTimestamp);
    }

    public int maximum() {
        return priceMap.lastKey();
    }

    public int minimum() {
        return priceMap.firstKey();
    }

}