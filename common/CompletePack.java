package common;

import java.util.Scanner;

/**
 * @author ppz
 * @version 1.0
 * @date 2020/9/4 22:08
 * @description: 已知：有N种物品和一个容量为V 的背包，每种物品都有无限件可用。放入第i种物品的耗费的空间是C[i]，得到的价值是W[i]。
 *
 * 求解：将哪些物品装入背包，可使这些物品的耗费的空间总和不超过背包容量，且价值总和最大？
 *
 *
 */

/**
 * 输入格式：
 5 10
 2 2 6 5 4
 6 3 5 4 6
 result:30
 * 第一行是物体个数、背包总空间；
 * 第二行是每个物体的空间；
 * 第三行是每个物体的收益。
 */
public class CompletePack {
    // N表示物体的个数，V表示背包的载重
    int N,V;
    //用于存储每个物体的重量，下标从1开始
    private int[] weight;
    //存储每个物体的收益，下标从1开始
    private int[] value;
    //解法一，二维数组，用来保存每种状态下的最大收益
    private int[][] F;
    //解法二，降成一维数组，用来保存每种状态下的最大收益
    private int[] F2;


    public static void main(String[] args) {
        CompletePack cp = new CompletePack();
        cp.init2();
        cp.completePack2();
    }


    private void init() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        V = sc.nextInt();

        //下标从1开始，表示第1个物品
        weight = new int[N + 1];
        value = new int[N + 1];
        F= new int[N + 1][V + 1];

        for(int i = 1; i <= N; i++) {
            weight[i] = sc.nextInt();
        }

        for(int i = 1; i <= N; i++) {
            value[i] = sc.nextInt();
        }
    }



    /**
     * 解法一
     * 按照01背包的思路，用二维数组F[i][v] 表示  前i件物品恰放入一个容量恰为v的背包可以获得的最大价值，则状态转移方程为
     *  * F[i,v] = max{ F[i - 1, v - kCi] + kWi | 0 <= kCi <= v}
     */
    private void completePack() {
        //对二维数组F进行初始化
        for(int i = 0; i <= V; i++) {
            F[0][i] = 0;
        }

        //注意边界问题，i是从1开始的
        for(int i = 1; i <= N; i++) {
            //j是正序、降序没影响
            for (int j = 0; j <= V; j++) {
                //和01背包的区别就在这里，01背包只有两种状态：放与不放
                //而完全背包可以放0到k个物品i，同样是取最大
                for (int k = 0; k <= V / weight[i]; k++) {
                    if (j >= k * weight[i]) {
                        //注意：状态转移方程是F[i][j]，而不是F[i - 1][j]
                        //因为这时放k个第i个物品，之后还可能继续放这个物体，所以应是F[i][j]
                        F[i][j] = Math.max(F[i - 1][j - k * weight[i]] + k * value[i], F[i][j]);
                    } else {
                        //可以省略，这里为什么不是F[i - 1][j]
                        //因为刚开始k=0，j >= 0 * weight[i]肯定成立，此时F[i][j] = F[i - 1][j]。
                        F[i][j] = F[i][j];
                    }
                }
            }
        }

        //打印所有结果，我们要求的是F[N][V]
        for(int i = 0; i <= N; i++) {
            for(int j = 0; j <= V; j++) {
                System.out.print(F[i][j] + " ");
            }
            System.out.println();
        }
    }


    public void init2() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        V = sc.nextInt();

        //下标从1开始，表示第1个物品
        weight = new int[N + 1];
        value = new int[N + 1];
        F2 = new int[V + 1];//注意是 V + 1

        for(int i = 1; i <= N; i++) {
            weight[i] = sc.nextInt();
        }

        for(int i = 1; i <= N; i++) {
            value[i] = sc.nextInt();
        }
    }


    /**
     *完全背包问题，时间复杂度为O(NV)的算法
     *与01背包的一维数组基本一样，唯一不同的就是v的遍历顺序是相反的
     */
    private void completePack2() {
        for (int i = 0; i <= V; i++) {
            F2[i] = 0;
        }

        for (int i = 1; i <= N ; i++) {
            for (int j = 0; j <= V; j++) {
                if(j >= weight[i]) {
                    F2[j] = Math.max(F2[j - weight[i]] + value[i], F2[j]);
                }
            }
        }


        for (int i = 0; i <= V; i++) {
            System.out.print(F2[i] + " ");
        }
        System.out.println();
    }




}