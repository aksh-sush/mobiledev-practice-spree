// Weatherdat.java

// YApi QuickType插件生成，具体参考文档:https://plugins.jetbrains.com/plugin/18847-yapi-quicktype/documentation

package com.example.weather_app_trial1;
import java.time.OffsetDateTime;
import java.util.List;
import java.time.OffsetDateTime;

public class Weatherdat {
    private long cnt;
    private String cod;
    private long message;
    private Main main;
    private List<ListElement> list;

    public long getCnt() { return cnt; }
    public void setCnt(long value) { this.cnt = value; }

    public String getCod() { return cod; }
    public void setCod(String value) { this.cod = value; }

    public long getMessage() { return message; }
    public void setMessage(long value) { this.message = value; }

    public List<ListElement> getList() { return list; }
    public void setList(List<ListElement> value) { this.list = value; }

    public Main getMain() {
        return main;
    }
}

// ListElement.java

// YApi QuickType插件生成，具体参考文档:https://plugins.jetbrains.com/plugin/18847-yapi-quicktype/documentation


class ListElement {
    private long dt;
    private long pop;
    private long visibility;
    private OffsetDateTime dtTxt;
    private List<Weather> weather;
    private Main main;
    private Clouds clouds;
    private Sys sys;
    private Wind wind;

    public long getDt() { return dt; }
    public void setDt(long value) { this.dt = value; }

    public long getPop() { return pop; }
    public void setPop(long value) { this.pop = value; }

    public long getVisibility() { return visibility; }
    public void setVisibility(long value) { this.visibility = value; }

    public OffsetDateTime getDtTxt() { return dtTxt; }
    public void setDtTxt(OffsetDateTime value) { this.dtTxt = value; }

    public List<Weather> getWeather() { return weather; }
    public void setWeather(List<Weather> value) { this.weather = value; }

    public Main getMain() { return main; }
    public void setMain(Main value) { this.main = value; }

    public Clouds getClouds() { return clouds; }
    public void setClouds(Clouds value) { this.clouds = value; }

    public Sys getSys() { return sys; }
    public void setSys(Sys value) { this.sys = value; }

    public Wind getWind() { return wind; }
    public void setWind(Wind value) { this.wind = value; }
}

// Clouds.java

// YApi QuickType插件生成，具体参考文档:https://plugins.jetbrains.com/plugin/18847-yapi-quicktype/documentation

class Clouds {
    private long all;

    public long getAll() { return all; }
    public void setAll(long value) { this.all = value; }
}

// Main.java

// YApi QuickType插件生成，具体参考文档:https://plugins.jetbrains.com/plugin/18847-yapi-quicktype/documentation

class Main {
    private double temp;
    private double tempMin;
    private long grndLevel;
    private double tempKf;
    private long humidity;
    private long pressure;
    private long seaLevel;
    private double feelsLike;
    private double tempMax;

    public double getTemp() { return temp; }
    public void setTemp(double value) { this.temp = value; }

    public double getTempMin() { return tempMin; }
    public void setTempMin(double value) { this.tempMin = value; }

    public long getGrndLevel() { return grndLevel; }
    public void setGrndLevel(long value) { this.grndLevel = value; }

    public double getTempKf() { return tempKf; }
    public void setTempKf(double value) { this.tempKf = value; }

    public long getHumidity() { return humidity; }
    public void setHumidity(long value) { this.humidity = value; }

    public long getPressure() { return pressure; }
    public void setPressure(long value) { this.pressure = value; }

    public long getSeaLevel() { return seaLevel; }
    public void setSeaLevel(long value) { this.seaLevel = value; }

    public double getFeelsLike() { return feelsLike; }
    public void setFeelsLike(double value) { this.feelsLike = value; }

    public double getTempMax() { return tempMax; }
    public void setTempMax(double value) { this.tempMax = value; }
}

// Sys.java

// YApi QuickType插件生成，具体参考文档:https://plugins.jetbrains.com/plugin/18847-yapi-quicktype/documentation

class Sys {
    private String pod;

    public String getPod() { return pod; }
    public void setPod(String value) { this.pod = value; }
}

// Weather.java

// YApi QuickType插件生成，具体参考文档:https://plugins.jetbrains.com/plugin/18847-yapi-quicktype/documentation

class Weather {
    private String icon;
    private String description;
    private String main;
    private long id;

    public String getIcon() { return icon; }
    public void setIcon(String value) { this.icon = value; }

    public String getDescription() { return description; }
    public void setDescription(String value) { this.description = value; }

    public String getMain() { return main; }
    public void setMain(String value) { this.main = value; }

    public long getid() { return id; }
    public void setid(long value) { this.id = value; }
}

// Wind.java

// YApi QuickType插件生成，具体参考文档:https://plugins.jetbrains.com/plugin/18847-yapi-quicktype/documentation

class Wind {
    private long deg;
    private double speed;
    private double gust;

    public long getDeg() { return deg; }
    public void setDeg(long value) { this.deg = value; }

    public double getSpeed() { return speed; }
    public void setSpeed(double value) { this.speed = value; }

    public double getGust() { return gust; }
    public void setGust(double value) { this.gust = value; }
}
