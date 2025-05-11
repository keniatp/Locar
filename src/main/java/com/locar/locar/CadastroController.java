package com.locar.locar;

import com.locar.locar.ExeExecutor.ExecutionResult;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CadastroController {
    @FXML
    private TextField nomeCompleto;
    @FXML
    private TextField CPF;
    @FXML
    private TextField email;
    @FXML
    private TextField telefone;
    @FXML
    private TextField codigo;
    @FXML
    private TextField senha;

    public void FazerLogin(javafx.event.ActionEvent event) {
        try {
            ExecutionResult result = ExeExecutor.executeExe("api_bundle.exe",
                    new String[] { "createEmployee", nomeCompleto.getText(), CPF.getText(), email.getText(),
                            telefone.getText(), codigo.getText(), senha.getText() });

            if (!result.stderr.isEmpty()) {
                throw new Exception(result.stderr);
            }

            System.out.println(result.stdout);

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Login.fxml"));
            Parent telaPrincipalRoot = fxmlLoader.load();

            Stage stageAtual = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stageAtual.getScene().setRoot(telaPrincipalRoot);

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Erro! Entre em contato com o suporte");
            alert.showAndWait();
            e.printStackTrace();
        }
    }

}
