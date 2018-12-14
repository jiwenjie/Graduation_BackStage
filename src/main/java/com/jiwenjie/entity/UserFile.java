package com.jiwenjie.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * author: Jiwenjie
 * email: Jiwenjie97@gmail.com
 * time: 2018-12-13
 * desc:
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserFile {

    private int id;
    private String fileName;
    private String filePath;

    @Override
    public String toString() {
        return "UserFile{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                ", filePath='" + filePath + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
