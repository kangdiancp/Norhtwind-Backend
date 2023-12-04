package com.northwind.backend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import jakarta.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Entity
@Table(name = "productphotos")
public class ProductPhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="prhoid")
    private Long prhoId;

    @Column(name="prhofilename")
    private String prhoFilename;

    @Column(name="prhofiletype")
    private  String prhoFiletype;

    @Column(name="prhofilesize")
    private  Integer prhoFileSize;

    @Column(name="prhofileurl")
    private String prhoFileUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="productid")
    private Product product;

}
