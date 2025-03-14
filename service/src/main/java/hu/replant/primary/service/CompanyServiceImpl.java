package hu.replant.primary.service;

import hu.replant.core.model.Company;
import hu.replant.core.mapper.CompanyMapper;
import hu.replant.secondary.persistence.entity.CompanyEntity;
import hu.replant.secondary.persistence.repository.CompanyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    private static final Logger logger = LoggerFactory.getLogger(CompanyServiceImpl.class);

    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Overridedfgsdfgdsfglsádéflg
    áésldfgá
    ésdfl
    public List<Company> getAllCompanies() {
        logger.info("Fetching all companies");
        List<CompanyEntity> companyEntities = companyRepository.findAll();
        logger.debug("Fetched {} companies from the database", companyEntities.size());
        return CompanyMapper.toModelList(companyEntities);
    }

    @Override
    public Company getCompanyById(Long id) {
        logger.info("Fetching company by ID: {}", id);
        CompanyEntity companyEntity = companyRepository.findById(id).orElse(null);
        if (companyEntity == null) {
            throw new RuntimeException("Company not found");
        } else {
            logger.debug("Fetched company: {}", companyEntity);
        }
        return CompanyMapper.toModel(companyEntity);
    }

    @Override
    public Company createCompany(Company company) {
        logger.info("Creating a new company: {}", company);
        CompanyEntity companyEntity = CompanyMapper.toEntity(company);
        CompanyEntity savedCompany = companyRepository.save(companyEntity);
        logger.debug("Company created successfully: {}", savedCompany);
        return CompanyMapper.toModel(savedCompany);
    }

    @Override
    public Company updateCompany(Long id, Company company) {
        logger.info("Updating company with ID {}: {}", id, company);
        CompanyEntity companyEntity = companyRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Company with ID {} not found for update", id);
                    return new RuntimeException("Company not found");
                });

        companyEntity.setOldId(company.getOldId() != null ? company.getOldId() : companyEntity.getOldId());
        companyEntity.setName(company.getName() != null ? company.getName() : companyEntity.getName());
        companyEntity.setTaxNumber(company.getTaxNumber() != null ? company.getTaxNumber() : companyEntity.getTaxNumber());
        companyEntity.setAccountingAddress(company.getAccountingAddress() != null ? company.getAccountingAddress() : companyEntity.getAccountingAddress());
        companyEntity.setWeb(company.getWeb() != null ? company.getWeb() : companyEntity.getWeb());
        companyEntity.setLogo(company.getLogo() != null ? company.getLogo() : companyEntity.getLogo());
        companyEntity.setContactName(company.getContactName() != null ? company.getContactName() : companyEntity.getContactName());
        companyEntity.setContactEmail(company.getContactEmail() != null ? company.getContactEmail() : companyEntity.getContactEmail());
        companyEntity.setContactPhone(company.getContactPhone() != null ? company.getContactPhone() : companyEntity.getContactPhone());
        companyEntity.setOwner(company.getOwner() != null ? company.getOwner() : companyEntity.getOwner());
        companyEntity.setDiscount(company.getDiscount() != null ? company.getDiscount() : companyEntity.getDiscount());
        companyEntity.setUnasId(company.getUnasId() != null ? company.getUnasId() : companyEntity.getUnasId());
        companyEntity.setMonthlyBilling(company.getMonthlyBilling() != null ? company.getMonthlyBilling() : companyEntity.getMonthlyBilling());

        CompanyEntity savedCompany = companyRepository.save(companyEntity);
        logger.debug("Company updated successfully: {}", savedCompany);
        return CompanyMapper.toModel(savedCompany);
    }

    @Override
    public void deleteCompany(Long id) {
        logger.info("Deleting company with ID: {}", id);
        if (!companyRepository.existsById(id)) {
            logger.warn("Company with ID {} not found for deletion", id);
            throw new RuntimeException("Company not found");
        }
        companyRepository.deleteById(id);
        logger.debug("Company with ID {} deleted successfully", id);
    }
}
