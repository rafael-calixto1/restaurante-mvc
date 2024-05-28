package br.edu.brazcubas.restaurante.view;

import br.edu.brazcubas.restaurante.model.entity.Funcionario;

import java.util.List;

public class FuncionarioView {
    public void imprimirInformacoesFuncionario(Funcionario funcionario) {
        System.out.println("INFORMAÇÕES DO FUNCIONÁRIO:");
        System.out.println("ID: " + funcionario.getId());
        System.out.println("Nome: " + funcionario.getNome());
        System.out.println("CPF: " + funcionario.getCpf());
        System.out.println("Data de Nascimento: " + funcionario.getDataNasc());
        System.out.println("Cargo: " + funcionario.getCargo());
        System.out.println("Senha: " + funcionario.getSenha());
    }

    public void imprimirListaFuncionarios(List<Funcionario> funcionarios) {
        for (Funcionario funcionario : funcionarios) {
            System.out.println("ID " + funcionario.getId() + " - " + funcionario.getNome() + " [CPF: " + funcionario.getCpf() + ", Cargo: " + funcionario.getCargo() + "]");
        }
    }
}
