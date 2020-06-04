package ar.com.scholarship.Scholarship.components.data;

import ar.com.scholarship.Scholarship.model.entity.ApplicationType;
import ar.com.scholarship.Scholarship.model.repository.ApplicationTypeRepository;
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
public class ApplicationTypeLoaderData implements ApplicationRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationTypeLoaderData.class);

    @Value("${spring.application.env}")
    private String appEnv;

    @Autowired @Qualifier("applicationTypeRepository")
    private ApplicationTypeRepository applicationTypeRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        LOGGER.info("Environment: " + appEnv);

        if (appEnv.equals("dev") || appEnv.equals("qa")) {

            LOGGER.info("Loading Application type initial data...");

            List<ApplicationType> typeList = Arrays.asList(
                    new ApplicationType("Full Aplicación"),
                    new ApplicationType("Beca 100%"),
                    new ApplicationType("Beca 75%"),
                    new ApplicationType("Beca 50%"),
                    new ApplicationType("Beca 25%")
            );
            typeList.forEach(applicationType -> applicationTypeRepository.save(applicationType));
        }
    }
}
