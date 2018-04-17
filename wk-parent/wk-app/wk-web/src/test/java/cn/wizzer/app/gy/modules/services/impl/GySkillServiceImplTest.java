package cn.wizzer.app.gy.modules.services.impl;

import cn.wizzer.app.TestBase;
import cn.wizzer.app.gy.modules.models.gy_skill;
import org.junit.Assert;
import org.junit.Test;
import org.nutz.lang.random.Random;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class GySkillServiceImplTest extends TestBase {

    String testGyId = "gy18011";
    String[] testSkillIds = {"sk_b0004","sk_t0001"};

    @Test
    public void getGySkills() {
        GySkillServiceImpl test = this.ioc.get(GySkillServiceImpl.class);
        log.debug(test.getGySkills(testGyId));
    }

    @Test
    public void setGySkillLevel() {
        GySkillServiceImpl test = this.ioc.get(GySkillServiceImpl.class);
        List<gy_skill> skillList = new ArrayList<gy_skill>(5);
        for (String skillid :testSkillIds){
            for(int n=0;n<5;n++){
                gy_skill skill = new gy_skill();
                skill.setGyid(testGyId);
                skill.setSkillid(skillid);
                skill.setSkilllevel((float) Math.random()*5);
                skillList.add(skill);
            }
        }
        assertTrue(test.setGySkillLevel(skillList));
    }
}