package vrcurso.modelo.enuns;

public enum Periodo {
    
    MATUTINO(1, "MATUTINO"), VESPERTINO(2, "VESPERTINO"), NOTURNO(3, "NOTURNO"), INTEGRAL(4, "INTEGRAL");

    private int id;
    private String descricao;

    private Periodo(int i_id, String i_descricao) {
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
        
        return "PERÍODO INVÁLIDO";
    }
    
}
