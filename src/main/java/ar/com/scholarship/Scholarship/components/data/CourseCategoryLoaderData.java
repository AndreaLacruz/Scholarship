package ar.com.scholarship.Scholarship.components.data;

import ar.com.scholarship.Scholarship.model.entity.CourseCategory;
import ar.com.scholarship.Scholarship.model.repository.CourseCategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class CourseCategoryLoaderData implements ApplicationRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(CourseCategoryLoaderData.class);

    @Value("${spring.application.env}")
    private String appEnv;

    @Autowired @Qualifier("courseCategoryRepository")
    private CourseCategoryRepository categoryRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        if (appEnv.equals("dev") || appEnv.equals("qa")) {
            LOGGER.info("Loading Course Category initial data...\n");

            List<CourseCategory> categories = Arrays.asList(
                    new CourseCategory(1L, "Ciencias"),
                    new CourseCategory(2L, "Tecnología"),
                    new CourseCategory(3L, "Marketing"),
                    new CourseCategory(4L, "Administración"),
                    new CourseCategory(5L, "Idiomas"),
                    new CourseCategory(6L, "Diseño"),
                    new CourseCategory(7L, "Ingenieria"),
                    new CourseCategory(8L, "Servicios")
            );
            categories.forEach(courseCategory -> categoryRepository.save(courseCategory));
    }
}}
