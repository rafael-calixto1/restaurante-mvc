package br.edu.brazcubas.restaurante.model.dao;

import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import br.edu.brazcubas.restaurante.database.PostgresConfig;
import br.edu.brazcubas.restaurante.model.entity.ItemPedido;
import br.edu.brazcubas.restaurante.model.entity.Pedido;
import br.edu.brazcubas.restaurante.model.entity.Prato;

public class ItemPedidoDAO implements IDAO<ItemPedido>{
    private Pedido pedidoLcl;

    public ItemPedidoDAO(){
    }

    public ItemPedidoDAO(Pedido pedidoLcl){
        this.pedidoLcl = pedidoLcl;
    }

    @Override
    public void registrar(ItemPedido itemPedido) throws SQLException {
        try {
            Connection conn = PostgresConfig.getConnection();
            String query = "INSERT INTO item_pedido VALUES (default, ?, ?, ?);";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, itemPedido.getPedido().getId());
            stmt.setInt(2, itemPedido.getPrato().getId());
            stmt.setInt(3, itemPedido.getQuantidade());
            stmt.executeUpdate();

            query = "UPDATE pedido SET valor_total = valor_total + ? WHERE id_pedido = ?;";
            stmt = conn.prepareStatement(query);
            stmt.setDouble(1, (itemPedido.getPrato().getPreco()*itemPedido.getQuantidade()));
            stmt.setInt(2, itemPedido.getPedido().getId());
            stmt.executeUpdate();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @Override
    public void atualizar(ItemPedido itemPedido) throws SQLException {
        try {
            Connection conn = PostgresConfig.getConnection();
            String query = "UPDATE item_pedido SET qtd = ? WHERE id_item_pedido = ?;";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, itemPedido.getQuantidade());
            stmt.setInt(2, itemPedido.getId());
            stmt.executeUpdate();

            Double totalAgora = itemPedido.getPrato().getPreco() * itemPedido.getQuantidade();
            Double totalAnteriormente = itemPedido.getPrato().getPreco() * itemPedido.getQuantidadeAnterior();

            if(itemPedido.getQuantidadeAnterior() > itemPedido.getQuantidade()){
                int diferenca = itemPedido.getQuantidadeAnterior() - itemPedido.getQuantidade();
                totalAgora = diferenca * itemPedido.getPrato().getPreco();
                query = "UPDATE pedido SET valor_total = valor_total - ? WHERE id_pedido = ?;";
            } else { 
                totalAgora = totalAgora - totalAnteriormente;
                query = "UPDATE pedido SET valor_total = valor_total + ? WHERE id_pedido = ?;";
            }
            
            stmt = conn.prepareStatement(query);
            stmt.setDouble(1, (totalAgora));
            stmt.setInt(2, itemPedido.getPedido().getId());
            stmt.executeUpdate();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @Override
    public void excluir(ItemPedido itemPedido) {
        try {
            Connection conn = PostgresConfig.getConnection();
            String query = "DELETE FROM item_pedido WHERE id_item_pedido = ?;";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, itemPedido.getId());
            stmt.executeUpdate();

            Double totalItemPedido = itemPedido.getPrato().getPreco() * itemPedido.getQuantidade();

            query = "UPDATE pedido SET valor_total = valor_total - ? WHERE id_pedido = ?;";
            stmt = conn.prepareStatement(query);
            stmt.setDouble(1, totalItemPedido);
            stmt.setInt(2, itemPedido.getPedido().getId());
            stmt.executeUpdate();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @Override
    public List<ItemPedido> retornarTodos() {
        List<ItemPedido> listaItens = new ArrayList<>();
        try {
            Connection conn = PostgresConfig.getConnection();
            
            String query = "SELECT ip.id_item_pedido AS id_item_pedido, ip.qtd AS qtd_item_pedido, "
                        + "p.id_prato AS id_prato, p.nome AS nome_prato, p.preco AS preco_prato, p.descricao AS descricao_prato, p.preco AS preco_prato, p.avaliacao AS avaliacao_prato "
                        + "FROM item_pedido ip INNER JOIN prato p ON ip.id_prato = p.id_prato WHERE ip.id_pedido = ?;";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, pedidoLcl.getId());
            ResultSet rs = stmt.executeQuery();
                
            while (rs.next()) {
                int idItemPedido = rs.getInt("id_item_pedido");
                int quantidade = rs.getInt("qtd_item_pedido");
                Prato prato = new Prato(rs.getInt("id_prato"), rs.getString("nome_prato"),
                rs.getString("descricao_prato"), rs.getDouble("preco_prato"),
                rs.getDouble("avaliacao_prato"));

                ItemPedido itemPedido = new ItemPedido(idItemPedido, this.pedidoLcl, quantidade, prato);
                listaItens.add(itemPedido);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return listaItens; 
    }

    @Override
    public ItemPedido retornar(ItemPedido itemPedidoLocal) {
        ItemPedido itemPedido = new ItemPedido(itemPedidoLocal.getId(), null, 0, null);
        try {
            Connection conn = PostgresConfig.getConnection();
            String query = "SELECT ip.id_item_pedido AS id_item_pedido, ip.qtd AS qtd_item_pedido, " 
                            +"p.id_prato AS id_prato, p.nome AS nome_prato, p.preco AS preco_prato, p.descricao AS descricao_prato, p.preco AS preco_prato, p.avaliacao AS avaliacao_prato "
                            +"FROM item_pedido ip INNER JOIN prato p ON ip.id_prato = p.id_prato WHERE ip.id_pedido = ?;";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, itemPedidoLocal.getPedido().getId());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int quantidade = rs.getInt("qtd_item_pedido");
                Prato prato = new Prato(rs.getInt("id_prato"), rs.getString("nome_prato"), rs.getString("descricao_prato"), rs.getDouble("preco_prato"), rs.getDouble("avaliacao_prato"));

                itemPedido.setPrato(prato);
                itemPedido.setQuantidade(quantidade);
            }

        } catch (Exception e) {
            System.err.println(e);
        }
        return itemPedido;
    }

    public List<ItemPedido> retornarTodosPorPedido(int id){
        List<ItemPedido> listaItensPedido = new ArrayList<>();
        try {
            Connection conn = PostgresConfig.getConnection();
            String query = "SELECT ip.id_item_pedido AS id_item_pedido, ip.qtd AS qtd_item_pedido, " 
                            +"p.id_prato AS id_prato, p.nome AS nome_prato, p.preco AS preco_prato, p.descricao AS descricao_prato, p.preco AS preco_prato, p.avaliacao AS avaliacao_prato "
                            +"FROM item_pedido ip INNER JOIN prato p ON ip.id_prato = p.id_prato WHERE ip.id_pedido = ?;";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int quantidade = rs.getInt("qtd_item_pedido");
                Prato prato = new Prato(rs.getInt("id_prato"), rs.getString("nome_prato"), rs.getString("descricao_prato"), rs.getDouble("preco_prato"), rs.getDouble("avaliacao_prato"));

                ItemPedido itemPedido = new ItemPedido(quantidade, prato);  
                listaItensPedido.add(itemPedido);
            }

        } catch (Exception e) {
            System.err.println(e);
        }
        return  listaItensPedido;
    }
    
}