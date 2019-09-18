package demo.repository;

import demo.entity.Hoby;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HobyRepository extends JpaRepository<Hoby, Long> {

}
