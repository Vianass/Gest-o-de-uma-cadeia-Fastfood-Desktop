package com.example.projecto2desktop;

import com.example.projecto2desktop.models.Funcionario;
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

        // Garantir que arranca fullscreen
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
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    public static void carregarTelaMenuFuncionario() throws IOException {
        FXMLLoader loader = new FXMLLoader(Projecto2desktopApplication.class.getResource("/com/example/projecto2desktop/menu_funcionario.fxml"));
        loader.setControllerFactory(springContext::getBean);
        Parent root = loader.load();

        primaryStage.setScene(new Scene(root));
        primaryStage.setMinWidth(1024);
        primaryStage.setMinHeight(768);
        primaryStage.setTitle("Burguo Nervoso - Funcionário");
        primaryStage.setMaximized(true);
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
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    public static void carregarTelaEfetuarEncomenda() throws IOException {
        FXMLLoader loader = new FXMLLoader(Projecto2desktopApplication.class.getResource("/com/example/projecto2desktop/efetuar_encomenda.fxml"));
        loader.setControllerFactory(springContext::getBean);

        Parent root = loader.load();
        primaryStage.setScene(new Scene(root));
        primaryStage.setMinWidth(1024);
        primaryStage.setMinHeight(768);
        primaryStage.setTitle("Burguo Nervoso - Efetuar Encomenda");
        primaryStage.show();
    }

    public static void carregarTelaArmazens() throws IOException {
        FXMLLoader loader = new FXMLLoader(Projecto2desktopApplication.class.getResource("/com/example/projecto2desktop/armazens.fxml"));
        loader.setControllerFactory(springContext::getBean);

        Parent root = loader.load();
        primaryStage.setScene(new Scene(root));
        primaryStage.setMinWidth(1024);
        primaryStage.setMinHeight(768);
        primaryStage.setTitle("Burguo Nervoso - Armazéns");
        primaryStage.show();
    }

    public static void carregarTelaDistribuicao() throws IOException {
        FXMLLoader loader = new FXMLLoader(Projecto2desktopApplication.class.getResource("/com/example/projecto2desktop/distribuicao.fxml"));
        loader.setControllerFactory(springContext::getBean);

        Parent root = loader.load();
        primaryStage.setScene(new Scene(root));
        primaryStage.setMinWidth(1024);
        primaryStage.setMinHeight(768);
        primaryStage.setTitle("Burguo Nervoso - Distribuição");
        primaryStage.show();
    }

    public static void carregarTelaGerirPedidos() throws IOException {
        FXMLLoader loader = new FXMLLoader(Projecto2desktopApplication.class.getResource("/com/example/projecto2desktop/gerir_pedidos.fxml"));
        loader.setControllerFactory(springContext::getBean);
        Parent root = loader.load();

        primaryStage.setScene(new Scene(root));
        primaryStage.setMinWidth(1024);
        primaryStage.setMinHeight(768);
        primaryStage.setTitle("Burguo Nervoso - Gerir Pedidos");
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    public static void carregarTelaReceberEncomenda() throws IOException {
        FXMLLoader loader = new FXMLLoader(Projecto2desktopApplication.class.getResource("/com/example/projecto2desktop/receber_encomenda.fxml"));
        loader.setControllerFactory(springContext::getBean);
        Parent root = loader.load();

        primaryStage.setScene(new Scene(root));
        primaryStage.setMinWidth(1024);
        primaryStage.setMinHeight(768);
        primaryStage.setTitle("Burguo Nervoso - Receber Encomenda");
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

}
