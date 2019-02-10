package vrcurso.framework;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

public class Mensagem {
   
    public static void exibirErro(JInternalFrame i_parentComponent, Exception i_exception) {
        JOptionPane.showMessageDialog(i_parentComponent, i_exception.getMessage(), i_parentComponent.getTitle(), JOptionPane.ERROR_MESSAGE);
    }

    public static void exibirAlerta(JInternalFrame i_parentComponent, Exception i_exception) {
        JOptionPane.showMessageDialog(i_parentComponent, i_exception.getMessage(), i_parentComponent.getTitle(), JOptionPane.WARNING_MESSAGE);
    }

    public static void exibirMensagem(JInternalFrame i_parentComponent, String i_msg) {
        JOptionPane.showMessageDialog(i_parentComponent, i_msg, i_parentComponent.getTitle(), JOptionPane.INFORMATION_MESSAGE);
    }

}
