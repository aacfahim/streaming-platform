package com.aacfahim.streaming_platform.controller;

import com.aacfahim.streaming_platform.service.IVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import static com.aacfahim.streaming_platform.config.ApiPath.VIDEOS;

@RestController
@RequestMapping(VIDEOS)
public class VideoController {

    private final IVideoService videoService;

    @Autowired
    public VideoController(IVideoService videoService) {
        this.videoService = videoService;
    }

    @GetMapping("/{filename}")
    public ResponseEntity<InputStreamResource> streamVideo(
            @PathVariable String filename,
            @RequestHeader(value = "Range", required = false) String rangeHeader) throws IOException {
        return videoService.streamVideo(filename, rangeHeader);
    }
}
