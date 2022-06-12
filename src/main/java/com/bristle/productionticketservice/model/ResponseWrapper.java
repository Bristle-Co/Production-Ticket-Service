package com.bristle.productionticketservice.model;

import java.time.LocalDateTime;

public class ResponseWrapper<T> {
    // This is implemented because I want the api response to have same structure
    // regardless of successful request or not
    // also reserved for future in case any information should be added to the response json structure

    String path;

    LocalDateTime timeStamp;
    int status;
    String message;
    T data;

    public ResponseWrapper() {
    }

    public ResponseWrapper(String path, LocalDateTime timeStamp, int status, String message, T data) {
        this.path = path;
        this.timeStamp = timeStamp;
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return "ResponseWrapper{" +
                "path='" + path + '\'' +
                ", timeStamp=" + timeStamp +
                ", status=" + status +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
