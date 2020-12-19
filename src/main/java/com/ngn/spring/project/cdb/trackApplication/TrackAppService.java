package com.ngn.spring.project.cdb.trackApplication;

import com.ngn.spring.project.base.BaseService;
import com.ngn.spring.project.commonDto.TasklistDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 18-Dec-20.
 */
@Service
public class TrackAppService extends BaseService{
    @Autowired
    private TrackAppDao trackAppDao;


    public List<TasklistDto> populateTrackApp(String applicationNo) {
        List<TasklistDto> dto=new ArrayList<TasklistDto>();
        dto= trackAppDao.populateTrackApp(applicationNo);
        return dto;
    }
}
