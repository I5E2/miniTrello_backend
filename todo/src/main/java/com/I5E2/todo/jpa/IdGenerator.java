package com.I5E2.todo.jpa;


import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.internal.util.config.ConfigurationHelper;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;


import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 데이터베이스 기본키 값을 양식에 맞춰서 id값을 증가시켜서 DB에 저장시키는 클래스
 * ex) 00000001 00000002
 */
public class IdGenerator implements IdentifierGenerator {
    private String entity;


    @Override
    public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {
        entity = ConfigurationHelper.getString(ENTITY_NAME,params);
        entity = CustomPhysicalNamingStrategy.convertEntityName(entity);
    }

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        String primaryKeyId = null;
        Connection con = null;
        String sql = "select count(*) "+ "from " + entity;
        try {
            con = session.getJdbcConnectionAccess().obtainConnection();
            CallableStatement callStatement = con.prepareCall(sql);
            callStatement.executeQuery();
            ResultSet rs = callStatement.getResultSet();
            if(rs.next()){
                primaryKeyId = rs.getString(1);
                primaryKeyId =  String.valueOf(Integer.valueOf(primaryKeyId+1));
                primaryKeyId = String.format("%8s",primaryKeyId).replace(' ','0');
            }
        }catch (SQLException sqlException){
            throw new HibernateException(sqlException);
        } finally {
            try {
                if(!con.isClosed()){
                    con.close();
                }
            }catch(SQLException e){

            }
        }
        return primaryKeyId;
    }
}


