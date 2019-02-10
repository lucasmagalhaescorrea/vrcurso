package vrcurso.view.tablemodel;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import vrcurso.framework.Format;
import vrcurso.modelo.CursoDisciplina;
import vrcurso.modelo.enuns.DiaSemana;

public class CursoDisciplinaTableModel  extends AbstractTableModel{
    
    private String[] vColunas = {"Código", "Descricao" , "Professor", "Dia Semana"};
    private List<CursoDisciplina> vDisciplina;

    public CursoDisciplinaTableModel(List<CursoDisciplina> vDisciplina) {
        this.vDisciplina = vDisciplina;
    }

    @Override
    public int getRowCount() {
        return vDisciplina.size();
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
        
        CursoDisciplina oDisciplina = vDisciplina.get(rowIndex);
        
        switch(columnIndex){
            case 0:
                return Format.number(oDisciplina.getId(), 6);
            case 1:
                return oDisciplina.getDisciplina();
            case 2:
                return oDisciplina.getProfessor();
            case 3:
                return DiaSemana.getDescricao(oDisciplina.getDiaSemana());
            default:
                return "";
        }
    }
    
}
