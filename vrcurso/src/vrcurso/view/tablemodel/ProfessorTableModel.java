package vrcurso.view.tablemodel;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import vrcurso.framework.Format;
import vrcurso.modelo.Professor;
import vrcurso.modelo.enuns.Titulo;

public class ProfessorTableModel extends AbstractTableModel{
    
    private String[] vColunas = {"Código", "Nome", "RG", "CPF", "Título"};
    private List<Professor> vProfessor;

    public ProfessorTableModel(List<Professor> vProfessor) {
        this.vProfessor = vProfessor;
    }

    @Override
    public int getRowCount() {
        return vProfessor.size();
    }

    @Override
    public int getColumnCount() {
        return vColunas.length;
    }

    @Override
    public String getColumnName(int column) {
        return vColunas[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return java.lang.String.class;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        Professor oProfessor = vProfessor.get(rowIndex);
        
        switch(columnIndex){
            case 0:
                return Format.number(oProfessor.getId(), 6);
            case 1:
                return oProfessor.getNome();
            case 2:
                return oProfessor.getRg();
            case 3:
                return oProfessor.getCpf();
            case 4:
                return Titulo.getDescricao(oProfessor.getTitulo());
            default:
                return "";
        }
    }
    
}

