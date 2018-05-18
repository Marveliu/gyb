package com.marveliu.framework.services.gy;
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

import com.marveliu.framework.model.gy.gy_skill;

import java.util.List;
import java.util.Map;

/**
 * @author Marveliu
 * @since 22/04/2018
 **/

public interface GySkillService {

    /**
     * 通过雇员id获得用户的技能等级
     * @param gyid
     * @return
     */
    public Map<String,Float> getGySkills(String gyid);


    /**
     * 批量评级
     * @param skills
     * @return
     */
    public Boolean setGySkillLevel(List<gy_skill> skills);
}
