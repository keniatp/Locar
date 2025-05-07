package com.locar.locar;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class CadastrarClienteController {

    public void CadastrarVeiculos(javafx.event.ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CadastrarVeiculo.fxml"));
            Parent telaPrincipalRoot = fxmlLoader.load();

            Stage stageAtual = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stageAtual.getScene().setRoot(telaPrincipalRoot);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void Painel(javafx.event.ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Painel.fxml"));
            Parent telaPrincipalRoot = fxmlLoader.load();

            Stage stageAtual = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stageAtual.getScene().setRoot(telaPrincipalRoot);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void IrTelaInicial(javafx.event.ActionEvent event) {
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
