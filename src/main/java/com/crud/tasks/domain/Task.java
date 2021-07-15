package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.Id;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity(name = "tasks")
public class Task {

    public Task(String title, String content) {
        this.title = title;
        this.content = content;
    }

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String title;

    @Column(name = "description")
    private String content;
}