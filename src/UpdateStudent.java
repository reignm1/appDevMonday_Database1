import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class UpdateStudent {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the name of the student: ");
        String studentName = scanner.nextLine();

        System.out.print("Enter the new course: ");
        String newCourse = scanner.nextLine();

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "UPDATE students SET course = ? WHERE name = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, newCourse);
            stmt.setString(2, studentName);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Student record updated successfully!");
            } else {
                System.out.println("No student found with that name.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}