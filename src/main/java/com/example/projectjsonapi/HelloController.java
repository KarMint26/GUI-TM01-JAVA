package com.example.projectjsonapi;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class HelloController {
    @FXML
    private TextField totalData;

    @FXML
    private TextField hargaTertinggiText;

    @FXML
    protected void onCloseButtonClick() {
        System.exit(100);
    }

    @FXML
    protected void onSubmitButton() throws IOException {
        ResultJSON resultJSON = new ResultJSON();
        resultJSON.result();

        totalData.appendText(String.valueOf(resultJSON.getJumlahData()));
        hargaTertinggiText.appendText(String.valueOf(resultJSON.getHargaTertinggi()));
    }
}