package com.opencode.alumxbackend.chat.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "messages",
        indexes = {
                @Index(name = "idx_chat_id", columnList = "chat_id")
        }
)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_id", nullable = false)
    private Chat chat;
    @Column(nullable = false)
    private String senderUsername;
    // make sure this username used here always exists in the DB before processing the query
    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /**
     * Derives the receiver username from the associated Chat and senderUsername.
     * This value is not persisted to avoid redundancy.
     */
    @Transient
    public String getReceiverUsername() {
        if (chat == null || senderUsername == null) {
            return null;
        }
        if (senderUsername.equals(chat.getUser1Username())) {
            return chat.getUser2Username();
        } else if (senderUsername.equals(chat.getUser2Username())) {
            return chat.getUser1Username();
        }
        return null;
    }
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
