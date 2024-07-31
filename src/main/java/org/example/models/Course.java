package org.example.models;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "courses")
public class Course extends CourseDAO{
    public Course(String name){
        super(name);
    }
}