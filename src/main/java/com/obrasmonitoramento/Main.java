package com.obrasmonitoramento;

import com.obrasmonitoramento.gui.ObraMonitorGUI;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main {
    public static void main(String[] args) {
        // Configurar o JavaFX SDK manualmente se necessário
        System.setProperty("javafx.runtime.version", "21");
        System.setProperty("module.path", "C:\\javafx-sdk-21.0.5\\lib");

        Application.launch(ObraMonitorGUI.class, args);
    }
}
