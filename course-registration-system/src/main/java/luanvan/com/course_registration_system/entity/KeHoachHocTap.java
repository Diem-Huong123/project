package luanvan.com.course_registration_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "ke_hoach_hoc_tap")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KeHoachHocTap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String maSinhVien; // Lưu MSSV thay vì liên kết với SinhVien entity (vì microservice tách biệt)

    @ManyToOne
    @JoinColumn(name = "ma_hoc_ky", referencedColumnName = "maHocKy", nullable = false)
    private HocKy hocKy; // Học kỳ vẫn giữ vì có trong cùng database

    @ElementCollection
    @CollectionTable(name = "kehoach_hocphanhoc", joinColumns = @JoinColumn(name = "keHoachHocTap_id"))
    @Column(name = "maHocPhan")
    private List<String> maHocPhans; // Danh sách mã học phần (thay vì liên kết với entity HocPhan)
}
