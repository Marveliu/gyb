package com.marveliu.app.gy.modules.services.impl;
/*
 * Copyright [2018] [Marveliu]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.alibaba.dubbo.config.annotation.Service;
import com.marveliu.framework.model.gy.gy_skill;
import com.marveliu.framework.services.base.BaseServiceImpl;
import com.marveliu.framework.services.gy.GySkillSubService;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Marveliu
 * @since 22/04/2018
 **/

@IocBean(args = {"refer:dao"})
@Service(interfaceClass = GySkillSubService.class)
public class GySkillSubServiceImpl extends BaseServiceImpl<gy_skill> implements GySkillSubService {

    public GySkillSubServiceImpl(Dao dao) {
        super(dao);
    }

    /**
     * 通过雇员id获得用户的技能等级
     *
     * @param gyid
     * @return
     */
    @Override
    public Map<String, Float> getGySkills(String gyid) {
            Map<String,Float> result = new HashMap<String, Float>();
            Map<String,Integer> count = new HashMap<>();
            Cnd cnd = Cnd.where("gyid","=",gyid);
            List<gy_skill> skillList =  this.dao().query(gy_skill.class,cnd);
            if(!skillList.isEmpty()){
                for (gy_skill skill : skillList){
                    if (count.containsKey(skill.getSkillid())){
                        // 加权计算
                        result.put(skill.getSkillid(),skill.getSkilllevel());
                        count.put(skill.getSkillid(),count.get(skill.getSkillid())+1);
                    }else {
                        result.put(skill.getSkillid(),skill.getSkilllevel());
                    }
                }
            }
            for (Map.Entry<String,Integer> item : count.entrySet()){
                if(result.containsKey(item.getKey())){
                    result.put(item.getKey(),result.get(item.getKey())/item.getValue());
                }
            }
            return result;
    }

    /**
     * 批量评级
     *
     * @param skills
     * @return
     */
    @Override
    public Boolean setGySkillLevel(List<gy_skill> skills) {
        return !this.dao().insert(skills).isEmpty() ;
    }
}
