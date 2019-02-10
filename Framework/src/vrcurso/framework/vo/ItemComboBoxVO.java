package vrcurso.framework.vo;

public class ItemComboBoxVO {
    
    private int id;
    private String descricao;

    public ItemComboBoxVO(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
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

    @Override
    public String toString() {
        return getDescricao();
    }

    @Override
    public boolean equals(Object obj) {
        return getId() == getId();
    }
    
}
