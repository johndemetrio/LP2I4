import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main extends JFrame {
    private JTextField maximoField, prioridade1Field, prioridade2Field;

    public Main() {
        JLabel maximoLabel = new JLabel("Máximo: ");
        maximoField = new JTextField(10);

        JLabel prioridade1Label = new JLabel("Prioridade 1: ");
        prioridade1Field = new JTextField(2);

        JLabel prioridade2Label = new JLabel("Prioridade 2: ");
        prioridade2Field = new JTextField(2);

        JButton iniciarButton = new JButton("Iniciar");
        iniciarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
    class Thread implements Runnable{
        int j = Integer.parseInt(maximoField.getText());
        @Override
        public void run() {
            for (int i = 0; i < j; i++) {

            }
        }
    }
}





