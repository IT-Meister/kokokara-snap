package jp.co.itmeister.userservice.userservice.responseBuilder;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.LinkedHashMap;

@Component
public class ResponseBuilder {
    
    //成功レスポンス
    public <T> ResponseEntity<Map<String , Object>> buildSuccessResponse (T data , ResponseCookie cookies) {
        Map<String , Object> responseBody = new LinkedHashMap<>();
        responseBody.put("status" , "Success");
        responseBody.put("data" , data);

        ResponseEntity.BodyBuilder responseBuilder =  ResponseEntity.ok();

        if(cookies != null) {
            responseBuilder.header(HttpHeaders.SET_COOKIE, cookies.toString());
        }
        return responseBuilder.body(responseBody);
    }

    //エラーレスポンス
    public ResponseEntity<Map<String , Object>> buildErrorResponse (String message , HttpStatus status) {
        Map<String , Object> responseBody = new LinkedHashMap<>();
        responseBody.put("status" , "Error");
        responseBody.put("data" , message);

        return new ResponseEntity<>(responseBody , status);
    }
}
