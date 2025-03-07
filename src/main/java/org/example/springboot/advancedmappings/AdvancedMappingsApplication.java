package org.example.springboot.advancedmappings;

import org.example.springboot.advancedmappings.dao.AppDAO;
import org.example.springboot.advancedmappings.entity.Course;
import org.example.springboot.advancedmappings.entity.Instructor;
import org.example.springboot.advancedmappings.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class AdvancedMappingsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdvancedMappingsApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(AppDAO appDAO) {

        return runner -> {
            //createInstructor(appDAO);
            //findInstructor(appDAO);
            //deleteInstructor(appDAO);
            //findInstructorDetail(appDAO);
            //deleteInstructorDetail(appDAO);
            //createInstructorWithCourses(appDAO);
            //findInstructorWithCourses(appDAO);
            findCoursesForInstructor(appDAO);
            findInstructorWithCoursesJoinFetch(appDAO);
        };
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
        int theId = 1;

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
