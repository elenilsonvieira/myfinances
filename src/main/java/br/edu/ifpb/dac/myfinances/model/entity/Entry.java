package br.edu.ifpb.dac.myfinances.model.entity;

import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.edu.ifpb.dac.myfinances.model.enums.EntryStatus;
import br.edu.ifpb.dac.myfinances.model.enums.EntryType;

@Entity
public class Entry {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false)
	private String description;
	@Column(nullable = false)
	private Integer month;
	@Column(nullable = false)
	private Integer year;
	@Column(nullable = false)
	private BigDecimal value;
	@Column(nullable = false)
	private EntryType type;
	@Column(nullable = false)
	private EntryStatus status;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}
	
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	
	public BigDecimal getValue() {
		return value;
	}
	public void setValue(BigDecimal value) {
		this.value = value;
	}
	
	public EntryType getType() {
		return type;
	}
	public void setType(EntryType type) {
		this.type = type;
	}
	
	public EntryStatus getStatus() {
		return status;
	}
	public void setStatus(EntryStatus status) {
		this.status = status;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(description, id, month, status, type, value, year);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Entry other = (Entry) obj;
		return Objects.equals(description, other.description) && Objects.equals(id, other.id)
				&& Objects.equals(month, other.month) && status == other.status && type == other.type
				&& Objects.equals(value, other.value) && Objects.equals(year, other.year);
	}
	@Override
	public String toString() {
		return "Entry [id=" + id + ", description=" + description + ", month=" + month + ", year=" + year + ", value="
				+ value + ", type=" + type + ", status=" + status + "]";
	}	
	
}
