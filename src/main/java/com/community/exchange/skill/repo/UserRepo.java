package com.community.exchange.skill.repo;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.community.exchange.skill.DAO.Message;
import com.community.exchange.skill.DAO.PasswordResetRequestEntity;
import com.community.exchange.skill.DAO.Skill;
import com.community.exchange.skill.DAO.User;
import com.community.exchange.skill.exception.UserNotFoundException;
import com.community.exchange.skill.exception.UserUpdateException;

@Repository
public class UserRepo {

	@Autowired JdbcTemplate jdbcTemplate;
	public void addUser(User user) {
		String query="INSERT INTO skill.user"
				+ "(userName,"
				+ "password,"
				+ "firstName,"
				+ "lastName,"
				+ "email,"
				
				+ "address,"
				+ "role) "
				+ "VALUES    (?,?,?,?,?,?,?)";
				
		
		jdbcTemplate.update(query, user.getUserName(),user.getPassword(),
				user.getFirstName(),user.getLastName(),
				user.getEmail(),user.getAddress(),user.getRole());
		
		
	}

	
	public User searchUserByName(String userName) throws UserNotFoundException {
		String query= "select userName,password,firstName,lastName,email,address from skill.user where userName=?";
		 List<User> users = null;

		    try {
		        // Use queryForList method to get a List<User> result
		        users = jdbcTemplate.query(query, userRowmapper, userName);
		    } catch (Exception e) {
		        e.printStackTrace();
		        throw new UserNotFoundException("userName and password are invalid.");
		    }

		    // Check if the list is not empty before accessing the first element
		    if (users != null && !users.isEmpty()) {
		        return users.get(0); // Return the first user in the list
		    } else {
		        // Handle the case where no user is found
		        throw new UserNotFoundException("User not found.");
		    }
		
	}
    private static final RowMapper<User> userRowmapper = (rs, rowNum) ->{
        User user = new User();
        user.setUserName(rs.getString("userName"));
        user.setPassword(rs.getString("password"));
       user.setFirstName(rs.getString("firstName"));
       user.setLastName(rs.getString("lastName"));
       user.setEmail(rs.getString("email"));
       user.setAddress(rs.getString("address"));
        return user;
    };
	public void saveMessage(Message msg) {
		
		String query="INSERT INTO skill.message"
				+ "(Message,"
				+ "senderName,"
				+ "receiverName,"
				+ "sentTime,"
				+ "skillId,"
				+ "status)"
				+ " VALUES    (?,?,?,?,?,?)";
				
		
		jdbcTemplate.update(query, msg.getContent(),msg.getSenderUserName(),msg.getReceiverUserName(),msg.getTimestamp(),msg.getSkillName(),msg.getStatus());
			
	}


	public List<Message> getMyRequests(String receiverUserName) {
		String query="select Id,Message, senderName,receiverName,sentTime,skillId from skill.message where receiverName=? and status not in(\"DEL\")";
				  List<Message> myRequests = jdbcTemplate.query(query, messageRowMapper, receiverUserName);
				// TODO Auto-generated method stub
				  return myRequests;
		
	}
	public List<Message> getAllRequests(String senderUserName) {
		String query="select Id,Message,senderName, receiverName,sentTime,skillId from skill.message where senderName=? and status not in(\"DEL\") ";
		  List<Message> myRequests=null;
		  try {
				myRequests = jdbcTemplate.query(query, messageRowMapper, senderUserName); 
				
		  }catch(Exception e) {
			  e.printStackTrace();
		  }
	
		
			System.out.println("myRequests size"+myRequests.size());	
				// TODO Auto-generated method stub
				  return myRequests;
		
	}
	 private static final RowMapper<Message> messageRowMapper = (rs, rowNum) ->{
	        Message msg = new Message();
	        msg.setId(rs.getLong("Id"));
	        msg.setContent(rs.getString("Message"));
	        msg.setSenderUserName(rs.getString("senderName"));
	        msg.setReceiverUserName(rs.getString("receiverName"));
	        Timestamp date=rs.getTimestamp("sentTime");
	        msg.setTimestamp( date.toLocalDateTime());
	       
	       msg.setSkillName(rs.getString("skillId"));
	    return msg;
	    };
	public void updatemessageStatus(Long id, String status) {
		
		String query=null;
		if(status.equalsIgnoreCase("DEL")) {
		query=" update Message set status=\"DEL\" where id=?";
	
		}else if(status.equalsIgnoreCase("REJ")) {
		query=" update Message set status=\"REJ\" where id=?";
		}else if(status.equalsIgnoreCase("ACK")) {
		query=" update Message set status=\"ACK\" where id=?";
		}
		
		jdbcTemplate.update(query, id);
	}


