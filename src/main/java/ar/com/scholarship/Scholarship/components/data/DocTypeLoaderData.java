package ar.com.scholarship.Scholarship.components.data;

import ar.com.scholarship.Scholarship.model.entity.DocType;
import ar.com.scholarship.Scholarship.model.repository.DocTypeRepository;
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
public class DocTypeLoaderData implements ApplicationRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(DocTypeLoaderData.class);

    @Value("${spring.application.env}")
    private String appEnv;

    @Autowired @Qualifier("docTypeRepository")
    private DocTypeRepository docTypeRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        LOGGER.info("Environment: " + appEnv);

        if (appEnv.equals("dev") || appEnv.equals("qa")){
            LOGGER.info("Loading Document Type initial data...");

            List<DocType> docTypeList = Arrays.asList(
                    new DocType(1L, "DNI"),
                    new DocType(2L, "Pasaporte"),
                    new DocType(3L, "Cédula"),
                    new DocType(4L, "Licencia")
            );

            docTypeList.forEach(docType ->
                    docTypeRepository.save(docType));
        }
    }
}
