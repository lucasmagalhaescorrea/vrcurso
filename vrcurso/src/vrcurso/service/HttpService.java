package vrcurso.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import vrcurso.framework.exception.ValidacaoException;

public class HttpService {

    protected String consumirWebService(String i_url, String i_json) throws Exception {

        String response = sendPost(i_url, i_json);

        validarRetorno(response);

        return response;
    }

    private void validarRetorno(String i_response) throws Exception {
        if (i_response.contains("|ERRO|")) {
            throw new Exception(i_response.replace("|ERRO|", ""));
        }

        if (i_response.contains("|ALERTA|")) {
            throw new ValidacaoException(i_response.replace("|ALERTA|", ""));
        }
    }

    private String sendPost(String url, String json) throws Exception {
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
    
    protected static class EndPoints {
        
        public static String PROFESSOR_CONSULTAR = "http://localhost:8080/vrcursoWS/webresources/professor/consultar";
        public static String PROFESSOR_SALVAR = "http://localhost:8080/vrcursoWS/webresources/professor/salvar";
        public static String PROFESSOR_REMOVER = "http://localhost:8080/vrcursoWS/webresources/professor/remover";
        
        public static String ALUNO_CONSULTAR = "http://localhost:8080/vrcursoWS/webresources/aluno/consultar";
        public static String ALUNO_SALVAR = "http://localhost:8080/vrcursoWS/webresources/aluno/salvar";
        public static String ALUNO_REMOVER = "http://localhost:8080/vrcursoWS/webresources/aluno/remover";
        
        public static String DISCIPLINA_CONSULTAR = "http://localhost:8080/vrcursoWS/webresources/disciplina/consultar";
        public static String DISCIPLINA_SALVAR = "http://localhost:8080/vrcursoWS/webresources/disciplina/salvar";
        public static String DISCIPLINA_REMOVER = "http://localhost:8080/vrcursoWS/webresources/disciplina/remover";
        
        public static String CURSO_CONSULTAR = "http://localhost:8080/vrcursoWS/webresources/curso/consultar";
        public static String CURSO_CARREGAR = "http://localhost:8080/vrcursoWS/webresources/curso/carregar";
        public static String CURSO_SALVAR = "http://localhost:8080/vrcursoWS/webresources/curso/salvar";
        public static String CURSO_REMOVER = "http://localhost:8080/vrcursoWS/webresources/curso/remover";
        
        public static String MATRICULA_CONSULTAR = "http://localhost:8080/vrcursoWS/webresources/matricula/consultar";
        public static String MATRICULA_CARREGAR = "http://localhost:8080/vrcursoWS/webresources/matricula/carregar";
        public static String MATRICULA_SALVAR = "http://localhost:8080/vrcursoWS/webresources/matricula/salvar";
        public static String MATRICULA_REMOVER = "http://localhost:8080/vrcursoWS/webresources/matricula/remover";
    }

}
