package com.helmes.spring.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name="policy")
public class Policy {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(name="price")
    @NotNull(message = "Required field!")
    private BigDecimal price;
    @Column(name="is_active", nullable = true)

    private Boolean active = true;
    @Column(name="id_type") //TODO change name to "type"
    @NotEmpty(message = "Required field!")
    private String type; //TODO supposed to be Type, not String
    @Transient
    private String typename;
   // @Transient
   // private BigDecimal pricef = new BigDecimal(0.00);
    @Transient
    private String typef;
    @Transient
    private Boolean activef;


   // public BigDecimal getPricef() {
   //     return pricef;
   // }

   // public void setPricef(BigDecimal pricef) {
   //     this.pricef = pricef;
   // }

    public String getTypef() {
        return typef;
    }

    public void setTypef(String typef) {
        this.typef = typef;
    }

    public Boolean getActivef() {
        return activef;
    }

    public void setActivef(Boolean activef) {
        this.activef = activef;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }



    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
                ", type='" + type + '\'' +
                ", typename='" + typename + '\'' +
                '}';
    }
}
