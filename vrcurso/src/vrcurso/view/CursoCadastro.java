package vrcurso.view;

import java.util.ArrayList;
import javax.swing.JFrame;
import vrcurso.framework.Format;
import vrcurso.framework.Mensagem;
import vrcurso.framework.MensagensPadrao;
import vrcurso.framework.exception.ValidacaoException;
import vrcurso.framework.view.InternalFrame;
import vrcurso.framework.vo.ItemComboBoxVO;
import vrcurso.modelo.Curso;
import vrcurso.modelo.CursoDisciplina;
import vrcurso.modelo.Disciplina;
import vrcurso.modelo.enuns.Periodo;
import vrcurso.service.CursoService;
import vrcurso.view.tablemodel.CursoDisciplinaTableModel;
import vrcurso.vo.CursoFiltroVO;

public class CursoCadastro extends InternalFrame {

    private Curso oCurso;

    public CursoCadastro(JFrame i_principal) throws Exception {
        initComponents();

        mainFrame = i_principal;

        mainFrame.add(this);

        toolbar.setInternalFrame(this);

        ArrayList vItemCombo = new ArrayList();

        vItemCombo.add(new ItemComboBoxVO(Periodo.MATUTINO.getId(), Periodo.MATUTINO.getDescricao()));
        vItemCombo.add(new ItemComboBoxVO(Periodo.VESPERTINO.getId(), Periodo.VESPERTINO.getDescricao()));
        vItemCombo.add(new ItemComboBoxVO(Periodo.NOTURNO.getId(), Periodo.NOTURNO.getDescricao()));
        vItemCombo.add(new ItemComboBoxVO(Periodo.INTEGRAL.getId(), Periodo.INTEGRAL.getDescricao()));
        cboPeriodo.setModel(vItemCombo);

        setSelected(true);
    }

    public void addDisciplina(Disciplina i_Disciplina) {
        CursoDisciplina oCursoDisciplina = new CursoDisciplina();
        oCursoDisciplina.setDiaSemana(i_Disciplina.getDiaSemana());
        oCursoDisciplina.setDisciplina(i_Disciplina.getDescricao());
        oCursoDisciplina.setIdDisciplina(i_Disciplina.getId());
        oCursoDisciplina.setProfessor(i_Disciplina.getProfessor());
        oCursoDisciplina.setCargahoraria(i_Disciplina.getCargaHoraria());
        oCursoDisciplina.setIdProfessor(i_Disciplina.getIdProfessor());

        oCurso.getvCursoDisciplina().add(oCursoDisciplina);

        configurarTabela();
    }

    private void configurarTabela() {
        tblDados.setModel(new CursoDisciplinaTableModel(oCurso.getvCursoDisciplina()));

        int[] tamCol = new int[6];
        tamCol[0] = 100;
        tamCol[1] = 200;
        tamCol[2] = 100;
        tamCol[3] = 200;
        tamCol[4] = 100;
        tamCol[5] = 100;

        for (int i = 0; i < tblDados.getModel().getColumnCount(); i++) {
            tblDados.getColumnModel().getColumn(i).setMinWidth(tamCol[i]);
            tblDados.getColumnModel().getColumn(i).setWidth(tamCol[i]);
            tblDados.getColumnModel().getColumn(i).setPreferredWidth(tamCol[i]);
        }

        tblDados.requestFocus();
    }

    private void removerDisciplinas() throws ValidacaoException {
        if (tblDados.getSelectedRow() == -1) {
            throw new ValidacaoException(MensagensPadrao.NENHUM_REGISTRO_SELECIONADO);
        }

        CursoDisciplina oCursoDisciplina = oCurso.getvCursoDisciplina().get(tblDados.convertRowIndexToModel(tblDados.getSelectedRow()));

        if (oCursoDisciplina.getId() > 0) {
            oCurso.getvCursoDisciplinaExclusao().add(oCursoDisciplina);
        }

        oCurso.getvCursoDisciplina().remove(oCursoDisciplina);

        configurarTabela();
    }

    private void limparDisciplinas() throws ValidacaoException {

        for (CursoDisciplina oCursoDisciplina : oCurso.getvCursoDisciplina()) {
            if (oCursoDisciplina.getId() > 0) {
                oCurso.getvCursoDisciplinaExclusao().add(oCursoDisciplina);
            }
        }
        
        oCurso.getvCursoDisciplina().clear();

        configurarTabela();
    }

    @Override
    public void salvar() throws Exception {
        oCurso.setDescricao(txtDescricao.getText());
        oCurso.setDuracaoMeses(Integer.parseInt(txtDuracao.getText()));
        oCurso.setQtdAlunos(Integer.parseInt(txtQtdeAlunos.getText()));
        oCurso.setPeriodo(cboPeriodo.getId());
        oCurso.setCargaHoraria(Integer.parseInt(txtCargaHoraria.getText()));

        oCurso = new CursoService().salvar(oCurso);

        carregarCurso(oCurso.getId());

        Mensagem.exibirMensagem(this, MensagensPadrao.REGISTRO_SALVO_SUCESSO);
    }

    @Override
    public void novo() throws Exception {
        oCurso = new Curso();
        txtCodigo.setText("");
        txtDescricao.setText("");
        txtDuracao.setText("");
        txtQtdeAlunos.setText("");
        txtCargaHoraria.setText("");
        cboPeriodo.setId(1);
    }

