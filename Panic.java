package kr.connotation.fiermemory;

import com.orm.SugarRecord;
import com.orm.dsl.Table;

/**
 * Created by Connotation on 2016-03-26.
 */
@Table
public class Panic extends SugarRecord {
    String title = "";
    String place = "";
    String mgroup = "";
    String memo = "";
    double lng = 0f;
    double lat = 0f;

    public Panic() {

    }

    public Panic(String title, String place, String mgroup, String memo, double lng, double lat) {
        this.title = title;
        this.place = place;
        this.mgroup = mgroup;
        this.memo = memo;
        this.lng = lng;
        this.lat = lat;
    }

    public String getTitle() {
        return title;
    }

    public String getPlace() {
        return place;
    }

    public String getmGroup() {
        return mgroup;
    }

    public String getMemo() {
        return memo;
    }

    public double getLng() {
        return lng;
    }

    public double getLat() {
        return lat;
    }
}
