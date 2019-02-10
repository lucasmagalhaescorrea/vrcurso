package ws.modelo.enuns;

public enum Titulo {
    
    MESTRE(1, "MESTRE"), DOUTOR(2, "DOUTOR"), PAD(3, "PAD");

    private int id;
    private String descricao;

    private Titulo(int i_id, String i_descricao) {
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
        
        for(Titulo s : Titulo.values()){
            if(s.getId() == i_id){
                return s.getDescricao();
            }
        }
        
        return "PERÍODO INVÁLIDO";
    }
    
}
