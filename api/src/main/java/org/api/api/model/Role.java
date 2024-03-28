package org.api.api.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data // getters, setters, toString, equals, and hashCode methods.
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Role {
   @Id
    private String id;
   /////////////////////////////////////////////////////////////
   @NotBlank(message = "Role is required")
   @Indexed(unique = true)
    private String name;
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}

}
