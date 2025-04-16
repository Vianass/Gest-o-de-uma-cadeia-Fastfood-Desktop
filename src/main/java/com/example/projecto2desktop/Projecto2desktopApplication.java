package com.example.projecto2desktop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

@SpringBootApplication
public class Projecto2desktopApplication extends Application {

    private static ConfigurableApplicationContext springContext;
    private static Stage primaryStage;

    @Override
    public void init() {
        // Inicia Spring primeiro, antes do JavaFX estar totalmente inicializado
        springContext = new SpringApplicationBuilder(Projecto2desktopApplication.class)
                .headless(false) // Importante para o macOS ARM
                .run();
    }

    public static void main(String[] args) {
        System.setProperty("prism.order", "sw");
        System.setProperty("javafx.verbose", "true");
        System.setProperty("java.awt.headless", "false");

        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        System.out.println("DEBUG: Entrou em start(Stage).");
        primaryStage = stage;

        carregarTelaLogin();  // Carrega a FXML de login
        System.out.println("DEBUG: Chamou carregarTelaLogin().");

        primaryStage.show();  // Mostra o palco
        System.out.println("DEBUG: Fez primaryStage.show().");
    }

    // ===================== Métodos de transição entre telas =====================

    public static void carregarTelaLogin() throws IOException {
        System.out.println("DEBUG: A carregar login.fxml...");
        FXMLLoader loader = new FXMLLoader(
                Projecto2desktopApplication.class.getResource("/com/example/projecto2desktop/login.fxml")
        );
        loader.setControllerFactory(springContext::getBean);

        Parent root = loader.load();
        System.out.println("DEBUG: login.fxml carregado com sucesso!");

        primaryStage.setScene(new Scene(root));
        primaryStage.setMinWidth(1024);
        primaryStage.setMinHeight(768);
        primaryStage.setTitle("Burguo Nervoso - Login");

        // Se quiseres fullscreen depois da janela estar aberta, podes fazer:
        // Platform.runLater(() -> primaryStage.setMaximized(true));

        System.out.println("DEBUG: Scene definida e pronto para mostrar tela de Login.");
    }

    public static void carregarTelaMenu() throws IOException {
        System.out.println("DEBUG: A carregar menu.fxml...");
        FXMLLoader loader = new FXMLLoader(
                Projecto2desktopApplication.class.getResource("/com/example/projecto2desktop/menu.fxml")
        );
        loader.setControllerFactory(springContext::getBean);

        Parent root = loader.load();
        System.out.println("DEBUG: menu.fxml carregado!");

        primaryStage.setScene(new Scene(root));
        primaryStage.setMinWidth(1024);
        primaryStage.setMinHeight(768);
        primaryStage.setTitle("Burguo Nervoso - Menu");

        // primaryStage.setMaximized(true); // podes descomentar após a janela abrir

        primaryStage.show();
        System.out.println("DEBUG: Tela de Menu mostrada.");
    }

    public static void carregarTelaMenuFuncionario() throws IOException {
        System.out.println("DEBUG: A carregar menu_funcionario.fxml...");
        FXMLLoader loader = new FXMLLoader(
                Projecto2desktopApplication.class.getResource("/com/example/projecto2desktop/menu_funcionario.fxml")
        );
        loader.setControllerFactory(springContext::getBean);

        Parent root = loader.load();
        System.out.println("DEBUG: menu_funcionario.fxml carregado!");

        primaryStage.setScene(new Scene(root));
        primaryStage.setMinWidth(1024);
        primaryStage.setMinHeight(768);
        primaryStage.setTitle("Burguo Nervoso - Funcionário");

        // primaryStage.setMaximized(true);

        primaryStage.show();
        System.out.println("DEBUG: Tela de Menu Funcionário mostrada.");
    }

    public static void carregarTelaFornecedores() throws IOException {
        System.out.println("DEBUG: A carregar fornecedores.fxml...");
        FXMLLoader loader = new FXMLLoader(
                Projecto2desktopApplication.class.getResource("/com/example/projecto2desktop/fornecedores.fxml")
        );
        loader.setControllerFactory(springContext::getBean);

        Parent root = loader.load();
        System.out.println("DEBUG: fornecedores.fxml carregado!");

        primaryStage.setScene(new Scene(root));
        primaryStage.setMinWidth(1024);
        primaryStage.setMinHeight(768);
        primaryStage.setTitle("Burguo Nervoso - Fornecedores");

        // primaryStage.setMaximized(true);

        primaryStage.show();
        System.out.println("DEBUG: Tela de Fornecedores mostrada.");
    }
}