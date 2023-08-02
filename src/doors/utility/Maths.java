package doors.utility;

public class Maths {

    public static int mod(int value, int modulus) {
        while(value < 0) {
            return value + modulus;
        }

        return value % modulus;
    }
    
}