package com.aacfahim.streaming_platform.service.impl;

import com.aacfahim.streaming_platform.service.IVideoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class VideoService implements IVideoService {

    private static final String VIDEO_DIR = "/Users/fahim/Desktop/videos";

    @Override
    public List<String> listVideos() {
        File dir = new File(VIDEO_DIR);
        if (!dir.exists() || !dir.isDirectory()) {
            return Collections.emptyList();
        }
        String[] files = dir.list((d, name) -> name.endsWith(".mp4") || name.endsWith(".mkv") || name.endsWith(".avi"));
        return files != null ? Arrays.asList(files) : Collections.emptyList();
    }

    @Override
    public ResponseEntity<InputStreamResource> streamVideo(String filename, String rangeHeader) throws IOException {
        File video = new File(VIDEO_DIR + "/" + filename);

        if (!video.exists()) {
            return ResponseEntity.notFound().build();
        }

        long fileLength = video.length();
        long rangeStart = 0;
        long rangeEnd = fileLength - 1;

        if (rangeHeader != null && rangeHeader.startsWith("bytes=")) {
            String[] ranges = rangeHeader.substring(6).split("-");
            rangeStart = Long.parseLong(ranges[0]);
            if (ranges.length > 1 && !ranges[1].isEmpty()) {
                rangeEnd = Long.parseLong(ranges[1]);
            }
        }

        long contentLength = rangeEnd - rangeStart + 1;
        InputStream inputStream = new FileInputStream(video);
        inputStream.skip(rangeStart);

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "video/mp4");
        headers.set(HttpHeaders.ACCEPT_RANGES, "bytes");
        headers.set(HttpHeaders.CONTENT_LENGTH, String.valueOf(contentLength));
        if (rangeHeader != null) {
            headers.set(HttpHeaders.CONTENT_RANGE, "bytes " + rangeStart + "-" + rangeEnd + "/" + fileLength);
            return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
                    .headers(headers)
                    .body(new InputStreamResource(inputStream));
        } else {
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(new InputStreamResource(inputStream));
        }
    }
}
