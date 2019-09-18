package demo.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Article extends BaseEntity {
    public static final String TABLE_NAME = "article";

    private String title;

    private String content;

    @ManyToOne
    private User author;
}
