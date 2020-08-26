package net.demo.jdbcfundamental.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public abstract class DataAccessObject<T extends DataTransferObject> {
    protected static final String LAST_VAL = "SELECT last_value FROM ";
    protected static final String CUSTOMER_SEQUENCE = "hp_customer_seq";
    protected final Connection connection;

    public DataAccessObject(Connection connection) {
        super();
        this.connection = connection;
    }

    public abstract T findById(long id);

    public abstract List<T> findAll();

    public abstract T update(T dto);

    public abstract T create(T dto);

    public abstract void delete(long id);

    protected long getLastValue(String sequence) {
        long key = 0;
        String sql = LAST_VAL + sequence;
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)
        ) {
            while (resultSet.next()) {
                key = resultSet.getLong(1);
            }
            return key;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }
}
