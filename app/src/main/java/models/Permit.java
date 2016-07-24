package models;

import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;

/**
 * Created by jackmann on 7/20/16.
 */

@JsonDeserialize(builder = AutoValue_Permit.Builder.class)
@AutoValue
public abstract class Permit implements Parcelable {

    @NonNull
    @JsonProperty("_permit_type")
    public abstract String permitType();

    @NonNull
    @JsonProperty("id")
    public abstract String id();

    @NonNull
    @JsonProperty("permit_")
    public abstract String permitCode();

    public static Builder builder() {
        return new AutoValue_Permit.Builder();
    }

    @AutoValue.Builder
    @JsonIgnoreProperties(ignoreUnknown = true)

    public interface Builder {

        @JsonProperty("_permit_type")
        Builder permitType(String permitType);

        @JsonProperty("id")
        Builder id(String id);

        @JsonProperty("permit_")
        Builder permitCode(String permitCode);

        Permit build();
    }
}
