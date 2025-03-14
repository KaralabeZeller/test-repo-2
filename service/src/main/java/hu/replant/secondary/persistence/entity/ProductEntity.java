package hu.replant.secondary.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Getter
@Entity
@Table(name = "products")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "products_id_gen")
    @SequenceGenerator(name = "products_id_gen", sequenceName = "product_id_seq", allocationSize = 1)

    @Column(name = "id", nullable = false)
    private Long id;

    @Setter
    @Column(name = "name", nullable = false, length = 200)
    private String name;

    @Setter
    @Column(name = "quantity", nullable = false)
    private int quantity;

    @CreationTimestamp
    @Column(name = "created_at")
    private Instant createdAt;

    @Setter
    @Column(name = "updated_at")
    private Instant updatedAt;

}