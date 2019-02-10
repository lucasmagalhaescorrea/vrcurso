package vrcurso.view.tablemodel;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import vrcurso.framework.Format;
import vrcurso.modelo.Disciplina;
import vrcurso.modelo.enuns.DiaSemana;

public class DisciplinaTableModel  extends AbstractTableModel{
    
    private String[] vColunas = {"Código", "Descricao", "Qtde Vagas", "Professor", "Dia Semana", "Carga Horária"};
    private List<Disciplina> vDisciplina;

    public DisciplinaTableModel(List<Disciplina> vDisciplina) {
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
        
        Disciplina oDisciplina = vDisciplina.get(rowIndex);
        
        switch(columnIndex){
            case 0:
                return Format.number(oDisciplina.getId(), 6);
            case 1:
                return oDisciplina.getDescricao();
            case 2:
                return Format.number(oDisciplina.getLimiteVagas(), 3);
            case 3:
                return oDisciplina.getProfessor();
            case 4:
                return DiaSemana.getDescricao(oDisciplina.getDiaSemana());
            case 5:
               return Format.number(oDisciplina.getCargaHoraria(), 2) + " hs";
            default:
                return "";
        }
    }
    
}
