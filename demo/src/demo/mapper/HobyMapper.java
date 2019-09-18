package demo.mapper;

import demo.entity.Hoby;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface HobyMapper {

    @Select(value = "select * from hoby where belong_to_id = #{personId}")
    List<Hoby> findHobyWithMybatisByPersonId(Long personId);
}
