package cn.bupt.dssc.utils;

import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

public class ConvertBase64 {

    public static String uri2Base64(String uri) {
        File file = new File(uri);
        byte[] bytes;
        try {
            bytes = Files.toByteArray(file);
        } catch (IOException e) {
            // 报错，日志
            return null;
        }

        Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(bytes);
    }
}
