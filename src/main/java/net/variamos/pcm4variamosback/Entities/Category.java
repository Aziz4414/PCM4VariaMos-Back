package net.variamos.pcm4variamosback.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
private String name;
private String description;
private String photo;
@OneToMany(mappedBy = "category")
private Collection<Product> products;

}
