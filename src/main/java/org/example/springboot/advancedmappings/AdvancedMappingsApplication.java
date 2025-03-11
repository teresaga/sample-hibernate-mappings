package org.example.springboot.advancedmappings;

import org.example.springboot.advancedmappings.dao.AppDAO;
import org.example.springboot.advancedmappings.entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.nio.charset.CoderResult;
import java.util.List;

@SpringBootApplication
public class AdvancedMappingsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdvancedMappingsApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(AppDAO appDAO) {

        return runner -> {
            
            //createCourseAndStudents(appDAO);
            //findCourseAndStudents(appDAO);

            findStudentAndCourses(appDAO);

            //addMoreCoursesForStudent(appDAO);
            
        };
    }

    private void addMoreCoursesForStudent(AppDAO appDAO) {
        int theId = 1;

        System.out.println("Finding Student: " + theId);
        Student student = appDAO.findStudentAndCoursesByStudentId(theId);

        Course course1 = new Course("TypeScript - Basics");
        Course course2 = new Course("SQL");

        student.addCourse(course1);
        student.addCourse(course2);

        System.out.println("Saving student: " + student);
        System.out.println("associated courses: " + student.getCourses());

        appDAO.updateStudent(student);

        System.out.println("DONE!!");
    }

    private void findStudentAndCourses(AppDAO appDAO) {
        int theId = 1;

        System.out.println("Finding Student: " + theId);
        Student student = appDAO.findStudentAndCoursesByStudentId(theId);

        System.out.println("Student: " + student);
        System.out.println("Courses: " + student.getCourses());

        System.out.println("DONE!!");
    }

    private void findCourseAndStudents(AppDAO appDAO) {

        int theId = 10;

        System.out.println("Finding Course: " + theId);
        Course course = appDAO.findCourseAndStudentsByCourseId(theId);

        System.out.println("Course: " + course);
        System.out.println("associated Students: " + course.getStudents());

        System.out.println("DONE!");
    }

    private void createCourseAndStudents(AppDAO appDAO) {

        // Create the course
        Course course = new Course("Javascript");

        // Create students
        Student student1 = new Student("Teresa", "Torres", "teresa@gmail.com");
        Student student2 = new Student("Jorge", "Lopez", "martin@gmail.com");

        // add students to the course
        course.addStudent(student1);
        course.addStudent(student2);

        // save the course and associated students
        System.out.println("Creating course: " + course);
        System.out.println("associated students: " + course.getStudents());

        appDAO.saveCourse(course);

        System.out.println("DONE!!");
    }

    private void deleteCourseAndReviews(AppDAO appDAO) {
        int theId = 10;

        System.out.println("Deleting course: " + theId);
        appDAO.deleteCourseById(theId);

        System.out.println("DONE!!");
    }

    private void retrieveCourseWithReviews(AppDAO appDAO) {
        int theId = 10;

        System.out.println("Finding course: " + theId);
        Course course = appDAO.findCourseWithReviewsById(theId);

        System.out.println(course);
        System.out.println(course.getReviews());

        System.out.println("DONE!!");

    }

    private void createCourseAndReviews(AppDAO appDAO) {

        Course course = new Course("OOP with Javascript");

        // add some reviews
        course.add( new Review("Great course"));
        course.add( new Review("Cool course"));
        course.add( new Review("I didn't like the course"));

        System.out.println("Saving course");
        System.out.println(course);
        System.out.println(course.getReviews());

        appDAO.saveCourse(course);

        System.out.println("DONE!!");


    }

    private void deleteCourse(AppDAO appDAO) {
        int theId = 3;

        System.out.println("Deleting course " + theId);
        appDAO.deleteCourseById(theId);

        System.out.println("DONE!!");
    }

    private void updateCourse(AppDAO appDAO) {
        int theId = 4;

        System.out.println("Finding Course " + theId);
        Course course = appDAO.findCourseById(theId);

        System.out.println("Updating Course " + course);
        course.setTitle("C#");

        appDAO.update(course);

        System.out.println("DONE!");
    }

    private void updateInstructor(AppDAO appDAO) {
        int theId = 6;

        System.out.println("Finding Instructor:" + theId);
        Instructor instructor = appDAO.findById(theId);

        System.out.println("Updating Instructor: " + theId);
        instructor.setLastName("TESTER");

        appDAO.update(instructor);

        System.out.println("DONE");
    }

    private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {
        int theId = 6;

        // find the instructor
        System.out.println("Finding instructor id:" + theId);
        Instructor instructor = appDAO.findInstructorByIdJoinFetch(theId);

        System.out.println("instructor:" + instructor);
        System.out.println("the associated courses: " + instructor.getCourses());

        System.out.println("DONE!");
    }

    private void findCoursesForInstructor(AppDAO appDAO) {
        int theId = 6;

        // find the instructor
        Instructor instructor = appDAO.findById(theId);
        System.out.println("Instructor: " + instructor);

        // find courses for instructor
        List<Course> courses = appDAO.findCoursesByInstructorId(theId);

        // associate the objects
        instructor.setCourses(courses);

        System.out.println("the associated courses: " + instructor.getCourses());
    }

    private void findInstructorWithCourses(AppDAO appDAO) {
        int theId = 6;
        System.out.println("Finding instructor id:" + theId);

        Instructor theInstructor = appDAO.findById(theId);

        System.out.println("theInstructor:" + theInstructor);
        System.out.println("the associated courses: " + theInstructor.getCourses());

        System.out.println("Done!");
    }

    private void createInstructorWithCourses(AppDAO appDAO) {

        // Create Instructor
        Instructor tempInstructor = new Instructor("Chad","Darby","darby@gmail.com");

        // Create Instructor detail
        InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.google.com","Code");

        // associate the objects
        tempInstructor.setInstructorDetail(tempInstructorDetail);

        // create some courses
        Course tempCourse1 = new Course("Kotlin");
        Course tempCourse2 = new Course("Dart");

        // add courses to instructor
        tempInstructor.addCourse(tempCourse1);
        tempInstructor.addCourse(tempCourse2);

        // save the instructor
        System.out.println("Saving instructor" + tempInstructor);
        System.out.println("The courses:" + tempInstructor.getCourses());
        appDAO.save(tempInstructor);

        System.out.println("DONE!");


    }

    private void deleteInstructorDetail(AppDAO appDAO) {
        int theId = 3;

        System.out.println("Deleting InstructorDetail");

        appDAO.deleteInstructorDetailById(theId);

        System.out.println("InstructorDetail deleted successfully");
    }

    private void findInstructorDetail(AppDAO appDAO) {
        int theId = 2;
        System.out.println("Finding instructor detail by id: " + theId);

        InstructorDetail instructorDetail = appDAO.findInstructorDetailById(theId);

        System.out.println("Instructor detail: " + instructorDetail);
        System.out.println("The associated Instructor: " + instructorDetail.getInstructor());
    }

    private void deleteInstructor(AppDAO appDAO) {
        int theId = 6;

        System.out.println("Deleting Instructor");

        appDAO.deleteInstructorById(theId);

        System.out.println("Instructor deleted");
    }

    private void findInstructor(AppDAO appDAO) {
        int instructorId = 1;
        System.out.println("Finding instructor " + instructorId);

        Instructor instructor = appDAO.findById(instructorId);

        System.out.println("Found instructor " + instructor);
        System.out.println("Instructor Details: " + instructor.getInstructorDetail());
    }

    private void createInstructor(AppDAO appDAO) {


        Instructor tempInstructor = new Instructor("Chad","Darby","darby@gmail.com");


        InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.google.com","Code");

/*
        Instructor tempInstructor = new Instructor("Teresa","Galaviz","galaviz@gmail.com");


        InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.youtube.com","Piano");
*/
        // associate the objects
        tempInstructor.setInstructorDetail(tempInstructorDetail);

        System.out.println("saving instructor " + tempInstructor);
        appDAO.save(tempInstructor);

        System.out.println("Done");
    }

}
