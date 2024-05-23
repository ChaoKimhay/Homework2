package repository;

import model.Course;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CourseRepository {
    private final String filePath = "courses.dat";
    private List<Course> courses;

    public CourseRepository() {
        this.courses = loadCoursesFromFile();
    }

    public void addCourse(Course course) {
        courses.add(course);
        saveCoursesToFile();
    }

    public List<Course> getAllCourses() {
        return new ArrayList<>(courses);
    }

    public Optional<Course> findCourseById(int id) {
        return courses.stream()
                .filter(c -> c.getId() == id)
                .findFirst();
    }

    public List<Course> findCourseByTitle(String title) {
        return courses.stream().filter(c -> c.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }

    public boolean removeCourseById(int id) {
        boolean removed = courses.removeIf(c -> c.getId() == id);
        if (removed) {
            saveCoursesToFile();
        }
        return removed;
    }

    @SuppressWarnings("unchecked")
    private List<Course> loadCoursesFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (List<Course>) ois.readObject();
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private void saveCoursesToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(courses);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
