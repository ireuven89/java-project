package com.example.school;

import com.example.school.resources.PupilResource;
import com.example.school.services.PupilService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class PupilServiceTest {


    private PupilService pupilService;

    @Before
    public void  init(){
        pupilService = new PupilService();
    }

    @Test()
    public void testEntity() throws Exception{
        pupilService.toEntity(new PupilResource());
    }
}
