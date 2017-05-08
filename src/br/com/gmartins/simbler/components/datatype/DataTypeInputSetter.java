package br.com.gmartins.simbler.components.datatype;

import br.com.gmartins.simbler.components.MainPanel;
import br.com.gmartins.simbler.instructions.DataConverter;
import br.com.gmartins.simbler.properties.Text;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class DataTypeInputSetter extends DataTypeComponent {

    /**
     * Por padrão, o componente é inciado com o tipo de data hexadecimal.
     * @param panel 
     */
    public DataTypeInputSetter(MainPanel panel) {
        super(panel);
        initComponents();
        getPopupMenu().setPreferredSize(new Dimension(169, 60));
        this.setDataType(DataType.HEXADECIMAL); // Aparentemente o erro está acontecendo aqui
        this.setMenuItemAction(menuAction);
    }

    @Override
    public String getBackgroundImage() {
        return "/br/com/gmartins/simbler/images/btn-datatype-input.png";
    }

    @Override
    public void onClickEvent(ActionEvent e) {
        btnShowMenu.setText(e.getActionCommand());
        boolean convertData = false;
        if (getPanel().getCodeTextArea().getText().isEmpty() == false) {
            int opt = JOptionPane.showConfirmDialog(null, Text.get("general.convert_data_ex"), Text.get("general.convert_data"), JOptionPane.YES_NO_CANCEL_OPTION);
            if (opt == JOptionPane.CANCEL_OPTION || opt == JOptionPane.CLOSED_OPTION) {
                return;
            }
            if (opt == JOptionPane.YES_OPTION) {
                convertData = true;
            }
        }

        DataConverter dataConverter = new DataConverter(getPanel());

        if (e.getActionCommand().equals(DataType.BINARY.getName())) {
            if (convertData) {
                dataConverter.covertTo(DataType.BINARY);
            }
        } else if (e.getActionCommand().equals(DataType.DECIMAL.getName())) {
            if (convertData) {
                dataConverter.covertTo(DataType.DECIMAL);
            }
        } else if (e.getActionCommand().equals(DataType.HEXADECIMAL.getName())) {
            if (convertData) {
                dataConverter.covertTo(DataType.HEXADECIMAL);
            }
        }

        super.onClickEvent(e);
        getPanel().getCodeTextArea().idleKeyboardAnalyzer();

    }
    private ActionListener menuAction = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            // Após trocar o tipo de "Entrada" do compilador, executa a verificação do código
            onClickEvent(e);
        }
    };

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnShowMenu = new javax.swing.JButton();

        setOpaque(false);

        btnShowMenu.setBackground(new java.awt.Color(51, 51, 51));
        btnShowMenu.setFont(new java.awt.Font("Tahoma", 1, 11));
        btnShowMenu.setForeground(new java.awt.Color(51, 51, 51));
        btnShowMenu.setText("Hexadecimal");
        btnShowMenu.setToolTipText("Tipo de Entrada");
        btnShowMenu.setContentAreaFilled(false);
        btnShowMenu.setFocusable(false);
        btnShowMenu.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        btnShowMenu.setMinimumSize(new java.awt.Dimension(150, 30));
        btnShowMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowMenuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnShowMenu, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnShowMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 29, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnShowMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowMenuActionPerformed
        this.getPopupMenu().show(this, 0, 36);
    }//GEN-LAST:event_btnShowMenuActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnShowMenu;
    // End of variables declaration//GEN-END:variables
}
