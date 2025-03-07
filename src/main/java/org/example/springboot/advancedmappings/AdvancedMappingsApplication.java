package org.example.springboot.advancedmappings;

import org.example.springboot.advancedmappings.dao.AppDAO;
import org.example.springboot.advancedmappings.entity.Instructor;
import org.example.springboot.advancedmappings.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
            deleteInstructorDetail(appDAO);
        };
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
