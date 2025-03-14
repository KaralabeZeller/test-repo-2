package hu.replant.dto;

import java.math.BigDecimal;

public class CompanyDTOFactory {

    public static CompanyDTO createFullCompanyDTO() {
        CompanyDTO dto = new CompanyDTO();
        dto.setOldId("OLD_001");
        dto.setName("Full Company Inc.");
        dto.setTaxNumber("TAX123456");
        dto.setAccountingAddress("123 Accounting Lane, City, Country");
        dto.setWeb("https://fullcompany.com");
        dto.setLogo("https://fullcompany.com/logo.png");
        dto.setContactName("John Doe");
        dto.setContactEmail("john.doe@fullcompany.com");
        dto.setContactPhone("+1-555-123456");
        dto.setOwner("Jane Doe");
        dto.setDiscount(new BigDecimal("10.5"));
        dto.setUnasId("UNAS001");
        dto.setMonthlyBilling(true);
        return dto;
    }
}
