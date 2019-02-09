/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vrcurso.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import vrcurso.framework.exception.ValidacaoException;

/**
 *
 * @author Lucas
 */
public class HttpService {

    protected void validarRetorno(String i_response) throws Exception {
        if (i_response.contains("|ERRO|")) {
            throw new Exception(i_response.replace("|ERRO|", ""));
        }

        if (i_response.contains("|ALERTA|")) {
            throw new ValidacaoException(i_response.replace("|ALERTA|", ""));
        }
    }

    protected String sendPost(String url, String json) throws Exception {
        // Cria um objeto HttpURLConnection:
        HttpURLConnection request = (HttpURLConnection) new URL(url).openConnection();

        try {
            request.setDoOutput(true);
            request.setDoInput(true);

            request.setRequestProperty("Content-Type", "application/json");

            request.setRequestMethod("POST");

            request.connect();

            try (OutputStream outputStream = request.getOutputStream()) {
                outputStream.write(json.getBytes("UTF-8"));
            }

            return readResponse(request);
        } finally {
            request.disconnect();
        }
    }

    protected String sendGet(String url) throws Exception {
        // Cria um objeto HttpURLConnection:
        HttpURLConnection request = (HttpURLConnection) new URL(url).openConnection();

        try {
            // Define que a conexão pode enviar informações e obtê-las de volta:
            request.setDoOutput(true);
            request.setDoInput(true);

            // Define o content-type:
            request.setRequestProperty("Content-Type", "application/json");

            // Define o método da requisição:
            request.setRequestMethod("GET");

            // Conecta na URL:
            request.connect();

            return readResponse(request);
        } finally {
            request.disconnect();
        }
    }

    private String readResponse(HttpURLConnection request) throws IOException {
        ByteArrayOutputStream os;
        try (InputStream is = request.getInputStream()) {
            os = new ByteArrayOutputStream();
            int b;
            while ((b = is.read()) != -1) {
                os.write(b);
            }
        }
        return new String(os.toByteArray());
    }

}
