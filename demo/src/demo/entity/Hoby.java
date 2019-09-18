package demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity(name = "hoby")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Hoby extends BaseEntity {
    private String name;

    //@JsonBackReference
    @JsonIgnoreProperties(value = {"hobies"})
    @ManyToOne(fetch = FetchType.EAGER)
    private Person belongTo;

    public static Hoby of (String name){
        Hoby hoby = new Hoby();
        hoby.setName(name);
        return hoby;
    }
}
