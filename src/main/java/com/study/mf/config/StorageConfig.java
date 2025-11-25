package com.study.mf.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StorageConfig {
    @Value("${storage.storage-folder}")
    private String storageFolderStr;

    public StorageConfig() {
    }

    public String getStorageFolderStr() {
        return storageFolderStr;
    }

    public void setStorageFolderStr(String storageFolderStr) {
        this.storageFolderStr = storageFolderStr;
    }
}
