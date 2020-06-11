package et.com.synctech.mobileappexam.service;

import et.com.synctech.mobileappexam.dto.EmployeeResponseDto;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("employees")
    Call<EmployeeResponseDto> fetchEmployees();

}
