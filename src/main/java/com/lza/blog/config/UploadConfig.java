package com.lza.blog.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description:
 * @Author: liuzian
 * @Date: Create in 18:11 2020/4/7
 */
@Component
@ConfigurationProperties(prefix = "upload")
@Data
public class UploadConfig {
    private String baseUrl;
    private List<String> allowTypes;
}
