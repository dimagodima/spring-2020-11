package ru.otus.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class Utils {

    private static final Logger log = LoggerFactory.getLogger(Utils.class);

    public static String resourceAsString(Class clazz, String source)  {
        try(InputStream is = clazz.getClassLoader().getResourceAsStream(source)) {
            return new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))
                    .lines().collect(Collectors.joining("\n"));
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return null;
    }
}
