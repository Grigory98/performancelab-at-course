import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Integer[] array = new Integer[args.length];

        for(int i = 0; i < args.length; i++)
            array[i] = Integer.parseInt(args[i]);

        var result = findCertainResult(array);
        System.out.println(result);
    }

    public static int findCertainResult(Integer[] numbers) {
        int needNumber = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            int currentNumber = numbers[i];
            int currentDistance = Math.abs(currentNumber - 10);
            int closestDistance = Math.abs(needNumber - 10);

            if (currentDistance < closestDistance || (currentDistance == closestDistance && currentNumber > needNumber))
                needNumber = currentNumber;
        }
        return needNumber;
    }
}