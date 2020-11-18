package com.dulab.common.dao;


import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is used to perform db operations.
 *
 * @author elan.
 */
@Repository
@Transactional
public class DAORepository implements IDAORepository{

    /**
     * jdbcTemplate.
     */
    private final JdbcTemplate jdbcTemplate;

    /**
     * namedJdbcTemplate.
     */
    private final NamedParameterJdbcTemplate namedJdbcTemplate;

    /**
     * Constructor to inject dependency.
     *
     * @param jdbcTemplateIn      {@link JdbcTemplate}
     * @param namedJdbcTemplateIn {@link NamedParameterJdbcTemplate}
     */
    public DAORepository(final JdbcTemplate jdbcTemplateIn, final NamedParameterJdbcTemplate namedJdbcTemplateIn) {
        this.jdbcTemplate = jdbcTemplateIn;
        this.namedJdbcTemplate = namedJdbcTemplateIn;
    }

    /**
     * This method is used to execute SQL with parameter and return object type.
     *
     * @param sql    SQL query
     * @param params Parameters
     * @param type   class
     * @param <T>    class type
     * @return List of objects
     */
    @Override
    public <T> List<T> queryForList(final String sql, final Map<String, Object> params, final Class<T> type) {
        return namedJdbcTemplate.queryForList(sql, params, type);
    }

    /**
     * This method is used to execute SQL with parameter.
     *
     * @param sql    SQL query
     * @param params Parameters
     * @return List of map with key as column name and value as column value.
     */
    @Override
    public List<Map<String, Object>> queryForListWithNamedParams(final String sql, final Map<String, Object> params) {
        return namedJdbcTemplate.queryForList(sql, params);
    }

    /**
     * This method is used to execute the sql passed as param.
     *
     * @param sql    sql
     * @param params query parameters
     * @return <code>List<Map<String, Object>></code>
     */
    @Override
    public List<Map<String, Object>> queryForList(final String sql, final Object... params) {
        return jdbcTemplate.queryForList(sql, params);
    }

    /**
     * This method is used to persist data in db.
     *
     * @param sql    SQL query
     * @param clazz  class type
     * @param <T>    class type
     * @param params query parameters
     */
    @Override
    public <T> List<T> queryForList(final String sql, final Class<T> clazz, final Object... params) {
        return jdbcTemplate.queryForList(sql, clazz, params);
    }

    /**
     * This method is used to execute the sql passed as param.
     *
     * @param sql    sql
     * @param params query parameters
     * @return the result Map (one entry for each column, using the column name as
     * the key)
     */
    @Override
    public Map<String, Object> queryForMap(final String sql, final Object... params) {
        return jdbcTemplate.queryForMap(sql, params);
    }

    /**
     * This method is used to execute the sql passed as param.
     *
     * @param sql    SQL query
     * @param params Parameters
     * @param type   required type
     * @param <T>    class type
     * @return single result object
     */
    @Override
    public <T> T queryForObject(final String sql, final Map<String, Object> params, final Class<T> type) {
        return namedJdbcTemplate.queryForObject(sql, params, type);
    }

    /**
     * This method is query for a single object.
     *
     * @param sql    SQL query
     * @param clazz  class
     * @param params query parameters
     * @param <T>    class type
     * @return Object
     */
    @Override
    public <T> T queryForObject(final String sql, final Class<T> clazz, final Object... params) {
        return jdbcTemplate.queryForObject(sql, params, clazz);
    }

    /**
     * This method is query for a single object.
     *
     * @param sql    SQL query
     * @param clazz  class
     * @param params query parameters
     * @param <T>    class type
     * @return Object
     */
    @Override
    public <T> T queryForObjectWithRowMapper(final String sql, final Class<T> clazz, final Object... params) {
        return jdbcTemplate.queryForObject(sql, params, new BeanPropertyRowMapper<>(clazz));
    }

