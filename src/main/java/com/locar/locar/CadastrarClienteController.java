package com.locar.locar;

import com.locar.locar.ExeExecutor.ExecutionResult;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.control.*;

public class CadastrarClienteController {
    // Cadastrar cliente
    @FXML
    private TextField cadastrarNome;
    @FXML
    private TextField cadastrarCpf;
    @FXML
    private TextField cadastrarTelefone;
    @FXML
    private TextField cadastrarEmail;
    @FXML
    private DatePicker cadastrarNascimento;

    // Informações do CNH
    @FXML
    private TextField numeroCNH;
    @FXML
    private TextField dataValidade;
    @FXML
    private TextField StatusCNH;
    @FXML
    private TextField ufCNH;
    @FXML
    private TextField categoriaCNH;

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

    public void salvarCliente() {
        try {
            ExecutionResult result = ExeExecutor.executeExe("api_bundle.exe",
                    new String[] { "createUser", cadastrarNome.getText(), cadastrarCpf.getText(),
                            cadastrarTelefone.getText(), cadastrarEmail.getText(),
                            cadastrarNascimento.getValue().toString(), numeroCNH.getText(), dataValidade.getText(),
                            StatusCNH.getText(), ufCNH.getText(), categoriaCNH.getText() });

            if (!result.stderr.isEmpty()) {
                throw new Exception(result.stderr);
            }

            System.out.println(result.stdout);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Sucesso!");
            alert.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
