package com.udacity.jdnd.course3.critter.user;
 import javax.persistence.*;
 import java.time.DayOfWeek;
 import java.util.HashSet;
 import java.util.Set;
@Entity
public class Employee {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<EmployeeSkill> skills = new HashSet<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<DayOfWeek> daysAvailable = new HashSet<>();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Set<EmployeeSkill> getSkills() { return skills; }
    public void setSkills(Set<EmployeeSkill> skills) { this.skills = skills; }

    public Set<DayOfWeek> getDaysAvailable() { return daysAvailable; }
    public void setDaysAvailable(Set<DayOfWeek> daysAvailable) { this.daysAvailable = daysAvailable; }
}