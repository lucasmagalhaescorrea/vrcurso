package vrcurso.view.tablemodel;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import vrcurso.framework.Format;
import vrcurso.modelo.Aluno;
import vrcurso.modelo.Matricula;

public class MatriculaTableModel extends AbstractTableModel{
    
    private String[] vColunas = {"Matricula", "Aluno", "Curso", "Qtde. Disciplinas"};
    private List<Matricula> vMatricula;

    @Override
    public int getRowCount() {
        return vMatricula.size();
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
        
        Matricula oMatricula = vMatricula.get(rowIndex);
        
        switch(columnIndex){
            case 0:
                return Format.number(oMatricula.getMatricula(), 6);
            case 1:
                return oMatricula.getAluno();
            case 2:
                return oMatricula.getCurso();
            case 3:
                return oMatricula.getQtdDisciplinas();
            default:
                return "";
        }
    }
    
}
