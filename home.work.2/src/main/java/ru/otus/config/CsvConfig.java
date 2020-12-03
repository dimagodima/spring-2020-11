package ru.otus.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("/application.properties")
public class CsvConfig {

    @Value("${csv.path}")
    private String path;

    @Value("${pass.score.test}")
    private int passScore;

    public int getPassScore() {
        return passScore;
    }

    public String getPath() {
        return path;
    }
}
