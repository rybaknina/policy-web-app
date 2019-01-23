package com.helmes.spring.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name="policy")
public class Policy {

    @Id
    @Type(type="pg-uuid")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @Column(name="price")
    private BigDecimal price;
    @Column(name="is_active", nullable = true)
    private Boolean active = true;
    @Column(name="is_delete", nullable = true)
    private Boolean delete = false;

    public Boolean isDelete() {
        return delete;
    }

    public void setDelete(Boolean delete) {
        this.delete = delete;
    }

    @ManyToOne(fetch = FetchType.EAGER)  // тут надо подтягивать на лист тип сразу, поэтому не дружит с LAZY
    @JoinColumn(name = "id_type", referencedColumnName = "id" )
    private com.helmes.spring.model.Type type;
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "policy")
    private List<Buy> buys;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_company", referencedColumnName = "id" )
    private Company company;

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<Buy> getBuys() {
        return buys;
    }

    public void setBuys(List<Buy> buys) {
        this.buys = buys;
    }

    @Transient
    private String typef;  // тут надо подумать над реквест параметром вместо левого поля

    public String getTypef() {
        return typef;
    }

    public void isTypef(String typef) {
        this.typef = typef;
    }


    public com.helmes.spring.model.Type getType() {
        return type;
    }

    public void setType(com.helmes.spring.model.Type type) {
        this.type = type;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }


    public UUID  getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Policy{" +
                "id=" + id +
                ", price=" + price +
                ", active=" + active +
                ", delete=" + delete +
                ", type=" + type +
                ", typef='" + typef + '\'' +
                '}';
    }
}
