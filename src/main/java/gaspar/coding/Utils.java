package gaspar.coding;

public class Utils {

    public static int[] rotate(int[] position) {
        int[] result = new int[2];
        if (position[0] >= 0 && position[1] >= 0) {
            result[0] = position[1];
            result[1] = -position[0];
        } else if (position[0] >= 0 && position[1] < 0) {
            result[0] = position[1];
            result[1] = -position[0];
        } else if (position[0] < 0 && position[1] < 0) {
            result[0] = position[1];
            result[1] = -position[0];
        } else {
            result[0] = position[1];
            result[1] = -position[0];
        }
        return result;
    }

    public static int[] calculatePath(int[] source, int[] target) {
        return new int[]{target[0] - source[0], target[1] - source[1]};
    }
}
