package com.udacity.jdnd.course3.critter.user;

import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
import java.util.Set;

public class EmployeeRequestDTO {
    private Set<EmployeeSkill> skills;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;

    public Set<EmployeeSkill> getSkills() { return skills; }
    public void setSkills(Set<EmployeeSkill> skills) { this.skills = skills; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
}