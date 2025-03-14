package hu.replant.primary.controller;

import hu.replant.dto.CompanyDTO;
import hu.replant.dto.CompanyDTOFactory;
import hu.replant.service.ServiceApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(
        classes = ServiceApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@TestPropertySource(properties = {
        "spring.datasource.url=jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1",
        "spring.datasource.driverClassName=org.h2.Driver",
        "spring.datasource.username=sa",
        "spring.datasource.password=",
        "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect",
        "spring.jpa.hibernate.ddl-auto=create-drop"
})
public class CompanyControllerIT {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String baseUrl;

    @BeforeEach
    void setUp() {
        baseUrl = "http://localhost:" + port + "/api/companies";
    }

    @Test
    void testCreateAndGetCompany() {
        CompanyDTO companyDTO = CompanyDTOFactory.createFullCompanyDTO();
        companyDTO.setName("Integration Test Company");

        // POST request to create the company.
        ResponseEntity<CompanyDTO> postResponse = restTemplate.postForEntity(baseUrl, companyDTO, CompanyDTO.class);
        assertThat(postResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        CompanyDTO createdCompany = postResponse.getBody();
        assertThat(createdCompany).isNotNull();
        assertThat(createdCompany.getId()).isNotNull();

        ResponseEntity<CompanyDTO> getResponse = restTemplate.getForEntity(baseUrl + "/" + createdCompany.getId(), CompanyDTO.class);
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        CompanyDTO fetchedCompany = getResponse.getBody();
        assertThat(fetchedCompany).isNotNull();
        assertThat(fetchedCompany.getName()).isEqualTo("Integration Test Company");

        createdCompany.setName("Updated Company Name");
        HttpEntity<CompanyDTO> updateEntity = new HttpEntity<>(createdCompany);
        ResponseEntity<CompanyDTO> putResponse = restTemplate.exchange(
                baseUrl + "/" + createdCompany.getId(),
                HttpMethod.PUT, updateEntity, CompanyDTO.class);
        assertThat(putResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        CompanyDTO updatedCompany = putResponse.getBody();
        assertThat(updatedCompany).isNotNull();
        assertThat(updatedCompany.getName()).isEqualTo("Updated Company Name");

        ResponseEntity<Void> deleteResponse = restTemplate.exchange(
                baseUrl + "/" + createdCompany.getId(),
                HttpMethod.DELETE, null, Void.class);
        assertThat(deleteResponse.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        ResponseEntity<CompanyDTO> getAfterDeleteResponse = restTemplate.getForEntity(
                baseUrl + "/" + createdCompany.getId(), CompanyDTO.class);
        assertThat(getAfterDeleteResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void testGetAllCompanies() {
        CompanyDTO companyDTO = CompanyDTOFactory.createFullCompanyDTO();
        companyDTO.setId(null);
        companyDTO.setName("Another Integration Test Company");
        ResponseEntity<CompanyDTO> postResponse = restTemplate.postForEntity(baseUrl, companyDTO, CompanyDTO.class);
        assertThat(postResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        ResponseEntity<CompanyDTO[]> getResponse = restTemplate.getForEntity(baseUrl, CompanyDTO[].class);
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        CompanyDTO[] companies = getResponse.getBody();
        assertThat(companies).isNotEmpty();
    }
}
