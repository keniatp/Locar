package com.locar.locar;

import java.util.HashMap;

import com.locar.locar.ExeExecutor.ExecutionResult;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LocacaoController {
    // Locação
    @FXML
    private MenuButton cliente;
    @FXML
    private MenuButton veiculo;
    @FXML
    private DatePicker periodoInicio;
    @FXML
    private DatePicker periodoFinal;
    @FXML
    private MenuButton taxa;

    // Pagamento
    @FXML
    private DatePicker dataPagamento;
    @FXML
    private MenuButton formaDePagamento;
    @FXML
    private TextField valorTotal;

    // Devolução
    @FXML
    private DatePicker dataDevolucao;
    @FXML
    private TextField combustivel;
    @FXML
    private MenuButton status;
    @FXML
    private TextField observacao;

    HashMap<String, Integer> idUsuarioSet = new HashMap<>();
    HashMap<String, Integer> idVeiculosSet = new HashMap<>();
    HashMap<String, Integer> idTaxasSet = new HashMap<>();

    @FXML
    public void initialize() {
        try {
            // Add all clients as menu items
            for (String usuario : ExeExecutor.executeExe("api_bundle.exe", new String[] { "getUsers" }).stdout
                    .split("_\t_")) {
                String[] data = usuario.trim().split("\n");

                Integer id_aipapai = Integer.parseInt(data[0].split(":")[1].trim());
                String nomeUsuario = data[1].split(":")[1].trim();
                String finalLabel = nomeUsuario.substring(1, nomeUsuario.length() - 1);
                idUsuarioSet.put(finalLabel, id_aipapai);

                cliente.getItems().add(new MenuItem(finalLabel));
            }

            // Add all vehicles as menu items
            for (String veiculos : ExeExecutor.executeExe("api_bundle.exe", new String[] { "getVehicles" }).stdout
                    .split("_\t_")) {
                String[] data = veiculos.trim().split("\n");

                String marca = data[1].split(":")[1].trim();
                String modelo = data[2].split(":")[1].trim();

                Integer id_aipapai = Integer.parseInt(data[0].split(":")[1].trim());
                String finalLabel = marca.substring(1, marca.length() - 1) + " "
                        + modelo.substring(1, modelo.length() - 1);
                idVeiculosSet.put(finalLabel, id_aipapai);

                veiculo.getItems().add(new MenuItem(finalLabel));
            }

            // Add all 'taxas' as menu items
            for (String taxas : ExeExecutor.executeExe("api_bundle.exe", new String[] { "getFees" }).stdout
                    .split("_\t_")) {
                String[] data = taxas.trim().split("\n");

                Integer id_aipapai = Integer.parseInt(data[0].split(":")[1].trim());
                String descricao = data[1].split(":")[1].trim();
                String finalLabel = descricao.substring(1, descricao.length() - 1);
                idTaxasSet.put(finalLabel, id_aipapai);

                taxa.getItems().add(new MenuItem(finalLabel));
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Erro! Entre em contato com o suporte");
            alert.showAndWait();
            e.printStackTrace();
        }

        cliente.getItems().forEach((item) -> item.setOnAction((_) -> cliente.setText(item.getText())));
        veiculo.getItems().forEach((item) -> item.setOnAction((_) -> veiculo.setText(item.getText())));
        formaDePagamento.getItems()
                .forEach((item) -> item.setOnAction((_) -> formaDePagamento.setText(item.getText())));
        status.getItems().forEach((item) -> item.setOnAction((_) -> status.setText(item.getText())));
        taxa.getItems().forEach((item) -> item.setOnAction((_) -> taxa.setText(item.getText())));
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

    public void Veiculo(javafx.event.ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CadastrarVeiculo.fxml"));
            Parent telaPrincipalRoot = fxmlLoader.load();

            Stage stageAtual = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stageAtual.getScene().setRoot(telaPrincipalRoot);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Clientes(javafx.event.ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CadastrarCliente.fxml"));
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

    public void salvarLocacao() {
        try {
            ExecutionResult result = ExeExecutor.executeExe("api_bundle.exe",
                    new String[] { "createRenting", valorTotal.getText(), "Ativa",
                            periodoInicio.getValue().toString(), periodoFinal.getValue().toString(),
                            idUsuarioSet.get(cliente.getText()).toString(),
                            HelloApplication.id_cadastroFuncionario.toString(),
                            idVeiculosSet.get(veiculo.getText()).toString(),
                            dataDevolucao.getValue().toString(), combustivel.getText(), status.getText(),
                            observacao.getText(), valorTotal.getText(), dataDevolucao.getValue().toString(),
                            formaDePagamento.getText(), idTaxasSet.get(taxa.getText()).toString() });

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
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Erro! Entre em contato com o suporte");
            alert.showAndWait();
            e.printStackTrace();
        }
    }
}
