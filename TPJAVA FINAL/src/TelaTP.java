import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.text.JTextComponent;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import com.google.gson.Gson;

public class TelaTP extends JFrame implements ActionListener
{
    
    private JPanel camposInclusao, painelBotoes;
    private JLabel labelNome, labelIdade, labelPeso, labelAltura, labelObjetivo;
    private JTextField textNome, textIdade, textPeso, textAltura, textObjetivo;
    private JButton btnIncluir, btnLimpar, btnApresentarDados, btnSair;
    private AlunoAcademiaDatabase alunoBanco;

    public TelaTP(String title) {
        super(title);
        layoutTela();
        instanciarCampos();
        registrarEventos();
        adicionandoCampos();
    }
    
    private void layoutTela(){
        setLocation(50, 50);
        setSize(500, 300);
        setLayout(new BorderLayout(10, 10));
        alunoBanco = new AlunoAcademiaDatabase();
    }

    private void instanciarCampos(){
        labelNome = new JLabel( "Nome: ");
        textNome = new JTextField(10);

        labelIdade = new JLabel( "Idade: ");
        textIdade = new JTextField(10);

        labelPeso = new JLabel( "Peso: ");
        textPeso = new JTextField(10);

        labelAltura = new JLabel( "Altura: ");
        textAltura = new JTextField(10);

        labelObjetivo = new JLabel( "Objetivo: ");
        textObjetivo = new JTextField(10);

        btnIncluir = new JButton("Incluir");
        btnLimpar = new JButton("Limpar");
        btnApresentarDados = new JButton("Apresentar");
        btnSair =  new JButton("Sair");
    }

    private void registrarEventos(){
        addWindowListener(new SairTela());
        btnSair.addActionListener(new SairTela());

        List<JTextComponent> campos = new ArrayList<JTextComponent>();
        campos.add(textNome);
        campos.add(textIdade);
        campos.add(textPeso);
        campos.add(textAltura);
        campos.add(textObjetivo);
        btnLimpar.addActionListener(new LimparCampos(campos));
        btnApresentarDados.addActionListener(this);
        btnIncluir.addActionListener(this);
    }

    private void adicionandoCampos(){

        camposInclusao = new JPanel();
        camposInclusao.setLayout(new GridLayout(5, 2));
        camposInclusao.add(labelNome);
        camposInclusao.add(textNome);
        camposInclusao.add(labelIdade);
        camposInclusao.add(textIdade);
        camposInclusao.add(labelPeso);
        camposInclusao.add(textPeso);
        camposInclusao.add(labelAltura);
        camposInclusao.add(textAltura);
        camposInclusao.add(labelObjetivo);
        camposInclusao.add(textObjetivo);
        painelBotoes = new JPanel();
        painelBotoes.setLayout(new GridLayout(1, 4));
        painelBotoes.add(btnIncluir);
        painelBotoes.add(btnLimpar);
        painelBotoes.add(btnApresentarDados);
        painelBotoes.add(btnSair);
        add(camposInclusao, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnApresentarDados) apresentarDados();
        if(e.getSource() == btnIncluir) incluir();
    }

    private void apresentarDados(){
        AlunoAcademia aluno = getAlunoCampos();
        Gson gson = new Gson();
        JOptionPane.showMessageDialog (this, gson.toJson(aluno));
    }

    private void incluir(){
        AlunoAcademia aluno = getAlunoCampos();
        try {
            Boolean adicionado = alunoBanco.postAlunoAcademia(aluno);

            if(adicionado)
                JOptionPane.showMessageDialog (this, "Aluno adicionado no sistema com sucesso!");
            else
                JOptionPane.showMessageDialog (null, "Erro ao incluir aluno");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            JOptionPane.showMessageDialog (null, "Erro ao incluir aluno");
        }
    }

    private AlunoAcademia getAlunoCampos(){
        AlunoAcademia aluno = new AlunoAcademia();

        aluno.setNome(textNome.getText());
        aluno.setObjetivo(textObjetivo.getText());
        aluno.setIdade(textIdade.getText().trim().isEmpty()? 0 : Integer.parseInt(textIdade.getText()));
        aluno.setPeso(textPeso.getText().trim().isEmpty() ? 0 : Float.parseFloat(textPeso.getText()));
        aluno.setAltura(textAltura.getText().trim().isEmpty() ? 0 : Float.parseFloat(textAltura.getText()));

        return aluno;
    }

    public static void main(String[] args) {
        TelaTP f = new TelaTP("TP Final");
        f.setVisible(true);
    }
}
