package com.opencode.alumxbackend.groupchatmessages.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.opencode.alumxbackend.groupchatmessages.dto.GroupMessageResponse;
import com.opencode.alumxbackend.groupchatmessages.dto.GroupMessageSearchRequest;
import com.opencode.alumxbackend.groupchatmessages.service.GroupMessageService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/group-chats")
@RequiredArgsConstructor
public class GroupMessageSearchController {

    private final GroupMessageService groupMessageService;

    @GetMapping("/{groupId}/messages/search")
    public ResponseEntity<List<GroupMessageResponse>> searchMessage(@PathVariable Long groupId, @RequestHeader("X-USER-ID") Long userId,
            @Valid @RequestBody GroupMessageSearchRequest request) {

        List<GroupMessageResponse> messages = groupMessageService.searchForMessage(groupId, userId, request);
        return ResponseEntity.ok(messages);
    }
}
