package com.weiyi.alorithm.recusion;

import java.util.ArrayList;
import java.util.List;

/**
 * 递归解决汉诺塔问题
 *
 * @author weiyi
 * @date 2023-01-11
 */
public class Hanota {

    public static void main(String[] args) {
        Hanota hanota = new Hanota();

        List<Integer> A = new ArrayList<>();
        A.add(2);
        A.add(1);
        A.add(0);
        List<Integer> B = new ArrayList<>();
        List<Integer> C = new ArrayList<>();
        hanota.hanota(A, B, C);

        C.forEach(System.out::println);
    }

    public void hanota(List<Integer> A, List<Integer> B, List<Integer> C) {
        hanota(A.size(), A, C, B);
    }

    public void hanota(int n, List<Integer> from, List<Integer> to, List<Integer> other) {
        if (n == 1) {
            // 终止条件
            to.add(from.remove(from.size() - 1));
            return;
        }

        // 一共三步

        // 1.把N-1从from移动到other
        hanota(n - 1, from, other, to);

        // 2.把最后一层从from移动到other
        to.add(from.remove(from.size() - 1));

        // 3.把N-1从other移动到to
        hanota(n - 1, other, to, from);
    }
}