	public Message getRequestById(Long id) {
		String query="select Id,Message,senderName, receiverName,sentTime,skillId from skill.message where id=? ";
Message msg=null;
		  try {
			List<Message> msgList=jdbcTemplate.query(query, messageRowMapper, id); 
			if(!msgList.isEmpty()) {
				msg=msgList.get(0);	
			}
				
		  }catch(Exception e) {
			  e.printStackTrace();
		  }
	
		
			return msg;
		
	}


	public void saveCode(PasswordResetRequestEntity request) {
		String query="INSERT INTO skill.auth ("
				+ "userName ,email,verificationCode ) values (?,?,?)";
		jdbcTemplate.update(query, request.getUserName(),request.getEmail(),request.getVerificationCode());
				
				
		
	}


	public String getUserById(String userName) {
		String query="SELECT  email from skill.user where userName=?";
	return	jdbcTemplate.queryForObject(query,String.class, userName);
		
	}


	public List<PasswordResetRequestEntity> validateOTP(String userName) {
		System.out.println("fetching otp to verify"+userName);
		String query="select Id,verificationCode,userName,email from skill.auth where userName=? order by Id desc LIMIT 1;";
	return	jdbcTemplate.query(query,AuthRowMapper, userName);
	}
	 private static final RowMapper<PasswordResetRequestEntity> AuthRowMapper = (rs, rowNum) ->{
	        PasswordResetRequestEntity msg = new PasswordResetRequestEntity();
	        msg.setId(rs.getLong("Id"));
	        msg.setEmail(rs.getString("email"));
	        msg.setUserName(rs.getString("userName"));
	        msg.setVerificationCode(rs.getString("verificationCode"));
	     	    return msg;
	    };
	public void updatePassword(String userName, String newPassword) {
		String updateQuery="update user set password=? where userName=?";
		jdbcTemplate.update(updateQuery, new Object[] {newPassword,userName});
		
	}


	public List<User> getUser(User user) {
	    StringBuilder queryBuilder = new StringBuilder("SELECT firstName, lastName, userName, email, password, address FROM skill.user");
	    List<Object> params = new ArrayList<>();

	    if (!user.getUserName().isBlank() || !user.getFirstName().isBlank() || !user.getLastName().isBlank() || !user.getEmail().isBlank()) {
	        queryBuilder.append(" WHERE ");
	        boolean addedCondition = false;
	        if (!user.getUserName().isBlank()) {
	            queryBuilder.append("LOWER(userName) LIKE LOWER(?)");
	            params.add("%" + user.getUserName() + "%");
	            addedCondition = true;
	        }
	        if (!user.getFirstName().isBlank()) {
	            if (addedCondition) {
	                queryBuilder.append(" OR ");
	            }
	            queryBuilder.append("LOWER(firstName) LIKE LOWER(?)");
	            params.add("%" + user.getFirstName() + "%");
	            addedCondition = true;
	        }
	        if (!user.getLastName().isBlank()) {
	            if (addedCondition) {
	                queryBuilder.append(" OR ");
	            }
	            queryBuilder.append("LOWER(lastName) LIKE LOWER(?)");
	            params.add("%" + user.getLastName() + "%");
	            addedCondition = true;
	        }
	        if (!user.getEmail().isBlank()) {
	            if (addedCondition) {
	                queryBuilder.append(" OR ");
	            }
	            queryBuilder.append("LOWER(email) LIKE LOWER(?)");
	            params.add("%" + user.getEmail() + "%");
	        }
	    }

	    return jdbcTemplate.query(queryBuilder.toString(), params.toArray(), userRowmapper);
	}



	public void updateUser(User user) throws UserUpdateException {
		
		String query="update skill.user set firstName=? , lastName=? , email=? , password=? , address=? , role=? where userName=?";
		try {
		jdbcTemplate.update(query, new Object[] {user.getFirstName(),user.getLastName(),user.getEmail(),
				user.getPassword(),user.getAddress(), user.getRole(),user.getUserName()});
		}catch(Exception e) {
			throw new UserUpdateException(e.getMessage());
		}
		
	}


	public Boolean deleteUser(String userName) {
		// TODO Auto-generated method stub
		String query=" delete from skill.user where userName=?";
		try {
		jdbcTemplate.update(query, userName);
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
