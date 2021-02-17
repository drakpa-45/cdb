package com.ngn.spring.project.token;

import com.ngn.spring.project.base.BaseDao;
import org.springframework.stereotype.Repository;

/**
 * Created by Deepak on 12/18/2020.
 */
@Repository
public class TokenDAO extends BaseDao{

    public Token findToken(){
       String sqlQuery = "SELECT id, access_token,created_on, expires_in, scope, token_type FROM token ORDER BY id DESC LIMIT 1";
        //String sqlQuery = "SELECT id FROM token ORDER BY id DESC LIMIT 1";
        org.hibernate.Query hQuery = hibernateQuery(sqlQuery, Token.class);
        Token token = (Token) hQuery.uniqueResult();
        return token;
    }

    public void deleteAll() {
       // delete(token);
        String sqlQuery = "DELETE FROM token";
        org.hibernate.Query hQuery = hibernateQuery(sqlQuery);
        hQuery.executeUpdate();
    }

    public void save(Token token){
        if(token != null){
            saveOrUpdate(token);
        }
    }
}
