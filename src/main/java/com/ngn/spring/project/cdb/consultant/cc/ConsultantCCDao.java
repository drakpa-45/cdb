package com.ngn.spring.project.cdb.consultant.cc;

import com.ngn.spring.project.base.BaseDao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * ==================================================================================
 * Created by user on 9/29/2019.
 * Description:
 * Modified by:
 * Reason :
 * ==================================================================================
 */
@Repository
public class ConsultantCCDao extends BaseDao {

    @Transactional
    public void saveUpdate(Object object) {
        saveOrUpdate(object);
    }
}

