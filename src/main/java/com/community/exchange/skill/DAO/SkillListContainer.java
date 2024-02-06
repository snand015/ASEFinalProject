package com.community.exchange.skill.DAO;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class SkillListContainer {
private List<Skill> skillList;

public List<Skill> getSkillList() {
	return skillList;
}

public void setSkillList(List<Skill> skillList) {
	this.skillList = skillList;
}

}
