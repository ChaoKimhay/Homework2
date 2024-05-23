package service;

import exception.CourseNotFoundException;
import model.Course;
import repository.CourseRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class CourseServiceImp implements CourseService {
    private final CourseRepository courseRepository = new CourseRepository();
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void addCourse() {
        try {
            System.out.print("--> Enter course ID: ");
            int id = Integer.parseInt(scanner.nextLine());
            System.out.print("--> Enter course title: ");
            String title = scanner.nextLine();
            System.out.print("--> Enter instructor names (comma separated): ");
            String[] instructorNames = scanner.nextLine().split(",");
            System.out.print("--> Enter course requirements (comma separated): ");
            String[] requirements = scanner.nextLine().split(",");
            Date startDate = new Date();
            Course course = new Course(id, title, instructorNames, requirements, startDate);
            courseRepository.addCourse(course);
            System.out.println("Course added: " + course);
        } catch (Exception e) {
            System.err.println("Error adding course: " + e.getMessage());
        }
    }

    @Override
    public void listCourses() {
        try {
            List<Course> courses = courseRepository.getAllCourses();
            if (courses.isEmpty()) {
                System.out.println("No available Course !");
            } else {
                System.out.printf("%-5s %-20s %-30s %-30s %-15s%n", "ID", "Title", "Instructors", "Requirements", "Start Date");
                System.out.println("---------------------------------------------------------------------------------------------------");
                for (Course course : courses) {
                    System.out.printf("%-5d %-20s %-30s %-30s %-15s%n",
                            course.getId(),
                            course.getTitle(),
                            String.join(", ", course.getInstructorName()).trim(),
                            String.join(", ", course.getRequirement()).trim(),
                            new SimpleDateFormat("yyyy-MM-dd").format(course.getStartDate()));
                }
            }
        } catch (Exception e) {
            System.err.println("Error listing courses: " + e.getMessage());
        }
    }

    @Override
    public void findCourseById() {
        try {
            System.out.print("Enter course ID to find: ");
            int id = Integer.parseInt(scanner.nextLine());
            Optional<Course> course = courseRepository.findCourseById(id);
            course.ifPresentOrElse(
                    System.out::println,
                    () -> { throw new CourseNotFoundException("Course not found with ID: " + id); }
            );
        } catch (CourseNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println("Error finding course by ID: " + e.getMessage());
        }
    }

    @Override
    public void findCourseByTitle() {
        try {
            System.out.print("Enter course title to find: ");
            String title = scanner.nextLine();
            List<Course> foundCourses = courseRepository.findCourseByTitle(title);
            if (!foundCourses.isEmpty()) {
                foundCourses.forEach(System.out::println);
            } else {
                throw new CourseNotFoundException("Course not found with title: " + title);
            }
        } catch (CourseNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println("Error finding course by title: " + e.getMessage());
        }
    }

    @Override
    public void removeCourseById() {
        try {
            System.out.print("Enter course ID to remove: ");
            int id = Integer.parseInt(scanner.nextLine());
            boolean removed = courseRepository.removeCourseById(id);
            if (removed) {
                System.out.println("Course removed with ID: " + id);
            } else {
                throw new CourseNotFoundException("No course found with ID: " + id);
            }
        } catch (CourseNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println("Error removing course by ID: " + e.getMessage());
        }
    }
}
