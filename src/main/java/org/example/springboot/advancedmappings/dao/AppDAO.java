package org.example.springboot.advancedmappings.dao;

import org.example.springboot.advancedmappings.entity.Instructor;
import org.example.springboot.advancedmappings.entity.InstructorDetail;

public interface AppDAO {

    void save(Instructor theInstructor);
    Instructor findById(int theId);
    void deleteInstructorById(int theId);
    InstructorDetail findInstructorDetailById(int theId);
    void deleteInstructorDetailById(int theId);
}
