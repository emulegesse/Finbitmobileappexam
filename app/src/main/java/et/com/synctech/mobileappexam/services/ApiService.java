package et.com.synctech.mobileappexam.services;

import et.com.synctech.mobileappexam.dto.EmployeeResponseDto;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    @GET("employees")
    Call<EmployeeResponseDto> fetchEmployees();

}
