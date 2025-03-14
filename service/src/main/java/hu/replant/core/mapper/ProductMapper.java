package hu.replant.core.mapper;

import hu.replant.dto.ProductDTO;
import hu.replant.core.model.Product;
import hu.replant.secondary.persistence.entity.ProductEntity;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

public class ProductMapper {

    public static Product toModel(ProductDTO dto) {
        if (dto == null) {
            return null;
        }
        return Product.builder()
                .id(dto.getId())
                .name(dto.getName())
                .quantity(dto.getQuantity())
                .createdAt(dto.getCreatedAt() != null ? dto.getCreatedAt().toInstant() : Instant.now())
                .updatedAt(dto.getUpdatedAt() != null ? dto.getUpdatedAt().toInstant() : null)
                .build();
    }

    public static ProductDTO toDto(Product model) {
        if (model == null) {
            return null;
        }
        ProductDTO dto = new ProductDTO();
        dto.setId(model.getId());
        dto.setName(model.getName());
        dto.setQuantity(model.getQuantity());
        dto.setCreatedAt(model.getCreatedAt() != null ? java.util.Date.from(model.getCreatedAt()) : null);
        dto.setUpdatedAt(model.getUpdatedAt() != null ? java.util.Date.from(model.getUpdatedAt()) : null);
        return dto;
    }

    public static List<Product> toModelListFromDTO(List<ProductDTO> dtos) {
        if (dtos == null) {
            return null;
        }
        return dtos.stream()
                .map(ProductMapper::toModel)
                .collect(Collectors.toList());
    }

    public static List<ProductDTO> toDtoList(List<Product> models) {
        if (models == null) {
            return null;
        }
        return models.stream()
                .map(ProductMapper::toDto)
                .collect(Collectors.toList());
    }

    public static Product toModel(ProductEntity entity) {
        if (entity == null) {
            return null;
        }

        return Product.builder()
                .id(entity.getId())
                .name(entity.getName())
                .quantity(entity.getQuantity())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    public static List<Product> toModelList(List<ProductEntity> all) {
        if (all == null) {
            return null;
        }
        return all.stream()
                .map(ProductMapper::toModel)
                .collect(Collectors.toList());
    }

    public static ProductEntity toEntity(Product product) {
        if (product == null) {
            return null;
        }
        ProductEntity entity = new ProductEntity();
        entity.setName(product.getName());
        entity.setQuantity(product.getQuantity());
        entity.setUpdatedAt(product.getUpdatedAt());
        return entity;
    }
}
