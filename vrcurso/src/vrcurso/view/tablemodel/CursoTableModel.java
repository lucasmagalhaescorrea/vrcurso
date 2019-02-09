package vrcurso.view.tablemodel;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import vrcurso.framework.Format;
import vrcurso.modelo.Curso;
import vrcurso.modelo.Professor;

public class CursoTableModel extends AbstractTableModel{
    
    private String[] vColunas = {"Código", "Descricao", "Duração", "Período", "Qtde. de Alunos", "Carga Horária"};
    private List<Curso> vCurso;

    @Override
    public int getRowCount() {
        return vCurso.size();
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
        
        Curso oCurso = vCurso.get(rowIndex);
        
        switch(columnIndex){
            case 0:
                return Format.number(oCurso.getId(), 6);
            case 1:
                return oCurso.getDescricao();
            case 2:
                return String.valueOf(oCurso.getDuracaoMeses()) + (oCurso.getDuracaoMeses() > 1 ? "Meses" : "Mês");
            case 3:
                return Format.number(oCurso.getQtdAlunos(), 3);
            case 4:
                return Format.number(oCurso.getCargaHoraria(), 2) + " hs";
            case 5:
            default:
                return "";
        }
    }
    
}
