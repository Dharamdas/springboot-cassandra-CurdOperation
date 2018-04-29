package com.test.address.configurations;

import com.datastax.driver.core.BatchStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.Statement;
import com.datastax.driver.mapping.Mapper;
import org.apache.commons.collections.CollectionUtils;
import lombok.extern.slf4j.Slf4j;
import javax.annotation.PreDestroy;
import java.util.List;

@Slf4j
public class CassandraStatementExecutor {

    protected final Session session;

    public CassandraStatementExecutor(Session session) {
        this.session = session;
    }

    protected void executeStatements(List<Statement> statements) {
        if (statements.size() > 1) {
            final BatchStatement batchStatementForUpdateExistingGuest = new BatchStatement(BatchStatement.Type.LOGGED);
            batchStatementForUpdateExistingGuest.addAll(statements);
            try {
                session.execute(batchStatementForUpdateExistingGuest);
            } catch (Exception e) {
                log.error("op=execute_batch_statement status=KO desc=Exception occurred during batch execution.", e);
                throw e;
            }
        } /* else if (CollectionUtils.isNotEmpty(statements)) {
            executeStatement(statements.get(0));
        }*/
    }

    protected <T> T executeStatement(Statement statement, Mapper<T> mapper) {
        final ResultSet resultSet = executeStatement(statement);

        return mapper.map(resultSet).one();
    }

    protected ResultSet executeStatement(Statement statement) {
        try {
            return session.execute(statement);
        } catch (Exception e) {
            log.error("op=execute_statement status=KO desc=Exception occurred during statement execution.", e);
            throw e;
        }
    }

    @PreDestroy
    public void destroySession() {
        session.closeAsync();
    }
}
