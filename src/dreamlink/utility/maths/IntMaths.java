package dreamlink.utility.maths;

public class IntMaths {

    public static int clamp(int value, int min, int max) {
        return value < min ? min : value > max ? max : value;
    }

    public static boolean inRange(int size, int index) {
        return index >= 0 && index < size;
    }
    
}
