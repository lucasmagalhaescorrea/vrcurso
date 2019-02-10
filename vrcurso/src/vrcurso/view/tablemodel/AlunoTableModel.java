package vrcurso.view.tablemodel;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import vrcurso.framework.Format;
import vrcurso.modelo.Aluno;
import vrcurso.modelo.Professor;

public class AlunoTableModel extends AbstractTableModel{
    
    private String[] vColunas = {"Código", "Nome", "RG", "CPF", "Título"};
    private List<Aluno> vAluno;

    @Override
    public int getRowCount() {
        return vAluno.size();
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
        
        Aluno oAluno = vAluno.get(rowIndex);
        
        switch(columnIndex){
            case 0:
                return Format.number(oAluno.getId(), 6);
            case 1:
                return Format.number(oAluno.getMatricula(), 6);
            case 2:
                return oAluno.getNome();
            case 3:
                return oAluno.getRg();
            case 4:
                return oAluno.getCpf();
            default:
                return "";
        }
    }
    
}