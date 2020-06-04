package ar.com.scholarship.Scholarship.service;

import ar.com.scholarship.Scholarship.model.dto.CompanyDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("companyServices")
public class CompanyServices implements Services<CompanyDTO> {


    @Override
    public CompanyDTO save(CompanyDTO dto) throws Exception {
        return null;
    }

    @Override
    public List<CompanyDTO> findAll() {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
