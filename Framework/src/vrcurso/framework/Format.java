package vrcurso.framework;

public class Format {
    
    public static String number(int i_number, int i_tamanho){
    
        String numero = String.valueOf(i_number);
        
        StringBuilder aux = new StringBuilder();
        
        for(int i= 0; i< (i_tamanho - numero.length()); i++){
            aux.append("0");
        }
        
        aux.append(numero);
        
        return aux.toString();
    }
    
}
