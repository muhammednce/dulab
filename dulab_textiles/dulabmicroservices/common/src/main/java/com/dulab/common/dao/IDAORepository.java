package com.dulab.common.dao;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * This class is used to perform db operations.
 * @author elan.
 */
public interface IDAORepository {

    /**
     * This method is used to execute SQL with parameter and return object type.
     *
     * @param sql    SQL query
     * @param params Parameters
     * @param type   class
     * @param <T>    class type
     * @return List of objects
     */
    <T> List<T> queryForList(String sql, Map<String, Object> params, Class<T> type);

    /**
     * This method is used to execute SQL with parameter.
     *
     * @param sql    SQL query
     * @param params Parameters
     * @return List of map with key as column name and value as column value.
     */
    List<Map<String, Object>> queryForListWithNamedParams(String sql, Map<String, Object> params);

    /**
     * This method is used to execute the sql passed as param.
     *
     * @param sql    sql
     * @param params query parameters
     * @return <code>List<Map<String, Object>></code>
     */
    List<Map<String, Object>> queryForList(String sql, Object... params);

    /**
     * This method is used to execute the sql passed as param.
     *
     * @param sql    SQL query
     * @param clazz  class
     * @param params query parameters
     * @param <T>    class type
     * @return List<T>
     */
    <T> List<T> queryForList(String sql, Class<T> clazz, Object... params);

    /**
     * This method is used to execute the sql passed as param.
     *
     * @param sql    sql
     * @param params query parameters
     * @return the result Map (one entry for each column, using the column name as
     * the key)
     */
    Map<String, Object> queryForMap(String sql, Object... params);

    /**
     * This method is used to execute the sql passed as param.
     *
     * @param sql    SQL query
     * @param params Parameters
     * @param type   required type
     * @param <T>    class type
     * @return single result object
     */
    <T> T queryForObject(String sql, Map<String, Object> params, Class<T> type);

    /**
     * This method is query for a single object.
     *
     * @param sql    SQL query
     * @param clazz  class
     * @param params query parameters
     * @param <T>    class type
     * @return Object
     */
    <T> T queryForObject(String sql, Class<T> clazz, Object... params);

    /**
     * This method is query for a single object.
     *
     * @param sql    SQL query
     * @param clazz  class
     * @param params query parameters
     * @param <T>    class type
     * @return Object
     */
    <T> T queryForObjectWithRowMapper(String sql, Class<T> clazz, Object... params);

    /**
     * This method is used to execute the sql passed as param.
     *
     * @param sql    sql
     * @param clazz  class type
     * @param params query parameters
     * @param <T>    class type
     * @return <code>List<T></code>
     */
    <T> List<T> query(String sql, Class<T> clazz, Object... params);

    /**
     * This method is query for list objects with row mapper.
     *
     * @param sql    SQL
     * @param params Parameters
     * @param clazz  class type
     * @param <T>    class type
     * @return <code>List<T></code>
     */
    <T> List<T> query(String sql, Map<String, Object> params, Class<T> clazz);

    /**
     * This method is used to query the db and process result using ResultSetExtractor.
     *
     * @param sql    SQL query
     * @param params Parameters
     * @return Result as list of object array
     */
    List<Object[]> queryWithRSExtractor(String sql, Object... params);

    /**
     * This method is used to query the db and process result using ResultSetExtractor.
     *
     * @param sql     SQL query
     * @param params  Parameters
     * @param columns Report column details
     * @return Result as list of object array
     */
    List<Object[]> queryWithRSExtractorWithNamedParams(String sql, Map<String, Object> params, List<String> columns);

    /**
     * This method is used to persist data in db using batch process.
     *
     * @param sql  SQL query
     * @param data Data
     */
    void batchUpdate(String sql, List<Map<String, Object>> data);

    /**
     * This method is used to persist list objects(POJO's) in db.
     *
     * @param sql     SQL query
     * @param objects Objects to persist
     */
    void batchUpdateWithSqlParameterSource(String sql, List<Object> objects);

    /**
     * This method is used to persist data in db using batch process.
     *
     * @param sql  SQL query
     * @param data Data
     */
    void batchUpdateList(String sql, List<Object[]> data);

    /**
     * This method is used to persist data in db.
     *
     * @param sql  SQL query
     * @param data Data
     */
    void updateWithParams(String sql, Map<String, Object> data);

    /**
     * This method is used to execute insert, update and delete queries.
     *
     * @param sql    SQL query
     * @param params query parameters
     */
    void update(String sql, Object... params);

    /**
     * This method is used to execute the SQL.
     *
     * @param sql SQL query
     */
    void execute(String sql);
}
