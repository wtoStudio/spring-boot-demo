package demo.mapper;

import demo.entity.Person;
import org.apache.ibatis.annotations.Select;

public interface PersonMapper {

    @Select(value = "select * from person where id = #{personId}")
    Person findPersonWithMybatis(Long personId);
}
