package model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

public class Course implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String title;
    private String[] instructorName;
    private String[] requirement;
    private Date startDate;

    public Course(Integer id, String title, String[] instructorName, String[] requirement, Date startDate) {
        this.id = id;
        this.title = title;
        this.instructorName = instructorName;
        this.requirement = requirement;
        this.startDate = startDate;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String[] getInstructorName() {
        return instructorName;
    }

    public String[] getRequirement() {
        return requirement;
    }

    public Date getStartDate() {
        return startDate;
    }


    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", instructorName=" + Arrays.toString(instructorName) +
                ", requirement=" + Arrays.toString(requirement) +
                ", startDate=" + startDate +
                '}';
    }
}
