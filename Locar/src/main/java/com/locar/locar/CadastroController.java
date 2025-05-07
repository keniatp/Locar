package com.locar.locar;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class CadastroController {
    public void FazerLogin(javafx.event.ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Login.fxml"));
            Parent telaPrincipalRoot = fxmlLoader.load();

            Stage stageAtual = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stageAtual.getScene().setRoot(telaPrincipalRoot);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
