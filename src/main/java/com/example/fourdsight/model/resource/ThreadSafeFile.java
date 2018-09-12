package com.example.fourdsight.model.resource;

import lombok.Getter;

import java.io.File;
@Getter
public class ThreadSafeFile {

    private final File file;

    public ThreadSafeFile(File file) {
        this.file = file;
    }
}
