/**
 * 
 */
package com.community.exchange.skill.repo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.community.exchange.skill.DAO.Skill;



@Repository
public class SkillRepository {
@Autowired JdbcTemplate jdbcTemplate;
public void addSkill(List<Skill> profile, String userName) {
	String query="INSERT INTO skill.skills"
			+ "(skillId,"
			+ "userName,"
			+ "skillDesc)"				
			+ "VALUES (?,?,?)";
			
	for (Skill skill : profile) {
	 
		
		
		jdbcTemplate.update(query, skill.getSkill(),userName, skill.getDescription());
			
	}
}
	public List<Skill> findByNameContainingIgnoreCase(String search) {
		
		String query= "SELECT skillId, userName, skillDesc "
				+ "FROM skills "
				+ "WHERE LOWER(skillId) LIKE LOWER(CONCAT('%', ? ,'%')) OR LOWER(skillDesc) LIKE LOWER(CONCAT('%', ? ,'%'))";
		 List<Skill> skillList = null;

		    try {
		        // Use queryForList method to get a List<User> result
		    	
		    	skillList = jdbcTemplate.query(query,skillRowmapper,new Object[] {search,search});
		    	System.out.println("after db call");
		    } catch (Exception e) {
		    	System.out.println("caught exception");
		        e.printStackTrace();
		      
		    }
System.out.println("resultSkills"+skillList.size());
		       return skillList;
		    
	}
	private static final RowMapper<Skill> skillRowmapper = (rs, rowNum) ->{
        Skill skill = new Skill();
        skill.setSkill(rs.getString("skillId"));
        skill.setUserName(rs.getString("userName"));
        skill.setDescription(rs.getString("skillDesc"));
        return skill;
    };
	public Skill findskillSet(String skill, String userName) {
		Skill skillSet=null;
		String query ="Select skillId, userName,skillDesc from skills "+
		"  where skillId=? and userName= ?";
		List<Skill> skillList= jdbcTemplate.query(query,skillRowmapper,new Object[] {skill,userName});
		if (skillList !=null || !(skillList.isEmpty())) {
			skillSet=skillList.get(0);
		}
		return skillSet;
	}
}
