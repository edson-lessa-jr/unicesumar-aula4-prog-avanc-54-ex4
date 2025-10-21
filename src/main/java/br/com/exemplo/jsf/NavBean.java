package br.com.exemplo.jsf;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "navBean")
@RequestScoped
public class NavBean {

    private String nome;
    private String idade;

    public String irParaCadastro() {
        // outcome implícito também funcionaria, mas aqui uso método para fins didáticos
        return "cadastro"; // navega para cadastro.xhtml (implícita)
    }

    public String processar() {
        // Validação simples para demonstrar outcomes diferentes
        if (nome != null && nome.trim().length() > 0) {
            int idadeNum = 0;
            try {
                idadeNum = Integer.parseInt(idade);
            } catch (NumberFormatException e) {
                // força erro
                return "erro";
            }
            if (idadeNum >= 18) {
                return "sucesso"; // faces-config mapeia para /sucesso.xhtml (com redirect)
            }
        }
        return "erro"; // faces-config mapeia para /erro.xhtml
    }

    public String cancelar() {
        return "home"; // faces-config mapeia para /index.xhtml
    }

    // getters e setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getIdade() { return idade; }
    public void setIdade(String idade) { this.idade = idade; }
}
