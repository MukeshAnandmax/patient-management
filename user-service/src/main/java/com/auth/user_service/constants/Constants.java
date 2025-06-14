package com.auth.user_service.constants;

public class Constants {

    private Constants() {
        // Private constructor to prevent instantiation
    }

    public static final String  STATUS_201 = "201";
    public static final String  MESSAGE_201 = "Account created successfully";
    public static final String  STATUS_200 = "200";
    public static final String  MESSAGE_200 = "Request processed successfully";
    public static final String  STATUS_204 = "204";
    public static final String  MESSAGE_204 = "User deleted successfully";
    public static final String  STATUS_417 = "417";
    public static final String  MESSAGE_417_UPDATE= "Update operation failed. Please try again or contact Dev team";
    public static final String  MESSAGE_417_DELETE= "Delete operation failed. Please try again or contact Dev team";
    public static final String  STATUS_500 = "500";
    public static final String  MESSAGE_500 = "An error occurred. Please try again or contact Dev team";
    public static final String  STATUS_400 = "400";
    public static final String  MESSAGE_400 = "Bad request. Please try again or contact Dev team";
    public static final String  STATUS_401 = "401";
    public static final String  MESSAGE_401 = "Unauthorized. Please try again or contact Dev team";
    public static final String  STATUS_403 = "403";
    public static final String  MESSAGE_403 = "Forbidden. Please try again or contact Dev team";
    public static final String  STATUS_404 = "404";
    public static final String  MESSAGE_404 = "Resource not found. Please try again or contact Dev team";
    public static final String  STATUS_409 = "409";
    public static final String  MESSAGE_409 = "Conflict. Please try again or contact Dev team";

    public static final String AUTHORIZATION = "Authorization";
    public static final String BEARER = "Bearer ";
    public static final String SUPER_ADMIN = "SUPER_ADMIN";
    public static final String ADMIN = "ADMIN";
    public static final String USER = "USER";
    public static final String NORMAL_USER = "Normal User";
    public static final String SUPER_ADMIN_ROLE = "SUPER_ADMIN_ROLE ";

}
