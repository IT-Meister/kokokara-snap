package jp.co.itmeister.userservice.userservice.dto;

import java.time.ZonedDateTime;
import java.math.BigDecimal;

public class PostDto {
    private Integer userId;
    private String url;
    private String title;
    private Short prefecture;
    private String cityName;
    private String description;
    private String brand;
    private String cameraName;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private ZonedDateTime snapTime;
    private Integer angle;
    private String iso;
    private BigDecimal fValue;
    private String shutterSpeed;

    public PostDto(){};

    // コンストラクタ
    public PostDto ( Integer userId, String url, String title, Short prefecture, 
                         String cityName, String description, String brand, String cameraName, 
                         BigDecimal latitude, BigDecimal longitude, ZonedDateTime snapTime, 
                         Integer angle, String iso, BigDecimal fValue, String shutterSpeed) {
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

    public Integer getUserId() {
        return userId;
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public Short getPrefecture() {
        return prefecture;
    }

    public String getCityName() {
        return cityName;
    }

    public String getDescription() {
        return description;
    }

    public String getBrand() {
        return brand;
    }

    public String getCameraName() {
        return cameraName;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public ZonedDateTime getSnapTime() {
        return snapTime;
    }

    public Integer getAngle() {
        return angle;
    }

    public String getIso() {
        return iso;
    }

    public BigDecimal getFValue() {
        return fValue;
    }

    public String getShutterSpeed() {
        return shutterSpeed;
    }

}
