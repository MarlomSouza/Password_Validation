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

    public static boolean IsCharSequence(char fisrtValue, char secondValue, char thirdValue){
        boolean primeiroMaisUmIgualSegundo = CharNextSequence(fisrtValue) == CharToInt(secondValue);
        boolean segundoMaisUmIgualTerceiro = CharNextSequence(secondValue) == CharToInt(thirdValue);
        return (primeiroMaisUmIgualSegundo && segundoMaisUmIgualTerceiro);
    }
}