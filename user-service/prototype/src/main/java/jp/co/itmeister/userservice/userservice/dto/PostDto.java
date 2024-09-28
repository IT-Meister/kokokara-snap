package jp.co.itmeister.userservice.userservice.dto;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.math.BigDecimal;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PostDto {
    private Long id;
    private Integer userId;
    private String url;
    private String title;
    private Short prefecture;
    private String cityName;
    private String description;
    private String brand;
    private String cameraName;
    private double latitude;
    private double longitude;
    private ZonedDateTime snapTime;
    private Integer angle;
    private String iso;

    @JsonProperty("f_value")
    private BigDecimal fValue;
    private String shutterSpeed;

    public PostDto(){};

    // コンストラクタ
    public PostDto ( Long id ,Integer userId, String url, String title, Short prefecture, 
                         String cityName, String description, String brand, String cameraName, 
                         double latitude, double longitude, ZonedDateTime snapTime, 
                         Integer angle, String iso, BigDecimal fValue, String shutterSpeed) {
        this.id = id;
        this.userId = userId;
        this.url = url;
        this.title = title;
        this.prefecture = prefecture;
        this.cityName = cityName;
        this.description = description;
        this.brand = brand;
        this.cameraName = cameraName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.snapTime = snapTime;
        this.angle = angle;
        this.iso = iso;
        this.fValue = fValue;
        this.shutterSpeed = shutterSpeed;
    }
    public Long getId () {
        return id;
    }

    public void setId (Long id ) {
        this.id = id;
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

    public Short getPrefecture() {
        return prefecture;
    }

    public void setPrefecture(Short prefecture) {
        this.prefecture = prefecture;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCameraName() {
        return cameraName;
    }

    public void setCameraName(String cameraName) {
        this.cameraName = cameraName;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public ZonedDateTime getSnapTime() {
        return snapTime;
    }

    public void setSnapTime(ZonedDateTime snapTime) {
        this.snapTime = snapTime;
    }

    public Integer getAngle() {
        return angle;
    }

    public void setAngle(Integer angle) {
        this.angle = angle;
    }

    public String getIso() {
        return iso;
    }

    public void setIso (String iso) {
        this.iso = iso;
    }

    public BigDecimal getFValue() {
        return fValue;
    }
    @JsonProperty("f_value")
    public void setFValue(BigDecimal fValue) {
        this.fValue = fValue;
    }

    public String getShutterSpeed() {
        return shutterSpeed;
    }

    public void setShutterSpeed(String shutterSpeed) {
        this.shutterSpeed = shutterSpeed;
    }

}
