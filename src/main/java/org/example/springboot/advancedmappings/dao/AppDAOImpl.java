package org.example.springboot.advancedmappings.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.example.springboot.advancedmappings.entity.Course;
import org.example.springboot.advancedmappings.entity.Instructor;
import org.example.springboot.advancedmappings.entity.InstructorDetail;
import org.example.springboot.advancedmappings.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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

        // get courses
        List<Course> theCourses = theInstructor.getCourses();

        // remove association of all courses for the instructor
        for (Course c : theCourses) {
            c.setInstructor(null);
        }

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

    @Override
    public List<Course> findCoursesByInstructorId(int theId) {
        // create query
        TypedQuery<Course> query = em.createQuery("from Course where instructor.id = :data", Course.class);
        query.setParameter("data", theId);

        // execute query
        List<Course> courses = query.getResultList();

        return courses;
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int theId) {
        TypedQuery<Instructor> query = em.createQuery("select i from Instructor i " +
                                                        "join fetch i.courses " +
                                                        "join fetch i.instructorDetail " +
                                                        "where i.id = :data", Instructor.class);
        query.setParameter("data", theId);

        Instructor instructor = query.getSingleResult();

        return instructor;
    }

    @Override
    @Transactional
    public void update(Instructor theInstructor) {
        em.merge(theInstructor);
    }

    @Override
    @Transactional
    public void update(Course theCourse) {
        em.merge(theCourse);
    }

    @Override
    public Course findCourseById(int theId) {
        return em.find(Course.class, theId);
    }

    @Override
    @Transactional
    public void deleteCourseById(int theId) {
        Course theCourse = em.find(Course.class, theId);

        em.remove(theCourse);
    }

    @Override
    @Transactional
    public void saveCourse(Course theCourse) {
        em.persist(theCourse);
    }

    @Override
    public Course findCourseWithReviewsById(int theId) {
        TypedQuery<Course> query = em.createQuery("SELECT c FROM Course c " +
                                                        "JOIN FETCH c.reviews " +
                                                        "WHERE c.id = :data", Course.class);
        query.setParameter("data", theId);

        Course course = query.getSingleResult();

        return course;
    }

    @Override
    public Course findCourseAndStudentsByCourseId(int theId) {

        TypedQuery<Course> query = em.createQuery("SELECT c FROM Course c " +
                                                        "JOIN FETCH c.students " +
                                                        "WHERE c.id = :data", Course.class);
        query.setParameter("data", theId);

        Course theCourse = query.getSingleResult();

        return theCourse;
    }

    @Override
    public Student findStudentAndCoursesByStudentId(int theId) {
        TypedQuery<Student> query = em.createQuery("SELECT s FROM Student s " +
                                                        "JOIN FETCH s.courses " +
                                                        "WHERE s.id = :data", Student.class);
        query.setParameter("data", theId);

        Student theStudent = query.getSingleResult();

        return theStudent;
    }

    @Override
    @Transactional
    public void updateStudent(Student theStudent) {
        em.merge(theStudent);
    }
}
