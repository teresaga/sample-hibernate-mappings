package org.example.springboot.advancedmappings.dao;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.example.springboot.advancedmappings.entity.Instructor;
import org.example.springboot.advancedmappings.entity.InstructorDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AppDAOImpl implements AppDAO {

    private EntityManager em;

    @Autowired
    public AppDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    @Transactional
    public void save(Instructor theInstructor) {
        em.persist(theInstructor);
    }

    @Override
    public Instructor findById(int theId) {
        return em.find(Instructor.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int theId) {
        Instructor theInstructor = em.find(Instructor.class, theId);

        em.remove(theInstructor);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int theId) {
        return em.find(InstructorDetail.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int theId) {
        InstructorDetail theInstructorDetail = em.find(InstructorDetail.class, theId);

        // remove the associated object reference
        // break bi-directional link
        theInstructorDetail.getInstructor().setInstructorDetail(null);

        em.remove(theInstructorDetail);

    }
}
