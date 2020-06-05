package ar.com.scholarship.Scholarship.components.data;

import ar.com.scholarship.Scholarship.model.entity.CompanyStatus;
import ar.com.scholarship.Scholarship.model.repository.CompanyStatusRepository;
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
public class CompanyStatusLoaderData implements ApplicationRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyStatusLoaderData.class);

    @Autowired @Qualifier("companyStatusRepository")
    private CompanyStatusRepository companyStatusRepository;

    @Value("${spring.application.env}")
    private String appEnv;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        LOGGER.info("Environment: " + appEnv);

        if (appEnv.equals("dev") || appEnv.equals("qa")) {

            LOGGER.info("Loading Company Status initial data...");

            List<CompanyStatus> statusList = Arrays.asList(
                    new CompanyStatus("Compañia Aprobada"),
                    new CompanyStatus("Compañia Rechazada"),
                    new CompanyStatus("Pendiente")
            );
            statusList.forEach(companyStatus -> companyStatusRepository.save(companyStatus));
        }
    }
}
