package org.example.models;

import lombok.*;
import javax.persistence.*;
import org.example.dao.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "courses")
public class Course extends CourseDAO {
    public Course(String name){
        super(name);
    }
}