package com.aditya.heeliumapp.Pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by aditya on 12/30/17.
 */

public class DocumentUploadResponse {

    @SerializedName("status")
    @Expose
    private boolean status;

    @SerializedName("msg")
    @Expose
    private String message;

    public boolean isStatus() {
        return status;
    }
    public String getMessage() {
        return message;
    }
}