    public void carregarCurso(int i_id) throws Exception {

        CursoFiltroVO oFiltro = new CursoFiltroVO();
        oFiltro.setId(String.valueOf(i_id));

        oCurso = new CursoService().carregar(oFiltro);
        txtCodigo.setText(Format.number(oCurso.getId(), 6));
        txtDescricao.setText(oCurso.getDescricao());
        txtDuracao.setText(String.valueOf(oCurso.getDuracaoMeses()));
        txtQtdeAlunos.setText(String.valueOf(oCurso.getQtdAlunos()));
        txtCargaHoraria.setText(String.valueOf(oCurso.getCargaHoraria()));
        cboPeriodo.setId(oCurso.getPeriodo());

        configurarTabela();
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
        jLabel7 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        txtDescricao = new javax.swing.JTextField();
        txtDuracao = new javax.swing.JTextField();
        txtQtdeAlunos = new javax.swing.JTextField();
        cboPeriodo = new vrcurso.framework.view.ComboBox();
        txtCargaHoraria = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDados = new javax.swing.JTable();
        btnNovo = new javax.swing.JButton();
        btnRemover = new javax.swing.JButton();
        btnLimpar = new javax.swing.JButton();

        setClosable(true);
        setTitle("VR Cursos - Curso");
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
                .addContainerGap(943, Short.MAX_VALUE)
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
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Curso"));
        jPanel3.setLayout(null);

        jLabel1.setText("Código");
        jPanel3.add(jLabel1);
        jLabel1.setBounds(10, 30, 39, 16);

        jLabel2.setText("Descrição");
        jPanel3.add(jLabel2);
        jLabel2.setBounds(120, 30, 110, 16);

        jLabel3.setText("Carga Horária");
        jPanel3.add(jLabel3);
        jLabel3.setBounds(10, 140, 130, 16);

        jLabel4.setText("Qtde Alunos");
        jPanel3.add(jLabel4);
        jLabel4.setBounds(160, 80, 130, 16);

        jLabel5.setText("Período");
        jPanel3.add(jLabel5);
        jLabel5.setBounds(330, 80, 140, 16);

        jLabel7.setText("Duração");
        jPanel3.add(jLabel7);
        jLabel7.setBounds(10, 80, 130, 16);

        txtCodigo.setEditable(false);
        txtCodigo.setEnabled(false);
        jPanel3.add(txtCodigo);
        txtCodigo.setBounds(10, 50, 100, 22);
        jPanel3.add(txtDescricao);
        txtDescricao.setBounds(120, 50, 500, 22);
        jPanel3.add(txtDuracao);
        txtDuracao.setBounds(10, 100, 140, 22);
        jPanel3.add(txtQtdeAlunos);
        txtQtdeAlunos.setBounds(160, 100, 160, 22);
        jPanel3.add(cboPeriodo);
        cboPeriodo.setBounds(330, 100, 270, 22);
        jPanel3.add(txtCargaHoraria);
        txtCargaHoraria.setBounds(10, 160, 130, 22);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Disciplinas"));
        jPanel4.setLayout(null);

        tblDados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblDados.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane1.setViewportView(tblDados);

        jPanel4.add(jScrollPane1);
        jScrollPane1.setBounds(20, 20, 1070, 366);

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
        jPanel4.add(btnNovo);
        btnNovo.setBounds(990, 390, 30, 30);

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
        jPanel4.add(btnRemover);
        btnRemover.setBounds(1020, 390, 30, 30);

        btnLimpar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vrcurso/framework/view/imagens/btnlimpar.png"))); // NOI18N
        btnLimpar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnLimpar.setContentAreaFilled(false);
        btnLimpar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLimpar.setMargin(new java.awt.Insets(2, 2, 2, 2));
        btnLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparActionPerformed(evt);
            }
        });
        jPanel4.add(btnLimpar);
        btnLimpar.setBounds(1050, 390, 30, 30);

        jPanel3.add(jPanel4);
        jPanel4.setBounds(10, 200, 1120, 450);

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
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 666, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(toolbar, javax.swing.GroupLayout.DEFAULT_SIZE, 1177, Short.MAX_VALUE)
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

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        try {
            DisciplinaConsulta form = new DisciplinaConsulta(mainFrame, this);
            form.consultar();
            form.setVisible(true);
        } catch (ValidacaoException e) {
            Mensagem.exibirAlerta(this, e);
        } catch (Exception e) {
            Mensagem.exibirErro(this, e);
        }
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverActionPerformed
        try {
            removerDisciplinas();
        } catch (ValidacaoException e) {
            Mensagem.exibirAlerta(this, e);
        } catch (Exception e) {
            Mensagem.exibirErro(this, e);
        }
    }//GEN-LAST:event_btnRemoverActionPerformed

    private void btnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparActionPerformed
        try {
            limparDisciplinas();
        } catch (ValidacaoException e) {
            Mensagem.exibirAlerta(this, e);
        } catch (Exception e) {
            Mensagem.exibirErro(this, e);
        }
    }//GEN-LAST:event_btnLimparActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLimpar;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnRemover;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnSalvar;
    private vrcurso.framework.view.ComboBox cboPeriodo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblDados;
    private vrcurso.framework.view.ToolBarPadrao toolbar;
    private javax.swing.JTextField txtCargaHoraria;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtDescricao;
    private javax.swing.JTextField txtDuracao;
    private javax.swing.JTextField txtQtdeAlunos;
    // End of variables declaration//GEN-END:variables
}
