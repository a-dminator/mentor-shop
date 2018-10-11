package io.adev.mentor_shop.entities.java;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "categories")
public class JavaCategory {

    @PrimaryKey
    private final int id;
    private final String title;
    private final String url;
    private final double rating;
    private final int productsCount;

    public JavaCategory(int id, String title, String url, double rating, int productsCount) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.rating = rating;
        this.productsCount = productsCount;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public double getRating() {
        return rating;
    }

    public int getProductsCount() {
        return productsCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JavaCategory that = (JavaCategory) o;

        if (id != that.id) return false;
        if (Double.compare(that.rating, rating) != 0) return false;
        if (productsCount != that.productsCount) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        return url != null ? url.equals(that.url) : that.url == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        temp = Double.doubleToLongBits(rating);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + productsCount;
        return result;
    }
}
