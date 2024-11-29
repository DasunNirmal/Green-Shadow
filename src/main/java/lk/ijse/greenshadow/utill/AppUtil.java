package lk.ijse.greenshadow.utill;

import java.util.Base64;

public class AppUtil {
    public static String imageToBase64(byte[] img) {
        return Base64.getEncoder().encodeToString(img);
    }
}
