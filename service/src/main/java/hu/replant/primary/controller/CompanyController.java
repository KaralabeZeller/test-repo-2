package hu.replant.primary.controller;

import hu.replant.dto.CompanyDTO;
import hu.replant.core.model.Company;
import hu.replant.core.mapper.CompanyMapper;
import hu.replant.primary.service.CompanyService;
import jakarta.validation.Valid;
import org.openapitools.api.CompaniesApi;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
public class CompanyController implements CompaniesApi {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<CompanyDTO>> getAllCompanies() {
        List<Company> companies = companyService.getAllCompanies();
        return ResponseEntity.ok(CompanyMapper.toDtoList(companies));
    }

    @Override
    @PostMapping
    public ResponseEntity<CompanyDTO> createCompany(@Valid @RequestBody CompanyDTO companyDto) {
        Company company = companyService.createCompany(CompanyMapper.toModel(companyDto));
        return new ResponseEntity<>(CompanyMapper.toDto(company), HttpStatus.CREATED);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<CompanyDTO> getCompanyById(@PathVariable Long id) {
        try {
            Company company = companyService.getCompanyById(id);
            return ResponseEntity.ok(CompanyMapper.toDto(company));

        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<CompanyDTO> updateCompany(@PathVariable Long id, @Valid @RequestBody CompanyDTO companyDto) {
        Company company = companyService.updateCompany(id, CompanyMapper.toModel(companyDto));
        return ResponseEntity.ok(CompanyMapper.toDto(company));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long id) {
        companyService.deleteCompany(id);
        return ResponseEntity.noContent().build();
    }
}
