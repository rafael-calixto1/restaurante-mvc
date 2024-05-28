package br.edu.brazcubas.restaurante.view;

import br.edu.brazcubas.restaurante.model.entity.Prato;

import java.util.List;

public class PratoView {
    public void imprimirInformacoesPrato(Prato prato) {
        System.out.println("INFORMAÇÕES DO PRATO:");
        System.out.println("ID: " + prato.getId());
        System.out.println("Nome: " + prato.getNome());
        System.out.println("Descrição: " + prato.getDescricao());
        System.out.println("Preço: R$ " + prato.getPreco());
        System.out.println("Avaliacao: "+ prato.getAvaliacao());
    }

    public void imprimirListaPratos(List<Prato> pratos) {
        for (Prato prato : pratos) {
            System.out.println("ID " + prato.getId() + " - Nome: " + prato.getNome() + " [Descrição: '" + prato.getDescricao() + "', Preço: " + prato.getPreco() + "]");
        }
    }
}
