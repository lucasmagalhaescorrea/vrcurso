package ws.modelo.enuns;

public enum DiaSemana {

    DOMINGO(1, "DOMINGO"), SEGUNDA(2, "SEGUNDA"), TERCA(3, "TERÇA"), QUARTA(4, "QUARTA"), QUINTA(5, "QUINTA"), SEXTA(6, "SEXTA"), SABADO(7, "SÁBADO");

    private int id;
    private String descricao;

    private DiaSemana(int i_id, String i_descricao) {
        this.id = i_id;
        this.descricao = i_descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public static String getDescricao(int i_id){
        
        for(DiaSemana s : DiaSemana.values()){
            if(s.getId() == i_id){
                return s.getDescricao();
            }
        }
        
        return "DIA SEMANA INVÁLIDO";
    }

}
