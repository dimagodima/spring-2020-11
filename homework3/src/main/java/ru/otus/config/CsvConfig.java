package ru.otus.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("csv")
public class CsvConfig {
    private String rupath;
    private String enpath;
    private int score;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getRupath() {
        return rupath;
    }

    public void setRupath(String rupath) {
        this.rupath = rupath;
    }

    public String getEnpath() {
        return enpath;
    }

    public void setEnpath(String enpath) {
        this.enpath = enpath;
    }
}
