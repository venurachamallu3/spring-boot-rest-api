package com.Department.Department.Service.Exceptions;



public class DepartmentNotFoundException extends Exception {


    public DepartmentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public DepartmentNotFoundException(Throwable cause) {
        super(cause);
    }

    protected DepartmentNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public DepartmentNotFoundException(String s) {
        super(s);
    }
}
