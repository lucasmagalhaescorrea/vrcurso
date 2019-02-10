package ws.modelo;

import java.util.ArrayList;
import java.util.List;

public class Matricula {
    
    private int id = 0;
    private int idAluno;
    private int matricula;
    private String aluno;
    private int idCurso;
    private String curso;
    private int periodo;
    private int qtdDisciplinas;
    private List<MatriculaDisciplina> vMatriculaDisciplina = new ArrayList<>();
    private List<MatriculaDisciplina> vMatriculaDisciplinaExclusao = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(int idAluno) {
        this.idAluno = idAluno;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getAluno() {
        return aluno;
    }

    public void setAluno(String aluno) {
        this.aluno = aluno;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }

    public List<MatriculaDisciplina> getvMatriculaDisciplina() {
        return vMatriculaDisciplina;
    }

    public void setvMatriculaDisciplina(List<MatriculaDisciplina> vMatriculaDisciplina) {
        this.vMatriculaDisciplina = vMatriculaDisciplina;
    }

    public List<MatriculaDisciplina> getvMatriculaDisciplinaExclusao() {
        return vMatriculaDisciplinaExclusao;
    }

    public void setvMatriculaDisciplinaExclusao(List<MatriculaDisciplina> vMatriculaDisciplinaExclusao) {
        this.vMatriculaDisciplinaExclusao = vMatriculaDisciplinaExclusao;
    }

    public int getQtdDisciplinas() {
        return qtdDisciplinas;
    }

    public void setQtdDisciplinas(int qtdDisciplinas) {
        this.qtdDisciplinas = qtdDisciplinas;
    }    
}
