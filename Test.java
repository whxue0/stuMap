package stuMap;
import java.util.Arrays;
/**
 * 获得数组全排列的一个实现算法
 *
 * @author 老紫竹的家(laozizhu.com)
 *
 */
public class Test {
    static int[] array = { 193, 2321, 543 };
    public static void main(String[] args) {
        getAllOrder(0, array.length - 1,array);
    }


    public static void getAllOrder(int begin, int end,int[]array) {
        if (begin == end) {
            check();
        } else {
            for (int i = begin; i <= end; i++) {
                // 交换数据
                swap(begin, i,array);
                getAllOrder(begin + 1, end,array);
                swap(i, begin,array);
            }
        }
    }
    public static void swap(int from, int to,int[] array) {
        // 这里应该加上各种防止无效交换的情况
        // 比如位置相同，或者2个位置的数据相同
        if (from == to) {
            return;
        }
        int tmp = array[from];
        array[from] = array[to];
        array[to] = tmp;
    }
    public static void check() {
        // 排列拿到了，可以进行你的判断了。
        System.out.println(Arrays.toString(array));
    }
}