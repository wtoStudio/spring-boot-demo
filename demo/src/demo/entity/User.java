package demo.entity;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = User.TABLE_NAME)

/**
 * @ConfigurationProperties获取值 和 @Value获取值的区别
 * -------- @ConfigurationProperties -------- @Value --------
 * 功能             批量赋值                   一个一个的赋值
 * 松散绑定          支持                      不支持
 * SpEL            不支持                      支持
 * JSR303校验        支持                      不支持
 * 复杂类型赋值       支持                      不支持
 */

//必须要被容器管理，才能使用ConfigurationProperties来给类属性赋值
@Component
@PropertySource("classpath:user_init.properties")
@ConfigurationProperties(prefix = "user")
//@Validated
public class User extends BaseEntity {

    public static final String TABLE_NAME = "user";

    @Value("${user._name}")
    @Length(min=4, max=30)
    private String name;

    @Length(min=8, max=20)
    private String password;

    @Past
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    @OneToMany
    private List<Article> article;

    public String getBirthday() {
        return this.birthday==null ? null : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(this.birthday);
    }
}
