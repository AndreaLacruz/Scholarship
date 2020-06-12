package ar.com.scholarship.Scholarship.components.data;

import ar.com.scholarship.Scholarship.model.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

//@Component
public class DataCleaner {

   /* private static final Logger LOGGER = LoggerFactory.getLogger(DataCleaner.class);

    @Value("${spring.application.env}")
    private String appEnv;

    @Autowired
    @Qualifier("courseCategoryRepository")
    private CourseCategoryRepository courseCategoryRepository;

    @Autowired
    @Qualifier("studentStatusRepository")
    private StudentStatusRepository studentStatusRepository;

    @Autowired
    @Qualifier("modalityRepository")
    private ModalityRepository modalityRepository;

    @Autowired
    @Qualifier("companyStatusRepository")
    private CompanyStatusRepository companyStatusRepository;

    @Autowired
    @Qualifier("docTypeRepository")
    private DocTypeRepository docTypeRepository;

    @Autowired
    @Qualifier("applicationTypeRepository")
    private ApplicationTypeRepository applicationTypeRepository;


    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {

        if (appEnv.equals("dev") || appEnv.equals("qa")) {
            LOGGER.info("Data Cleaner initializer...\n");

            //Borrar los registros de las tablas
    }

    */
}