package com.mithra.apsf.common;

public enum StatusCode {

    OK,
    ERROR,
    SUCCESS,
    FAILED,
    // Authentication codes
    UN_AUTHORIZED_USER,
    USER_INACTIVE,
    PENDING_ACTIVATION,
    INAVLID_STATUS,
    ACCOUNT_DEACTIVATED,
    INITIALIZED,
    IN_VALID_CREDENTIALS,
    NO_DATABASE_FOUND,
    USER_NOT_FOUND;
}
