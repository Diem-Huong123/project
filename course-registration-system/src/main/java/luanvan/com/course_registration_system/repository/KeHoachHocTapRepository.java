package luanvan.com.course_registration_system.repository;

import luanvan.com.course_registration_system.entity.KeHoachHocTap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface KeHoachHocTapRepository extends JpaRepository<KeHoachHocTap, Long> {
    List<KeHoachHocTap> findByMaSinhVien(String maSinhVien);
//    List<KeHoachHocTap> findByMssv(String mssv);
    Optional<KeHoachHocTap> findByMaSinhVienAndHocKy_MaHocKy(String maSinhVien, String maHocKy);

}
