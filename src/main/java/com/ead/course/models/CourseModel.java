package com.ead.course.models;

import com.ead.course.enums.CourseLevel;
import com.ead.course.enums.CourseStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "TB_COURSES")
public class CourseModel implements Serializable {
   private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID courseId;

    @Column(nullable = false, unique = true, length = 150)
    private String name;

    @Column(nullable = false, length = 255)
    private String description;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    @Column(nullable = false)
    private LocalDate creationDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    @Column(nullable = false)
    private LocalTime lastUpdate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CourseStatus courseStatus;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CourseLevel courseLevel;

    @Column(nullable = false)
    private UUID userInstructor;

    @Column(length = 255)
    private String imageUrl;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "course")
    private Set<ModuleModel> modules;

 public UUID getCourseId() {
  return courseId;
 }

 public void setCourseId(UUID courseId) {
  this.courseId = courseId;
 }

 public String getName() {
  return name;
 }

 public void setName(String name) {
  this.name = name;
 }

 public String getDescription() {
  return description;
 }

 public void setDescription(String description) {
  this.description = description;
 }

 public LocalDate getCreationDate() {
  return creationDate;
 }

 public void setCreationDate(LocalDate creationDate) {
  this.creationDate = creationDate;
 }

 public LocalTime getLastUpdate() {
  return lastUpdate;
 }

 public void setLastUpdate(LocalTime lastUpdate) {
  this.lastUpdate = lastUpdate;
 }

 public CourseStatus getCourseStatus() {
  return courseStatus;
 }

 public void setCourseStatus(CourseStatus courseStatus) {
  this.courseStatus = courseStatus;
 }

 public CourseLevel getCourseLevel() {
  return courseLevel;
 }

 public void setCourseLevel(CourseLevel courseLevel) {
  this.courseLevel = courseLevel;
 }

 public UUID getUserInstructor() {
  return userInstructor;
 }

 public void setUserInstructor(UUID userInstructor) {
  this.userInstructor = userInstructor;
 }

 public String getImageUrl() {
  return imageUrl;
 }

 public void setImageUrl(String imageUrl) {
  this.imageUrl = imageUrl;
 }

 public Set<ModuleModel> getModules() {
  return modules;
 }

 public void setModules(Set<ModuleModel> modules) {
  this.modules = modules;
 }
}
