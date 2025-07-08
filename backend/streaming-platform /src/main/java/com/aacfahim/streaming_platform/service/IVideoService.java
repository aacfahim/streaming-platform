package com.aacfahim.streaming_platform.service;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

public interface IVideoService {

    List<String> listVideos();

    ResponseEntity<InputStreamResource> streamVideo(String filename, String rangeHeader) throws IOException;
}
