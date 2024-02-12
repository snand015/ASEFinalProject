/**
 * 
 */
package com.community.exchange.skill.repo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.community.exchange.skill.DAO.Skill;
import com.community.exchange.skill.exception.PendingDependenciesException;



@Repository
public class SkillRepository {
@Autowired JdbcTemplate jdbcTemplate;
public void addSkill(Skill profile, String userName) {
	String query = "INSERT INTO skill.skills "
            + "(skillId, userName, skillDesc, imagePath1, imagePath2, imagePath3) "
            + "VALUES (?, ?, ?, ?, ?, ?)";

//Get the image paths from the skill object
List<String> imagePaths = profile.getImagePaths();

//Initialize image path variables
String imagePath1 = null;
String imagePath2 = null;
String imagePath3 = null;

//Set image path variables based on the list size
if (imagePaths != null && imagePaths.size() >= 1) {
   imagePath1 = imagePaths.get(0);
}
if (imagePaths != null && imagePaths.size() >= 2) {
   imagePath2 = imagePaths.get(1);
}
if (imagePaths != null && imagePaths.size() >= 3) {
   imagePath3 = imagePaths.get(2);
}

//Execute the SQL query with the image paths
jdbcTemplate.update(query, profile.getSkill(), userName, profile.getDescription(), imagePath1, imagePath2, imagePath3);

			
	//}
}
	public List<Skill> findByNameContainingIgnoreCase(String search) {
		
		String query= "SELECT skillId, userName, skillDesc ,Id "
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
	private static final RowMapper<Skill> skillRowmapperDetails = (rs, rowNum) ->{
        Skill skill = new Skill();
        skill.setSkill(rs.getString("skillId"));
        skill.setUserName(rs.getString("userName"));
        skill.setDescription(rs.getString("skillDesc"));
        skill.setId(rs.getLong("Id"));
        List<String>imagePaths= new ArrayList();
        String imagePath1=rs.getString("imagePath1");
        String imagePath2=rs.getString("imagePath2");
        String imagePath3=rs.getString("imagePath3");
        imagePaths.add(imagePath1);
        imagePaths.add(imagePath2);
        imagePaths.add(imagePath3);
        skill.setImagePaths(imagePaths);
        return skill;
    };
	public Skill findskillSet(String skill, String userName) {
		Skill skillSet=null;
		String query ="Select skillId, userName,skillDesc,Id,imagePath1,imagePath2,imagePath3 from skills "+
		"  where skillId=? and userName= ?";
		List<Skill> skillList= jdbcTemplate.query(query,skillRowmapperDetails,new Object[] {skill,userName});
		if (skillList !=null || !(skillList.isEmpty())) {
			skillSet=skillList.get(0);
		}
		return skillSet;
	}
	public void deleteSkillById(Long id) throws PendingDependenciesException {
		// TODO Auto-generated method stub
		String deletequery="delete from skills where id=?";
		try {
			jdbcTemplate.update(deletequery,id);	
		}catch(Exception e) {
			throw new PendingDependenciesException("Can not delete as there are pending messages on this skill");
		}
			
	}
	public List<Skill> getSkillById(Long id) {
		String query="select skillId,userName,Id,skillDesc, imagePath1, imagePath2,imagePath3 from skills where Id=?";
		return jdbcTemplate.query(query,skillRowmapperDetails,id);
		
	}
	public void modifySkillDetails(Skill skill, String userName) {
	    // Construct the update query
	    StringBuilder updateQuery = new StringBuilder("UPDATE skills SET skillId = ?, skillDesc = ?");
	    List<Object> queryParams = new ArrayList<>(Arrays.asList(skill.getSkill(), skill.getDescription()));
	    if (skill.getImagePaths() != null) {
	    // Check if imagePath1 is provided
	    	for(int i=0; i<skill.getImagePaths().size();i++) {
	    		if(skill.getImagePaths().get(i)!=null) {
	    			  updateQuery.append(", imagePath"+(i+1)+" = ?");
	    		        queryParams.add(skill.getImagePaths().get(i));
	    		}
	    	}
	    
	    }
	    // Add the WHERE clause to the query
	    updateQuery.append(" WHERE id = ?");
	    queryParams.add(skill.getId());

	    // Execute the update query with parameters
	    jdbcTemplate.update(updateQuery.toString(), queryParams.toArray());
	}

	
	public List<Skill> getskillsByUserName(String userName) {
		String query="select Id, SkillId, userName,skillDesc from skills where userName=?";
		List<Skill>skillList=null;
	skillList=	jdbcTemplate.query(query, skillRowmapper,userName);
	return skillList;
	}
	public void flagPost(Skill skill) {
		String query="update skills set complaint=? ,flagPost='Y' where id=?";
		jdbcTemplate.update(query, new Object[] {skill.getComplaint(),skill.getId()});
		
	}
}
