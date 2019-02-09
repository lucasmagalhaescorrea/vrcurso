/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vrcurso.framework;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author vitor
 */
public class Mensagem {
   
    public static void exibirErro(JInternalFrame i_parentComponent, Exception i_exception) {
        JOptionPane.showMessageDialog(i_parentComponent, i_exception.getMessage(), i_parentComponent.getTitle(), JOptionPane.ERROR_MESSAGE);
    }

    public static void exibirAlerta(JInternalFrame i_parentComponent, Exception i_exception) {
        JOptionPane.showMessageDialog(i_parentComponent, i_exception.getMessage(), i_parentComponent.getTitle(), JOptionPane.WARNING_MESSAGE);
    }

    public static boolean exibirConfirmar() {
        return true;
    }

}
