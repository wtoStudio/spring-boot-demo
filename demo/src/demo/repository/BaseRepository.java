package demo.repository;

import demo.entity.BaseEntity;
import org.springframework.data.repository.Repository;

/**
 * 1. Repository是一个空接口，即标记接口。
 *
 * 2. 若我们自己定义的接口继承了Repository接口，则该接口会被IOC容器识别为一个Repository Bean, 并生成该接口的代理对象纳入到IOC容器。
 *
 * 3. 若不继承Respository接口，也可通过@RepositoryDefinition注解来标记，例如：@RepositoryDefinition(domainClass = BaseEntity.class, idClass = Long.class)
 */

//@RepositoryDefinition(domainClass = BaseEntity.class, idClass = Long.class)
public interface BaseRepository extends Repository<BaseEntity, Long> {

    BaseEntity findById(Long id);

    BaseEntity save(BaseEntity o);

    boolean deleteById(Long id);

    /*@Query("UPDATE User SET name = :newName WHERE name = :oldName ")
    void testUpdate(@Param("oldName")String oldName, @Param("newName")String newName);*/
}