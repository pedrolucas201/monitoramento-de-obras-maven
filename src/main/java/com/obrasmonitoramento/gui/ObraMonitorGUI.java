package com.obrasmonitoramento.gui;

import com.obrasmonitoramento.entities.*;
import com.obrasmonitoramento.services.*;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class ObraMonitorGUI extends Application {

    private ObrasIImplRepositorio obrasRepositorio = new ObrasIImplRepositorio();
    private EngenheiroService engenheiroService = new EngenheiroService();
    private TarefaService tarefaService = new TarefaService();
    private TextArea logArea;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Monitoramento de Obras");

        // Campos para entrada de dados
        TextField nomeField = new TextField();
        nomeField.setPromptText("Nome da Obra");

        TextField descricaoField = new TextField();
        descricaoField.setPromptText("Descrição");

        TextField enderecoField = new TextField();
        enderecoField.setPromptText("Endereço");

        // ComboBox para selecionar tipo de obra
        ComboBox<String> tipoObraComboBox = new ComboBox<>();
        tipoObraComboBox.getItems().addAll("Comercial", "Residencial");
        tipoObraComboBox.setValue("Comercial");

        // Campos específicos para Obra Comercial
        TextField empresaField = new TextField();
        empresaField.setPromptText("Empresa Responsável");
        empresaField.setDisable(false);

        // Campos específicos para Obra Residencial
        TextField numeroApartamentosField = new TextField();
        numeroApartamentosField.setPromptText("Número de Apartamentos");
        numeroApartamentosField.setDisable(true);

        // Campos de Engenheiro
        TextField engenheiroNomeField = new TextField();
        engenheiroNomeField.setPromptText("Nome do Engenheiro");

        TextField engenheiroCREAField = new TextField();
        engenheiroCREAField.setPromptText("Registro CREA");

        // Campos de Tarefa
        TextField tarefaDescricaoField = new TextField();
        tarefaDescricaoField.setPromptText("Descrição da Tarefa");

        DatePicker tarefaDataPrevistaField = new DatePicker();
        tarefaDataPrevistaField.setPromptText("Data Prevista");

        DatePicker tarefaDataRealizadaField = new DatePicker();
        tarefaDataRealizadaField.setPromptText("Data Realizada");

        // Botões para ações
        Button addButton = new Button("Adicionar Obra");
        Button listButton = new Button("Listar Obras");

        // Área de log para exibição de informações
        logArea = new TextArea();
        logArea.setEditable(false);
        logArea.setPrefHeight(200);

        // Configuração dos botões
        addButton.setOnAction(e -> adicionarObra(nomeField, descricaoField, enderecoField, tipoObraComboBox, empresaField, numeroApartamentosField, engenheiroNomeField, engenheiroCREAField, tarefaDescricaoField, tarefaDataPrevistaField, tarefaDataRealizadaField));
        listButton.setOnAction(e -> listarObras());

        tipoObraComboBox.setOnAction(event -> {
            String tipoObra = tipoObraComboBox.getValue();
            if ("Comercial".equals(tipoObra)) {
                empresaField.setDisable(false);
                numeroApartamentosField.setDisable(true);
            } else {
                empresaField.setDisable(true);
                numeroApartamentosField.setDisable(false);
            }
        });

        // Layout
        GridPane formPane = new GridPane();
        formPane.setPadding(new Insets(10));
        formPane.setHgap(10);
        formPane.setVgap(10);
        formPane.add(new Label("Nome:"), 0, 0);
        formPane.add(nomeField, 1, 0);
        formPane.add(new Label("Descrição:"), 0, 1);
        formPane.add(descricaoField, 1, 1);
        formPane.add(new Label("Endereço:"), 0, 2);
        formPane.add(enderecoField, 1, 2);
        formPane.add(new Label("Tipo de Obra:"), 0, 3);
        formPane.add(tipoObraComboBox, 1, 3);
        formPane.add(new Label("Empresa Responsável:"), 0, 4);
        formPane.add(empresaField, 1, 4);
        formPane.add(new Label("Número de Apartamentos:"), 0, 5);
        formPane.add(numeroApartamentosField, 1, 5);
        formPane.add(new Label("Nome do Engenheiro:"), 0, 6);
        formPane.add(engenheiroNomeField, 1, 6);
        formPane.add(new Label("Registro CREA:"), 0, 7);
        formPane.add(engenheiroCREAField, 1, 7);
        formPane.add(new Label("Descrição da Tarefa:"), 0, 8);
        formPane.add(tarefaDescricaoField, 1, 8);
        formPane.add(new Label("Data Prevista:"), 0, 9);
        formPane.add(tarefaDataPrevistaField, 1, 9);
        formPane.add(new Label("Data Realizada:"), 0, 10);
        formPane.add(tarefaDataRealizadaField, 1, 10);

        VBox mainLayout = new VBox(10);
        mainLayout.setPadding(new Insets(10));
        mainLayout.getChildren().addAll(formPane, addButton, listButton, logArea);

        // Cena principal
        Scene scene = new Scene(mainLayout, 400, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void adicionarObra(TextField nomeField, TextField descricaoField, TextField enderecoField, ComboBox<String> tipoObraComboBox, TextField empresaField, TextField numeroApartamentosField, TextField engenheiroNomeField, TextField engenheiroCREAField, TextField tarefaDescricaoField, DatePicker tarefaDataPrevistaField, DatePicker tarefaDataRealizadaField) {
        String nome = nomeField.getText();
        String descricao = descricaoField.getText();
        String endereco = enderecoField.getText();

        if (nome.isEmpty() || descricao.isEmpty() || endereco.isEmpty()) {
            log("Todos os campos são obrigatórios para adicionar uma obra!");
            return;
        }

        String tipoObra = tipoObraComboBox.getValue();
        Obra obra = null;

        if ("Comercial".equals(tipoObra)) {
            String empresa = empresaField.getText();
            if (empresa.isEmpty()) {
                log("O campo 'Empresa Responsável' é obrigatório para obra comercial!");
                return;
            }
            obra = new ObraComercial(nome, descricao, endereco, empresa);
        } else if ("Residencial".equals(tipoObra)) {
            String numApartamentosText = numeroApartamentosField.getText();
            if (numApartamentosText.isEmpty()) {
                log("O campo 'Número de Apartamentos' é obrigatório para obra residencial!");
                return;
            }
            int numeroApartamentos = Integer.parseInt(numApartamentosText);
            obra = new ObraResidencial(nome, descricao, endereco, numeroApartamentos);
        }

        obrasRepositorio.salvar(obra);
        Engenheiro engenheiro = new Engenheiro(engenheiroNomeField.getText(), engenheiroCREAField.getText());
        engenheiroService.salvar(engenheiro);

        Tarefa tarefa = new Tarefa(tarefaDescricaoField.getText(),
                java.sql.Date.valueOf(tarefaDataPrevistaField.getValue()),
                java.sql.Date.valueOf(tarefaDataRealizadaField.getValue()),
                obra);
        tarefaService.salvar(tarefa);

        log("Obra adicionada: " + obra);
        limparCampos(nomeField, descricaoField, enderecoField, empresaField, numeroApartamentosField, engenheiroNomeField, engenheiroCREAField, tarefaDescricaoField, tarefaDataPrevistaField, tarefaDataRealizadaField);
    }

    private void listarObras() {
        List<Obra> obras = obrasRepositorio.listarTodas();
        if (obras.isEmpty()) {
            log("Nenhuma obra cadastrada.");
        } else {
            log("Lista de Obras:");
            for (Obra obra : obras) {
                log(obra.toString());
            }
        }
    }

    private void log(String mensagem) {
        logArea.appendText(mensagem + "\n");
    }

    private void limparCampos(javafx.scene.control.Control... controles) {
        for (javafx.scene.control.Control controle : controles) {
            if (controle instanceof javafx.scene.control.TextField) {
                ((javafx.scene.control.TextField) controle).clear();
            } else if (controle instanceof javafx.scene.control.DatePicker) {
                ((javafx.scene.control.DatePicker) controle).setValue(null);
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
