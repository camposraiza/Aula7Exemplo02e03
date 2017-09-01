package Aula07Exemplo002;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class JanelaListaPessoas extends JFrame{
    private List<Pessoa> pessoas = new ArrayList<>();
    
    private final JLabel lblNome = new JLabel("Nome:");
    private final JLabel lblIdade = new JLabel("Idade:");
    private final JTextField txtNome = new JTextField(10);
    private final JTextField txtIdade = new JTextField(10);
    private final JButton btnExcluir = new JButton("Excluir");
    private final PessoasListModel modelo = new PessoasListModel(pessoas);
    private final JList lstNumeros = new JList(modelo);
    

    public JanelaListaPessoas() throws HeadlessException {
    
        super("Lista");
        setLayout(new BorderLayout(5,5));
        JPanel pnlNome = new JPanel();
        pnlNome.setLayout(new BorderLayout(5,5));
        pnlNome.add(lblNome, BorderLayout.WEST);//Adiciona o label a esquerda
        pnlNome.add(txtNome,BorderLayout.CENTER);
        
        JPanel pnlIdade = new JPanel();
        pnlIdade.setLayout(new BorderLayout(5,5));
        pnlIdade.add(lblIdade, BorderLayout.WEST);//Adiciona o label a esquerda
        pnlIdade.add(txtIdade,BorderLayout.CENTER);
        
        JPanel pnlEntrada = new JPanel(new FlowLayout());              
        pnlEntrada.add(pnlNome);
        pnlEntrada.add(pnlIdade);
        add(btnExcluir,BorderLayout.SOUTH);//Adiciona o botão excluir
        add(new JScrollPane(lstNumeros),BorderLayout.CENTER);
        add(pnlEntrada,BorderLayout.NORTH);
 
        for(int i = 0; i<3;i++){
            Pessoa p = new Pessoa();
            p.setNome("Pessoa: " + i);
            p.setIdade(18+1);
            pessoas.add(p);
        }
        
        txtNome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtIdade.requestFocus();// Muda o cursor de escrita para o txtIdade
                
            }
        });
        
        txtIdade.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer idade = Integer.parseInt(txtIdade.getText());
                String nome = txtNome.getText();
                Pessoa p = new Pessoa();
                p.setNome(nome);
                p.setIdade(idade);
                pessoas.add(p);
                txtNome.setText("");
                txtIdade.setText("");
                txtNome.requestFocus();
                lstNumeros.updateUI(); //Para atualizar a lista de forma forçada
                
            }
        });
        
        btnExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Pessoa> selecionados = lstNumeros.getSelectedValuesList();
                for(Pessoa o : selecionados){
                    pessoas.remove(o);
                }
                lstNumeros.clearSelection();//Retirar a seleção marcada
                lstNumeros.updateUI();
            }
        });
        
        
        
        
    }
    
}
