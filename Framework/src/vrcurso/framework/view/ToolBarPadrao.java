/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vrcurso.framework.view;

import javax.swing.JButton;
import javax.swing.SwingUtilities;
import vrcurso.framework.Mensagem;
import vrcurso.framework.exception.ValidacaoException;

/**
 *
 * @author vitor
 */
public class ToolBarPadrao extends javax.swing.JPanel {

    private boolean consultarVisible = false;
    private boolean novoVisible = false;
    private boolean removerVisible = false;
    private boolean salvarVisible = false;
    private boolean editarVisible = false;

    private InternalFrame internalFrame;

    public ToolBarPadrao() {
        initComponents();

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                btnConsultar.setVisible(isConsultarVisible());
                btnNovo.setVisible(isNovoVisible());
                btnRemover.setVisible(isRemoverVisible());
                btnSalvar.setVisible(isSalvarVisible());
                btnEditar.setVisible(isEditarVisible());

                organizarBotoes();
                revalidate();
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnConsultar = new javax.swing.JButton();
        btnNovo = new javax.swing.JButton();
        btnRemover = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        setPreferredSize(new java.awt.Dimension(400, 39));
        setLayout(null);

        btnConsultar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vrcurso/framework/view/imagens/btnConsultar.png"))); // NOI18N
        btnConsultar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnConsultar.setContentAreaFilled(false);
        btnConsultar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnConsultar.setMargin(new java.awt.Insets(2, 2, 2, 2));
        btnConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarActionPerformed(evt);
            }
        });
        add(btnConsultar);
        btnConsultar.setBounds(5, 5, 30, 30);

        btnNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vrcurso/framework/view/imagens/btnAdicionar.png"))); // NOI18N
        btnNovo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnNovo.setContentAreaFilled(false);
        btnNovo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNovo.setMargin(new java.awt.Insets(2, 2, 2, 2));
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });
        add(btnNovo);
        btnNovo.setBounds(40, 5, 30, 30);

        btnRemover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vrcurso/framework/view/imagens/btnRemover.png"))); // NOI18N
        btnRemover.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnRemover.setContentAreaFilled(false);
        btnRemover.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRemover.setMargin(new java.awt.Insets(2, 2, 2, 2));
        btnRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverActionPerformed(evt);
            }
        });
        add(btnRemover);
        btnRemover.setBounds(75, 5, 30, 30);

        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vrcurso/framework/view/imagens/btnSalvar.png"))); // NOI18N
        btnSalvar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnSalvar.setContentAreaFilled(false);
        btnSalvar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSalvar.setMargin(new java.awt.Insets(2, 2, 2, 2));
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });
        add(btnSalvar);
        btnSalvar.setBounds(110, 5, 30, 30);

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vrcurso/framework/view/imagens/btnEditar.png"))); // NOI18N
        btnEditar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnEditar.setContentAreaFilled(false);
        btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEditar.setMargin(new java.awt.Insets(2, 2, 2, 2));
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        add(btnEditar);
        btnEditar.setBounds(145, 5, 30, 30);
    }// </editor-fold>//GEN-END:initComponents

    private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarActionPerformed
        try {
            internalFrame.consultar();
        } catch (ValidacaoException e) {
            Mensagem.exibirAlerta(internalFrame, e);
        } catch (Exception e) {
            Mensagem.exibirErro(internalFrame, e);
        }
    }//GEN-LAST:event_btnConsultarActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        try {
            internalFrame.novo();
        } catch (ValidacaoException e) {
            Mensagem.exibirAlerta(internalFrame, e);
        } catch (Exception e) {
            Mensagem.exibirErro(internalFrame, e);
        }
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverActionPerformed
        try {
            internalFrame.remover();
        } catch (ValidacaoException e) {
            Mensagem.exibirAlerta(internalFrame, e);
        } catch (Exception e) {
            Mensagem.exibirErro(internalFrame, e);
        }
    }//GEN-LAST:event_btnRemoverActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        try {
            internalFrame.editar();
        } catch (ValidacaoException e) {
            Mensagem.exibirAlerta(internalFrame, e);
        } catch (Exception e) {
            Mensagem.exibirErro(internalFrame, e);
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        try {
            internalFrame.salvar();
        } catch (ValidacaoException e) {
            Mensagem.exibirAlerta(internalFrame, e);
        } catch (Exception e) {
            Mensagem.exibirErro(internalFrame, e);
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConsultar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnRemover;
    private javax.swing.JButton btnSalvar;
    // End of variables declaration//GEN-END:variables

    public boolean isConsultarVisible() {
        return consultarVisible;
    }

    public void setConsultarVisible(boolean consultarVisible) {
        this.consultarVisible = consultarVisible;
    }

    public boolean isNovoVisible() {
        return novoVisible;
    }

    public void setNovoVisible(boolean novoVisible) {
        this.novoVisible = novoVisible;
    }

    public boolean isRemoverVisible() {
        return removerVisible;
    }

    public void setRemoverVisible(boolean removerVisible) {
        this.removerVisible = removerVisible;
    }

    public boolean isSalvarVisible() {
        return salvarVisible;
    }

    public void setSalvarVisible(boolean salvarVisible) {
        this.salvarVisible = salvarVisible;
    }

    public boolean isEditarVisible() {
        return editarVisible;
    }

    public void setEditarVisible(boolean editarVisible) {
        this.editarVisible = editarVisible;
    }

    public InternalFrame getInternalFrame() {
        return internalFrame;
    }

    public void setInternalFrame(InternalFrame internalFrame) {
        this.internalFrame = internalFrame;
    }

    private JButton[] getBotoes() {

        return new JButton[]{btnConsultar, btnNovo, btnRemover, btnSalvar, btnEditar};
    }

    private void organizarBotoes() {

        int y = 5;
        int x = 5;
        int largura = 30;
        int altura = 30;

        for (JButton btn : getBotoes()) {
            if (btn.isVisible()) {
                btn.setBounds(x, y, largura, altura);
                x += (largura + 5);
            }
        }

    }

}
