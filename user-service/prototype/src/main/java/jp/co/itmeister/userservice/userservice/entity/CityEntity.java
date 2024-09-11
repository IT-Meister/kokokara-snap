package jp.co.itmeister.userservice.userservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cities")
public class CityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "prefecture_id", nullable = false)
    private Integer prefectureId;

    @Column(name = "name", nullable = false)
    private String name;

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPrefectureId() {
        return prefectureId;
    }

    public void setPrefectureId(Integer prefectureId) {
        this.prefectureId = prefectureId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}