package appjjang.fitpet.global.error.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    SAMPLE_ERROR(HttpStatus.BAD_REQUEST, "AJ4000", "Sample Error Message")
    ;

    private final HttpStatus status;
    private final String errorCode;
    private final String message;
}
