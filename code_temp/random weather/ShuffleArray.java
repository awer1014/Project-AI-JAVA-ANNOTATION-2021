import java.util.Arrays;
import java.util.Random;

public class ShuffleArray {

    public static void main(String[] args) {

        int[] array = {1, 2, 3, 4};

        Random rand = new Random();

        for (int i = 0; i < array.length; i++) {
            int randomIndexToSwap = rand.nextInt(array.length);
            int temp = array[randomIndexToSwap];
            array[randomIndexToSwap] = array[i];
            array[i] = temp;
        }
        for (int i = 0; i < array.length; i++){
            if(array[i] == 1){
                System.out.print("春");
            }
            if(array[i] == 2){
                System.out.print("夏");
            }
            if(array[i] == 3){
                System.out.print("秋");
            }
            if(array[i] == 4){
                System.out.print("冬");
            }
        }
        System.out.println(" ");
        System.out.println(Arrays.toString(array));
    }
}