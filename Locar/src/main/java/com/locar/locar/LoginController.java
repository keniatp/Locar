package com.locar.locar;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;

public class LoginController {
    public void CriarConta(javafx.event.ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Cadastro.fxml"));
            Parent telaPrincipalRoot = fxmlLoader.load();

            Stage stageAtual = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stageAtual.getScene().setRoot(telaPrincipalRoot);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void TelaInicial(javafx.event.ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TelaInicial.fxml"));
            Parent telaPrincipalRoot = fxmlLoader.load();

            Stage stageAtual = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stageAtual.getScene().setRoot(telaPrincipalRoot);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
