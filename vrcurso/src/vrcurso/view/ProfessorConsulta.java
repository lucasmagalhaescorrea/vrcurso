package vrcurso.view;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import vrcurso.framework.Mensagem;
import vrcurso.framework.MensagensPadrao;
import vrcurso.framework.exception.ValidacaoException;
import vrcurso.framework.view.InternalFrame;
import vrcurso.modelo.Professor;
import vrcurso.service.ProfessorService;
import vrcurso.view.tablemodel.ProfessorTableModel;
import vrcurso.vo.ProfessorFiltroVO;

public class ProfessorConsulta extends InternalFrame {

    private List<Professor> vProfessor = new ArrayList<>();

    @Override
    public void consultar() throws Exception {
        
        limparTabela(tblConsulta);

        ProfessorFiltroVO oFiltro = new ProfessorFiltroVO();
        oFiltro.setCpf(txtCPF.getText());
        oFiltro.setId(txtCodigo.getText());
        oFiltro.setNome(txtNome.getText());

        vProfessor = new ProfessorService().consultar(oFiltro);

        configurarTabela();
    }

    private void configurarTabela() {
        tblConsulta.setModel(new ProfessorTableModel(vProfessor));

        int[] tamCol = new int[5];
        tamCol[0] = 50;
        tamCol[1] = 200;
        tamCol[2] = 120;
        tamCol[3] = 120;
        tamCol[4] = 120;

        for (int i = 0; i < tblConsulta.getModel().getColumnCount(); i++) {
            tblConsulta.getColumnModel().getColumn(i).setMinWidth(tamCol[i]);
            tblConsulta.getColumnModel().getColumn(i).setWidth(tamCol[i]);
            tblConsulta.getColumnModel().getColumn(i).setPreferredWidth(tamCol[i]);
        }

        tblConsulta.requestFocus();
    }

    @Override
    public void editar() throws Exception {
        if (tblConsulta.getSelectedRow() == -1) {
            throw new ValidacaoException(MensagensPadrao.NENHUM_REGISTRO_SELECIONADO);
        }

        Professor oProfessor = vProfessor.get(tblConsulta.convertRowIndexToModel(tblConsulta.getSelectedRow()));

        ProfessorCadastro form = new ProfessorCadastro(mainFrame);
        form.carregarProfessor(oProfessor.getId());
        form.setVisible(true);
    }

    @Override
    public void remover() throws Exception {
        if (tblConsulta.getSelectedRow() == -1) {
            throw new ValidacaoException(MensagensPadrao.NENHUM_REGISTRO_SELECIONADO);
        }

        Professor oProfessor = vProfessor.get(tblConsulta.convertRowIndexToModel(tblConsulta.getSelectedRow()));
        new ProfessorService().remover(oProfessor);

        vProfessor.remove(oProfessor);
        configurarTabela();

        Mensagem.exibirMensagem(this, MensagensPadrao.REGISTRO_EXCLUIDO_SUCESSO);
    }

    @Override
    public void novo() throws Exception {

        ProfessorCadastro form = new ProfessorCadastro(mainFrame);
        form.novo();
        form.setVisible(true);
    }

    public ProfessorConsulta(JFrame i_principal) throws Exception {
        initComponents();
        mainFrame = i_principal;

        toolbar.setInternalFrame(this);

        setSelected(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        toolbar = new vrcurso.framework.view.ToolBarPadrao();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblConsulta = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtCPF = new javax.swing.JTextField();
        txtCodigo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btnConsultar = new javax.swing.JButton();

        setClosable(true);
        setMaximizable(true);
        setTitle("VR Curso - Professores");

        toolbar.setConsultarVisible(true);
        toolbar.setNovoVisible(true);
        toolbar.setRemoverVisible(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        tblConsulta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblConsulta.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tblConsulta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblConsultaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblConsulta);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Filtros"));
        jPanel2.setLayout(null);

        jLabel1.setText("CPF");
        jPanel2.add(jLabel1);
        jLabel1.setBounds(520, 30, 22, 16);
        jPanel2.add(txtCPF);
        txtCPF.setBounds(520, 55, 190, 22);
        jPanel2.add(txtCodigo);
        txtCodigo.setBounds(10, 55, 90, 22);

        jLabel2.setText("Código");
        jPanel2.add(jLabel2);
        jLabel2.setBounds(10, 30, 39, 16);
        jPanel2.add(txtNome);
        txtNome.setBounds(110, 55, 400, 22);

        jLabel3.setText("Nome");
        jPanel2.add(jLabel3);
        jLabel3.setBounds(110, 30, 33, 16);

        btnConsultar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vrcurso/framework/view/imagens/btnConsultar.png"))); // NOI18N
        btnConsultar.setText("Consultar");
        btnConsultar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnConsultar.setContentAreaFilled(false);
        btnConsultar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnConsultar.setFocusable(false);
        btnConsultar.setMargin(new java.awt.Insets(2, 2, 2, 2));
        btnConsultar.setOpaque(true);
        btnConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarActionPerformed(evt);
            }
        });
        jPanel2.add(btnConsultar);
        btnConsultar.setBounds(720, 45, 90, 33);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(toolbar, javax.swing.GroupLayout.DEFAULT_SIZE, 1041, Short.MAX_VALUE)
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

    private void tblConsultaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblConsultaMouseClicked
        try {
            if (evt.getClickCount() == 2) {
                editar();
            }
        } catch (ValidacaoException e) {
            Mensagem.exibirAlerta(this, e);
        } catch (Exception e) {
            Mensagem.exibirErro(this, e);
        }
    }//GEN-LAST:event_tblConsultaMouseClicked

    private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarActionPerformed
        try {
            consultar();
        } catch (ValidacaoException e) {
            Mensagem.exibirAlerta(this, e);
        } catch (Exception e) {
            Mensagem.exibirErro(this, e);
        }
    }//GEN-LAST:event_btnConsultarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConsultar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblConsulta;
    private vrcurso.framework.view.ToolBarPadrao toolbar;
    private javax.swing.JTextField txtCPF;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtNome;
    // End of variables declaration//GEN-END:variables
}
