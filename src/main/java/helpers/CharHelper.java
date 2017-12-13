package helpers;

public final class CharHelper {

    public static int CharToInt(char value){
        return  value + 0;
    }

    public static String CharToSring(char value){
        return  "" + value;
    }

    public static int CharNextSequence(char value){
        return  value + 1;
    }

    public static boolean IsCharSequence(char value, char nextValue){
        return CharNextSequence(value) == CharToInt(nextValue);
    }
}