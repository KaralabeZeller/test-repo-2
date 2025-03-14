package hu.replant.core.mapper;

import hu.replant.dto.CompanyDTO;
import hu.replant.core.model.Company;
import hu.replant.secondary.persistence.entity.CompanyEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class CompanyMapper {

    public static Company toModel(CompanyDTO dto) {
        if (dto == null) {
            return null;
        }
        return Company.builder()
                .id(dto.getId())
                .oldId(dto.getOldId())
                .name(dto.getName())
                .taxNumber(dto.getTaxNumber())
                .accountingAddress(dto.getAccountingAddress())
                .web(dto.getWeb())
                .logo(dto.getLogo())
                .contactName(dto.getContactName())
                .contactEmail(dto.getContactEmail())
                .contactPhone(dto.getContactPhone())
                .owner(dto.getOwner())
                .discount(dto.getDiscount() != null ? new BigDecimal(String.valueOf(dto.getDiscount())) : null)
                .unasId(dto.getUnasId())
                .monthlyBilling(dto.getMonthlyBilling())
                .build();
    }

    public static CompanyDTO toDto(Company model) {
        if (model == null) {
            return null;
        }
        CompanyDTO dto = new CompanyDTO();
        dto.setId(model.getId());
        dto.setOldId(model.getOldId());
        dto.setName(model.getName());
        dto.setTaxNumber(model.getTaxNumber());
        dto.setAccountingAddress(model.getAccountingAddress());
        dto.setWeb(model.getWeb());
        dto.setLogo(model.getLogo());
        dto.setContactName(model.getContactName());
        dto.setContactEmail(model.getContactEmail());
        dto.setContactPhone(model.getContactPhone());
        dto.setOwner(model.getOwner());
        dto.setDiscount(model.getDiscount() != null ? model.getDiscount() : null);
        dto.setUnasId(model.getUnasId());
        dto.setMonthlyBilling(model.getMonthlyBilling());
        return dto;
    }

    public static List<Company> toModelListFromDTO(List<CompanyDTO> dtos) {
        if (dtos == null) {
            return null;
        }
        return dtos.stream()
                .map(CompanyMapper::toModel)
                .collect(Collectors.toList());
    }

    public static List<CompanyDTO> toDtoList(List<Company> models) {
        if (models == null) {
            return null;
        }
        return models.stream()
                .map(CompanyMapper::toDto)
                .collect(Collectors.toList());
    }

    public static Company toModel(CompanyEntity entity) {
        if (entity == null) {
            return null;
        }

        return Company.builder()
                .id(entity.getId())
                .oldId(entity.getOldId())
                .name(entity.getName())
                .taxNumber(entity.getTaxNumber())
                .accountingAddress(entity.getAccountingAddress())
                .web(entity.getWeb())
                .logo(entity.getLogo())
                .contactName(entity.getContactName())
                .contactEmail(entity.getContactEmail())
                .contactPhone(entity.getContactPhone())
                .owner(entity.getOwner())
                .discount(entity.getDiscount())
                .unasId(entity.getUnasId())
                .monthlyBilling(entity.getMonthlyBilling())
                .build();
    }

    public static List<Company> toModelList(List<CompanyEntity> all) {
        if (all == null) {
            return null;
        }
        return all.stream()
                .map(CompanyMapper::toModel)
                .collect(Collectors.toList());
    }

    public static CompanyEntity toEntity(Company company) {
        if (company == null) {
            return null;
        }
        CompanyEntity entity = new CompanyEntity();
        entity.setOldId(company.getOldId());
        entity.setName(company.getName());
        entity.setTaxNumber(company.getTaxNumber());
        entity.setAccountingAddress(company.getAccountingAddress());
        entity.setWeb(company.getWeb());
        entity.setLogo(company.getLogo());
        entity.setContactName(company.getContactName());
        entity.setContactEmail(company.getContactEmail());
        entity.setContactPhone(company.getContactPhone());
        entity.setOwner(company.getOwner());
        entity.setDiscount(company.getDiscount());
        entity.setUnasId(company.getUnasId());
        entity.setMonthlyBilling(company.getMonthlyBilling());
        return entity;
    }
}
