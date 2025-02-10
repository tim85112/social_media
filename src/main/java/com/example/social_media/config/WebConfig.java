

package com.example.social_media.config;

        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;
        import org.springframework.beans.factory.annotation.Value;
        import org.springframework.context.annotation.Configuration;
        import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
        import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

        import java.nio.file.Paths;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private static final String UPLOAD_DIR = "file:C:/Users/ivan/Desktop/專案圖片/";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**")
                .addResourceLocations(UPLOAD_DIR);
    }
}


