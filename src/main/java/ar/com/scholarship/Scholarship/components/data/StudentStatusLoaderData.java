package ar.com.scholarship.Scholarship.components.data;

import ar.com.scholarship.Scholarship.model.entity.StudentStatus;
import ar.com.scholarship.Scholarship.model.repository.StudentStatusRepository;
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
public class StudentStatusLoaderData implements ApplicationRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentStatusLoaderData.class);

    @Value("${spring.application.env}")
    private String appEnv;

    @Autowired
    @Qualifier("studentStatusRepository")
    private StudentStatusRepository statusRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        LOGGER.info("Environment: " + appEnv);

        if (appEnv.equals("dev") || appEnv.equals("qa")) {

            LOGGER.info("Loading Student Status initial data...");

            List<StudentStatus> statusList = Arrays.asList(
                    new StudentStatus("Estudiante Aprobado"),
                    new StudentStatus("Estudiante Rechazado"),
                    new StudentStatus("Pendiente")
            );
            statusList.forEach(studentStatus -> statusRepository.save(studentStatus));

        }
    }
}
