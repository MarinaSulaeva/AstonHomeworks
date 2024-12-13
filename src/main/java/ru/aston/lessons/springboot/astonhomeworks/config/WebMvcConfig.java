package ru.aston.lessons.springboot.astonhomeworks.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@ComponentScan("ru.aston.lessons.springboot.astonhomeworks")
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {

}
