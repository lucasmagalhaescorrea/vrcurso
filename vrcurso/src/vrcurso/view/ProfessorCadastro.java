package vrcurso.view;

import java.util.ArrayList;
import javax.swing.JFrame;
import vrcurso.framework.Format;
import vrcurso.framework.Mensagem;
import vrcurso.framework.MensagensPadrao;
import vrcurso.framework.exception.ValidacaoException;
import vrcurso.framework.view.InternalFrame;
import vrcurso.framework.vo.ItemComboBoxVO;
import vrcurso.modelo.Professor;
import vrcurso.modelo.enuns.Titulo;
import vrcurso.service.ProfessorService;
import vrcurso.vo.ProfessorFiltroVO;

public class ProfessorCadastro extends InternalFrame {

    private Professor oProfessor;

    public ProfessorCadastro(JFrame i_principal) throws Exception {
        initComponents();
        
        mainFrame = i_principal;
        
        mainFrame.add(this);

        toolbar.setInternalFrame(this);
        
        ArrayList vItemCombo = new ArrayList();
        vItemCombo.add(new ItemComboBoxVO(Titulo.DOUTOR.getId(), Titulo.DOUTOR.getDescricao()));
        vItemCombo.add(new ItemComboBoxVO(Titulo.MESTRE.getId(), Titulo.MESTRE.getDescricao()));
        vItemCombo.add(new ItemComboBoxVO(Titulo.PAD.getId(), Titulo.PAD.getDescricao()));
        cboTitulo.setModel(vItemCombo);
        
        setSelected(true);
    }

    @Override
    public void salvar() throws Exception {
        oProfessor.setNome(txtNome.getText());
        oProfessor.setCpf( Long.parseLong(txtCPF.getText()));
        oProfessor.setRg(txtRG.getText());
        oProfessor.setTitulo(cboTitulo.getId());
        
        new ProfessorService().salvar(oProfessor);
        
        carregarProfessor(oProfessor.getId());
        
        Mensagem.exibirMensagem(this, MensagensPadrao.REGISTRO_SALVO_SUCESSO);
    }

    @Override
    public void novo() throws Exception {
        oProfessor = new Professor();
        txtCodigo.setText("");
        txtNome.setText("");
        txtCPF.setText("");
        txtRG.setText("");
        cboTitulo.setSelectedIndex(0);
    }

    public void carregarProfessor(int i_id) throws Exception {

        ProfessorFiltroVO oFiltro = new ProfessorFiltroVO();
        oFiltro.setId(String.valueOf(i_id));

        oProfessor = new ProfessorService().consultar(oFiltro).get(0);
        txtCodigo.setText(Format.number(oProfessor.getId(), 6));
        txtNome.setText(oProfessor.getNome());
        txtCPF.setText(Format.number(oProfessor.getCpf(), 11));
        txtRG.setText(oProfessor.getRg());
        cboTitulo.setId(oProfessor.getTitulo());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        toolbar = new vrcurso.framework.view.ToolBarPadrao();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnSalvar = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        txtCodigo = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtCPF = new javax.swing.JTextField();
        txtRG = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cboTitulo = new vrcurso.framework.view.ComboBox();

        setClosable(true);
        setMaximizable(true);
        setTitle("VR Curso - Professores");

        toolbar.setNovoVisible(true);
        toolbar.setSalvarVisible(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vrcurso/framework/view/imagens/btnSalvar.png"))); // NOI18N
        btnSalvar.setText("Salvar");
        btnSalvar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnSalvar.setContentAreaFilled(false);
        btnSalvar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnSalvar.setFocusable(false);
        btnSalvar.setMargin(new java.awt.Insets(2, 2, 2, 2));
        btnSalvar.setOpaque(true);
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vrcurso/framework/view/imagens/btnSair.png"))); // NOI18N
        btnSair.setText("Sair");
        btnSair.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnSair.setContentAreaFilled(false);
        btnSair.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnSair.setFocusable(false);
        btnSair.setMargin(new java.awt.Insets(2, 2, 2, 2));
        btnSair.setOpaque(true);
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Professor"));
        jPanel3.setLayout(null);
        jPanel3.add(txtCodigo);
        txtCodigo.setBounds(10, 50, 100, 22);

        jLabel1.setText("Código");
        jPanel3.add(jLabel1);
        jLabel1.setBounds(10, 30, 39, 16);

        jLabel2.setText("Nome");
        jPanel3.add(jLabel2);
        jLabel2.setBounds(120, 30, 33, 16);
        jPanel3.add(txtNome);
        txtNome.setBounds(120, 50, 500, 22);

        jLabel3.setText("CPF");
        jPanel3.add(jLabel3);
        jLabel3.setBounds(10, 80, 22, 16);
        jPanel3.add(txtCPF);
        txtCPF.setBounds(10, 100, 160, 22);
        jPanel3.add(txtRG);
        txtRG.setBounds(180, 100, 160, 22);

        jLabel4.setText("RG");
        jPanel3.add(jLabel4);
        jLabel4.setBounds(180, 80, 16, 16);

        jLabel5.setText("Título");
        jPanel3.add(jLabel5);
        jLabel5.setBounds(350, 80, 140, 16);
        jPanel3.add(cboTitulo);
        cboTitulo.setBounds(350, 100, 270, 22);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(toolbar, javax.swing.GroupLayout.DEFAULT_SIZE, 661, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(toolbar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        dispose();
    }//GEN-LAST:event_btnSairActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        try {
            salvar();
        } catch (ValidacaoException e) {
            Mensagem.exibirAlerta(this, e);
        } catch (Exception e) {
            Mensagem.exibirErro(this, e);
        }
    }//GEN-LAST:event_btnSalvarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnSalvar;
    private vrcurso.framework.view.ComboBox cboTitulo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private vrcurso.framework.view.ToolBarPadrao toolbar;
    private javax.swing.JTextField txtCPF;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtRG;
    // End of variables declaration//GEN-END:variables
}
