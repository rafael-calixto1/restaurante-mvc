package br.edu.brazcubas.restaurante.model.dao;

import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import br.edu.brazcubas.restaurante.database.PostgresConfig;
import br.edu.brazcubas.restaurante.model.entity.ItemPedido;

public class ItemPedidoDAO implements IDAO<ItemPedido>{
    @Override
    public void registrar(ItemPedido itemPedido) throws SQLException {
        try {
            Connection conn = PostgresConfig.getConnection();
            String query = "INSERT INTO itemPedido VALUES (default, ?, ?, ?);";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, itemPedido.getIdPedido());
            stmt.setInt(2, itemPedido.getIdPrato());
            stmt.setInt(3, itemPedido.getQuantidade());
            stmt.executeUpdate();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @Override
    public void atualizar(ItemPedido itemPedido) throws SQLException {
        try {
            Connection conn = PostgresConfig.getConnection();
            String query = "UPDATE itemPedido SET qtd = ? WHERE id_itemPedido = ?;";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, itemPedido.getQuantidade());
            stmt.executeUpdate();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @Override
    public void excluir(int id) {
        try {
            Connection conn = PostgresConfig.getConnection();
            String query = "DELETE FROM itemPedido WHERE id_itemPedido = ?;";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @Override
    public List<ItemPedido> retornarTodos() {
        List<ItemPedido> minhaList = new ArrayList<ItemPedido>();
        try {
            Connection conn = PostgresConfig.getConnection();
            String query = "SELECT ip.id_pedido AS id_pedido, p.nome AS nome_prato, ip.qtd AS quantidade FROM itemPedido ip INNER JOIN pedido p ON ip.id_pedido = p.id_pedido;";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id_pedido = rs.getInt("id_pedido");
                String nome_prato = rs.getString("nome_prato");
                int quantidade = rs.getInt("quantidade");

                ItemPedido itemPedido = new ItemPedido(id_pedido, quantidade, nome_prato);

                minhaList.add(itemPedido);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return minhaList;
    }

    @Override
    public ItemPedido retornarPorId(int id) {
        ItemPedido itemPedido = new ItemPedido(id, 0, null);
        try {
            Connection conn = PostgresConfig.getConnection();
            String query = "SELECT ip.id_pedido AS id_pedido, p.nome AS nome_prato, ip.qtd AS quantidade FROM itemPedido ip INNER JOIN pedido p ON ip.id_pedido = p.id_pedido AND ip.id_itemPedido = ?;";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String nome_prato = rs.getString("nome_prato");
                int quantidade = rs.getInt("quantidade");

                itemPedido.setNomePrato(nome_prato);
                itemPedido.setQuantidade(quantidade);
            }

        } catch (Exception e) {
            System.err.println(e);
        }
        return itemPedido;
    }
    
}