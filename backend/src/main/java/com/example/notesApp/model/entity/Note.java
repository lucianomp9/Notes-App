package com.example.notesApp.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "note")
public class Note {

    @Id
    @Column(name = "id")
    public UUID uuid;

    @Column(name = "title")
    public String title;

    @Column(name = "description")
    public String description;

    @ManyToOne
    @JoinColumn(name = "category_id")
    public Category category;

    @Column(name = "status")
    public boolean status;

    @Column(name = "archived")
    public boolean archived;

}