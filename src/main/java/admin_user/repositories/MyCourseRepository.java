package admin_user.repositories;

import admin_user.model.MyCourseList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MyCourseRepository extends JpaRepository<MyCourseList,Integer> {

}
