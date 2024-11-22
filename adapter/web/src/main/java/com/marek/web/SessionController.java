package com.marek.web;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RequestMapping(SessionController.REQUEST_MAPPING)
public class SessionController {
    public static final String REQUEST_MAPPING = "/api/session";

    @PostMapping
    public ResponseEntity<Object> startSession(HttpSession session) {
        if (session.isNew()){
            session.setAttribute(UUID.randomUUID().toString(), session.getId());

            log.atInfo().log("Session with personId {} started", session.getId());
        }

        return ResponseEntity.ok().build();
    }
}
