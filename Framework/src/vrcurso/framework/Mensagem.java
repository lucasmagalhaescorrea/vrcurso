package vrcurso.framework;

import java.awt.Component;
import javax.swing.JOptionPane;

public class Mensagem {
   
    public static void exibirErro(Component i_parentComponent, Exception i_exception) {
        JOptionPane.showMessageDialog(i_parentComponent, i_exception.getMessage(), "Erro de Sistema", JOptionPane.ERROR_MESSAGE);
    }

    public static void exibirAlerta(Component i_parentComponent, Exception i_exception) {
        JOptionPane.showMessageDialog(i_parentComponent, i_exception.getMessage(), "Alerta do Sistema", JOptionPane.WARNING_MESSAGE);
    }

    public static void exibirMensagem(Component i_parentComponent, String i_msg) {
        JOptionPane.showMessageDialog(i_parentComponent, i_msg, "Mensagem do Sistema", JOptionPane.INFORMATION_MESSAGE);
    }

}
