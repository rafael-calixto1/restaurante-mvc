package br.edu.brazcubas.restaurante.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import br.edu.brazcubas.restaurante.database.PostgresConfig;
import br.edu.brazcubas.restaurante.model.entity.Prato;

public class PratoDAO implements IDAO<Prato> {
    @Override
    public void registrar(Prato prato) throws SQLException {
        try {
            Connection conn = PostgresConfig.getConnection();
            String query = "INSERT INTO prato VALUES (default, ?, ?, ?, ?);";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, prato.getNome());
            stmt.setDouble(2, prato.getPreco());
            stmt.setString(3, prato.getDescricao());
            stmt.setDouble(4, prato.getAvaliacao());
            stmt.executeUpdate();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @Override
    public void atualizar(Prato prato) throws SQLException {
        try {
            Connection conn = PostgresConfig.getConnection();
            String query = "UPDATE prato SET nome = ?, preco = ?, descricao = ?, avaliacao = ? WHERE id_prato = ?;";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, prato.getNome());
            stmt.setDouble(2, prato.getPreco());
            stmt.setString(3, prato.getDescricao());
            stmt.setDouble(4, prato.getAvaliacao());
            stmt.setInt(5, prato.getId());
            stmt.executeUpdate();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @Override
    public void excluir(Prato prato) {
        try {
            Connection conn = PostgresConfig.getConnection();
            String query = "DELETE FROM prato WHERE id_prato = ?;";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, prato.getId());
            stmt.executeUpdate();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @Override
    public List<Prato> retornarTodos() {
        List<Prato> minhaList = new ArrayList<Prato>();
        try {
            Connection conn = PostgresConfig.getConnection();
            String query = "SELECT * FROM prato;";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id_prato");
                String nome = rs.getString("nome");
                Double preco = rs.getDouble("preco");
                String descricao = rs.getString("descricao");
                Double avaliacao = rs.getDouble("avaliacao");

                Prato prato = new Prato(id, nome, descricao, preco, avaliacao);

                minhaList.add(prato);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return minhaList;
    }

    @Override
    public Prato retornar(Prato pratoLocal) {
        Prato prato = new Prato(pratoLocal.getId(), "", "", 0.00, 0.00);
        try {
            Connection conn = PostgresConfig.getConnection();
            String query = "SELECT * FROM prato WHERE id_prato = ?;";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, pratoLocal.getId());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String nome = rs.getString("nome");
                Double preco = rs.getDouble("preco");
                String descricao = rs.getString("descricao");
                Double avaliacao = rs.getDouble("avaliacao");

                prato.setNome(nome);
                prato.setDescricao(descricao);
                prato.setPreco(preco);
                prato.setAvaliacao(avaliacao);
            }

        } catch (Exception e) {
            System.err.println(e);
        }
        return prato;
    }
    
}