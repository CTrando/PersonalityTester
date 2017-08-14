package com.trando.personalitytester.traits;

import java.util.HashMap;

/**
 * Created by Cameron on 3/5/2017.
 */
public class TraitHolder {
    public static HashMap<Trait, Integer> traitValues = new HashMap<Trait, Integer>();

    public static void incrementTrait(Trait trait, int value){
        if(traitValues.get(trait) == null){
            traitValues.put(trait, value);
        }
        else traitValues.put(trait, traitValues.get(trait) + value);
    }

    public static Trait getBestTrait(){
        Trait bestTrait = null;
        for(Trait trait: traitValues.keySet()){
            if(bestTrait == null || traitValues.get(bestTrait) < traitValues.get(trait)){
                bestTrait = trait;
            }
        }
        return bestTrait;
    }
}
