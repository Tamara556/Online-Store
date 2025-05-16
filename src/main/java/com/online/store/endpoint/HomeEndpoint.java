package com.online.store.endpoint;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class HomeEndpoint {

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ResponseEntity<Resource> getIndexHtml() throws IOException {
        ClassPathResource htmlFile = new ClassPathResource("/static/index.html");
        return ResponseEntity
                .ok()
                .contentType(MediaType.TEXT_HTML)
                .body(htmlFile);
    }
}