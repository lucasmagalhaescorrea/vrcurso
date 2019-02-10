package vrcurso.view;

import java.util.ArrayList;
import javax.swing.JFrame;
import vrcurso.framework.Format;
import vrcurso.framework.Mensagem;
import vrcurso.framework.MensagensPadrao;
import vrcurso.framework.exception.ValidacaoException;
import vrcurso.framework.view.InternalFrame;
import vrcurso.framework.vo.ItemComboBoxVO;
import vrcurso.modelo.Disciplina;
import vrcurso.modelo.enuns.DiaSemana;
import vrcurso.service.DisciplinaService;
import vrcurso.vo.DisciplinaFiltroVO;

public class DisciplinaCadastro extends InternalFrame {

    private Disciplina oDisciplina;

    public DisciplinaCadastro(JFrame i_principal) throws Exception {
        initComponents();
        
        mainFrame = i_principal;
        
        mainFrame.add(this);

        toolbar.setInternalFrame(this);
        
        ArrayList vItemCombo = new ArrayList();
        
        vItemCombo.add(new ItemComboBoxVO(DiaSemana.SEGUNDA.getId(), DiaSemana.SEGUNDA.getDescricao()));
        vItemCombo.add(new ItemComboBoxVO(DiaSemana.TERCA.getId(), DiaSemana.TERCA.getDescricao()));
        vItemCombo.add(new ItemComboBoxVO(DiaSemana.QUARTA.getId(), DiaSemana.QUARTA.getDescricao()));
        vItemCombo.add(new ItemComboBoxVO(DiaSemana.QUINTA.getId(), DiaSemana.QUINTA.getDescricao()));
        vItemCombo.add(new ItemComboBoxVO(DiaSemana.SEXTA.getId(), DiaSemana.SEXTA.getDescricao()));
        vItemCombo.add(new ItemComboBoxVO(DiaSemana.SABADO.getId(), DiaSemana.SABADO.getDescricao()));
        cboDiaSemana.setModel(vItemCombo);
        
        setSelected(true);
    }

    @Override
    public void salvar() throws Exception {
        oDisciplina.setDescricao(txtDescricao.getText());
        oDisciplina.setLimiteVagas(Integer.parseInt(txtLimiteVagas.getText()));
        oDisciplina.setCargaHoraria(Integer.parseInt(txtCargaHoraria.getText()));
        oDisciplina.setDiaSemana(cboDiaSemana.getId());
        oDisciplina.setIdProfessor(Integer.parseInt(txtCodProfessor.getText()));
        oDisciplina.setEmenta(txtEmenta.getText());
        
        oDisciplina = new DisciplinaService().salvar(oDisciplina);
        
        carregarDisciplina(oDisciplina.getId());
        
        Mensagem.exibirMensagem(this, MensagensPadrao.REGISTRO_SALVO_SUCESSO);
    }

    @Override
    public void novo() throws Exception {
        oDisciplina = new Disciplina();
        txtCodigo.setText("");
        txtDescricao.setText("");
        txtLimiteVagas.setText("");
        txtCargaHoraria.setText("");
        cboDiaSemana.setId(1);
        txtCodProfessor.setText("");
        txtNomeProfessor.setText("");
        txtEmenta.setText("");
    }

