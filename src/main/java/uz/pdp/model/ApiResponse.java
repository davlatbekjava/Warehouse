package uz.pdp.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@Setter
public class ApiResponse<T>{

    private T data;

    private boolean success;

    private String errorMessage;

    private Long timestamp;

    private Long totalCount;

    public ApiResponse(T data){
        this.data = data;
        this.success = true;
        this.errorMessage = "";
        this.timestamp = System.currentTimeMillis();
    }

    public ApiResponse(T data, Long totalCount){
        this.data = data;
        this.success = true;
        this.errorMessage = "";
        this.timestamp = System.currentTimeMillis();
        this.totalCount = totalCount;
    }

    public ApiResponse(String errorMessage){
        this.data = null;
        this.success = false;
        this.errorMessage = errorMessage;
        this.timestamp = System.currentTimeMillis();
    }



    public ApiResponse(T data, String errorMessage){
        this.data = data;
        this.success = false;
        this.errorMessage = errorMessage;
        this.timestamp = System.currentTimeMillis();
    }

    //Success true response (For example delete response)
    public ApiResponse(){
        this.success = true;
        this.errorMessage = "";
        this.timestamp = System.currentTimeMillis();
    }

    public static <T> ResponseEntity<ApiResponse<T>> response(T data){
        return ResponseEntity.ok(new ApiResponse<>(data));
    }

    public static <T> ResponseEntity<ApiResponse<T>> response(T data, Long totalCount){
        return ResponseEntity.ok(new ApiResponse<>(data, totalCount));
    }

    public static <T> ResponseEntity<ApiResponse<T>> response(T data, HttpStatus httpStatus){
        return new ResponseEntity<>(new ApiResponse<>(data), httpStatus);
    }

    public static <T> ResponseEntity<ApiResponse<T>> response(String errorMessage, HttpStatus httpStatus){
        return new ResponseEntity<>(new ApiResponse<>(errorMessage), httpStatus);
    }
}
