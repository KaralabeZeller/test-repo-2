package hu.replant.core.mapper;

import hu.replant.dto.CompanyDTO;
import hu.replant.core.model.Company;
import hu.replant.secondary.persistence.entity.CompanyEntity;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CompanyMapperTest {

    @Test
    void toModel_ShouldConvertDtoToModel() {
        CompanyDTO dto = new CompanyDTO();
        dto.setId(1L);
        dto.setName("Test Company");
        dto.setDiscount(new BigDecimal("10.50"));

        Company model = CompanyMapper.toModel(dto);

        assertNotNull(model);
        assertEquals(dto.getId(), model.getId());
        assertEquals(dto.getName(), model.getName());
        assertEquals(dto.getDiscount(), model.getDiscount());
    }

    @Test
    void toDto_ShouldConvertModelToDto() {
        Company model = Company.builder()
                .id(1L)
                .name("Test Company")
                .discount(new BigDecimal("10.50"))
                .build();

        CompanyDTO dto = CompanyMapper.toDto(model);

        assertNotNull(dto);
        assertEquals(model.getId(), dto.getId());
        assertEquals(model.getName(), dto.getName());
        assertEquals(model.getDiscount(), dto.getDiscount());
    }

    @Test
    void toModelListFromDTO_ShouldConvertListOfDtosToModels() {
        CompanyDTO dto = new CompanyDTO();
        dto.setId(1L);
        dto.setName("Test Company");

        List<Company> models = CompanyMapper.toModelListFromDTO(Collections.singletonList(dto));

        assertNotNull(models);
        assertEquals(1, models.size());
        assertEquals(dto.getName(), models.get(0).getName());
    }

    @Test
    void toDtoList_ShouldConvertListOfModelsToDtos() {
        Company model = Company.builder().id(1L).name("Test Company").build();

        List<CompanyDTO> dtos = CompanyMapper.toDtoList(Collections.singletonList(model));

        assertNotNull(dtos);
        assertEquals(1, dtos.size());
        assertEquals(model.getName(), dtos.get(0).getName());
    }

    @Test
    void toModel_ShouldConvertEntityToModel() {
        CompanyEntity entity = new CompanyEntity();
        entity.setName("Test Company");

        Company model = CompanyMapper.toModel(entity);

        assertNotNull(model);
        assertEquals(entity.getId(), model.getId());
        assertEquals(entity.getName(), model.getName());
    }

    @Test
    void toEntity_ShouldConvertModelToEntity() {
        Company model = Company.builder().id(1L).name("Test Company").build();

        CompanyEntity entity = CompanyMapper.toEntity(model);

        assertNotNull(entity);
        assertNull(entity.getId());
        assertEquals(model.getName(), entity.getName());
    }

    @Test
    void toModel_ShouldReturnNull_WhenDtoIsNull() {
        assertNull(CompanyMapper.toModel((CompanyDTO) null));
    }

    @Test
    void toDto_ShouldReturnNull_WhenModelIsNull() {
        assertNull(CompanyMapper.toDto(null));
    }

    @Test
    void toModelListFromDTO_ShouldReturnNull_WhenInputIsNull() {
        assertNull(CompanyMapper.toModelListFromDTO(null));
    }

    @Test
    void toDtoList_ShouldReturnNull_WhenInputIsNull() {
        assertNull(CompanyMapper.toDtoList(null));
    }

    @Test
    void toModel_ShouldReturnNull_WhenEntityIsNull() {
        assertNull(CompanyMapper.toModel((CompanyEntity) null));
    }

    @Test
    void toEntity_ShouldReturnNull_WhenModelIsNull() {
        assertNull(CompanyMapper.toEntity(null));
    }
}