    public void carregarDisciplina(int i_id) throws Exception {

        DisciplinaFiltroVO oFiltro = new DisciplinaFiltroVO();
        oFiltro.setId(String.valueOf(i_id));

        oDisciplina = new DisciplinaService().consultar(oFiltro).get(0);
        txtCodigo.setText(Format.number(oDisciplina.getId(), 6));
        txtDescricao.setText(oDisciplina.getDescricao());
        txtLimiteVagas.setText(String.valueOf(oDisciplina.getLimiteVagas()));
        txtCargaHoraria.setText(String.valueOf(oDisciplina.getCargaHoraria()));
        cboDiaSemana.setId(oDisciplina.getDiaSemana());
        txtCodProfessor.setText(String.valueOf(oDisciplina.getIdProfessor()));
        txtNomeProfessor.setText(oDisciplina.getProfessor());
        txtEmenta.setText(oDisciplina.getEmenta());
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        txtDescricao = new javax.swing.JTextField();
        txtLimiteVagas = new javax.swing.JTextField();
        txtCargaHoraria = new javax.swing.JTextField();
        cboDiaSemana = new vrcurso.framework.view.ComboBox();
        txtCodProfessor = new javax.swing.JTextField();
        btnConsultar = new javax.swing.JButton();
        txtNomeProfessor = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtEmenta = new javax.swing.JTextArea();

        setClosable(true);
        setMaximizable(true);
        setTitle("VR Cursos - Disciplina");
        setEnabled(false);

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
        btnSalvar.setNextFocusableComponent(txtNomeProfessor);
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
                .addContainerGap(435, Short.MAX_VALUE)
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
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Aluno"));
        jPanel3.setLayout(null);

        jLabel1.setText("Código");
        jPanel3.add(jLabel1);
        jLabel1.setBounds(10, 30, 39, 16);

        jLabel2.setText("Descrição");
        jPanel3.add(jLabel2);
        jLabel2.setBounds(120, 30, 110, 16);

        jLabel3.setText("Ementa");
        jPanel3.add(jLabel3);
        jLabel3.setBounds(10, 200, 130, 16);

        jLabel4.setText("Carga Horária");
        jPanel3.add(jLabel4);
        jLabel4.setBounds(160, 80, 130, 16);

        jLabel5.setText("Dia da Semana");
        jPanel3.add(jLabel5);
        jLabel5.setBounds(330, 80, 140, 16);

        jLabel6.setText("Professor");
        jPanel3.add(jLabel6);
        jLabel6.setBounds(10, 140, 130, 16);

        jLabel7.setText("Limite de Vagas");
        jPanel3.add(jLabel7);
        jLabel7.setBounds(10, 80, 130, 16);

        txtCodigo.setEditable(false);
        txtCodigo.setEnabled(false);
        jPanel3.add(txtCodigo);
        txtCodigo.setBounds(10, 50, 100, 22);
        jPanel3.add(txtDescricao);
        txtDescricao.setBounds(120, 50, 500, 22);
        jPanel3.add(txtLimiteVagas);
        txtLimiteVagas.setBounds(10, 100, 140, 22);
        jPanel3.add(txtCargaHoraria);
        txtCargaHoraria.setBounds(160, 100, 160, 22);
        jPanel3.add(cboDiaSemana);
        cboDiaSemana.setBounds(330, 100, 270, 22);

        txtCodProfessor.setEditable(false);
        txtCodProfessor.setEnabled(false);
        jPanel3.add(txtCodProfessor);
        txtCodProfessor.setBounds(10, 160, 70, 22);

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
        jPanel3.add(btnConsultar);
        btnConsultar.setBounds(85, 155, 30, 30);

        txtNomeProfessor.setEditable(false);
        txtNomeProfessor.setEnabled(false);
        jPanel3.add(txtNomeProfessor);
        txtNomeProfessor.setBounds(120, 160, 500, 22);

        txtEmenta.setColumns(20);
        txtEmenta.setRows(5);
        jScrollPane1.setViewportView(txtEmenta);

        jPanel3.add(jScrollPane1);
        jScrollPane1.setBounds(10, 230, 620, 240);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 499, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(toolbar, javax.swing.GroupLayout.DEFAULT_SIZE, 669, Short.MAX_VALUE)
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

    private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarActionPerformed
        try {
            txtCodProfessor.setText("");
            txtNomeProfessor.setText("");
            
            ProfessorConsulta form = new ProfessorConsulta(mainFrame, txtCodProfessor, txtNomeProfessor);
            form.consultar();
            form.setVisible(true);
            
        } catch (ValidacaoException e) {
            Mensagem.exibirAlerta(this, e);
        } catch (Exception e) {
            Mensagem.exibirErro(this, e);
        }
    }//GEN-LAST:event_btnConsultarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConsultar;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnSalvar;
    private vrcurso.framework.view.ComboBox cboDiaSemana;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private vrcurso.framework.view.ToolBarPadrao toolbar;
    private javax.swing.JTextField txtCargaHoraria;
    private javax.swing.JTextField txtCodProfessor;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtDescricao;
    private javax.swing.JTextArea txtEmenta;
    private javax.swing.JTextField txtLimiteVagas;
    private javax.swing.JTextField txtNomeProfessor;
    // End of variables declaration//GEN-END:variables
}
