package io.pivotal.pal.tracker;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

@Component
public class JdbcTimeEntryRepository implements TimeEntryRepository {

    private JdbcTemplate jdbcTemplate;

    public JdbcTimeEntryRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public TimeEntry create(TimeEntry timeEntry) {

        String sql = "Insert into time_entries (project_id,user_id,date,hours) values(?,?,?,?)";

        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setLong(1,timeEntry.getProjectId());
                preparedStatement.setLong(2, timeEntry.getUserId());
                preparedStatement.setDate(3, Date.valueOf(timeEntry.getDate()));
                preparedStatement.setInt(4,timeEntry.getHours());
                return preparedStatement;
            }
        }, holder);

        long test = (long) holder.getKey().longValue();
        return new TimeEntry(test,timeEntry);


    }

    @Override
    public TimeEntry find(Long timeEntryId) {
        String sql = "Select id, project_id,user_id,date,hours from time_entries where id= ?";
        RowMapper<TimeEntry> mapper =  new RowMapper<TimeEntry>() {
            @Override
            public TimeEntry mapRow(ResultSet rs, int rowNum) throws SQLException {
                TimeEntry timeEntry = new TimeEntry(rs.getInt(1) ,rs.getLong(2), rs.getLong(3), rs.getDate(4).toLocalDate(),rs.getInt(5));
                return timeEntry;
            }
        };
        Object[] object = new Object[] {timeEntryId};
        List<TimeEntry> list = jdbcTemplate.query(sql,object,mapper);
        if ( list == null || list.isEmpty()){
            return null;
        }

        return list.get(0);

    }

    @Override
    public void delete(Long id) {
        String sql = "delete from time_entries where id =?";
        jdbcTemplate.update(sql,id);
    }

    @Override
    public List list() {
        String sql = "Select id, project_id,user_id,date,hours from time_entries ";
        RowMapper<TimeEntry> mapper =  new RowMapper<TimeEntry>() {
            @Override
            public TimeEntry mapRow(ResultSet rs, int rowNum) throws SQLException {
                TimeEntry timeEntry = new TimeEntry(rs.getInt(1) ,rs.getLong(2), rs.getLong(3), rs.getDate(4).toLocalDate(),rs.getInt(5));
                return timeEntry;
            }
        };

        List<TimeEntry> list = jdbcTemplate.query(sql,mapper);
        return list;

    }

    @Override
    public TimeEntry update(Long id, TimeEntry te) {
        String sql = "update time_entries set project_id = ? , user_id = ? , date = ?  , hours = ? where id =?";
        jdbcTemplate.update(sql,te.getProjectId(),te.getUserId(),Date.valueOf(te.getDate()),te.getHours()
                ,id);

        return this.find(id);
    }
}