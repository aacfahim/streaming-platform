package com.aacfahim.streaming_platform.config;

public class ApiPath {

    public static final String API_CORE_NAME = "/api";
    public static final String API_VERSION = "/v1";
    public static final String API_BASE_PATH = API_CORE_NAME + API_VERSION;
    public static final String PRODUCT_TITLE = "/streaming-platform";
    public static final String AUTH = "/auth";
    public static final String LOGIN = "/login";
    public static final String REGISTER = "/register";
    public static final String VERIFY_OTP = "/verify";
    public static final String REGISTER_WITH_OTP = REGISTER + "/initiate";
    public static final String VIDEOS = PRODUCT_TITLE + "/videos";


}