    /**
     * This method is used to execute the sql passed as param.
     *
     * @param sql   sql
     * @param clazz class type
     * @param <T>   class type
     * @return <code>List<T></code>
     */
    @Override
    public <T> List<T> query(final String sql, final Class<T> clazz, final Object... params) {
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(clazz), params);
    }

    /**
     * This method is query for list objects with row mapper.
     *
     * @param sql    SQL
     * @param params Parameters
     * @param clazz  class type
     * @param <T>    class type
     * @return <code>List<T></code>
     */
    @Override
    public <T> List<T> query(final String sql, final Map<String, Object> params, final Class<T> clazz) {
        return namedJdbcTemplate.query(sql, params, new BeanPropertyRowMapper<>(clazz));
    }

    /**
     * This method is used to query the db and process result using ResultSetExtractor.
     *
     * @param sql    SQL query
     * @param params Parameters
     * @return Result as list of object array
     */
    @Override
    public List<Object[]> queryWithRSExtractor(final String sql, final Object... params) {
        return jdbcTemplate.query(sql, rs -> {
            var result = new ArrayList<Object[]>();
            var rsMetaData = rs.getMetaData();
            while (rs.next()) {
                var rowData = new Object[rsMetaData.getColumnCount()];
                for (var i = 1; i <= rsMetaData.getColumnCount(); i++) {
                    switch (rsMetaData.getColumnType(i)) {
                        case Types.DECIMAL:
                        case Types.DOUBLE:
                        case Types.FLOAT:
                        case Types.NUMERIC:
                            rowData[i - 1] = rs.getBigDecimal(i);
                            break;
                        case Types.BOOLEAN:
                            rowData[i - 1] = rs.getBoolean(i);
                            break;
                        case Types.INTEGER:
                        case Types.BIGINT:
                            rowData[i - 1] = rs.getLong(i);
                            break;
                        default:
                            rowData[i - 1] = rs.getString(i);
                            break;
                    }
                }
                result.add(rowData);
            }
            return result;
        }, params);
    }

    /**
     * This method is used to query the db and process result using ResultSetExtractor.
     *
     * @param sql     SQL query
     * @param params  Parameters
     * @param columns Report column details
     * @return Result as list of object array
     */
    @Override
    public List<Object[]> queryWithRSExtractorWithNamedParams(final String sql, final Map<String, Object> params,
                                                              final List<String> columns) {
        return namedJdbcTemplate.query(sql, params, rs -> {
            var result = new ArrayList<Object[]>();
            var rsMetaData = rs.getMetaData();
            var indexMap = getColumnIndex(rsMetaData);
            while (rs.next()) {
                var rowData = new Object[rsMetaData.getColumnCount()];
                var i = 0;
                for (var column : columns) {
                    var columnIndex = indexMap.get(column);
                    switch (rsMetaData.getColumnType(columnIndex)) {
                        case Types.DECIMAL:
                        case Types.DOUBLE:
                        case Types.FLOAT:
                        case Types.NUMERIC:
                            rowData[i] = rs.getBigDecimal(columnIndex);
                            break;
                        case Types.BOOLEAN:
                            rowData[i] = rs.getBoolean(columnIndex);
                            break;
                        case Types.INTEGER:
                        case Types.BIGINT:
                            rowData[i] = rs.getLong(columnIndex);
                            break;
                        case Types.DATE:
                            rowData[i] = rs.getDate(columnIndex);
                            break;
                        default:
                            rowData[i] = rs.getString(columnIndex);
                            break;
                    }
                    i++;
                }
                result.add(rowData);
            }
            return result;
        });
    }

    /**
     * This method is used to get column index.
     *
     * @param rsMetaData ResultSet meta data.
     * @return Map with key as column name and column position as value
     * @throws SQLException if a database access error occurs
     */
    private Map<String, Integer> getColumnIndex(final ResultSetMetaData rsMetaData) throws SQLException {
        var indexMap = new HashMap<String, Integer>();
        for (var i = 1; i <= rsMetaData.getColumnCount(); i++) {
            indexMap.put(rsMetaData.getColumnLabel(i), i);
        }
        return indexMap;
    }

    /**
     * This method is used to persist data in db using batch process.
     *
     * @param sql  SQL query
     * @param data Data
     */
    @Override
    @SuppressWarnings("unchecked")
    @Transactional
    public void batchUpdate(final String sql, final List<Map<String, Object>> data) {
        Map<String, Object>[] params = data.toArray(new Map[0]);
        namedJdbcTemplate.batchUpdate(sql, params);
    }

    /**
     * This method is used to persist list objects(POJO's) in db.
     *
     * @param sql     SQL query
     * @param objects Objects to persist
     */
    @Transactional
    @Override
    public void batchUpdateWithSqlParameterSource(final String sql, final List<Object> objects) {
        var smMapping = SqlParameterSourceUtils.createBatch(objects);
        namedJdbcTemplate.batchUpdate(sql, smMapping);
    }

    /**
     * This method is used to persist data in db using batch process.
     *
     * @param sql  SQL query
     * @param data Data
     */
    @Override
    @Transactional
    public void batchUpdateList(final String sql, final List<Object[]> data) {
        jdbcTemplate.batchUpdate(sql, data);
    }

    /**
     * This method is used to persist data in db.
     *
     * @param sql  SQL query
     * @param data Data
     */
    @Override
    @Transactional
    public void updateWithParams(final String sql, final Map<String, Object> data) {
        namedJdbcTemplate.update(sql, data);
    }

    /**
     * This method is used to execute insert, update and delete queries.
     *
     * @param sql    SQL query
     * @param params query parameters
     */
    @Override
    public void update(final String sql, final Object... params) {
        jdbcTemplate.update(sql, params);
    }

    /**
     * This method is used to execute the SQL.
     *
     * @param sql SQL query
     */
    @Override
    public void execute(final String sql) {
        jdbcTemplate.execute(sql);
    }
}
