package jp.co.itmeister.userservice.userservice.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.math.BigDecimal;
import org.locationtech.jts.geom.Point;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import jakarta.persistence.*;
import jp.co.itmeister.userservice.userservice.postGis.PointSerializer;

import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Table(name = "posts")
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @Type(type = "org.hibernate.type.UUIDCharType")
    @Column(name = "uid" , nullable = false , unique = true)
    private UUID uid;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "title", nullable = false, length = 128)
    private String title;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "city_id")
    private CityEntity city;

    @Column(name = "description", length = 255)
    private String description;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "camera_id" , nullable = false)
    private CameraEntity camera;

    @JsonSerialize(using = PointSerializer.class)
    @Column(name = "latlng", nullable = false , columnDefinition =  "geography(Point,4326)")
    private Point latlng;  

    @Column(name = "snap_time")
    private ZonedDateTime snapTime;

    @Column(name = "angle")
    private double angle;

    @Column(name = "iso", length = 32)
    private String iso;

    @Column(name = "f_value", precision = 5, scale = 2)
    private BigDecimal fValue;

    @Column(name = "shutter_speed", length = 32)
    private String shutterSpeed;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private ZonedDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private ZonedDateTime updatedAt;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getUid() {
        return uid;
    }

    public void setUid(UUID uid) {
        this.uid = uid;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public CityEntity getCity() {
        return city;
    }

    public void setCity(CityEntity city) {
        this.city = city;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CameraEntity getCamera() {
        return camera;
    }

    public void setCamera(CameraEntity camera) {
        this.camera = camera;
    }

    public Point getLatlng() {
        return latlng;
    }

    public void setLatlng(Point latlng) {
        this.latlng = latlng;
    }

    public ZonedDateTime getSnapTime() {
        return snapTime;
    }

    public void setSnapTime(ZonedDateTime snapTime) {
        this.snapTime = snapTime;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public String getIso() {
        return iso;
    }

    public void setIso(String iso) {
        this.iso = iso;
    }

    public BigDecimal getFValue() {
        return fValue;
    }

    public void setFValue(BigDecimal fValue) {
        this.fValue = fValue;
    }

    public String getShutterSpeed() {
        return shutterSpeed;
    }

    public void setShutterSpeed(String shutterSpeed) {
        this.shutterSpeed = shutterSpeed;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public ZonedDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(ZonedDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}