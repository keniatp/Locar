package com.locar.locar;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.stage.FileChooser;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.control.*;

import java.io.File;

public class CadastrarVeiculoController {
    // Cadastrar veiculo
    @FXML
    private TextField marca;
    @FXML
    private TextField modelo;
    @FXML
    private TextField ano;
    @FXML
    private TextField cor;
    @FXML
    private TextField placa;
    @FXML
    private TextField renavam;
    @FXML
    private MenuButton capacidade;
    @FXML
    private MenuButton cambio;

    // Documentação
    @FXML
    private DatePicker ipva;
    @FXML
    private DatePicker seguro;
    @FXML
    private DatePicker vistoria;
    @FXML
    private MenuButton status;
    @FXML
    private TextField quilometragem;
    @FXML
    private DatePicker dataAquisicao;

    // manutenção e constrole
    @FXML
    private DatePicker ultimaManutencao;
    @FXML
    private DatePicker proximaManutencao;
    @FXML
    private TextField tipoDeManutencao;
    @FXML
    private TextField observacao;

    // Adicionar imagem
    @FXML
    private Button btnSelecionarImagem;
    @FXML
    private ImageView imageViewVeiculo;

    // Adicionar arquivos
    @FXML
    private Button btnSelecionarArquivo;
    @FXML
    private Label labelArquivoSelecionado;

    @FXML // Adicionar imagem
    private void selecionarImagem(javafx.event.ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecionar Imagem");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Imagens", "*.png", "*.jpg", "*.jpeg"));
        File arquivoSelecionado = fileChooser.showOpenDialog(null);
        if (arquivoSelecionado != null) {
            Image imagem = new Image(arquivoSelecionado.toURI().toString());
            imageViewVeiculo.setImage(imagem);
        }
    }

    // Adicionar arquivos
    @FXML
    private void selecionarArquivo() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecionar Arquivo");

        // Permitir todos os tipos de arquivo:
        /*
         * fileChooser.getExtensionFilters().add(
         * new FileChooser.ExtensionFilter("Todos os arquivos", "*.*")
         * );
         */

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PDF", "*.pdf"),
                new FileChooser.ExtensionFilter("Word", "*.docx"));

        File arquivoSelecionado = fileChooser.showOpenDialog(null);

        if (arquivoSelecionado != null) {
            labelArquivoSelecionado.setText(arquivoSelecionado.getName());
            // Aqui você pode salvar o caminho ou processar o arquivo
        } else {
            labelArquivoSelecionado.setText("Nenhum arquivo selecionado.");
        }
    }

    public void CadastroDeCliente(javafx.event.ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CadastrarCliente.fxml"));
            Parent telaPrincipalRoot = fxmlLoader.load();

            Stage stageAtual = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stageAtual.getScene().setRoot(telaPrincipalRoot);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void IrPainel(javafx.event.ActionEvent event) {
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
