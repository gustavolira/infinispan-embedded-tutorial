package org.infinispan.tutorial.embedded;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Set;

import org.infinispan.commons.marshall.AdvancedExternalizer;
import org.infinispan.commons.util.Util;

public class LocationWeather {

   final float temperature;

   final String conditions;

   final String country;

   public LocationWeather(float temperature, String conditions, String country) {
      this.temperature = temperature;
      this.conditions = conditions;
      this.country = country;
   }

   @Override
   public String toString() {
      return String.format("Temperature: %.1f° C, Conditions: %s", temperature, conditions);
   }


   public static class LocationWeatherAdvancedExternalizer implements AdvancedExternalizer<LocationWeather> {

      @Override
      public void writeObject(ObjectOutput output, LocationWeather locationWeather)
            throws IOException {
         output.writeObject(locationWeather.temperature);
         output.writeObject(locationWeather.conditions);
         output.writeObject(locationWeather.country);
      }

      @Override
      public LocationWeather readObject(ObjectInput input)
            throws IOException, ClassNotFoundException {
         return new LocationWeather(input.readFloat(), (String) input.readObject(), (String) input.readObject());
      }

      @Override
      public Set<Class<? extends LocationWeather>> getTypeClasses() {
         return Util.asSet(LocationWeather.class);
      }

      @Override
      public Integer getId() {
         return 2345;
      }
   }

}

