package demo.exception;

import lombok.Getter;

import java.util.Date;

@Getter
public class AppException extends RuntimeException {

    private Date when;

    private String how;

    public AppException(String name, Date when, String how){
        super(name);
        this.when = when;
        this.how = how;
    }
}
