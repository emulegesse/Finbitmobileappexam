package et.com.synctech.mobileappexam.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EmployeeResponseDto {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<Employee> data = null;

    /**
     * No args constructor for use in serialization
     */
    public EmployeeResponseDto() {
    }

    /**
     * @param data
     * @param status
     */
    public EmployeeResponseDto(String status, List<Employee> data) {
        super();
        this.status = status;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Employee> getData() {
        return data;
    }

    public void setData(List<Employee> data) {
        this.data = data;
    }

}