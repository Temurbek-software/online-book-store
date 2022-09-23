package com.onlinebook.demo.exception;

import com.onlinebook.demo.payload.ErrorData;
import com.onlinebook.demo.utils.RestConstants;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class RestException extends RuntimeException {
    private String userMsg;
    private HttpStatus status;
    private String resourceName;
    private String fieldName;
    private Object fieldValue;
    private List<ErrorData> error;
    private Integer errorCode;

    public RestException(String resourceName,
                         String fieldName,
                         Object fieldValue,
                         String userMsg)
    {
        super(String.format("%s not found with %s : '%s'", resourceName,
                fieldName,
                fieldValue));
        this.userMsg = userMsg;
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
        this.status = HttpStatus.BAD_REQUEST;
        this.errorCode = RestConstants.NO_ITEMS_FOUND;
    }

    private RestException(String userMsg,
                          HttpStatus status) {
        super(userMsg);
        this.userMsg = userMsg;
        this.status = status;
    }

    private RestException(String userMsg,
                          int errorCode,
                          HttpStatus status)
    {
        super(userMsg);
        this.userMsg = userMsg;
        this.status = status;
        this.error = Collections.singletonList(new ErrorData(userMsg, errorCode));
    }

    public static RestException restThrow(String userMsg, HttpStatus httpStatus) {
        return new RestException(userMsg, httpStatus);
    }

    public static RestException restThrow(String resourceName, String fieldName,
                                          Object fieldValue, String userMsg) {
        return new RestException(resourceName, fieldName, fieldValue, userMsg);
    }

    public static RestException restThrow(String userMsg, int errorCode, HttpStatus status) {
        return new RestException(userMsg, errorCode, status);
    }

}
