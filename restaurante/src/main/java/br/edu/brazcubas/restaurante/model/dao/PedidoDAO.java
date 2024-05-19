package br.edu.brazcubas.restaurante.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.sql.Date;

import br.edu.brazcubas.restaurante.database.PostgresConfig;
import br.edu.brazcubas.restaurante.model.entity.Pedido;

public class PedidoDAO implements IDAO<Pedido>{
    @Override
    public void registrar(Pedido pedido) throws SQLException {
        try {
            Connection conn = PostgresConfig.getConnection();
            String query = "INSERT INTO pedido VALUES (default, ?, ?, ?, ?, ?, ?);";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, pedido.getIdCliente());
            stmt.setInt(2, pedido.getIdFuncionario());
            stmt.setDate(3, pedido.getDataPedido());
            stmt.setDouble(4, pedido.getTotalPagamento());
            stmt.setString(5, pedido.getFormaPagamento());
            stmt.setString(6, pedido.getStatus());
            stmt.executeUpdate();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @Override
    public void atualizar(Pedido pedido) throws SQLException {
        try {
            Connection conn = PostgresConfig.getConnection();
            String query = "UPDATE pedido SET id_func = ?, data_pedido = ?, valor_total = ?, pagamento = ?, status = ? WHERE id_pedido = ?;";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, pedido.getIdFuncionario());
            stmt.setDate(2, pedido.getDataPedido());
            stmt.setDouble(3, pedido.getTotalPagamento());
            stmt.setString(4, pedido.getFormaPagamento());
            stmt.setString(5, pedido.getStatus());
            stmt.setInt(6, pedido.getId());
            stmt.executeUpdate();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @Override
    public void excluir(int id) {
        try {
            Connection conn = PostgresConfig.getConnection();
            String query = "DELETE FROM pedido WHERE id_pedido = ?;";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @Override
    public List<Pedido> retornarTodos() {
        List<Pedido> minhaList = new ArrayList<Pedido>();
        try {
            Connection conn = PostgresConfig.getConnection();
            String query = "SELECT p.id_pedido AS id_pedido, c.nome AS nome_cliente, p.data_pedido AS data_pedido, p.valor_total AS valor_total, p.pagamento AS pagamento, p.status AS status FROM pedido p INNER JOIN cliente c ON c.id_cliente = p.id_cliente;";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id_pedido");
                String nomeCliente = rs.getString("nome_cliente");
                Date data_pedido = rs.getDate("data_pedido");
                Double valor_total = rs.getDouble("valor_total");
                String pagamento = rs.getString("pagamento");
                String status = rs.getString("status");

                Pedido pedido = new Pedido(id, pagamento, nomeCliente, data_pedido, valor_total, status);

                minhaList.add(pedido);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return minhaList;
    }

    @Override
    public Pedido retornarPorId(int id) {
        Pedido pedido = new Pedido(id, "", "", null, 0.00, "");
        try {
            Connection conn = PostgresConfig.getConnection();
            String query = "SELECT p.id_pedido AS id_pedido, c.nome AS nome_cliente, p.data_pedido AS data_pedido, p.valor_total AS valor_total, p.pagamento AS pagamento, p.status AS status FROM pedido p INNER JOIN cliente c ON c.id_cliente = p.id_cliente AND p.id_pedido = ?;";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String nomeCliente = rs.getString("nome_cliente");
                Date data_pedido = rs.getDate("data_pedido");
                Double valor_total = rs.getDouble("valor_total");
                String pagamento = rs.getString("pagamento");
                String status = rs.getString("status");

                pedido.setNomeCliente(nomeCliente);
                pedido.setDataPedido(data_pedido);
                pedido.setTotalPedido(valor_total);
                pedido.setFormaPagamento(pagamento);
                pedido.setStatus(status);
            }

        } catch (Exception e) {
            System.err.println(e);
        }
        return pedido;
    }

}