package view;

import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;
import service.CourseService;
import service.CourseServiceImp;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CourseService courseService = new CourseServiceImp();
        Scanner input = new Scanner(System.in);
        int options;

        do {
            Table table = new Table(4, BorderStyle.UNICODE_BOX_DOUBLE_BORDER_WIDE, ShownBorders.SURROUND);
            table.addCell("   1. ADD NEW COURSE");
            table.addCell("   2. LIST COURSE");
            table.addCell("   3. FIND COURSE BY ID");
            table.addCell("   4. FIND COURSE BY NAME");
            table.addCell("   5. REMOVE COURSE BY ID");
            table.addCell("   0,99. EXIT...");
            System.out.println(table.render());
            System.out.print("Enter your choice: ");
            options = input.nextInt();

            switch (options) {
                case 1:
                    courseService.addCourse();
                    break;
                case 2:
                    courseService.listCourses();
                    break;
                case 3:
                    courseService.findCourseById();
                    break;
                case 4:
                    courseService.findCourseByTitle();
                    break;
                case 5:
                    courseService.removeCourseById();
                    break;
                case 0:
                    System.out.println("==>> Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (options!=0);
    }
}
