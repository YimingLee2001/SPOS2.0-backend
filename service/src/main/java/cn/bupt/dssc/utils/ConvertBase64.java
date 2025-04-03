package cn.bupt.dssc.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

@Slf4j
public class ConvertBase64 {

    public static String uri2Base64(String uri) {
        byte[] bytes;
        try {
            Path path = Paths.get(uri);
            bytes = Files.readAllBytes(path);
        } catch (IOException e) {
            log.warn("文件资源读取错误 -> ", uri);
            return null;
        }

        Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(bytes);
    }
}
