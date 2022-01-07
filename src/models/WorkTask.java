package models;

import java.sql.Timestamp;
import java.time.LocalTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({
    @NamedQuery(name = "getAllWorkTasks", query = "SELECT w FROM WorkTask AS w ORDER BY w.deadline ASC, w.deadline_time ASC"),
    @NamedQuery(name = "getAllWorkTasksSort", query = "SELECT w FROM WorkTask AS w ORDER BY w.id DESC"),
    @NamedQuery(name = "getWorkTasksCount", query = "SELECT COUNT(w) FROM WorkTask AS w")
})
@Table(name = "Worktasks")
public class WorkTask {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "content", length = 255, nullable = false)
    private String content;

    @Column(name = "created_at", nullable = false)
    private Timestamp created_at;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updated_at;

    @Column(name = "deadline", nullable = true)
    private Date deadline;

    @Column(name = "deadline_time", nullable = true)
    private LocalTime deadline_time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public LocalTime getDeadline_time() {
        return deadline_time;
    }

    public void setDeadline_time(LocalTime deadline_time) {
        this.deadline_time = deadline_time;
    }
}

