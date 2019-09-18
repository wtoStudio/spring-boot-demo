package demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Entity(name = "person")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Person extends BaseEntity{
    private String name;
    private Integer age;

    //@JsonManagedReference
    @JsonIgnoreProperties(value = {"belongTo"})
    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "belongTo")
    private List<Hoby> hobies;

    public static Person of(String name, Integer age) {
        Person person = new Person();
        person.setName(name);
        person.setAge(age);
        return person;
    }
}