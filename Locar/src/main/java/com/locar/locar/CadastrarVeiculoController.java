package com.locar.locar;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class CadastrarVeiculoController {
    //ADicionar imagem
    @FXML
    private Button btnSelecionarImagem;
    @FXML
    private ImageView imageViewVeiculo;

    //Adicionar arquivos
    @FXML
    private Button btnSelecionarArquivo;
    @FXML
    private Label labelArquivoSelecionado;

    @FXML //Adicionar imagem
    private void selecionarImagem(javafx.event.ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecionar Imagem");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Imagens", "*.png", "*.jpg", "*.jpeg")
        );
        File arquivoSelecionado = fileChooser.showOpenDialog(null);
        if (arquivoSelecionado != null) {
            Image imagem = new Image(arquivoSelecionado.toURI().toString());
            imageViewVeiculo.setImage(imagem);
        }
    }


    //Adicionar arquivos
    @FXML
    private void selecionarArquivo() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecionar Arquivo");

        // Permitir todos os tipos de arquivo:
        /*
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Todos os arquivos", "*.*")
        );
        */

        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("PDF", "*.pdf"),
            new FileChooser.ExtensionFilter("Word", "*.docx")
        );

        File arquivoSelecionado = fileChooser.showOpenDialog(null);

        if (arquivoSelecionado != null) {
            labelArquivoSelecionado.setText(arquivoSelecionado.getName());
            // Aqui você pode salvar o caminho ou processar o arquivo
        } else {
            labelArquivoSelecionado.setText("Nenhum arquivo selecionado.");
        }
    }



}