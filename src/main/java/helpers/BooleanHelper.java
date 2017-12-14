package helpers;

public final class BooleanHelper{
    private static int ConvertBooleanToInt(boolean value){
        return value ? 1: 0;
    }

    public static boolean MinPositive(int minPositive, boolean...bs){
        int count =0;
        for (boolean var : bs) {
            count += ConvertBooleanToInt(var);
        }
        return count >= minPositive;
    }
}