package ar.com.scholarship.Scholarship.components.data;

import ar.com.scholarship.Scholarship.model.entity.Modality;
import ar.com.scholarship.Scholarship.model.repository.ModalityRepository;
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
public class ModalityLoaderData implements ApplicationRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(ModalityLoaderData.class);

    @Value("${spring.application.env}")
    private String appEnv;

    @Autowired
    @Qualifier("modalityRepository")
    private ModalityRepository modalityRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        if (appEnv.equals("dev") || appEnv.equals("qa")) {
            LOGGER.info("Loading Course Modality initial data...\n");

            List<Modality> modalities = Arrays.asList(
                    new Modality(1L, "Online"),
                    new Modality(2L, "Presencial"),
                    new Modality(3L, "Combinados")
            );

            modalityRepository.saveAll(modalities);
        }
    }
}