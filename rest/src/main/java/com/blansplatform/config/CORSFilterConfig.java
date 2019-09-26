//package com.blansplatform.config;
//
//        import org.springframework.context.annotation.Configuration;
//        import org.springframework.web.servlet.config.annotation.*;
//
//        import static javax.ws.rs.HttpMethod.*;
//
//@Configuration
//@EnableWebMvc
//public class CORSFilterConfig implements WebMvcConfigurer {
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        CorsRegistration corsRegistration = registry.addMapping("/**");
//        corsRegistration.allowedMethods("*");
//        corsRegistration.allowedOrigins("*");
//        corsRegistration.allowedHeaders("*");
//        corsRegistration.maxAge(1111111111111111111L);
//        corsRegistration.allowCredentials(true);
//    }
//}
