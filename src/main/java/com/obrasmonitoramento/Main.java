package com.obrasmonitoramento;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Configuração inicial do JavaFX
        System.out.println("Hello and welcome!");

        for (int i = 1; i <= 5; i++) {
            System.out.println("i = " + i);
        }

        // Aqui você pode adicionar a inicialização de sua interface gráfica (GUI)
        // Usando o primaryStage, que é a janela principal do JavaFX
        primaryStage.setTitle("Monitoramento de Obras");
        primaryStage.show(); // Exibe a janela

        // Se você tiver algum layout ou cena, você pode configurar aqui
    }

    public static void main(String[] args) {
        launch(args); // Inicia a aplicação JavaFX
    }
}
