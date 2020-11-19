package com.ngn.spring.project.auth;


import com.ngn.spring.project.base.BaseDao;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Repository
public class LoginDao extends BaseDao {

    //region private variables
    private Query hQuery;
    //endregion

    //region public method

    /**
     * to get the user information while logging in.
     *
     * @param username username
     * @return UserSetupDTO
     */
    @Transactional(readOnly = true)
    public LoginDTO login(String username) {
        sqlQuery = properties.getProperty("LoginDao.login");
        hQuery = hibernateQuery(sqlQuery, LoginDTO.class).setParameter("username", username);
        List list = hQuery.list();
        return (LoginDTO) (list.isEmpty()?null:list.get(0));
    }

    @Transactional(readOnly = true)
    public List<UserRoleDTO> getUserRoleList(String userId) {
        sqlQuery = properties.getProperty("LoginDao.getUserRoleList");
        hQuery = hibernateQuery(sqlQuery, UserRoleDTO.class).setParameter("userId", userId);
        return (List<UserRoleDTO>) (hQuery.list());
    }

    @Transactional
    public LoginDTO checkForUserName(String userLogin) {
        sqlQuery = properties.getProperty("LoginDao.checkForUserName");
        hQuery = hibernateQuery(sqlQuery, LoginDTO.class).setParameter("username", userLogin);
        List list = hQuery.list();
        return (LoginDTO) (list.isEmpty()?null:list.get(0));
    }

    //endregion
}
