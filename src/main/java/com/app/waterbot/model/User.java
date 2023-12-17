package com.app.waterbot.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "tg_user_id")
    private Long tgUserId;

    @Column(name = "chat_id")
    private Long chatId;

    @Column(name = "start_time")
    private String startTime;

    @Column(name = "end_time")
    private String endTime;

    @Column(name = "gap_time")
    private Long gapTime;

    @Column(name = "timer")
    private Long timer;

    @Column(name = "sleep")
    private Boolean sleep;
}
