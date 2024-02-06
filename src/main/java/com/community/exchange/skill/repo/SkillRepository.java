/**
 * 
 */
package com.community.exchange.skill.repo;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.community.exchange.skill.DAO.Skill;



@Repository
public class SkillRepository {
@Autowired JdbcTemplate jdbcTemplate;
public void addSkill(Skill profile, String userName) {
	String query="INSERT INTO skill.skills"
			+ "(skillId,"
			+ "userName,"
			+ "skillDesc)"				
			+ "VALUES (?,?,?)";
			
	//for (Skill skill : profile) {
	 
		
		
		jdbcTemplate.update(query, profile.getSkill(),userName, profile.getDescription());
			
	//}
}
	public List<Skill> findByNameContainingIgnoreCase(String search) {
		
		String query= "SELECT skillId, userName, skillDesc ,Id"
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
        skill.setId(rs.getLong("Id"));
        return skill;
    };
	public Skill findskillSet(String skill, String userName) {
		Skill skillSet=null;
		String query ="Select skillId, userName,skillDesc,Id from skills "+
		"  where skillId=? and userName= ?";
		List<Skill> skillList= jdbcTemplate.query(query,skillRowmapper,new Object[] {skill,userName});
		if (skillList !=null || !(skillList.isEmpty())) {
			skillSet=skillList.get(0);
		}
		return skillSet;
	}
	public void deleteSkillById(Long id) {
		// TODO Auto-generated method stub
		String deletequery="delete from skills where id=?";
		jdbcTemplate.update(deletequery,id);		
	}
	public List<Skill> getSkillById(Long id) {
		String query="select skillId,userName,Id,skillDesc from skills where Id=?";
		return jdbcTemplate.query(query,skillRowmapper,id);
		
	}
	public void modifySkillDetails(Skill skill, String userName) {
		// TODO Auto-generated method stub
		String updatequery="update skills set skillId=?, skillDesc=? where id=?";
		jdbcTemplate.update(updatequery, new Object[] {skill.getSkill(),skill.getDescription(),skill.getId()});
	}
	public List<Skill> getskillsByUserName(String userName) {
		String query="select Id, SkillId, userName,skillDesc from skills where userName=?";
		List<Skill>skillList=null;
	skillList=	jdbcTemplate.query(query, skillRowmapper,userName);
	return skillList;
	}
}
