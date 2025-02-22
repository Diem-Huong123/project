package luanvan.com.course_registration_system.service;

import luanvan.com.course_registration_system.dto.response.HocPhanResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CourseServiceClient {

    private final RestTemplate restTemplate;

    public CourseServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Gọi API course-service để lấy danh sách học phần
//    public List<HocPhanResponse> getHocPhansByMaHocPhan(List<String> maHocPhans) {
//        String url = "http://localhost:8082/api/courses/filter?ids=" + String.join(",", maHocPhans);
//
//        ResponseEntity<List<HocPhanResponse>> response = restTemplate.exchange(
//                url, HttpMethod.GET, null,
//                new ParameterizedTypeReference<List<HocPhanResponse>>() {}
//        );
//        return response.getBody();
//    }
    public HocPhanResponse getHocPhanByMaHocPhan(String maHocPhan) {
        String url = "http://localhost:8082/course/api/courses/" + maHocPhan;
        try {
            return restTemplate.getForObject(url, HocPhanResponse.class);
        } catch (Exception e) {
            return null;
        }
    }

}
