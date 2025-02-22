package luanvan.com.course_registration_system.repository;

import luanvan.com.course_registration_system.entity.HocKy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HocKyRepository extends JpaRepository<HocKy, String> {
    Optional<HocKy> findByMaHocKy(String maHocKy);
}
