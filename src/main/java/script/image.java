package script;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class image {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // Connect to MySQL
            String url = "jdbc:mysql://localhost:3306/signify";
            String user = "root";
            String password ="v=ltX5AtW9v30";
            connection = DriverManager.getConnection(url, user, password);


            String folderPath = "D:\\demo1\\src\\main\\resources\\com\\example\\demo1\\alphabets\\Alphabets";


            File folder = new File(folderPath);
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile() && (file.getName().endsWith(".png") || file.getName().endsWith(".jpg") || file.getName().endsWith(".jpeg"))) {
                        // Insert image information into the MySQL table
                        String name = file.getName().replaceFirst("[.][^.]+$", ""); // Remove file extension
                        String imagePath = file.getAbsolutePath();

                        String insertQuery = "INSERT INTO sign_symbols (name, image_path) VALUES (?, ?)";
                        preparedStatement = connection.prepareStatement(insertQuery);
                        preparedStatement.setString(1, name);
                        preparedStatement.setString(2, imagePath);
                        preparedStatement.executeUpdate();
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
