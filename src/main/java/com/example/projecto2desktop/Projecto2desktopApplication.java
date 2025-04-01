package com.example.projecto2desktop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

@SpringBootApplication
public class Projecto2desktopApplication extends Application {

    private static ConfigurableApplicationContext springContext;
    private static Stage primaryStage; // Mantém referência ao palco principal

    public static void main(String[] args) {
        springContext = SpringApplication.run(Projecto2desktopApplication.class, args);
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        carregarTelaLogin();

        // Reforça o tamanho total logo após show()
        primaryStage.setX(0);
        primaryStage.setY(0);
        primaryStage.setWidth(javafx.stage.Screen.getPrimary().getVisualBounds().getWidth());
        primaryStage.setHeight(javafx.stage.Screen.getPrimary().getVisualBounds().getHeight());
        primaryStage.setMaximized(true);
    }


    public static void carregarTelaLogin() throws IOException {
        FXMLLoader loader = new FXMLLoader(Projecto2desktopApplication.class.getResource("/com/example/projecto2desktop/login.fxml"));
        loader.setControllerFactory(springContext::getBean); // <- importante

        Parent root = loader.load();
        primaryStage.setScene(new Scene(root));
        primaryStage.setMinWidth(1024);
        primaryStage.setMinHeight(768);
        primaryStage.setTitle("Burguo Nervoso - Login");
        primaryStage.setMaximized(true);
        primaryStage.show();
    }



    public static void carregarTelaMenu() throws IOException {
        FXMLLoader loader = new FXMLLoader(Projecto2desktopApplication.class.getResource("/com/example/projecto2desktop/menu.fxml"));
        loader.setControllerFactory(springContext::getBean);
        Parent root = loader.load();

        primaryStage.setScene(new Scene(root));
        primaryStage.setMinWidth(1024);
        primaryStage.setMinHeight(768);

        primaryStage.setTitle("Burguo Nervoso - Menu");
        primaryStage.show();

    }

    public static void carregarTelaFornecedores() throws IOException {
        FXMLLoader loader = new FXMLLoader(Projecto2desktopApplication.class.getResource("/com/example/projecto2desktop/fornecedores.fxml"));
        loader.setControllerFactory(springContext::getBean);
        Parent root = loader.load();

        primaryStage.setScene(new Scene(root));
        primaryStage.setMinWidth(1024);
        primaryStage.setMinHeight(768);
        primaryStage.setTitle("Burguo Nervoso - Fornecedores");

        primaryStage.show();               // primeiro mostra
    }

}
