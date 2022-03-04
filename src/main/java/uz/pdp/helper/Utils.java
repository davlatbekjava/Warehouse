package uz.pdp.helper;

import java.util.UUID;

public class Utils {

    public static boolean isEmpty(String str) {
        return str == null || str.isBlank();
    }

    public static boolean isEmpty(Object object) {
        return object == null;
    }

    public static String generateCode(){
        return UUID.randomUUID().toString();
    }
}
