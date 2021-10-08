package com.pe.proyectotechnologico.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import static javax.persistence.GenerationType.IDENTITY;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = "username"))
public class User implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	@Column(name = "username", unique = true)
	private String username;
	@Column(name = "password")
	private String password;


	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_teacher", referencedColumnName = "id_teacher")
	private Teacher teacher;

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
}
