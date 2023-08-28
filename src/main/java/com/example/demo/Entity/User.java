package com.example.demo.Entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
	public class User  {
	    @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long id;
	    
	    private String username;
	    @OneToMany(mappedBy = "user")
	    private List<Task> assignedTasks;

		public Long getId() {
			return id;
		}

		public List<Task> getAssignedTasks() {
			return assignedTasks;
		}

		public void setAssignedTasks(List<Task> assignedTasks) {
			this.assignedTasks = assignedTasks;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}
	  
}

    
    