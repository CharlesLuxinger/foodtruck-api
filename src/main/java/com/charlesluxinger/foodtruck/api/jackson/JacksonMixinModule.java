package com.charlesluxinger.foodtruck.api.jackson;

import com.charlesluxinger.foodtruck.api.domain.model.Restaurante;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.stereotype.Component;

@Component
public class JacksonMixinModule extends SimpleModule {

    public JacksonMixinModule(){
       setMixInAnnotation(Restaurante.class, RestauranteMixin.class);
    }

}
